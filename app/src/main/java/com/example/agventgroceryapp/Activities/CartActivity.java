package com.example.agventgroceryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.agventgroceryapp.Adapters.MyCartAdapter;
import com.example.agventgroceryapp.Models.MyCartModel;
import com.example.agventgroceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView cartRecyclerView;
    MyCartAdapter myCartAdapter;
    ArrayList<MyCartModel> cartData;
    Activity act;
    Context context;

    FirebaseFirestore db;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        act = this;
        context = this;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MY CART");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartData = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(CartActivity.this, cartData);
        cartRecyclerView.setAdapter(myCartAdapter);

        db.collection("AddToCart").document(mAuth.getCurrentUser().getUid()).collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MyCartModel myCartModel =  documentSnapshot.toObject(MyCartModel.class);

                        cartData.add(new MyCartModel(
                                documentSnapshot.getData().get("productaName").toString(),
                                documentSnapshot.getData().get("productPrice").toString(),
                                "",
                                "",
                                documentSnapshot.getData().get("totalQuantity").toString(),
                                documentSnapshot.getData().get("totalPrice").toString()
                        ));

                        myCartAdapter.updateCart(cartData);

//                        cartData.add(myCartModel);
//                        myCartAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(act, "Error>" + task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}