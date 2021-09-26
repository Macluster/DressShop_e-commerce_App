package com.example.dressshop;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.ContentHandler;
import java.util.ArrayList;

public class StyleAdapter   extends RecyclerView.Adapter<StyleAdapter.ViewHolder> {





    Context context;
    ArrayList<NewStylesData> arrayList;



    public StyleAdapter(Context context, ArrayList<NewStylesData> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(context).inflate(R.layout.news_styles_template,parent,false);
        return new ViewHolder(itemview);
    }

    @NonNull
    @Override
    public void onBindViewHolder( StyleAdapter.ViewHolder holder, int position) {

      NewStylesData data=arrayList.get(position);
      //holder.imageView.setImageURI(Uri.parse(data.Style_Image));
      Picasso.get().load(Uri.parse(data.Style_Image)).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.StyleImageView);

        }
    }

}

