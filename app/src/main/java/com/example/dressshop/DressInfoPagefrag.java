package com.example.dressshop;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class DressInfoPagefrag extends Fragment {



     ShowDressData data;
     ShowDressFrag obj;

    public DressInfoPagefrag(ShowDressData data,ShowDressFrag Obj) {
        // Required empty public constructor
        this.data=data;
        this.obj=Obj;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dress_info_page_frag, container, false);


        Button AddtoCartButton=view.findViewById(R.id.AddToCartBtn);
        AddtoCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper helper=new DatabaseHelper(getContext());
                helper.InsertToCart(data.DressCode,data.DressName,data.DressImage,data.DressPrice);
            }
        });


        ImageView image=view.findViewById(R.id.DressInfo_ImageView);
        Picasso.get().load(Uri.parse(data.DressImage)).into(image);

        TextView dressName=view.findViewById(R.id.DressInfoDressName_TextView);
        dressName.setText(data.DressName);

        TextView dressPrice=view.findViewById(R.id.DressInfoPriceTextView);
        dressPrice.setText( String.valueOf(data.DressPrice) );


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference(obj.DressType);



        if(obj.DressType.equals("Pants"))
        {
            TextView Sleeves=view.findViewById(R.id.DressInfoSleeveInput);
            Sleeves.setVisibility(View.INVISIBLE);
            TextView Sleeves2=view.findViewById(R.id.DressInfoSleeveOutput);
            Sleeves2.setVisibility(View.INVISIBLE);

        }





        ValueEventListener value=new ValueEventListener() {



            @Override
            public void onDataChange( DataSnapshot snapshot) {
                TextView NameDesc=view.findViewById(R.id.DressInfoDressSmallDrescription_TextView);
                TextView PackOf=view.findViewById(R.id.DressInfoPackInput);
                TextView Fabric=view.findViewById(R.id.DressInfoFabricInput);
                TextView Sleeves=view.findViewById(R.id.DressInfoSleeveInput);
                TextView color=view.findViewById(R.id.DressInfoColourInput);
                TextView pattern=view.findViewById(R.id.DressInfoPatternInput);

                int i=0;
                for (DataSnapshot snap:snapshot.child("Boys").getChildren())
                {
                    if(snap.child("Name").getValue(String.class).equals(data.DressName))
                    {

                        NameDesc.setText(snap.child("NameDesc").getValue(String.class));
                        PackOf.setText(snap.child("PackOf").getValue(Integer.class).toString());
                        Fabric.setText(snap.child("Fabric").getValue(String.class));
                        if(!obj.DressType.equals("Pants"))
                        Sleeves.setText(snap.child("Sleeves").getValue(String.class));
                        color.setText(snap.child("Colour").getValue(String.class));
                        pattern.setText(snap.child("Pattern").getValue(String.class));
                        break;

                    }

                }



            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        };
        myref.addValueEventListener(value);








        return  view;
    }
}