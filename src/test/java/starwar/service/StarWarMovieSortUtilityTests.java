package starwar.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import starwar.models.Movie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class StarWarMovieSortUtilityTests extends ApplicationTests
{
  @Autowired
  private StarWarMovieSortUtility starWarMovieSortUtility;

  private List<Movie> movieList;
  private List<Movie> expectListSortedByEpisode;
  private List<Movie> expectListSortedByRelease;
  private List<Movie> expectListSortedByMachete;

  @Before
  public void setup()
  {
    movieList = new ArrayList<>();
    Movie movie1 = new Movie("tt2488491", "aaa", "aaa", "5", "2", "2" );
    Movie movie2 = new Movie("tt2488492", "bbb", "bbb", "7", "7", "6" );
    Movie movie3 = new Movie("tt2488493", "ccc", "ccc", "1", "4", null );
    Movie movie4 = new Movie("tt2488494", "ddd", "ddd", "3", "6", "4" );
    Movie movie5 = new Movie("tt2488495", "eee", "eee", "2", "5", "3" );

    movieList.add(movie1);
    movieList.add(movie2);
    movieList.add(movie3);
    movieList.add(movie4);
    movieList.add(movie5);

    expectListSortedByEpisode = Arrays.asList(movie3, movie5, movie4, movie1, movie2);
    expectListSortedByRelease = Arrays.asList(movie1, movie3, movie5, movie4, movie2);
    expectListSortedByMachete = Arrays.asList(movie1, movie5, movie4, movie2, movie3);

  }


  @Test
  public void testSortMovieByEpisode(){

    List<Movie> sortedByEpisode = starWarMovieSortUtility.sortMovieByParam(movieList, "episode");
    List<Movie> sortedByRelease = starWarMovieSortUtility.sortMovieByParam(movieList, "release");
    List<Movie> sortedByMachete = starWarMovieSortUtility.sortMovieByParam(movieList, "Machete");

    Assert.assertEquals(expectListSortedByEpisode, sortedByEpisode);
    Assert.assertEquals(expectListSortedByRelease, sortedByRelease);
    Assert.assertEquals(expectListSortedByMachete, sortedByMachete);
  }

//  @Test
//  public void testRetrieveIMBDDetailByID(){
//    starWarMovieService.retrieveIMDBDetailByID("tt0086190");
//  }



}