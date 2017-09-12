package com.ssu.cse.paperlesssmartreceipt_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            addReceiptLayout(receiptInformArrayList.get(i));
        }
    }

    public void addReceiptInform(String stringTemp) {
        ReceiptInform receiptInform = new ReceiptInform(stringTemp);
        dbHelper.insertInform(dbHelper.getWritableDatabase(), receiptInform);
        addReceiptLayout(receiptInform);
        receiptInformArrayList.add(receiptInform);
    }

    private void addReceiptLayout(ReceiptInform receiptInform) {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout linearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.receipt_layout, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        linearLayoutTemp.setLayoutParams(layoutParams);

        TextView storeNameText = (TextView)linearLayoutTemp.findViewById(R.id.storeNameText);
        storeNameText.setText(receiptInform.getStoreName());
        TextView repreNameText = (TextView)linearLayoutTemp.findViewById(R.id.repreNameText);
        repreNameText.setText(receiptInform.getRepreName());
        TextView corpRegistNumberText = (TextView)linearLayoutTemp.findViewById(R.id.corpRegistNumberText);
        corpRegistNumberText.setText(receiptInform.getCorpRegistNumber());
        TextView addressText = (TextView)linearLayoutTemp.findViewById(R.id.addressText);
        addressText.setText(receiptInform.getAddress());
        TextView dateText = (TextView)linearLayoutTemp.findViewById(R.id.dateText);
        dateText.setText(receiptInform.getDate());
        TextView receiptNumberText = (TextView)linearLayoutTemp.findViewById(R.id.receiptNumberText);
        receiptNumberText.setText(receiptInform.getReceiptNumber());

        // 상품목록
        LinearLayout productInformLin = (LinearLayout)linearLayoutTemp.findViewById(R.id.productInformLin);
        ArrayList<ReceiptInform.ProductInform> productInformArrayListTemp = receiptInform.getProductInformArrayList();

        int totalPrice = 0;

        for(int i = 0; i < productInformArrayListTemp.size(); i++) {

            ReceiptInform.ProductInform productInformTemp = productInformArrayListTemp.get(i);

            LayoutInflater pInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout pLinearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.product_layout, null);

            LinearLayout.LayoutParams pLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //pLayoutParams.setMargins(0, 0, 0, 0);
            pLinearLayoutTemp.setLayoutParams(layoutParams);

            TextView productNameText = (TextView)pLinearLayoutTemp.findViewById(R.id.productNameText);
            productNameText.setText(productInformTemp.getProductName());
            TextView unitPriceText = (TextView)pLinearLayoutTemp.findViewById(R.id.unitPriceText);
            unitPriceText.setText(Integer.toString(productInformTemp.getUnitPrice()));
            TextView quantityText = (TextView)pLinearLayoutTemp.findViewById(R.id.quantityText);
            quantityText.setText(Integer.toString(productInformTemp.getQuantity()));

            totalPrice += productInformTemp.getUnitPrice() * productInformTemp.getQuantity();

            productInformLin.addView(pLinearLayoutTemp);
        }

        TextView sumText = (TextView)linearLayoutTemp.findViewById(R.id.sumText);
        sumText.setText(Integer.toString(totalPrice));
        TextView extraTaxText = (TextView)linearLayoutTemp.findViewById(R.id.extraTaxText);
        extraTaxText.setText(Integer.toString(receiptInform.getExtraTax())); // int
        TextView taxText = (TextView)linearLayoutTemp.findViewById(R.id.taxText);
        taxText.setText(Integer.toString(receiptInform.getTax())); // int
        TextView cardSortText = (TextView)linearLayoutTemp.findViewById(R.id.cardSortText);
        cardSortText.setText(receiptInform.getCardSort());
        TextView cardNumberText = (TextView)linearLayoutTemp.findViewById(R.id.cardNumberText);
        cardNumberText.setText(receiptInform.getCardNumber());
        TextView expDateText = (TextView)linearLayoutTemp.findViewById(R.id.expDateText);
        expDateText.setText(receiptInform.getExpDate());
        TextView monthlyPlanText = (TextView)linearLayoutTemp.findViewById(R.id.monthlyPlanText);
        monthlyPlanText.setText(receiptInform.getMonthlyPlan());

        TextView sellPriceText = (TextView)linearLayoutTemp.findViewById(R.id.sellPriceText);
        sellPriceText.setText(Integer.toString(receiptInform.getExtraTax()));
        TextView approvalPriceText = (TextView)linearLayoutTemp.findViewById(R.id.approvalPriceText);
        approvalPriceText.setText(Integer.toString(totalPrice));

        TextView approvalNumberText = (TextView)linearLayoutTemp.findViewById(R.id.approvalNumberText);
        approvalNumberText.setText(Integer.toString(receiptInform.getApprovalNumber())); // int
        TextView approvalDateText = (TextView)linearLayoutTemp.findViewById(R.id.approvalDateText);
        approvalDateText.setText(receiptInform.getApprovalDate());

        scrollLinearLayout.addView(linearLayoutTemp,0);
    }


    public void showDateSearchLayout(String stringTemp) {
        scrollLinearLayout.removeAllViews();
        for(int i = 0; i < receiptInformArrayList.size(); i++) {
            String searchTemp = receiptInformArrayList.get(i).getDate();
            if(searchTemp.equals(stringTemp)) {
                addReceiptLayout(receiptInformArrayList.get(i));
            }
        }
    }

    public void showAllLayout() {
        scrollLinearLayout.removeAllViews();
        for(int i = 0; i < receiptInformArrayList.size(); i++) {
            addReceiptLayout(receiptInformArrayList.get(i));
        }
    }
}
