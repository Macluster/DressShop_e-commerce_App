package com.example.dressshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    public HomeFrag() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.home_frag, container, false);




        showNewStyles(view);





        return  view;
    }


    public void showNewStyles(View view)
    {
        ArrayList<NewStylesData> arrayList=new ArrayList<>();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("DressStyles/NewStylesData");

       // NewStylesData data=new NewStylesData("https://firebasestorage.googleapis.com/v0/b/dressshop-52bd5.appspot.com/o/CakeApp%2FNewStyleImages%2Fboy.jpg?alt=media&token=7564d910-b145-4922-9a51-c359d82908ee",1);



        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {

                int i=1;
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    String image=snapshot.child("ID"+i).child("Style_Image").getValue(String.class);
                    String id= snapshot.child("ID"+i).child("Style_Id").getValue(String.class);
                    //Toast.makeText(getActivity(),image,Toast.LENGTH_LONG).show();
                    NewStylesData data=new NewStylesData(id,image);
                    arrayList.add(data);
                    StyleAdapter adapter=new StyleAdapter(getActivity(),arrayList);

                    RecyclerView StyleRecyclerview=view.findViewById(R.id.StylesRecyclerView);
                    LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

                    StyleRecyclerview.setLayoutManager(layoutManager);
                    StyleRecyclerview.setAdapter(adapter);
                    i++;
                }

            }

            @Override
            public void onCancelled( DatabaseError error){

            }
        };
            myref.addValueEventListener(valueEventListener);



            DatabaseReference myref2=database.getReference("BestSellers");
            ValueEventListener valueEventListener1=new ValueEventListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onDataChange( DataSnapshot snapshot) {
                    String Image; String DressCode;
                    int j=1;
                    ArrayList<NewStylesData> arrayList1=new ArrayList<>();

                    for(DataSnapshot snap: snapshot.getChildren())
                    {
                      Image= snapshot.child("ID"+j).child("Image").getValue(String.class);
                      DressCode=snapshot.child("ID"+j).child("DressCode").getValue(String.class);
                      arrayList1.add(new NewStylesData(DressCode,Image));





                        j++;
                    }

                    StyleAdapter adapter=new StyleAdapter(getActivity(),arrayList1);

                    RecyclerView recyclerView = view.findViewById(R.id.StylesRecyclerView2);
                    LinearLayoutManager layout=new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);

                    recyclerView.setLayoutManager(layout);
                    recyclerView.setAdapter(adapter);



                }

                @Override
                public void onCancelled( DatabaseError error) {

                }
            };
            myref2.addValueEventListener(valueEventListener1);


        ImageButton pantsBtn=view.findViewById(R.id.PantsBtn);
        pantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame,new ShowDressFrag("Pants","Boys"),null).addToBackStack(null).commit();


            }
        });

        ImageButton ShirtBtn=view.findViewById(R.id.ShirtBtn);
        ShirtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame,new ShowDressFrag("Shirts","Boys"),null).addToBackStack(null).commit();

            }
        });








    }








}