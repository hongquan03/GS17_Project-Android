package com.example.gs17.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gs17.R;

public class History extends AppCompatActivity {
    TextView ten, sodienthoai, diachi, tongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ TextView trong layout activity_history
        ten = findViewById(R.id.ten);
        sodienthoai = findViewById(R.id.sodienthoai);
        diachi = findViewById(R.id.diachi);
        tongTien = findViewById(R.id.TongTien);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            String totalText = intent.getStringExtra("total");
            String tenText = intent.getStringExtra("ten");
            String diachiText = intent.getStringExtra("diachi");
            String sodienthoaiText = intent.getStringExtra("sodienthoai");

            // Hiển thị dữ liệu lên các TextView
            tongTien.setText(totalText);
            ten.setText(tenText);
            sodienthoai.setText(sodienthoaiText);
            diachi.setText(diachiText);
        }
    }
}

