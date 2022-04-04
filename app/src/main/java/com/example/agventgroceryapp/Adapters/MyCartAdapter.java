package com.example.agventgroceryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agventgroceryapp.Models.ExploreAllProductsModel;
import com.example.agventgroceryapp.Models.MyCartModel;
import com.example.agventgroceryapp.R;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    ArrayList<MyCartModel> cartData;

    public MyCartAdapter(Context context, ArrayList<MyCartModel> cartData) {
        this.context = context;
        this.cartData = cartData;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {

        holder.name.setText(cartData.get(position).getProductName());
        holder.price.setText(cartData.get(position).getProductPrice());
        holder.date.setText(cartData.get(position).getCurrentDate());
        holder.time.setText(cartData.get(position).getCurrentTime());
        holder.quantity.setText(cartData.get(position).getTotalQuantity());
        holder.total.setText(cartData.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }

    public void updateCart( ArrayList<MyCartModel> cartData){
        this.cartData = cartData;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, date, time, quantity, total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            total = itemView.findViewById(R.id.total_price);
        }
    }
}
