package com.example.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



// TODO da se zavrsit adapterov i drugiot da se naprajt
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    ArrayList<Movie> items;

    public MovieListAdapter(ArrayList<Movie> items){
        this.items=items;
    }
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView title,year;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.movieTitle);
            year =  (TextView) itemView.findViewById(R.id.movieYear);
        }

        public void bind(Movie m) {
            title.setText(m.getTitle());
            year.setText(m.getYear());
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {
//        holder.bind(items.get(position));
        holder.title.setText(items.get(position).getTitle());
        holder.year.setText(items.get(position).getYear()+"");
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    void submitList(ArrayList<Movie> movies) {
        items = movies;
    }
}


