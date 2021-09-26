package com.example.dressshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.security.Permission;

public class BasePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_page);
        getSupportActionBar().hide();


        if (ContextCompat.checkSelfPermission(BasePage.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(BasePage.this, new String[] {Manifest.permission.INTERNET }, 1);
        }
        else {
            Toast.makeText(BasePage.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }


        if(findViewById(R.id.ContentFrame)!=null) {

            if (savedInstanceState != null)
                return;
            getSupportFragmentManager().beginTransaction().add(R.id.ContentFrame, new HomeFrag(), null).commit();
        }


        ImageButton Userbutton=findViewById(R.id.UserBtn);
        Userbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame,new UserDetailsFrag(),null).addToBackStack(null).commit();
            }
        });


        ImageButton HomeBtn=findViewById(R.id.homeBtn);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.ContentFrame,new HomeFrag(),null).commit();
            }
        });


        ImageButton Cartbutton=findViewById(R.id.CartBtn);
        Cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(BasePage.this,CartPage.class);
               startActivity(intent);
            }
        });





    }
}