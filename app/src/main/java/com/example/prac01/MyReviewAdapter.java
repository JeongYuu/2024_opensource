package com.example.prac01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ViewHolder> {

    private ArrayList<Review> reviewList;
    private final Context context;

    public MyReviewAdapter(ArrayList<Review> reviewList, Context context){
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.menu_name.setText(review.getMenu_name());
        holder.my_content.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menu_name;
        TextView my_content;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.menu_name = itemView.findViewById(R.id.menu_name);
            this.my_content= itemView.findViewById(R.id.my_content);
        }
    }


}