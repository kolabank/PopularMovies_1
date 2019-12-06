package android.example.com.popularmovies_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv_test;
    ImageView imgTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test = findViewById(R.id.tvTest);
     //   imgTest = findViewById(R.id.imgTest);
        //This is the URL to be passed to the Network class for parsing



        String popularURLString = "https://api.themoviedb.org/3/movie/popular?api_key=ac151895b9e322dd2d1a1cedef5bf9ab";

        URL moviesURL = NetworkUtility.makeUrl(popularURLString);

        new gettingResponse().execute(popularURLString);


    }

    public class gettingResponse extends AsyncTask<String, Void, String[]> {


        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String URLString = params[0];
            URL moviesURL = NetworkUtility.makeUrl(URLString);

            try {
                String[] JSONResponse = {NetworkUtility.urlResponse(moviesURL)};

                return JSONResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] JSONData) {

            String JSONString =JSONData[0];
            JSONUtil jsonUtil = new JSONUtil();
            MovieDetails[] mainMovieDetails = jsonUtil.populateFromJson(JSONString);
          for (int i=0; i<1;i++) {
              tv_test.setText(mainMovieDetails[i].getPlotSynopsis());
          }

            //Picasso.get().load(movieDetails.getThumbnail()).into(imgTest);



        }
    }

}

