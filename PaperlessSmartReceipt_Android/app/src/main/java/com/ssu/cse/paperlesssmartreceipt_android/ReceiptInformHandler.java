package com.ssu.cse.paperlesssmartreceipt_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by qwsdx on 2017-09-08.
 */

public class ReceiptInformHandler {
    private ArrayList<ReceiptInform> receiptInformArrayList;
    private LinearLayout scrollLinearLayout;

    private Activity activity;
    private DBHelper dbHelper;


    public ReceiptInformHandler(Activity activity) {

        this.activity = activity;
        scrollLinearLayout = (LinearLayout)activity.findViewById(R.id.scrollLin);
        dbHelper = new DBHelper(activity.getApplicationContext());
        receiptInformArrayList = dbHelper.getDB(dbHelper.getWritableDatabase());


        for(int i = 0 ; i < receiptInformArrayList.size(); i++) {
            addMinReceiptLayout(receiptInformArrayList.get(i));
        }
        if (receiptInformArrayList.size() == 0) {
            showNullLayout();
        }
    }

    public void addReceiptInform(String stringTemp) {
        ReceiptInform receiptInform = new ReceiptInform(stringTemp);
        dbHelper.insertInform(dbHelper.getWritableDatabase(), receiptInform);
        //addReceiptLayout(receiptInform);
        addMinReceiptLayout(receiptInform);
        receiptInformArrayList.add(receiptInform);
    }

    private void addMinReceiptLayout(final ReceiptInform receiptInform) {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.min_receipt_layout, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        linearLayoutTemp.setLayoutParams(layoutParams);

        TextView minDateText = (TextView)linearLayoutTemp.findViewById(R.id.minDateText);
        minDateText.setText(receiptInform.getDate());
        TextView minStoreNameText = (TextView)linearLayoutTemp.findViewById(R.id.minStoreNameText);
        minStoreNameText.setText(receiptInform.getStoreName());
        TextView minSumText = (TextView)linearLayoutTemp.findViewById(R.id.minSumText);
        int totalPrice = 0;
        ArrayList<ReceiptInform.ProductInform> productInformArrayListTemp = receiptInform.getProductInformArrayList();
        for(int i = 0; i < productInformArrayListTemp.size(); i++) {
            ReceiptInform.ProductInform productInformTemp = productInformArrayListTemp.get(i);
            totalPrice += productInformTemp.getUnitPrice() * productInformTemp.getQuantity();
        }
        minSumText.setText(Integer.toString(totalPrice) + "원");
        linearLayoutTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent receiptIntent = new Intent(activity.getApplicationContext(), ReceiptActivity.class);
                    receiptIntent.putExtra("receiptInform", (Serializable) receiptInform);
                    activity.startActivity(receiptIntent);
                }catch (Exception e) {
                    Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        scrollLinearLayout.addView(linearLayoutTemp, 0);
    }

    public void showDateSearchLayout(String stringTemp) {
        scrollLinearLayout.removeAllViews();
        boolean layoutCheck = false;
        for(int i = 0; i < receiptInformArrayList.size(); i++) {
            String searchTemp = receiptInformArrayList.get(i).getDate();
            if(searchTemp.equals(stringTemp)) {
                layoutCheck = true;
                addMinReceiptLayout(receiptInformArrayList.get(i));
            }
        }

        if(!layoutCheck) {
            showNullLayout();
        }
    }

    public void showAllLayout() {
        scrollLinearLayout.removeAllViews();
        for(int i = 0; i < receiptInformArrayList.size(); i++) {
            addMinReceiptLayout(receiptInformArrayList.get(i));
        }
    }

    private void showNullLayout() {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.null_layout, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 20, 20, 20);

        linearLayoutTemp.setLayoutParams(layoutParams);
        scrollLinearLayout.addView(linearLayoutTemp);
    }
}
