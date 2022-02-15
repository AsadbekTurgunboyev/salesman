package com.example.salesman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesman.adapter.CategoryAdapter;
import com.example.salesman.adapter.SearchAdapter;
import com.example.salesman.model.CategoryModel;
import com.example.salesman.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickCategories, TextWatcher,BottomInterface{
    public RecyclerView categoryRec;
    public CategoryAdapter adapter;
    public SearchAdapter searchAdapter;
    BottomSheet bottomSheet;


    public List<ProductModel> productModels = new ArrayList<>();
    public List<CategoryModel> categoryModelList = new ArrayList<>();
    public List<ProductModel> products;

    FirebaseDatabase database;
    DatabaseReference reference;

    EditText searchEdt;

    boolean doubleClick = false;

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            categoryModelList.clear();
            for (DataSnapshot ds : snapshot.getChildren()) {

                CategoryModel model = ds.getValue(CategoryModel.class);
                categoryModelList.add(model);
            }
            gridRec(categoryModelList);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    ValueEventListener searchProductListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            productModels.clear();
            for (DataSnapshot ds : snapshot.getChildren()) {
                ProductModel model = ds.getValue(ProductModel.class);
                productModels.add(model);
            }
            linerRec(productModels);
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        searchEdt.addTextChangedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        doubleClick = true;
        reference.addValueEventListener(listener);
        getProducts();
    }

//    private void setModel() {
//        productModels = new ArrayList<>();
//        ProductModel model = new ProductModel("Fanta(1L)", "Ichimliklar", 5400, 6500, 10);
//        ProductModel model1 = new ProductModel("Fanta(1.5L)", "Ichimliklar", 7000, 8000, 8);
//           productModels.add(model);
//        productModels.add(model1);
//           for (int i = 0; i < productModels.size(); i++) {
//            reference.child(All_Strings.ICHIMLIK).child("list").push().setValue(productModels.get(i));
//
//        }

//        }


//        private void setCat () {
//            List<CategoryModel> id_categories1 = new ArrayList<>();
////            CategoryModel categoryModel = new CategoryModel("Go'sht mahsulotlari", 0, productModels);
//            id_categories1.add(categoryModel);
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("category",All_Strings.MEVA);
//            hashMap.put("id", 5);
//            hashMap.put("list", id_categories1.get(0).getList());
//        reference.child(All_Strings.MEVA).setValue(hashMap);

//        }


    private void initViews() {
        categoryRec = findViewById(R.id.categoryRec);
        searchEdt = findViewById(R.id.editText);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(All_Strings.PRODUCTS);
    }



    private void getProducts() {
        products = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("list").exists()) {
                        for (DataSnapshot snapshot1 : ds.child("list").getChildren()) {
                            ProductModel model = snapshot1.getValue(ProductModel.class);
                            products.add(model);
                        }

                    }
                }
                Log.e("toplam",products.size()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }


    @Override
    public void onClick(String catName) {
        doubleClick = false;
        reference.child(catName).child("list").addValueEventListener(searchProductListener);
    }

    @Override
    public void onBackPressed() {

        if (doubleClick) {

            super.onBackPressed();
        } else {
            searchEdt.setText("");
            reference.addValueEventListener(listener);
            doubleClick = true;
        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().length() > 0) {
            doubleClick = false;
            filter(charSequence.toString());
        } else {
            doubleClick = true;
            gridRec(categoryModelList);
        }
    }


    @Override
    public void afterTextChanged(Editable editable) {

    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String txt) {
        List<ProductModel> searchList = new ArrayList<>();
        for (ProductModel item : products) {
            if (item.getProduct_name().toLowerCase().contains(txt.toLowerCase())) {
                searchList.add(item);
            }
        }
        linerRec(searchList);
        searchAdapter.notifyDataSetChanged();
    }

    private void linerRec(List<ProductModel> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        categoryRec.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(getApplicationContext(), list,this);
        categoryRec.setAdapter(searchAdapter);
    }

    private void gridRec(List<CategoryModel> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        categoryRec.setLayoutManager(layoutManager);
        adapter = new CategoryAdapter(getApplicationContext(), list, MainActivity.this);
        categoryRec.setAdapter(adapter);
    }


    @Override
    public void showBottom(ProductModel model) {
        bottomSheet = new BottomSheet(model);
        bottomSheet.show(getSupportFragmentManager(),"TAG");
    }
}