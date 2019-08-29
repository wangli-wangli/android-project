package com.example.feigou.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME="jizhang.db";//数据库名
	private static final int version=2;//数据库版本
	
	public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	
	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	//当MyDBHelper对象被创建时，该方法被调用，该方法主要是用来做一些初始化数据，比如表的创建
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("sqlite", "****MyDBHelper's onCreate()....");
		//创建表
		String createSQL="create table come(inorout varcher(20) not null,money varchar(20) not null,"
				+ "style varchar(20) not null,fenlei vatchar(20),time vatchar(20) not null,"
				+ "note varchar(50) not null) ";
        db.execSQL(createSQL);
	}

	//当数据版本修改时，该方法会被调用，该方法主要是完成一些重构的操作，比如删除原来的表，创建新表
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSQL="drop table come";
		db.execSQL(dropSQL);
		onCreate(db);
		Log.d("sqlite", "** MyDBHelper onUpgrade()...");
		

	}

}
