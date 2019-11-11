package starwar.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import starwar.dao.StarWarMovieRepository;
import starwar.dao.UserRequestRepository;
import starwar.models.Movie;
import starwar.models.UserFavouriteMovie;
import starwar.models.UserRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Service("StarWarMovieService")
public class StarWarMovieService
{
  @Autowired
  private UserRequestRepository userRequestRepository;

  @Autowired
  private StarWarMovieRepository starWarMovieRepository;

  @Value("${zeSignature}")
  private String zeSignature;


  public String getSignature()
  {
    return "Proudly handcrafted by " + zeSignature + " ! :)";
  }

  public List<Movie> retrieveStarWarMovieListBySortParam(String ip, String query, String sortParam){
  	UserRequest userRequest = new UserRequest();
    userRequest.setIpAddress(ipStringToInt(ip));
    userRequest.setReqQuery(query);
    storeUserRequest(userRequest);


  	List<Movie> retList = new ArrayList<>();
	retList = (ArrayList<Movie>) retrieveIMDBStarWarList();

	StarWarMovieSortUtility starWarMovieSortUtility = new StarWarMovieSortUtility();
	retList = starWarMovieSortUtility.sortMovieByParam(retList, sortParam);
	retList.forEach(movie -> {
		movie.setMovieTitle(retrieveIMDBDetailByID(movie.getImdbId()).getMovieTitle());
		movie.setActors(retrieveIMDBDetailByID(movie.getImdbId()).getActors());
	});

	return retList;

  }

  public UserFavouriteMovie storeFavouriteMovieRequest(String ip, String query, UserFavouriteMovie userFavouriteMovie) {

	UserRequest userRequest = new UserRequest();
    userRequest.setIpAddress(ipStringToInt(ip));
    userRequest.setReqQuery(query);
    storeUserRequest(userRequest);

    userFavouriteMovie.setIpAddress(!"".equalsIgnoreCase(ip)? ipStringToInt(ip): 0);
	return starWarMovieRepository.saveFavouriteMovie(userFavouriteMovie);
  }

  protected List<Movie> retrieveIMDBStarWarList(){
    String retMsg = "";
    List<Movie> retList = new ArrayList<>();
    try {

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client
		   .target("https://mysterious-peak-27876.herokuapp.com/");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		retMsg = response.readEntity(String.class);


		JsonArray starWarMovieList = new JsonParser().parse(retMsg).getAsJsonArray();
		starWarMovieList.forEach(starWarMovie -> {
			JsonObject jsonObject = starWarMovie.getAsJsonObject();
			Movie movie = new Movie();
			movie.setImdbId(jsonObject.get("imdbId")!=null?jsonObject.get("imdbId").getAsString():null);
			movie.setRelease(jsonObject.get("release")!=null?jsonObject.get("release").getAsString():null);
			movie.setEpisode(jsonObject.get("release")!=null?jsonObject.get("episoe").getAsString():null);
			movie.setMachete(jsonObject.get("release")!=null?jsonObject.get("machete").getAsString():null);
			retList.add(movie);
		} );

		System.out.println("Output from Server .... \n");
		System.out.println(retMsg);

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	  return retList;
  }

  protected Movie retrieveIMDBDetailByID(String imdbID){
    String retMsg = "";
    Movie movie = new Movie();
    try {

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client
		   .target("http://www.omdbapi.com/?" + "i=" + imdbID + "&apikey=57ec2f6d");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		retMsg = response.readEntity(String.class);

		JsonObject starWarMovie = new JsonParser().parse(retMsg).getAsJsonObject();

		movie.setMovieTitle(starWarMovie.get("Title").getAsString());
		movie.setActors(starWarMovie.get("Actors").getAsString());


		System.out.println("Output from Server .... \n");
		System.out.println(retMsg);

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	  return movie;
  }

  public void storeUserRequest(UserRequest userRequest){
	userRequestRepository.saveUserRequest(userRequest);
  }

  public int ipStringToInt(String ip) {
    //Converts a String that represents an IP to an int.
    InetAddress i= null;
    try {
      i = InetAddress.getByName(ip);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ByteBuffer.wrap(i.getAddress()).getInt();
  }

}
