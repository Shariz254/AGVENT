package com.example.agventgroceryapp.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agventgroceryapp.Models.ExploreAllProductsModel;
import com.example.agventgroceryapp.Models.RecommendedProductsModel;
import com.example.agventgroceryapp.R;

import java.util.ArrayList;

public class RecommendedProductsAdapter extends RecyclerView.Adapter<RecommendedProductsAdapter.ViewHolder> {

    Context context;
    ArrayList<RecommendedProductsModel> recommendedData;

    public RecommendedProductsAdapter(Context context, ArrayList<RecommendedProductsModel> recommendedData) {
        this.context = context;
        this.recommendedData = recommendedData;
    }

    @NonNull
    @Override
    public RecommendedProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedProductsAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(recommendedData.get(position).getImg_url()).into(holder.img_rec);
        holder.name.setText(recommendedData.get(position).getName());
        holder.price.setText(recommendedData.get(position).getPrice());
        holder.rating.setText(recommendedData.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return recommendedData.size();
    }

    public void recommendedExplore( ArrayList<RecommendedProductsModel> recommendedData){
        this.recommendedData = recommendedData;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_rec;
        TextView name, price, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_rec = itemView.findViewById(R.id.recommended_img);
            name = itemView.findViewById(R.id.rec_name);
            price = itemView.findViewById(R.id.rec_price);
            rating = itemView.findViewById(R.id.rec_rating);
        }
    }
}
