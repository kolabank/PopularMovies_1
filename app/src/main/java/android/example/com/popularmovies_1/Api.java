package android.example.com.popularmovies_1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {



            String BASEURL = "http://api.themoviedb.org/3/movie/419704";

            @GET ("/videos?api_key=ac151895b9e322dd2d1a1cedef5bf9ab")
             Call<ArrayList<ResultsClass>> getResults();



}
