package starwar.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import starwar.models.Movie;
import starwar.models.UserFavouriteMovie;
import starwar.models.UserRequest;
import starwar.service.StarWarMovieService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class MainController
{
  @Autowired
  private StarWarMovieService starWarMovieService;

  @RequestMapping(value = "/retrieveMovieList", method = RequestMethod.GET)
  public String retrieveMovieListByParam(@Context HttpServletRequest requestContext,
          @RequestParam(value = "sortParam", defaultValue = "episode") String sortParam) {
    String ip = requestContext.getRemoteAddr();
    String requestQuery = requestContext.getRequestURI() + "&sortParam=" + sortParam;

    List<Movie> movieList = starWarMovieService.retrieveStarWarMovieListBySortParam(ip, requestQuery, sortParam);
    Gson gson = new GsonBuilder().create();

    String json = gson.toJson(movieList);
    return json;
  }

  @RequestMapping (value= "/storeFavouriteMovie", method = RequestMethod.POST)
  public UserFavouriteMovie storeFavouriteMovie(@Context HttpServletRequest requestContext, String json) {
    String ip = requestContext.getRemoteAddr();
    String requestQuery = requestContext.getRequestURI() + "&json=" + json;

    Gson gson = new Gson();
    UserFavouriteMovie userFavouriteMovie = gson.fromJson(json, UserFavouriteMovie.class);
    return starWarMovieService.storeFavouriteMovieRequest(ip, requestQuery, userFavouriteMovie);
  }

  public void setStarWarMovieService(StarWarMovieService starWarMovieService){
    this.starWarMovieService = starWarMovieService;
  }

  private String ipIntToString(int intRepresentation) {
    InetAddress i= null;
    try {
      i = InetAddress.getByName(String.valueOf(intRepresentation));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return i.getHostAddress();
  }

}
