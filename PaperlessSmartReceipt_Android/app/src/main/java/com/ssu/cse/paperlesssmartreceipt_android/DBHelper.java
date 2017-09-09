package com.ssu.cse.paperlesssmartreceipt_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by eunhye Lee on 2017-09-04.
 */

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(Context context)
    {
        super(context, "ReceiptDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS ReceiptTBL (" +
                "storeName CHAR(20) NOT NULL," +
                "repreName CHAR(20) NOT NULL," +
                "corpRegistNumber CHAR(15) NOT NULL," +
                "address CHAR(100) NOT NULL," +
                "date CHAR(20) NOT NULL," +
                "receiptNumber CHAR(20) PRIMARY KEY," +
                "productInformString CHAR(3000) NOT NULL," +
                "extraTax Long NOT NULL," +
                "tax Long NOT NULL," +
                "cardSort CHAR(30)," +
                "cardNumber CHAR(20)," +
                "expDate CAHR(5)," +
                "monthlyPlan CHAR(10)," +
                "approvalNumber Long," +
                "approvalDate CHAR(15));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS ReceiptTBL");
        onCreate(db);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void insertInform(SQLiteDatabase db, ReceiptInform inform)
    {
        db.execSQL("INSERT INTO ReceiptTBL VALUES ('" +
                inform.getStoreName()+"','" +
                inform.getRepreName()+"','" +
                inform.getCorpRegistNumber()+"','" +
                inform.getAddress()+"','" +
                inform.getDate()+"','" +
                inform.getReceiptNumber()+"','" +
                inform.getProductInformString()+"'," +
                inform.getExtraTax()+"," +
                inform.getTax()+",'" +
                inform.getCardSort()+"','" +
                inform.getCardNumber()+"','" +
                inform.getExpDate()+"','" +
                inform.getMonthlyPlan()+"'," +
                inform.getApprovalNumber()+",'" +
                inform.getApprovalDate()+"');" );
        db.close();
    }

    public ArrayList<ReceiptInform> getDB(SQLiteDatabase db)
    {
        Cursor cursor;
        ArrayList<ReceiptInform> data = new ArrayList<ReceiptInform>();

        cursor = db.rawQuery("SELECT * FROM ReceiptTBL;",null);

        while(cursor.moveToNext())
        {
            ReceiptInform temp = new ReceiptInform();
            
            temp.setStoreName(cursor.getString(0));
            temp.setRepreName(cursor.getString(1));
            temp.setCorpRegistNumber(cursor.getString(2));
            temp.setAddress(cursor.getString(3));
            temp.setDate(cursor.getString(4));
            temp.setReceiptNumber(cursor.getString(5));
            temp.setProductInformString(cursor.getString(6));
            temp.setExtraTax(Integer.parseInt(cursor.getString(7)));
            temp.setTax(Integer.parseInt(cursor.getString(8)));
            temp.setCardSort(cursor.getString(9));
            temp.setCardNumber(cursor.getString(10));
            temp.setExpDate(cursor.getString(11));
            temp.setMonthlyPlan(cursor.getString(12));
            temp.setApprovalNumber(Integer.parseInt(cursor.getString(13)));
            temp.setApprovalDate(cursor.getString(14));

            data.add(temp);
        }

        cursor.close();
        db.close();

        return data;
    }

}
