package com.example.agventgroceryapp.Adapters;

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
import com.example.agventgroceryapp.Activities.ProductDetailsActivity;
import com.example.agventgroceryapp.Models.CategoryModel;
import com.example.agventgroceryapp.Models.ViewAllModel;
import com.example.agventgroceryapp.R;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    ArrayList<ViewAllModel> allData;

    public ViewAllAdapter(Context context, ArrayList<ViewAllModel> allData) {
        this.context = context;
        this.allData = allData;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(allData.get(position).getImg_url()).into(holder.all_img);
        holder.all_name.setText(allData.get(position).getName());
        holder.all_description.setText(allData.get(position).getDescription());
        holder.all_price.setText(allData.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("detail", allData.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    public void viewAllList(ArrayList<ViewAllModel> allData){
        this.allData = allData;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView all_img;
        TextView all_name, all_description, all_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            all_img = itemView.findViewById(R.id.viewAll_img);
            all_name = itemView.findViewById(R.id.viewAll_name);
            all_description = itemView.findViewById(R.id.viewAll_description);
            all_price = itemView.findViewById(R.id.viewAll_price);
        }
    }
}
