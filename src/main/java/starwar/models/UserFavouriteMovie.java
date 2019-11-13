package starwar.models;

public class UserFavouriteMovie
{
  public UserFavouriteMovie() {
  }

  public UserFavouriteMovie(Integer id, Integer ipAddress, String imdbId) {
    this.id = id;
    this.ipAddress = ipAddress;
    this.imdbId = imdbId;
  }

  private Integer id;
  private Integer ipAddress;
  private String imdbId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(Integer ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

    @Override public String toString() {
    return "FavouriteMovie{" + "id=" + id + ", ipAddress=" + ipAddress + ", imdbId='" + imdbId + '\'' + '}';
  }

}