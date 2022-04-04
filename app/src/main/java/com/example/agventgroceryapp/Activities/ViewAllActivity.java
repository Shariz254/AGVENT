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
import com.example.agventgroceryapp.Adapters.ViewAllAdapter;
import com.example.agventgroceryapp.Models.CategoryModel;
import com.example.agventgroceryapp.Models.ViewAllModel;
import com.example.agventgroceryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView viewAllRecyclerView;
    ArrayList<ViewAllModel> allData;
    ViewAllAdapter viewAllAdapter;

    Toolbar toolbar;
    FirebaseFirestore db;
    Activity act;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        String type = getIntent().getStringExtra("type");


        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Processing Data..");
        pd.show();

        act = this;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VIEW ALL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //CATEGORY PRODUCTS
        allData = new ArrayList<ViewAllModel>();
        viewAllAdapter = new ViewAllAdapter(ViewAllActivity.this, allData);

        viewAllRecyclerView = findViewById(R.id.viewAllRecyclerView);
        viewAllRecyclerView.setHasFixedSize(true);
        viewAllRecyclerView.setAdapter(viewAllAdapter);
        viewAllRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        db = FirebaseFirestore.getInstance();

        if (type != null && type.equalsIgnoreCase("fruits")){
            db.collection("ViewAllProducts").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);

                        allData.add(new ViewAllModel(
                                document.getData().get("name").toString(),
                                document.getData().get("description").toString(),
                                document.getData().get("img_url").toString(),
                                document.getData().get("price").toString()
                        ));
                        Log.d(TAG, "onComplete: " + allData);
                        pd.dismiss();
                        viewAllAdapter.viewAllList(allData);

                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("milk")){
            db.collection("ViewAllProducts").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);

                        allData.add(new ViewAllModel(
                                document.getData().get("name").toString(),
                                document.getData().get("description").toString(),
                                document.getData().get("img_url").toString(),
                                document.getData().get("price").toString()
                        ));
                        Log.d(TAG, "onComplete: " + allData);
                        pd.dismiss();
                        viewAllAdapter.viewAllList(allData);

                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("eggs")){
            db.collection("ViewAllProducts").whereEqualTo("type", "eggs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);

                        allData.add(new ViewAllModel(
                                document.getData().get("name").toString(),
                                document.getData().get("description").toString(),
                                document.getData().get("img_url").toString(),
                                document.getData().get("price").toString()
                        ));
                        Log.d(TAG, "onComplete: " + allData);
                        pd.dismiss();
                        viewAllAdapter.viewAllList(allData);

                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("fish")){
            db.collection("ViewAllProducts").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);

                        allData.add(new ViewAllModel(
                                document.getData().get("name").toString(),
                                document.getData().get("description").toString(),
                                document.getData().get("img_url").toString(),
                                document.getData().get("price").toString()
                        ));
                        Log.d(TAG, "onComplete: " + allData);
                        pd.dismiss();
                        viewAllAdapter.viewAllList(allData);

                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("vegetables")){
            db.collection("ViewAllProducts").whereEqualTo("type", "vegetables").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);

                        allData.add(new ViewAllModel(
                                document.getData().get("name").toString(),
                                document.getData().get("description").toString(),
                                document.getData().get("img_url").toString(),
                                document.getData().get("price").toString()
                        ));
                        Log.d(TAG, "onComplete: " + allData);
                        pd.dismiss();
                        viewAllAdapter.viewAllList(allData);

                    }
                }
            });
        }
        //EventChangeListener();

    }

//    private void EventChangeListener() {
//        //POPULAR PRODUCTS
//        db.collection("ViewAllProducts")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, "onComplete: " + document.getData());
//
//                                ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);
//
//                                allData.add(new ViewAllModel(
//                                        document.getData().get("name").toString(),
//                                        document.getData().get("description").toString(),
//                                        document.getData().get("img_url").toString(),
//                                        document.getData().get("price").toString()
//                                ));
//                                Log.d(TAG, "onComplete: " + allData);
//                                pd.dismiss();
//                                viewAllAdapter.viewAllList(allData);
//
//                            }
//                        } else {
//                            Toast.makeText(act, "Error>" + task.getException().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }

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