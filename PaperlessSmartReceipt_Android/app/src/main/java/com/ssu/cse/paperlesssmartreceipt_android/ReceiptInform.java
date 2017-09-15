package com.ssu.cse.paperlesssmartreceipt_android;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by eunhye Lee on 2017-08-22.
 */

public class ReceiptInform implements Serializable
{
    public class ProductInform implements Serializable {
        private String productName;   //상품명
        private int unitPrice;    //단가
        private int quantity; //수량

        String stringData; // 파싱전 string

        public ProductInform(String stringData) {
            this.stringData = stringData;

            parseString();
        }

        private void parseString() {
            String[] stringTemp = stringData.split("#");

            productName = stringTemp[0];
            unitPrice = Integer.parseInt(stringTemp[1]);
            quantity = Integer.parseInt(stringTemp[2]);
        }

        public String getProductName() {
            return productName;
        }
        public int getUnitPrice() {
            return unitPrice;
        }
        public int getQuantity() { return quantity; }
    }

    private String stringData; // 파싱전 string

    private String storeName;   //매장명
    private String repreName;   //대표자
    private String corpRegistNumber;    //사업자번호
    private String address; //주소
    private String date;    //매출일
    private String receiptNumber;   //영수증

    private String productInformString;
    private ArrayList<ProductInform> productInformArrayList;

    //금액과 합계금액은 unitPrice * quantity 로 계산
    private int extraTax;   //과세물품가액
    private int tax;    //부가세

    //신용승인정보
    private String cardSort;    //카드종류
    private String cardNumber;  //카드번호, 암호처리
    private String expDate; //유효기간, 암호처리
    private String monthlyPlan;     //할부개월
    //판매금액 == 과세물품가액, 부가세 == 부가세, 승인금액 == 합계금액
    private int approvalNumber;  //승인번호
    private String approvalDate;    //승인일시

    public ReceiptInform(String stringData) {
        this.stringData = stringData;

        parseString();
    }
    public ReceiptInform() {}

    private void parseString() {
        // 큰틀은 !로 구분, 상품들은 $로 구분, 상품상세정보는 #로 구분

        String[] stringTemp = stringData.split("!");
        // 0 ~ 14, 총 15개의 변수
        storeName = stringTemp[0];
        repreName = stringTemp[1];
        corpRegistNumber = stringTemp[2];
        address = stringTemp[3];
        date = stringTemp[4];
        receiptNumber = stringTemp[5];

        // 상품목록 구하기
        setProductInformString(stringTemp[6]);

        extraTax = Integer.parseInt(stringTemp[7]);
        tax = Integer.parseInt(stringTemp[8]);
        cardSort = stringTemp[9];
        cardNumber = stringTemp[10];
        expDate = stringTemp[11];
        monthlyPlan = stringTemp[12];
        approvalNumber = Integer.parseInt(stringTemp[13]);
        approvalDate = stringTemp[14];
    }




    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRepreName() {
        return repreName;
    }

    public void setRepreName(String repreName) {
        this.repreName = repreName;
    }

    public String getCorpRegistNumber() {
        return corpRegistNumber;
    }

    public void setCorpRegistNumber(String corpRegistNumber) { this.corpRegistNumber = corpRegistNumber; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
    

    public ArrayList<ProductInform> getProductInformArrayList() {
        return productInformArrayList;
    }

    public String getProductInformString() { return productInformString; }
    public void setProductInformString(String productInformString) {
        this.productInformString = productInformString;

        productInformArrayList = new ArrayList<ProductInform>();
        String[] productStringTemp = productInformString.split("%");
        for(int i = 0; i < productStringTemp.length; i++) {
            productInformArrayList.add(new ProductInform(productStringTemp[i]));
        }
    }


    public int getExtraTax() {
        return extraTax;
    }

    public void setExtraTax(int extraTax) {
        this.extraTax = extraTax;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getCardSort() {
        return cardSort;
    }

    public void setCardSort(String cardSort) {
        this.cardSort = cardSort;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(String monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public int getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(int approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }
}
