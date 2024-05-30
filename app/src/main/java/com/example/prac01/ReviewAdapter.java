package com.example.prac01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private ArrayList<Review> reviewList;
    private final Context context;

    public ReviewAdapter(ArrayList<Review> reviewList, Context context){
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.user_id.setText(review.getUser_id().substring(0,10)+" 님");
        holder.content.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_id;
        TextView content;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.user_id = itemView.findViewById(R.id.user_id);
            this.content= itemView.findViewById(R.id.content);
        }
    }


}