public class Movie {

    private int runTime;
    private double userRating;

    private String title;
    private String cast;
    private String director;
    private String overview;

    public Movie (int runTime, double userRating, String title, String cast, String director, String overview) {
        this.runTime = runTime;
        this.userRating = userRating;
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
    }

    public int getRunTime () {
        return runTime;
    }

    public double getUserRating () {
        return userRating;
    }

    public String getTitle () {
        return title;
    }

    public String getCast () {
        return cast;
    }

    public String getDirector () {
        return director;
    }

    public String getOverview () {
        return overview;
    }

}
