package com.example.dressshop;

public class CartData {

    String DressCode;
    String DressImage;
    String DressName;
    float DressPrice;
    CartData(String Dresscode,String DressName,String DressImage,float DressPrice)
    {
        this.DressCode=Dresscode;
        this.DressName=DressName;
        this.DressImage=DressImage;
        this.DressPrice=DressPrice;

    }
}
