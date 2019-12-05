package android.example.com.popularmovies_1;

public class MovieDetails {

    private String originalTitle;
    private String plotSynopsis;
    private int userRating;
    private String releaseDate;


    //Setters for movie details variables
    
    public void setoriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    


    //Getters for movie details variables
    
    public String getoriginalTitle() {
        return originalTitle;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public int getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
