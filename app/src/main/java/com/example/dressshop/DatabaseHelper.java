package com.example.dressshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "DressShop" ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Dress( DressCode TEXT, DressName Text,DressImage Text,DressPrice float)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Dress");
    }

    public  Boolean InsertToCart(String Code,String Name,String image,float  DressPrice)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("DressCode",Code);
        contentValues.put("DressName",Name);
        contentValues.put("DressImage",image);
        contentValues.put("DressPrice",DressPrice);


        long result= db.insert("Dress",null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor getdataFromCart()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Dress",null);
        return cursor;


    }
    public void  DeleteFromCart(String Code)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.rawQuery("Delete from Dress where DressCode='"+Code+"'",null);



    }




}
