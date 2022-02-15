package com.example.salesman.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.salesman.All_Strings;
import com.example.salesman.model.CategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConnect {
    FirebaseDatabase database;
    DatabaseReference reference;
    List<CategoryModel> id_categories ;
    List<CategoryModel> id_categories1;


    Context context;

    public FirebaseConnect(Context context) {
        this.context = context;
    }

    public void getProductsFromCategories() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(All_Strings.CATEGORIES);
    }

    public List<CategoryModel> getCategories() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(All_Strings.CATEGORIES);
        id_categories1 = new ArrayList<>();
        id_categories =  new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id_categories1.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    CategoryModel categoryModel = ds.getValue(CategoryModel.class);
                    id_categories1.add(categoryModel);
//                    Toast.makeText(context, ""+id_categories.size(), Toast.LENGTH_SHORT).show();
                }
               id_categories.addAll(id_categories1);
                FirebaseConnect.this.notifyAll();

//                Toast.makeText(context, ""+id_categories.size(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(context, ""+id_categories.size(), Toast.LENGTH_SHORT).show();
        return id_categories;


    }
}
