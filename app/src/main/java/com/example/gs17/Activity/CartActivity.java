package com.example.gs17.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gs17.Adapter.CartListAdapter;
import com.example.gs17.Helper.ManagementCart;
import com.example.gs17.Interface.ChangeNumberItemsListener;
import com.example.gs17.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CartActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;
private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
private double tax;
private ScrollView scrollerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        managementCart = new ManagementCart(this);
        initView();
        initList();
        bottomNavigation();
        calculateCard();
        bottomSheet();

    }

    private void bottomSheet() {
        ConstraintLayout showBottomSheet= findViewById(R.id.show_bottom_sheep);
        showBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickOpenBottomSheetDialog();
            }
        });
    }

    private void clickOpenBottomSheetDialog() {
        // Lấy giá trị từ totalTxt
        String totalText = totalTxt.getText().toString();

        // Inflate layout cho dialog bottom sheet
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheep, null);

        // Khởi tạo BottomSheetDialog và hiển thị
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();

        // Ánh xạ và thiết lập giá trị cho TextView TongTien
        TextView TongTien = viewDialog.findViewById(R.id.TongTien);
        TongTien.setText(totalText);

        // Ánh xạ các EditText
        EditText Ten = viewDialog.findViewById(R.id.ten);
        EditText Diachi = viewDialog.findViewById(R.id.diachi);
        EditText SoDienThoai = viewDialog.findViewById(R.id.sodienthoai);

        // Thiết lập sự kiện khi click vào cst_xacnhan
        ConstraintLayout cst_xacnhan = viewDialog.findViewById(R.id.cst_xacnhan);
        cst_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String tenText = Ten.getText().toString();
                String diachiText = Diachi.getText().toString();
                String sodienthoaiText = SoDienThoai.getText().toString();

                // Hiển thị AlertDialog để xác nhận mua hàng
                new AlertDialog.Builder(CartActivity.this)
                        .setTitle("Xác nhận mua hàng")
                        .setMessage("Bạn chắc chắn muốn mua hàng?")
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Điều hướng tới activity History khi người dùng đồng ý
                                Intent intent = new Intent(getApplicationContext(), History.class);
                                // Đưa dữ liệu vào Intent (nếu cần)
                                intent.putExtra("total", totalText);
                                intent.putExtra("ten", tenText);
                                intent.putExtra("diachi", diachiText);
                                intent.putExtra("sodienthoai", sodienthoaiText);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Đóng dialog nếu người dùng hủy
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }





    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollerView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollerView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax=0.00; //bạn có thể đổi mục này lấy giá thuế
        double delivery=10; //bạn có thể đổi mặt hàng này bạn cần giá giao hàng
        tax=Math.round((managementCart.getTotalFee()*percentTax)*100.0)/100.0;
        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal=Math.round(managementCart.getTotalFee()*100.0)/100.0;
        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt= findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollerView=findViewById(R.id.scrollerView);
        emptyTxt= findViewById(R.id.emptyTxt);
    }
}