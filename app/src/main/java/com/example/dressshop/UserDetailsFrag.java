package com.example.dressshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class UserDetailsFrag extends Fragment {




    public UserDetailsFrag() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

       Uri imageUri;
       Bitmap ImageBit;
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();


                        imageUri = data.getData();

                        ImageView image= getActivity().findViewById(R.id.UserImageView);
                        image.setImageURI(imageUri);

                        try {
                            ImageBit=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                }
            });



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.user_details_frag, container, false);

        EditText Name=view.findViewById(R.id.UserNameEditText);
        EditText Number=view.findViewById(R.id.UserNumberEditText);
        EditText Email=view.findViewById(R.id.UserEmailEditText);
        EditText Adress =view.findViewById(R.id.UserAdressEditText);
        ImageView UserImageView=view.findViewById(R.id.UserImageView);


        UserImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                someActivityResultLauncher.launch(intent);

                try {
                    SavetoLocal(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    SavetoLocal(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    SavetoLocal(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    SavetoLocal(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Adress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    SavetoLocal(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return  view;
    }

    public void SavetoLocal(View view) throws IOException {


        EditText Name=view.findViewById(R.id.UserNameEditText);
        EditText Number=view.findViewById(R.id.UserNumberEditText);
        EditText Email=view.findViewById(R.id.UserEmailEditText);
        EditText Adress =view.findViewById(R.id.UserAdressEditText);

        File file=new File(Environment.getExternalStorageDirectory(),"DressShop");
        if(!file.exists())
        {
            file.mkdir();
        }
        File path=new File(file,"User.txt");
        File Imagepath=new File(file,"Image.jpg");


        FileOutputStream f=new FileOutputStream(path);
        f.write((Name.getText().toString()+"/"+Number.getText().toString()+"/"+Email.getText().toString()+"/"+Adress.getText().toString()).getBytes());

        FileOutputStream f2=new FileOutputStream(Imagepath);
        ByteArrayOutputStream BA =new ByteArrayOutputStream();
        ImageBit.compress(Bitmap.CompressFormat.JPEG,100,BA);


        f2.write(BA.toByteArray());




    }

}