package android.example.com.popularmovies_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test = findViewById(R.id.tv_test);
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
        protected void onPostExecute(String[] JSONArray) {

            tv_test.setText(JSONArray[0]);
            //  Log.i("reply", JSONarray.toString());

        }
    }
}

