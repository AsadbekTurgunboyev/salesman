package com.example.salesman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesman.ClickCategories;
import com.example.salesman.R;
import com.example.salesman.model.CategoryModel;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<CategoryModel> list;
    ClickCategories categories;
    final int[] catImages = {R.drawable.meats,R.drawable.img,R.drawable.img,R.drawable.fruits,R.drawable.drinks};

    public CategoryAdapter(Context context, List<CategoryModel> list,ClickCategories callback) {
        this.context = context;
        this.list = list;
        this.categories = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.categoryName.setText(list.get(position).getCategory());
        holder.imageView.setImageResource(catImages[list.get(position).getId()]);
        holder.itemView.setOnClickListener(view -> {
            categories.onClick(list.get(holder.getAdapterPosition()).getCategory());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView categoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
