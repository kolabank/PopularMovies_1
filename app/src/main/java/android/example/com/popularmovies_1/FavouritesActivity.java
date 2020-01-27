package android.example.com.popularmovies_1;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

public class FavouritesActivity extends AppCompatActivity {

   private RecyclerView rv_Favourites;

    private AppDataBase favDB;

    private String[] thumbNailArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        rv_Favourites = findViewById(R.id.rv_favourites);



        favDB = AppDataBase.getInstance(getApplicationContext());



    }

    @Override
    protected void onResume() {
        super.onResume();

    thumbNailArray = new String[4];



    }

}
