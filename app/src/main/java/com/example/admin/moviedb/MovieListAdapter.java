package com.example.admin.moviedb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.admin.moviedb.model.Result;

import java.util.ArrayList;

/**
 * Created by Admin on 9/8/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>  {


    ArrayList<Result> results = new ArrayList<>();
    Context context;

    public MovieListAdapter(ArrayList<Result> resultList) {
        this.results = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, final int position) {
        holder.tvName.setText(results.get(position).getTitle());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/" + results.get(position)
                .getPosterPath())
                .fitCenter()
                .override(200, 400)
                .into(holder.ivPoster);
        //Log.d("ViewHolder", "onBindViewHolder: Glide Done");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie" ,results.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(results != null){
            return results.size();
        }
        else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
