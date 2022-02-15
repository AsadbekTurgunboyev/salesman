package com.example.salesman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesman.adapter.OrderAdapter;
import com.example.salesman.database.ProductData;
import com.example.salesman.model.SellModel;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
        RecyclerView orderRec;
        OrderAdapter adapter;
        ProductData data;
        List<SellModel> list;
        TextView txtAll;
        MaterialButton buyurtma_berish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();
        data = new ProductData(this);
        list = data.getAllData();
        adapter = new OrderAdapter(list,this);
         orderRec.setAdapter(adapter);
        txtAll.setText(adapter.jami());
        buyurtma_berish.setOnClickListener(view -> {
            Date currentTime = Calendar.getInstance().getTime();
            adapter.writeFirebase(String.valueOf(currentTime));
        });

    }

    private void initViews() {
        orderRec = findViewById(R.id.orderRec);
        txtAll = findViewById(R.id.txtAll);
        buyurtma_berish = findViewById(R.id.materialButton);
    }
}