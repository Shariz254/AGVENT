package com.example.agventgroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agventgroceryapp.Models.CategoryModel;
import com.example.agventgroceryapp.Models.PopularProductsModel;
import com.example.agventgroceryapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModel> categoryData;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(categoryData.get(position).getImg_url()).into(holder.caegoryImg);
        holder.catName.setText(categoryData.get(position).getName());
        holder.catDecsription.setText(categoryData.get(position).getDescription());
        holder.catDiscount.setText(categoryData.get(position).getDiscount());
        holder.catPrice.setText(categoryData.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public void categoryList(ArrayList<CategoryModel> categoryData){
        this.categoryData = categoryData;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView caegoryImg;
        TextView catName, catDecsription, catDiscount, catPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            caegoryImg = itemView.findViewById(R.id.category_img);
            catName = itemView.findViewById(R.id.category_name);
            catDecsription = itemView.findViewById(R.id.category_description);
            catDiscount = itemView.findViewById(R.id.category_discount);
            catPrice = itemView.findViewById(R.id.category_price);
        }
    }
}
