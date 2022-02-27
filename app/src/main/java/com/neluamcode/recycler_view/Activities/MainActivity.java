package com.neluamcode.recycler_view.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.neluamcode.recycler_view.Models.Movie;
import com.neluamcode.recycler_view.Adapters.MyAdapter;
import com.neluamcode.recycler_view.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private List<Movie> movies;
    private RecyclerView.LayoutManager mLayoutmanager;
    private RecyclerView.Adapter mAdapter;

    private int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=getAllmovies();
        recycler=findViewById(R.id.recycler);
        mLayoutmanager= new LinearLayoutManager(this);
        //mLayoutmanager= new GridLayoutManager(this, 2);
        //mLayoutmanager= new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);


        mAdapter=new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                remvoeMovie(position);
            }
        });

        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setLayoutManager(mLayoutmanager);
        recycler.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   private void addMovie(int position){
        movies.add(position, new Movie("New movie"+ (++count), R.drawable.newmovie) );
        mAdapter.notifyItemInserted(position);
        mLayoutmanager.scrollToPosition(position);
    }


    private void remvoeMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private List<Movie> getAllmovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Avengers", R.drawable.aven));
            add(new Movie("DeadPool", R.drawable.dead));
            add(new Movie("End Game", R.drawable.end));
            add(new Movie("Infinity war", R.drawable.gauntlet));
        }};
    }

}