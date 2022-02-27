package com.neluamcode.recycler_view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neluamcode.recycler_view.Models.Movie;
import com.neluamcode.recycler_view.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies=movies;
        this.layout=layout;
        this.itemClickListener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context=parent.getContext();
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewname;
        public ImageView imageViewPoster;

        public ViewHolder(View v){
            super(v);
            textViewname = (TextView) v.findViewById(R.id.textViewTitle);
            imageViewPoster=(ImageView) v.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener){
            textViewname.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie, int position);
    }

}
