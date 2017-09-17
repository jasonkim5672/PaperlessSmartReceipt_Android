package com.ssu.cse.paperlesssmartreceipt_android;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_layout);

        ReceiptInform receiptInform = (ReceiptInform)getIntent().getSerializableExtra("receiptInform");

        TextView storeNameText = (TextView)findViewById(R.id.storeNameText);
        storeNameText.setText(receiptInform.getStoreName());
        TextView repreNameText = (TextView)findViewById(R.id.repreNameText);
        repreNameText.setText(receiptInform.getRepreName());
        TextView corpRegistNumberText = (TextView)findViewById(R.id.corpRegistNumberText);
        corpRegistNumberText.setText(receiptInform.getCorpRegistNumber());
        TextView addressText = (TextView)findViewById(R.id.addressText);
        addressText.setText(receiptInform.getAddress());
        TextView dateText = (TextView)findViewById(R.id.dateText);
        dateText.setText(receiptInform.getDate());
        TextView receiptNumberText = (TextView)findViewById(R.id.receiptNumberText);
        receiptNumberText.setText(receiptInform.getReceiptNumber());

        // 상품목록
        LinearLayout productInformLin = (LinearLayout)findViewById(R.id.productInformLin);
        ArrayList<ReceiptInform.ProductInform> productInformArrayListTemp = receiptInform.getProductInformArrayList();

        int totalPrice = 0;

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        for(int i = 0; i < productInformArrayListTemp.size(); i++) {
            ReceiptInform.ProductInform productInformTemp = productInformArrayListTemp.get(i);

            LayoutInflater pInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout pLinearLayoutTemp = (LinearLayout)inflater.inflate(R.layout.product_layout, null);

            LinearLayout.LayoutParams pLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //pLayoutParams.setMargins(0, 0, 0, 0);
            pLinearLayoutTemp.setLayoutParams(layoutParams);

            TextView productNameText = (TextView)pLinearLayoutTemp.findViewById(R.id.productNameText);
            productNameText.setText(productInformTemp.getProductName());
            TextView unitPriceText = (TextView)pLinearLayoutTemp.findViewById(R.id.unitPriceText);
            unitPriceText.setText(Integer.toString(productInformTemp.getUnitPrice()) + "원");
            TextView quantityText = (TextView)pLinearLayoutTemp.findViewById(R.id.quantityText);
            quantityText.setText(Integer.toString(productInformTemp.getQuantity()));

            totalPrice += productInformTemp.getUnitPrice() * productInformTemp.getQuantity();

            productInformLin.addView(pLinearLayoutTemp);
        }

        TextView sumText = (TextView)findViewById(R.id.sumText);
        sumText.setText(Integer.toString(totalPrice) + "원");
        TextView extraTaxText = (TextView)findViewById(R.id.extraTaxText);
        extraTaxText.setText(Integer.toString(receiptInform.getExtraTax()) + "원"); // int
        TextView taxText = (TextView)findViewById(R.id.taxText);
        taxText.setText(Integer.toString(receiptInform.getTax()) + "원"); // int
        TextView cardSortText = (TextView)findViewById(R.id.cardSortText);
        cardSortText.setText(receiptInform.getCardSort());
        TextView cardNumberText = (TextView)findViewById(R.id.cardNumberText);
        cardNumberText.setText(receiptInform.getCardNumber());
        TextView expDateText = (TextView)findViewById(R.id.expDateText);
        expDateText.setText(receiptInform.getExpDate());
        TextView monthlyPlanText = (TextView)findViewById(R.id.monthlyPlanText);
        monthlyPlanText.setText(receiptInform.getMonthlyPlan());

        TextView sellPriceText = (TextView)findViewById(R.id.sellPriceText);
        sellPriceText.setText(Integer.toString(receiptInform.getExtraTax()) + "원");
        TextView approvalPriceText = (TextView)findViewById(R.id.approvalPriceText);
        approvalPriceText.setText(Integer.toString(totalPrice) + "원");

        TextView approvalNumberText = (TextView)findViewById(R.id.approvalNumberText);
        approvalNumberText.setText(Integer.toString(receiptInform.getApprovalNumber())); // int
        TextView approvalDateText = (TextView)findViewById(R.id.approvalDateText);
        approvalDateText.setText(receiptInform.getApprovalDate());

    }
}
