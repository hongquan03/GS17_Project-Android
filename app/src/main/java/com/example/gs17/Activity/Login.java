package com.example.gs17.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gs17.Database.DBHelper;
import com.example.gs17.R;

public class Login extends AppCompatActivity {

    Button btn_Login;
    EditText edt_Username, edt_Password;
    TextView txv_Sign_up;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_Login = findViewById(R.id.btn_SignUp);
        edt_Username = findViewById(R.id.edt_Username);
        edt_Password = findViewById(R.id.edt_Password);
        txv_Sign_up = findViewById(R.id.txv_Sign_up);
        DB = new DBHelper(this);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_Username.getText().toString();
                String  password = edt_Password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
                    Toast.makeText(Login.this, "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
                else {
                    Boolean ktramatkhau = DB.ktramatkhau(user, password);
                    if (ktramatkhau == true) {
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "Tài khoản mật khẩu không đúng", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    public void Sign_up(View view) {

        startActivity(new Intent(Login.this, SignUp.class));
    }
}