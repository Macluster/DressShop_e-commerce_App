package com.example.dressshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowDressFrag extends Fragment {

    String DressType;
    String Gender;
    public ShowDressFrag(String DressType,String Gender) {
        // Required empty public constructor
        this.DressType=DressType;
        this.Gender=Gender;


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.show_dress_frag, container, false);



        TextView heading=view.findViewById(R.id.ShowDressHeadingTextView);

        heading.setText(Gender+" "+DressType);




        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference mref=database.getReference(DressType);

        ValueEventListener value=new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                int i=1;
                ArrayList<ShowDressData> arrayList=new ArrayList<>();
                String Dress_Code;
                String DressName;
                String DressImage;
                float DressPrice;
                float DressRating;

                for (DataSnapshot snap:snapshot.child("Boys").getChildren())
                {
                   Dress_Code=snapshot.child(Gender).child("ID"+i).child("Dress_Code").getValue(String.class);
                   DressName=snapshot.child(Gender).child("ID"+i).child("Name").getValue(String.class);
                   DressImage=snapshot.child(Gender).child("ID"+i).child("Image").getValue(String.class);
                   DressPrice=snapshot.child(Gender).child("ID"+i).child("Price").getValue(Float.class);
                   DressRating=snapshot.child(Gender).child("ID"+i).child("Rating").getValue(Float.class);

                   arrayList.add(new ShowDressData(DressName,DressImage,Dress_Code,DressPrice,DressRating));

                    Toast.makeText(getActivity(),DressName,Toast.LENGTH_SHORT).show();



                   i++;
                }


                ShowDressAdapter adapter=new ShowDressAdapter(getActivity(),ShowDressFrag.this,arrayList);

                RecyclerView recyclerView=view.findViewById(R.id.ShowDressRecyclerview1);
                GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(adapter);





            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        };

        mref.addValueEventListener(value);








        return view;
    }
}