package android.example.com.popularmovies_1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;


// The MainActivity implements the ClickViewInterface to allow the recylcer view images to respond to clicks

public class MainActivity extends AppCompatActivity implements ClickViewInterface{

    private RecyclerView thumbnailList; //RecyclerView is named as thumbnailList

    //To hold the string value of the URL for the movie API using popularity and top rating
    private String popularURLString, topRatedURLString;

    private TextView txtNoInternet;

    //These arrays hold the string values of the description of the movies
     public static String [] thumbArray, ratingArray, synopsisArray, dateArrray, titleArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbnailList = findViewById(R.id.rv_recyclerView);
        txtNoInternet = findViewById(R.id.txtNoInternet);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); //GridLayoutManager takes 2 parameters
        thumbnailList.setLayoutManager(gridLayoutManager);
        thumbnailList.setHasFixedSize(false);


        //Assign string value to strings
        popularURLString = "https://api.themoviedb.org/3/movie/popular?api_key=ac151895b9e322dd2d1a1cedef5bf9ab";
        topRatedURLString = "https://api.themoviedb.org/3/movie/top_rated?api_key=ac151895b9e322dd2d1a1cedef5bf9ab";

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        //Check for internet connectivity by calling isOnline method
        if (isOnline()==false){
            txtNoInternet.setVisibility(View.VISIBLE);
        }

        else{
            txtNoInternet.setVisibility(View.INVISIBLE);
        //The default response (by popularity) when app is run
        new gettingResponse().execute(popularURLString);
        }
    }


    //To handle selection of sorting either by popularity or top rating
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Check for internet connection to prevent program from crashing when options are selected when there is internet connectivity
        if (isOnline() == false) {
            txtNoInternet.setVisibility(View.VISIBLE);

        } else {
            if (id == R.id.action_popular) {
                new gettingResponse().execute(popularURLString);
                return true;
            } else if (id == R.id.action_rating) {
                new gettingResponse().execute(topRatedURLString);
                return true;
            }
                }
        return super.onOptionsItemSelected(item);
    }

// This method checks for internet connectivity

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }


    //Perform networking activities in background thread

    private class gettingResponse extends AsyncTask<String, Void, String[]> {

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
            ThumbnailAdapter tAdapter;
            assignMethod(JSONData[0]); //Method defined below
            tAdapter = new ThumbnailAdapter(thumbArray);
            thumbnailList.setAdapter(tAdapter);
        }
    }

   //This method will takes the JSON string as argument and sets data into the arraye earlier declared

    private void assignMethod (String JSONParameter){

        JSONUtil jsonUtil = new JSONUtil();
        jsonUtil.populateFromJson(JSONParameter);
        thumbArray = new String[jsonUtil.thumbNailArray.length];
        ratingArray=new String[jsonUtil.userRatingArray.length];
        synopsisArray = new String[jsonUtil.plotSynopsisArray.length];
        dateArrray =new String[jsonUtil.releaseDateArray.length];
        titleArray = new String[jsonUtil.originalTitleArray.length];

        for (int i=0;i<jsonUtil.thumbNailArray.length;i++){
            thumbArray[i] = jsonUtil.thumbNailArray[i];
            ratingArray[i]=jsonUtil.userRatingArray[i];
            synopsisArray[i] = jsonUtil.plotSynopsisArray[i];
            dateArrray[i] = jsonUtil.releaseDateArray[i];
            titleArray[i]=jsonUtil.originalTitleArray[i];

        }

    }

    //To handle click of movie posters

    @Override
    public void userItemClick(int pos) {

        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("ItemPosition", pos);
        startActivity(intent);

    }




}

