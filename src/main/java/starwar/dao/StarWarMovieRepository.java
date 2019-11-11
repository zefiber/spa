package starwar.dao;

import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import starwar.models.UserFavouriteMovie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StarWarMovieRepository
{
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional(readOnly = true)
  public List<UserFavouriteMovie> findAll()
  {
    return jdbcTemplate.query("select * from favourite_movie", new FavouriteMovieRowMapper());
  }

  @Transactional(readOnly = true)
  public List<UserFavouriteMovie> findFavouriteMovieByIP(int ipAddress)
  {
    List<UserFavouriteMovie> userFavouriteMovieList = new ArrayList<>();
    List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from favourite_movie where ip_address=?", ipAddress);

    for(Map row: rows){
      UserFavouriteMovie obj = new UserFavouriteMovie((Integer)row.get("id"), (Integer)row.get("ip_addresss"), (String)row.get("movie_imdb_id"));
      userFavouriteMovieList.add(obj);
    }
    return userFavouriteMovieList;
  }

  public UserFavouriteMovie saveFavouriteMovie(UserFavouriteMovie userFavouriteMovie){
    final String sql = "insert into favourite_movie(ip_address, movie_imdb_id) values(?,?)";

    KeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator()
    {
      public PreparedStatement createPreparedStatement(Connection connection)
          throws SQLException
      {
        PreparedStatement ps = connection.prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userFavouriteMovie.getIpAddress());
        ps.setString(2, userFavouriteMovie.getImdbId());
        return ps;
      }
    }, holder);

    return userFavouriteMovie;

  }


}

class FavouriteMovieRowMapper implements RowMapper<UserFavouriteMovie>
{
  @Override
  public UserFavouriteMovie mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    UserFavouriteMovie userFavouriteMovie = new UserFavouriteMovie(rs.getInt("id"), rs.getInt("ip_addresss"), rs.getString("movie_imdb_id"));


    return userFavouriteMovie;
  }
}