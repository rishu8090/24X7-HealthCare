package com.example.healthcare_app.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text, email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text, product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderPlace(username text, fullname text, address text, contactno text, packagesFees text, date text, time text, amount float, otype text)";
        sqLiteDatabase.execSQL(qry3);

       // String qry4 = "create table bookAppoint(useranem text, fullname text, address text, contact text, )";
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null,cv);
        db.close();
    }

    public int login(String username, String password){
        int result = 0;
        String str[] = new String[2];  // here this string array is created bcz rawQuery needs args of string array type.
            str[0] = username;
            str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?" ,str);
        if(c.moveToFirst()){  // this case arises when we have already login record of user, or previously registered valid user.
            result = 1;
        }
        return result;  // and if result is 0 , it means we didn't have record of user, ie it means it have to register itself.
    }


    public void addCart(String username, String product, float price, String otype){      // otype is used for cart for what lab tests or medicine
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    public int checkCart(String username, String product){
        /// to check item is previously added or not.
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?" , str);
            if(c.moveToFirst()){
                result = 1;
            }
            db.close();
            return result;
    }

    public void removeCart(String username, String otype){   // otype is used for cart for what lab tests or medicine
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username= ? and otype=?" , str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"₹"+price);
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname,String address, String packagesFees, String contact , String date, String time , String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);           //0
        cv.put("fullname", fullname);          //1
        cv.put("address", address);           //2
        cv.put("packagesFees",packagesFees);  // 3
        cv.put("contactno", contact);         //4
        //cv.put("pincode",pincode);
        cv.put("date", date);              //5
        cv.put("time", time);             //6
        //cv.put("amount", price);
        cv.put("otype", otype);         //7
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderPlace", null, cv);
        db.close();
    }

    public void addAppoint(String username, String name, String address, String fees, String contact, String date, String time,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("addresss" ,address);
        cv.put("fees", fees);
        cv.put("contact", contact);
        cv.put("date",date);
        cv.put("time", time);
        cv.put("otype" , otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderPlace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderPlace where username = ?", str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());   // 1 ful name, 2 address, 3 packagefees, 4 contact , 5 date 6 time 7 lab
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contactno, String date, String time){
        int result = 0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contactno;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderPlace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        db.close();
        return  result;
    }

}
