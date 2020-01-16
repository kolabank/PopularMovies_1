package android.example.com.popularmovies_1;

import java.util.ArrayList;

public class RetroParse {

    private String id;
    private ArrayList<ResultsClass>results;

    public String getId() {
        return id;
    }

    public ArrayList<ResultsClass> getResults() {
        return results;
    }

    public RetroParse(String id, ArrayList<ResultsClass> results) {
        this.id = id;
        this.results = results;
    }
}



