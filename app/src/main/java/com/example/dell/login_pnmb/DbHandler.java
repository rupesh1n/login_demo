package com.example.dell.login_pnmb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    private static final String Db_Name = "users";
    private static final int Db_Version = 1;
    private static final String Table_Name = "user";
    private static final String User_id = "id";
    private static final String User_name = "name";
    private static final String User_password = "password";

    public DbHandler(Context context) {
        super(context, Db_Name, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY,name TEXT,password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void addUser(User usr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User_name, usr.getName());
        cv.put(User_password, usr.getPassword());
        db.insert(Table_Name, null, cv);
        db.close();
    }

    public int checkUser(User us) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT id FROM user WHERE name=? AND password=?", new String[]{us.getName(), us.getPassword()});
        if (cursor.getCount() <= 0) {
            return -1;
        }
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        cursor.close();
        return id;
    }

    public void deleteUsers(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Table_Name,null,null);
        db.close();
    }
}
