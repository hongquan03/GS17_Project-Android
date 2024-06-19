package com.example.gs17.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gs17.Database.DBHelper;
import com.example.gs17.R;

public class SignUp extends AppCompatActivity {
    Button btn_back, btn_SginUp;
    EditText edt_Username_sgp, edt_Password1_sgp, edt_Password2_sgp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_SginUp = findViewById(R.id.btn_SginUp);
        edt_Username_sgp = findViewById(R.id.edt_Username_sgp);
        edt_Password1_sgp = findViewById(R.id.edt_Password1_sgp);
        edt_Password2_sgp = findViewById(R.id.edt_Password2_sgp);
        btn_back = findViewById(R.id.btn_back);
        DB = new DBHelper(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        btn_SginUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_Username_sgp.getText().toString();
                String  pass = edt_Password1_sgp.getText().toString();
                String  repass = edt_Password2_sgp.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(SignUp.this, "Không được để trống", Toast.LENGTH_LONG).show();
                else {
                    // Ktra mật khẩu xem trùng khớp k
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.ktratendangnhap(user);
                        if(checkuser==false) {
                            Boolean insert = DB.inserDB(user, pass);
                            // Ktra đki thành công k
                            if (insert == true) {
                                Toast.makeText(SignUp.this, "Bạn đa đăng ký thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Đăng ki thất bại", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(SignUp.this, "Người dùng đã tồn tại", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }



}