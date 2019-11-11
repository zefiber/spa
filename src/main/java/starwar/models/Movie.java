package starwar.models;

public class Movie
{
  private String imdbId;
  private String movieTitle;
  private String actors;
  private String episode;
  private String release;
  private String machete;

  public Movie(){}

  public Movie(String imdbId, String movieTitle, String actors, String episode, String release, String machete) {
    this.imdbId = imdbId;
    this.movieTitle = movieTitle;
    this.actors = actors;
    this.episode = episode;
    this.release = release;
    this.machete = machete;
  }

  @Override public String toString() {
    return "Movie{" + "imdbId='" + imdbId + '\'' + ", movieTitle='" + movieTitle + '\'' + ", actors='" + actors + '\'' + ", episode='" + episode
            + '\'' + ", release='" + release + '\'' + ", machete='" + machete + '\'' + '}';
  }


  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }

  public String getEpisode() {
    return episode;
  }

  public void setEpisode(String episode) {
    this.episode = episode;
  }

  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public String getMachete() {
    return machete;
  }

  public void setMachete(String machete) {
    this.machete = machete;
  }


}