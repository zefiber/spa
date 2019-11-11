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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import starwar.models.UserRequest;


@Repository
public class UserRequestRepository
{
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional(readOnly = true)
  public List<UserRequest> findAll()
  {
    return jdbcTemplate.query("select * from user_request", new UserRequestRowMapper());
  }


  public UserRequest saveUserRequest(UserRequest UserRequest){
    final String sql = "insert into user_request(ip_address, req_query, req_time) values(?,?,?)";

    KeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator()
    {
      public PreparedStatement createPreparedStatement(Connection connection)
          throws SQLException
      {
        PreparedStatement ps = connection.prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, UserRequest.getIpAddress());
        ps.setString(2, UserRequest.getReqQuery());
        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        return ps;
      }
    }, holder);

//    int newUserId = holder.getKey().intValue();
//    user.setId(newUserId);
    return UserRequest;

  }


}

class UserRequestRowMapper implements RowMapper<UserRequest>
{
  @Override
  public UserRequest mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    UserRequest userRequest = new UserRequest(rs.getInt("id"), rs.getInt("ip_addresss"), rs.getString("req_query"), rs.getString("req_time"));


    return userRequest;
  }
}