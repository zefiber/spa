package starwar.models;

public class UserRequest
{
  public Integer getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(Integer ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getReqQuery() {
    return reqQuery;
  }

  public void setReqQuery(String reqQuery) {
    this.reqQuery = reqQuery;
  }

  public String getReqTime() {
    return reqTime;
  }

  public void setReqTime(String reqTime) {
    this.reqTime = reqTime;
  }

  private Integer id;
  private Integer ipAddress;
  private String reqQuery;

  @Override public String toString() {
    return "UserRequest{" + "id=" + id + ", ipAddress=" + ipAddress + ", reqQuery='" + reqQuery + '\'' + ", reqTime='" + reqTime + '\'' + '}';
  }

  public UserRequest(){
  }

  public UserRequest(Integer id, Integer ipAddress, String reqQuery, String reqTime) {
    this.id = id;
    this.ipAddress = ipAddress;
    this.reqQuery = reqQuery;
    this.reqTime = reqTime;

  }

  private String reqTime;


}