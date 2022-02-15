package com.example.salesman;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesman.database.ProductData;
import com.example.salesman.model.ProductModel;
import com.example.salesman.model.SellModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class BottomSheet extends BottomSheetDialogFragment implements TextWatcher {
    MaterialButton btnSavat;
    TextInputLayout edtMassa;
    TextView productName, productPrice;
    ProductModel model;
    TextInputEditText edt;
    FirebaseDatabase database;
    DatabaseReference reference;
    ProductData data;

    public BottomSheet(ProductModel model) {
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet, container, false);
        initViews(v);
        initVars();
        data = new ProductData(getContext());
        edt.addTextChangedListener(this);
        btnSavat.setOnClickListener(view -> {
            if (Objects.requireNonNull(edtMassa.getEditText()).getText().toString().length() > 0) {
                SellModel sellModel = new SellModel(model.getProduct_name(),
                        model.getCat_name(),
                        model.getKey(),
                        0,
                        productPrice.getText().toString(),
                        Integer.parseInt(edtMassa.getEditText().getText().toString()));


//                   reference.push().setValue(hashMap);

                data.createTable(sellModel);
                Toast.makeText(getContext(), "Bazaga yozildi", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return v;
    }

    private void initVars() {
        productPrice.setText(String.valueOf(model.getSotilgan_narx()));
        productName.setText(model.getProduct_name());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("orders");
    }

    private void initViews(View v) {
        btnSavat = v.findViewById(R.id.btnSavat);
        edtMassa = v.findViewById(R.id.edtMassa);
        productName = v.findViewById(R.id.productName);
        productPrice = v.findViewById(R.id.productPrice);
        edt = v.findViewById(R.id.edt);
    }

    @Override
    public int getTheme() {

        return R.style.AppBottomSheetDialogTheme;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        setPrice(editable);
    }

    private void setPrice(Editable editable) {
        double price;
        if (editable.toString().length() > 0) {
            price = Double.parseDouble(editable.toString()) * model.getSotilgan_narx();
            productPrice.setText(String.valueOf(price));
        } else {
            productPrice.setText(String.valueOf(model.getSotilgan_narx()));
        }
    }
}
