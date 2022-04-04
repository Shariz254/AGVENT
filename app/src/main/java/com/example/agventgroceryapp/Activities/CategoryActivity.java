package com.example.agventgroceryapp.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
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

import com.example.agventgroceryapp.Adapters.CategoryAdapter;
import com.example.agventgroceryapp.Adapters.PopularProductsAdapter;
import com.example.agventgroceryapp.Models.CategoryModel;
import com.example.agventgroceryapp.Models.PopularProductsModel;
import com.example.agventgroceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> categoryData;

    Toolbar toolbar;
    FirebaseFirestore db;
    Activity act;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Processing Data..");
        pd.show();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CATEGORY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        act = this;

        //CATEGORY PRODUCTS
        categoryData = new ArrayList<CategoryModel>();
        categoryAdapter = new CategoryAdapter(CategoryActivity.this, categoryData);

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        db = FirebaseFirestore.getInstance();

        EventChangeListener();
    }

    private void EventChangeListener() {
        //POPULAR PRODUCTS
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "onComplete: " + document.getData());

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);

                                categoryData.add(new CategoryModel(
                                        document.getData().get("img_url").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        document.getData().get("discount").toString(),
                                        document.getData().get("price").toString()
                                ));
                                Log.d(TAG, "onComplete: " + categoryData);
                                pd.dismiss();
                                categoryAdapter.categoryList(categoryData);

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