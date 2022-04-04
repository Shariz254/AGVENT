package com.example.agventgroceryapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agventgroceryapp.Activities.ViewAllActivity;
import com.example.agventgroceryapp.Models.ExploreAllProductsModel;
import com.example.agventgroceryapp.Models.PopularProductsModel;
import com.example.agventgroceryapp.R;

import java.util.ArrayList;

public class ExploreAllProductsAdapter extends RecyclerView.Adapter<ExploreAllProductsAdapter.ViewHolder> {

    Context context;
    ArrayList<ExploreAllProductsModel> exploreData ;

    public ExploreAllProductsAdapter(Context context, ArrayList<ExploreAllProductsModel> exploreData) {
        this.context = context;
        this.exploreData = exploreData;
    }

    @NonNull
    @Override
    public ExploreAllProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_products, parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ExploreAllProductsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(exploreData.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(exploreData.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type", exploreData.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exploreData.size();
    }

    public void updateExplore( ArrayList<ExploreAllProductsModel> exploreData){
        this.exploreData = exploreData;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catImg = itemView.findViewById(R.id.home_cat_img);
            name = itemView.findViewById(R.id.cat_home_name);
        }
    }
}
