package android.example.com.popularmovies_1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtil {

    //MovieDetails array declared to hold required details for all movies

   // ArrayList<MovieDetails> movieDetails = new ArrayList<MovieDetails>();

    MovieDetails[] movieDetailsArray;

    //This method reads the JSON into respective variables and assigns values into the MovieDetails array elements

   public MovieDetails populateFromJson(String jsonObject){

    MovieDetails movieDetails = new MovieDetails();
       try {
           JSONObject rootJSON = new JSONObject(jsonObject);
           JSONArray resultsArray = rootJSON.getJSONArray("results");
           movieDetailsArray = new MovieDetails[resultsArray.length()];

           for (int i = 0; i<resultsArray.length(); i++){
               JSONObject furtherDetails = resultsArray.getJSONObject(i);

                String plotSynopsis = furtherDetails.optString("overview");
         //       movieDetails[0].setPlotSynopsis(plotSynopsis);
               movieDetails.setPlotSynopsis("this");
            //   movieDetails[0].plotSynopsis = "this";


               String originalTitle = furtherDetails.optString("original_title");
              // movieDetails[i].setoriginalTitle(originalTitle);


               String userRating = furtherDetails.optString("vote_average");
               //movieDetails[i].setUserRating(userRating);

               String releaseDate = furtherDetails.optString("release_date");
               //movieDetails[i].setReleaseDate(releaseDate);

               String posterPathJSON = furtherDetails.optString("poster_path");
               //Append remaining string of thumbnail path
                 String fullPosterPath = "https://image.tmdb.org/t/p/w185/"+posterPathJSON;
           //    movieDetails[i].setThumbnail(fullPosterPath);
           }

       }

       catch (Exception e){
           e.printStackTrace();
       }

       return movieDetails;
   }

}
