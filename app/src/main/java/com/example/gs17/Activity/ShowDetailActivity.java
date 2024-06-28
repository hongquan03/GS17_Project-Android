package com.example.gs17.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.gs17.Domain.FoodDomain;
import com.example.gs17.Helper.ManagementCart;
import com.example.gs17.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCarBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPrice, starTxt, caloryTxt, timeTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        managementCart=new ManagementCart(this);
        iniView();
        getBundle();
    }
    private void getBundle(){
        object=(FoodDomain)getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOder));
        caloryTxt.setText(object.getCalories()+" Calories");
        starTxt.setText(object.getStar()+ "");
        timeTxt.setText(object.getTime()+ " minutes");
        totalPrice.setText("$"+numberOder*object.getFee());
        //totalPrice.setText("$"+Math.round(numberOder*object.getFee()));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOder=numberOder+1;
                numberOrderTxt.setText(String.valueOf(numberOder));
                totalPrice.setText("$"+numberOder * object.getFee());
                //totalPrice.setText("$"+Math.round(numberOder*object.getFee()));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOder>1){
                    numberOder=numberOder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOder));
                totalPrice.setText("$"+numberOder*object.getFee());
                //totalPrice.setText("$"+Math.round(numberOder*object.getFee()));
            }
        });
        addToCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOder);
                managementCart.insertFood(object);
            }
        });

    }

    private void iniView() {
        addToCarBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberItemTxt);
        plusBtn=findViewById(R.id.plusCardBtn);
        minusBtn=findViewById(R.id.minusCardBtn);
        picFood=findViewById(R.id.foodPic);
        timeTxt=findViewById(R.id.timeTxt);
        totalPrice=findViewById(R.id.totalPriceTxt);
        starTxt=findViewById(R.id.starTxt);
        caloryTxt=findViewById(R.id.VicaloriesTxt);


    }
}