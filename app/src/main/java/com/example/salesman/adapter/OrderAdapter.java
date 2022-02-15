package com.example.salesman.adapter;

import static com.example.salesman.R.drawable.bottom_bg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesman.HomeActivity;
import com.example.salesman.R;
import com.example.salesman.database.ProductData;
import com.example.salesman.model.SellModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
        List<SellModel> list;
        Context context;
        ProductData data;
        Dialog dialog;


    public OrderAdapter(List<SellModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getProduct_name());
        holder.txtKg.setText(String.valueOf(list.get(position).getOlingan_miqdor()));
        holder.textPrice.setText(currencyFormatter(list.get(position).getSotilgan_narx()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKg,textView,textPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textPrice = itemView.findViewById(R.id.textPrice);
            textView = itemView.findViewById(R.id.textView);
            txtKg = itemView.findViewById(R.id.txtKg);
        }
    }

    public String jami (){
        int price = 0;

       for (int i = 0; i< list.size(); i++){
           price+= Integer.parseInt(list.get(i).getSotilgan_narx());
       }


        return currencyFormatter(String.valueOf(price));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void writeFirebase(String time){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if(list.size()>0){
            dialog.show();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data = new ProductData(context);
        DatabaseReference reference = database.getReference("orders").child(time);
        for (SellModel model : list){
            reference.child(model.getKey()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        data.deleteData(model.getId());
                    }
                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    list.clear();
                    notifyDataSetChanged();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((Activity)context).finish();

                }
            },3000);

        }

    }
    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m);
    }


}
