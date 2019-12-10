package android.example.com.popularmovies_1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtil {

    //MovieDetails array declared to hold required details for all movies

    String [] originalTitleArray, plotSynopsisArray,
            releaseDateArray, userRatingArray, thumbNailArray;


    //This method reads the JSON into respective variables and assigns values into the MovieDetails array elements

   public void populateFromJson(String jsonObject){

    MovieDetails movieDetails = new MovieDetails();
       try {
           JSONObject rootJSON = new JSONObject(jsonObject);
           JSONArray resultsArray = rootJSON.getJSONArray("results");


           originalTitleArray = new String[resultsArray.length()];
           plotSynopsisArray = new String[resultsArray.length()];
           releaseDateArray = new String[resultsArray.length()];
           userRatingArray = new String[resultsArray.length()];
           thumbNailArray = new String[resultsArray.length()];


           for (int i = 0; i<resultsArray.length(); i++){
               JSONObject furtherDetails = resultsArray.getJSONObject(i);


               String originalTitle = furtherDetails.optString("original_title");
               movieDetails.setoriginalTitle(originalTitle);
               originalTitleArray[i]= movieDetails.getoriginalTitle();

               String plotSynopsis = furtherDetails.optString("overview");
               movieDetails.setPlotSynopsis(plotSynopsis);
               plotSynopsisArray[i]= movieDetails.getPlotSynopsis();


               String userRating = furtherDetails.optString("vote_average");
               movieDetails.setUserRating(userRating);
               userRatingArray[i] = movieDetails.getUserRating();


               String releaseDate = furtherDetails.optString("release_date");
               movieDetails.setReleaseDate(releaseDate);
               releaseDateArray[i] = movieDetails.getReleaseDate();


               String posterPathJSON = furtherDetails.optString("poster_path");
               //Append remaining string of thumbnail path
               String fullPosterPath = "https://image.tmdb.org/t/p/w185/"+posterPathJSON;
               movieDetails.setThumbnail(fullPosterPath);
               thumbNailArray[i] = movieDetails.getThumbnail();
           }

       }

       catch (Exception e){
           e.printStackTrace();
       }

      // return movieDetails;
   }

}