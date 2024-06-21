package com.example.gs17.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gs17.Adapter.CategoryAdapter;
import com.example.gs17.Adapter.RecommendedAdapter;
import com.example.gs17.Domain.CategoryDomain;
import com.example.gs17.Domain.FoodDomain;
import com.example.gs17.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategotyList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    recyclerViewCategoty();
    recyclerViewPopular();


    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni, mozzaarella chesse, fresh oregano, ground black pepper, pizza sauce", 13.0, 10, 20, 1000));
        foodlist.add(new FoodDomain("Chesse Burger", "burger", "slices pepperoni, mozzaarella chesse, fresh oregano, ground black pepper, pizza sauce", 13.20, 4, 18, 1500));
        foodlist.add(new FoodDomain("Vagetable pizza", "pizza3", "slices pepperoni, mozzaarella chesse, fresh oregano, ground black pepper, pizza sauce", 11.0, 3, 16, 800));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategoty() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList=findViewById(R.id.view1);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategotyList.setAdapter(adapter);
    }
}