package android.example.com.popularmovies_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailedActivity extends AppCompatActivity {

  private  TextView tv_DetailedTitle, tv_DetailedReleaseDate, tv_DetailedSynopsis,tv_DetailedUserRating;
    private ImageView iv_DetailedThumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ArrayList<ResultsClass>> call = api.getResults();

        call.enqueue(new Callback<ArrayList<ResultsClass>>() {
            @Override
            public void onResponse(Call<ArrayList<ResultsClass>> call, Response<ArrayList<ResultsClass>> response) {

                ArrayList<ResultsClass> results = response.body();

                for(ResultsClass r:results){
                    Log.d("site", r.getSite());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ResultsClass>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });

        tv_DetailedTitle = findViewById(R.id.tv_DetailedTitle);
        tv_DetailedReleaseDate = findViewById(R.id.tv_DetailedReleaseDate);
        tv_DetailedUserRating = findViewById(R.id.tv_DetailedUserRating);
        tv_DetailedSynopsis = findViewById(R.id.tv_DetailedPlotSynopsis);
        iv_DetailedThumbnail = findViewById(R.id.iv_DetailedThumbnail);


       Intent intentThatStartedActivity = getIntent();
       Integer movieReference =  intentThatStartedActivity.getIntExtra("ItemPosition",0);

         populateDetailedActivity(movieReference);



    }

    //Method to set values into the layout views

   public void populateDetailedActivity(int position){

        String userRating = MainActivity.ratingArray[position];
        tv_DetailedUserRating.setText(getString(R.string.detailedUserRating) + " " + userRating);

        String originalTitle = MainActivity.titleArray[position];
        tv_DetailedTitle.setText(originalTitle);

        String releaseDate = MainActivity.dateArrray[position];
        tv_DetailedReleaseDate.setText(getString(R.string.detailedReleaseDate)+ " " + releaseDate);

        String plotSynopsis = MainActivity.synopsisArray[position];
        tv_DetailedSynopsis.setText(plotSynopsis);

        String thumbNailString = MainActivity.thumbArray[position];
        Picasso.get().load(thumbNailString).into(iv_DetailedThumbnail);

        String movieIdString = MainActivity.movieIDArray[position];



         }


}
