package com.example.salesman.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.salesman.model.SellModel;

import java.util.ArrayList;
import java.util.List;

public class ProductData extends SQLiteOpenHelper {
    public static String TABLE_NAME = "orders";
    public static int VERSION = 1;
    public static String DATABASE = "data.db";
    List<SellModel> list;
    public ProductData( Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, _key TEXT, sotilgan_narx INTEGER, cat_name TEXT, order_num INTEGER, product_name TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createTable(SellModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("_key",model.getKey());
        cv.put("sotilgan_narx",model.getSotilgan_narx());
        cv.put("cat_name",model.getCat_name());
        cv.put("order_num",model.getOlingan_miqdor());
        cv.put("product_name",model.getProduct_name());
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public List<SellModel> getAllData(){
        list = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        while (cursor.moveToNext()){
            list.add(new SellModel(cursor.getString(5),
                    cursor.getString(3),
                    cursor.getString(1),
                    cursor.getInt(0),
                    cursor.getString(2),
                    cursor.getInt(4)));

        }
        database.close();
        return list;
    }

    public void deleteData(int position){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"id=?", new String[]{String.valueOf(position)});
        database.close();
    }
}
