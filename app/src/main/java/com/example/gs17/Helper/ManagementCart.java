package com.example.gs17.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.gs17.Domain.FoodDomain;
import com.example.gs17.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    /**
     * Thêm món ăn vào giỏ hàng.
     *
     * @param item món ăn cần thêm vào giỏ hàng
     */
    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
    }

    /**
     * Lấy danh sách món ăn trong giỏ hàng từ TinyDB.
     *
     * @return ArrayList chứa danh sách món ăn trong giỏ hàng
     */
    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    /**
     * Giảm số lượng món ăn trong giỏ hàng.
     *
     * @param listfood                   danh sách món ăn trong giỏ hàng
     * @param position                   vị trí của món ăn cần giảm số lượng
     * @param changeNumberItemsListener listener để thông báo khi số lượng thay đổi
     */
    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    /**
     * Tăng số lượng món ăn trong giỏ hàng.
     *
     * @param listfood                   danh sách món ăn trong giỏ hàng
     * @param position                   vị trí của món ăn cần tăng số lượng
     * @param changeNumberItemsListener listener để thông báo khi số lượng thay đổi
     */
    public void plusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    /**
     * Tính tổng chi phí của các món ăn trong giỏ hàng.
     *
     * @return tổng chi phí của các món ăn trong giỏ hàng
     */
    public Double getTotalFee() {
        ArrayList<FoodDomain> listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}
