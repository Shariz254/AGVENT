package com.example.agventgroceryapp.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.agventgroceryapp.Adapters.ExploreAllProductsAdapter;
import com.example.agventgroceryapp.Adapters.PopularProductsAdapter;
import com.example.agventgroceryapp.Adapters.RecommendedProductsAdapter;
import com.example.agventgroceryapp.Models.ExploreAllProductsModel;
import com.example.agventgroceryapp.Models.PopularProductsModel;
import com.example.agventgroceryapp.Models.RecommendedProductsModel;
import com.example.agventgroceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllProductsActivity extends AppCompatActivity {

    PopularProductsAdapter popularProductsAdapter;
    ExploreAllProductsAdapter exploreAllProductsAdapter;
    RecommendedProductsAdapter recommendedProductsAdapter;

    ArrayList<PopularProductsModel> popularProductsData;
    ArrayList<ExploreAllProductsModel> exploreData;
    ArrayList<RecommendedProductsModel> recommendedData;


    Toolbar toolbar;

    RecyclerView allProductsRecyclerView, exploreProductsRecyclerView, recommendedRecyclerView;

    FirebaseFirestore db;
    Activity act;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);

        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Processing Data..");
        pd.show();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ALL PRODUCTS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        act = this;

        //POPULAR PRODUCTS
        popularProductsData = new ArrayList<PopularProductsModel>();
        popularProductsAdapter = new PopularProductsAdapter(AllProductsActivity.this, popularProductsData);

        allProductsRecyclerView = findViewById(R.id.allProductsRecyclerView);
        allProductsRecyclerView.setHasFixedSize(true);
        allProductsRecyclerView.setAdapter(popularProductsAdapter);
        allProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        //EXPLORE ALL PRODUCTS
        exploreData = new ArrayList<ExploreAllProductsModel>();
        exploreAllProductsAdapter = new ExploreAllProductsAdapter(AllProductsActivity.this, exploreData);

        exploreProductsRecyclerView = findViewById(R.id.exploreProductsRecyclerView);
        exploreProductsRecyclerView.setHasFixedSize(true);
        exploreProductsRecyclerView.setAdapter(exploreAllProductsAdapter);
        exploreProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        //RECOMMENDED PRODUCTS
        recommendedData = new ArrayList<RecommendedProductsModel> ();
        recommendedProductsAdapter = new RecommendedProductsAdapter(AllProductsActivity.this, recommendedData);

        recommendedRecyclerView = findViewById(R.id.recommendedRecyclerView);
        recommendedRecyclerView.setHasFixedSize(true);
        recommendedRecyclerView.setAdapter(recommendedProductsAdapter);
        recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        db = FirebaseFirestore.getInstance();

        EventChangeListener();
    }

    private void EventChangeListener() {
        //POPULAR PRODUCTS
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "onComplete: " + document.getData());
                                PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);

                                popularProductsData.add(new PopularProductsModel(
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        document.getData().get("rating").toString(),
                                        document.getData().get("discount").toString(),
                                        document.getData().get("type").toString(),
                                        document.getData().get("img_url").toString()
                                ));
                                Log.d(TAG, "onComplete: " + popularProductsData);
                                pd.dismiss();
                                popularProductsAdapter.updateList(popularProductsData);

                            }
                        } else {
                            Toast.makeText(act, "Error>" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //EXPLORE PRODUCTS
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "onComplete: " + document.getData());
                                ExploreAllProductsModel exploreAllProductsModel = document.toObject(ExploreAllProductsModel.class);

                                exploreData.add(new ExploreAllProductsModel(
                                        document.getData().get("name").toString(),
                                        document.getData().get("img_url").toString(),
                                        document.getData().get("type").toString()
                                ));
                                Log.d(TAG, "onComplete: " + exploreData);
                                pd.dismiss();
                                exploreAllProductsAdapter.updateExplore(exploreData);

                            }
                        } else {
                            Toast.makeText(act, "Error>" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //RECOMMENDED PRODUCTS
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "onComplete: " + document.getData());

                                RecommendedProductsModel recommendedProductsModel = document.toObject(RecommendedProductsModel.class);

                                recommendedData.add(new RecommendedProductsModel(
                                        document.getData().get("img_url").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("price").toString(),
                                        document.getData().get("rating").toString()
                                ));
                                Log.d(TAG, "onComplete: " + recommendedData);
                                pd.dismiss();
                                recommendedProductsAdapter.recommendedExplore(recommendedData);

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