package android.example.com.popularmovies_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;
import java.net.URL;
import java.util.ArrayList;


// The MainActivity implements the ClickViewInterface to allow the recylcer view images to respond to clicks

public class MainActivity extends AppCompatActivity implements ClickViewInterface{

    private ThumbnailAdapter tAdapter;
    private RecyclerView thumbnailList; //RecyclerView is named as thumbnailList

    //To hold the string value of the URL for the movie API using popularity and top rating
    private String popularURLString, topRatedURLString;

    //These arrays hold the string values of the description of the movies
     public static String [] thumbArray, ratingArray, synopsisArray, dateArrray, titleArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbnailList = findViewById(R.id.rv_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); //GridLayoutManager takes 2 parameters
        thumbnailList.setLayoutManager(gridLayoutManager);
        thumbnailList.setHasFixedSize(false);


        //Assign string value to strings
        popularURLString = "https://api.themoviedb.org/3/movie/popular?api_key=ac151895b9e322dd2d1a1cedef5bf9ab";
        topRatedURLString = "https://api.themoviedb.org/3/movie/top_rated?api_key=ac151895b9e322dd2d1a1cedef5bf9ab";

        //The default response (by popularity) when app is run
        new gettingResponse().execute(popularURLString);
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
        if(id==R.id.action_popular){
            new gettingResponse().execute(popularURLString);
            return  true;
        }

        else if (id==R.id.action_rating){
            new gettingResponse().execute(topRatedURLString);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

