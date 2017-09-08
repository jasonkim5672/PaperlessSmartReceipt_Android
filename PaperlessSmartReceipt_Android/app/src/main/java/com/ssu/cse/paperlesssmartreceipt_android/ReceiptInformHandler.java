package com.ssu.cse.paperlesssmartreceipt_android;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

    }


    private void addLayout(ReceiptInform receiptInform) {
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout linearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.receipt_layout, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        linearLayoutTemp.setLayoutParams(layoutParams);

        TextView storeNameText = (TextView)activity.findViewById(R.id.storeNameText);
        storeNameText.setText(receiptInform.getStoreName());
        TextView repreNameText = (TextView)activity.findViewById(R.id.repreNameText);
        repreNameText.setText(receiptInform.getRepreName());
        TextView corpRegistNumberText = (TextView)activity.findViewById(R.id.corpRegistNumberText);
        corpRegistNumberText.setText(receiptInform.getCorpRegistNumber());
        TextView addressText = (TextView)activity.findViewById(R.id.addressText);
        addressText.setText(receiptInform.getAddress());
        TextView dateText = (TextView)activity.findViewById(R.id.dateText);
        dateText.setText(receiptInform.getDate());
        TextView receiptNumberText = (TextView)activity.findViewById(R.id.receiptNumberText);
        receiptNumberText.setText(receiptInform.getReceiptNumber());
        // 상품목록
        TextView t = (TextView)activity.findViewById(R.id.productInformText);
        t.setText(receiptInform.getProductInformString());
        //TextView test = new TextView();
        //TextView storeNameText = (TextView)activity.findViewById(R.id.storeNameText);
        //storeNameText.setText("테스트");
        //
        TextView extraTaxText = (TextView)activity.findViewById(R.id.extraTaxText);
        extraTaxText.setText(receiptInform.getExtraTax()); // int
        TextView taxText = (TextView)activity.findViewById(R.id.taxText);
        taxText.setText(receiptInform.getTax()); // int
        TextView cardSortText = (TextView)activity.findViewById(R.id.cardSortText);
        cardSortText.setText(receiptInform.getCardSort());
        TextView cardNumberText = (TextView)activity.findViewById(R.id.cardNumberText);
        cardNumberText.setText(receiptInform.getCardNumber());
        TextView expDateText = (TextView)activity.findViewById(R.id.expDateText);
        expDateText.setText(receiptInform.getExpDate());
        TextView monthlyPlanText = (TextView)activity.findViewById(R.id.monthlyPlanText);
        monthlyPlanText.setText(receiptInform.getMonthlyPlan());
        TextView approvalNumberText = (TextView)activity.findViewById(R.id.approvalNumberText);
        approvalNumberText.setText(receiptInform.getApprovalNumber()); // int
        TextView approvalDateText = (TextView)activity.findViewById(R.id.approvalDateText);
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
