package com.example.dressshop;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartAdapater extends RecyclerView.Adapter<CartAdapater.ViewHolder> {


    Context context;
    ArrayList<CartData> arrayList;
    CartAdapater(Context context, ArrayList<CartData> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.cart_twmplate,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CartAdapater.ViewHolder holder, int position) {

        CartData data=arrayList.get(position);
        holder.DressName.setText(data.DressName);
        holder.DressPrce.setText(String.valueOf( data.DressPrice)+" "+"Rs");
        Picasso.get().load(Uri.parse(data.DressImage)).into(holder.DressImage);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {


        ImageView DressImage;
        TextView DressName;
        TextView DressPrce;
        public ViewHolder( View itemView) {
            super(itemView);

            DressImage=itemView.findViewById(R.id.CartDressImage);
            DressName=itemView.findViewById(R.id.CartDressName);
            DressPrce=itemView.findViewById(R.id.CartDressPrice);
        }
    }





}
