
package starwar.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import starwar.controllers.MainController;
import starwar.models.Movie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestController extends ApplicationTests
{

  @Autowired
  private MainController mainController;

  @Mock
  private StarWarMovieService starWarMovieService;

  @Mock
  private HttpServletRequest mockRequest;

  private List<Movie> expectListSortedByEpisode;
  private String expectReturnJson = "[{\"imdbId\":\"tt2488493\",\"movieTitle\":\"ccc\",\"actors\":\"ccc\",\"episode\":\"1\",\"release\":\"4\","
  + "\"image\":\"image3\"},"
  + "{\"imdbId\":\"tt2488495\",\"movieTitle\":\"eee\",\"actors\":\"eee\",\"episode\":\"2\",\"release\":\"5\",\"machete\":\"3\","
  + "\"image\":\"image5\"},"
  + "{\"imdbId\":\"tt2488494\",\"movieTitle\":\"ddd\",\"actors\":\"ddd\",\"episode\":\"3\",\"release\":\"6\",\"machete\":\"4\",\"image\":\"image4\"},"
  + "{\"imdbId\":\"tt2488491\",\"movieTitle\":\"aaa\",\"actors\":\"aaa\",\"episode\":\"5\",\"release\":\"2\",\"machete\":\"2\","
  + "\"image\":\"image1\"},"
  + "{\"imdbId\":\"tt2488492\",\"movieTitle\":\"bbb\",\"actors\":\"bbb\",\"episode\":\"7\",\"release\":\"7\",\"machete\":\"6\","
  + "\"image\":\"image2\"}]";


  @Before
  public void setup()
  {
    mainController.setStarWarMovieService(starWarMovieService);
    Movie movie1 = new Movie("tt2488491", "aaa", "aaa", "5", "2", "2","image1" );
    Movie movie2 = new Movie("tt2488492", "bbb", "bbb", "7", "7", "6","image2" );
    Movie movie3 = new Movie("tt2488493", "ccc", "ccc", "1", "4", null, "image3" );
    Movie movie4 = new Movie("tt2488494", "ddd", "ddd", "3", "6", "4", "image4" );
    Movie movie5 = new Movie("tt2488495", "eee", "eee", "2", "5", "3", "image5");
    expectListSortedByEpisode = Arrays.asList(movie3, movie5, movie4, movie1, movie2);
  }

  @Test
  public void testRetrieveMovieListByParam() throws Exception{
    when(starWarMovieService.retrieveStarWarMovieListBySortParam(anyString(), anyString(), anyString())).thenReturn(expectListSortedByEpisode);
    String actualResult = mainController.retrieveMovieListByParam(mockRequest, "episode");
    Assert.assertEquals(expectReturnJson, actualResult);
  }

  @Test
  public void testStoreFavouriteMovie() throws Exception{
    String favouriteMovie = "{\"imdbId\":\"tt2488493\"}";

    when(mockRequest.getRemoteAddr()).thenReturn("127.0.0.1");
    mainController.storeFavouriteMovie(mockRequest, favouriteMovie);
    verify(starWarMovieService, times(1)).storeFavouriteMovieRequest(anyString(), anyString(), anyObject());

  }
}