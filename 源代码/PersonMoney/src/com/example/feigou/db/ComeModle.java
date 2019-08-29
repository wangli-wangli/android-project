package com.example.feigou.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class ComeModle {

	MyDBHelper dbHelper;
	SQLiteDatabase sqlite;
	
	public ComeModle(Context context) {
		dbHelper=new MyDBHelper(context,"jizhang.db",null,2);
		
		try {
			//打开数据库
			sqlite=dbHelper.getWritableDatabase();
		}catch(Exception e) {
			sqlite=dbHelper.getReadableDatabase();
		}
		
		
		
	}
	
	
	//保存数据
	public boolean save(String string,String money,String sStyle,String sFenlei,String strTime,String note) {
		try {
			String insertSQL = "insert into come(inorout,money,style,fenlei,time,note) values(?,?,?,?,?,?)";
			sqlite.execSQL(insertSQL, new Object[] { string, money, sStyle, sFenlei, strTime, note });
			return true;
		} catch (SQLiteException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
