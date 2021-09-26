package com.example.dressshop;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowDressAdapter extends RecyclerView.Adapter<ShowDressAdapter.ViewHolder> {


    Context context;
    FragmentActivity fragment;
    ArrayList<ShowDressData>arrayList;
    ShowDressFrag Obj;
    public ShowDressAdapter(Context context,ShowDressFrag OBj ,ArrayList<ShowDressData>arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
        this.fragment=(FragmentActivity)context;
        this.Obj=OBj;
    }


    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.show_dress_template,parent,false);

        return new ViewHolder(itemView);
    }

    @NonNull
    @Override
    public void onBindViewHolder( ShowDressAdapter.ViewHolder holder, int position) {

        ShowDressData data=arrayList.get(position);
        Picasso.get().load(Uri.parse(data.DressImage)).into(holder.DressImage);
        holder.DressRating.setText(String.valueOf(data.DressRating));
        holder.DressPrice.setText(String.valueOf(data.DressPrice));
        holder.DressName.setText(data.DressName);

        holder.DressImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             fragment.getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame,new DressInfoPagefrag(data,Obj),null).addToBackStack(null).commit();

            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
          ImageView DressImage;
          TextView DressName;
          TextView DressPrice;
          TextView DressRating;


        public ViewHolder( View itemView) {
            super(itemView);

            DressImage=itemView.findViewById(R.id.showDressImageImageView);
            DressName=itemView.findViewById(R.id.ShowDressNameTextView);
            DressPrice=itemView.findViewById(R.id.ShowDressPriceTextView);
            DressRating=itemView.findViewById(R.id.ShowDressRatingTextView);






        }
    }




}
