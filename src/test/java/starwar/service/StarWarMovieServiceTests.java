package starwar.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class StarWarMovieServiceTests extends ApplicationTests
{
  @Autowired
  private StarWarMovieService starWarMovieService;

  @Test
  public void testRetrieveIMBDStarWarList(){
    starWarMovieService.retrieveIMDBStarWarList();
  }

  @Test
  public void testRetrieveIMBDDetailByID(){
    starWarMovieService.retrieveIMDBDetailByID("tt0086190");
  }



}