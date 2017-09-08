package com.ssu.cse.paperlesssmartreceipt_android;

/**
 * Created by eunhye Lee on 2017-08-22.
 */

public class ReceiptInform
{
    private String storeName;   //매장명
    private String repreName;   //대표자
    private String corpRegistNumber;    //사업자번호
    private String address; //주소
    private String date;    //매출일
    private String receiptNumber;   //영수증
    private String productName;   //상품명
    private String unitPrice;    //단가
    private String quantity; //수량
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

    public void setCorpRegistNumber(String corpRegistNumber) {
        this.corpRegistNumber = corpRegistNumber;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
