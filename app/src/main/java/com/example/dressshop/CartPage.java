package com.example.dressshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        getSupportActionBar().hide();

        int totalPrice=0;

        ArrayList<CartData> arrayList=new ArrayList<>();

        DatabaseHelper helper=new DatabaseHelper(CartPage.this);
        Cursor cursor=helper.getdataFromCart();

        CartData data;

        while(cursor.moveToNext())
        {

            data=new CartData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getFloat(3));
            arrayList.add(data);
            totalPrice+=cursor.getFloat(3);

        }


        TextView TotalPriceText=findViewById(R.id.CartTotalPrice);
        TotalPriceText.setText(String.valueOf(totalPrice));



        CartAdapater adapater=new CartAdapater(CartPage.this,arrayList);
        RecyclerView recyclerView=findViewById(R.id.CartRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(CartPage.this, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapater);


    }
}