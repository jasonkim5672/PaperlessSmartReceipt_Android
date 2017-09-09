package com.ssu.cse.paperlesssmartreceipt_android;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
    private SQLiteDatabase sqLiteDatabase;


    public ReceiptInformHandler(Activity activity) {

        this.activity = activity;
        scrollLinearLayout = (LinearLayout)activity.findViewById(R.id.scrollLin);
        dbHelper = new DBHelper(activity.getApplicationContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        receiptInformArrayList = dbHelper.getDB(sqLiteDatabase);


        for(int i = 0; i < receiptInformArrayList.size(); i++) {
            addLayout(receiptInformArrayList.get(i));
        }

        //testLayout(); // 테스트 코드 추후 삭제해야함
    }

    private void testLayout() {
        for(int i = 0; i < 10; i++) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout linearLayoutTemp = (LinearLayout) inflater.inflate(R.layout.receipt_layout, null);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20, 20, 20, 20);
            linearLayoutTemp.setLayoutParams(layoutParams);

            TextView storeNameText = (TextView)linearLayoutTemp.findViewById(R.id.storeNameText);
            storeNameText.setText("test" + i);

            scrollLinearLayout.addView(linearLayoutTemp);
        }
    }


    private void addLayout(ReceiptInform receiptInform) {
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
        TextView t = (TextView)linearLayoutTemp.findViewById(R.id.productInformText);
        t.setText(receiptInform.getProductInformString());
        //TextView test = new TextView();
        //TextView storeNameText = (TextView)activity.findViewById(R.id.storeNameText);
        //storeNameText.setText("테스트");
        //
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
        TextView approvalNumberText = (TextView)linearLayoutTemp.findViewById(R.id.approvalNumberText);
        approvalNumberText.setText(Integer.toString(receiptInform.getApprovalNumber())); // int
        TextView approvalDateText = (TextView)linearLayoutTemp.findViewById(R.id.approvalDateText);
        approvalDateText.setText(receiptInform.getApprovalDate());

        scrollLinearLayout.addView(linearLayoutTemp);
    }

    public void addReceiptInform(String stringTemp) {
        ReceiptInform receiptInform = new ReceiptInform(stringTemp);
        addLayout(receiptInform);
        dbHelper.insertInform(sqLiteDatabase, receiptInform);
        receiptInformArrayList.add(receiptInform);
    }

    public void search(String stringTemp) {
        // search
    }
}
