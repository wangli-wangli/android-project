package com.example.feigou.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME="jizhang.db";//���ݿ���
	private static final int version=2;//���ݿ�汾
	
	public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	
	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	//��MyDBHelper���󱻴���ʱ���÷��������ã��÷�����Ҫ��������һЩ��ʼ�����ݣ������Ĵ���
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("sqlite", "****MyDBHelper's onCreate()....");
		//������
		String createSQL="create table come(inorout varcher(20) not null,money varchar(20) not null,"
				+ "style varchar(20) not null,fenlei vatchar(20),time vatchar(20) not null,"
				+ "note varchar(50) not null) ";
        db.execSQL(createSQL);
	}

	//�����ݰ汾�޸�ʱ���÷����ᱻ���ã��÷�����Ҫ�����һЩ�ع��Ĳ���������ɾ��ԭ���ı������±�
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropSQL="drop table come";
		db.execSQL(dropSQL);
		onCreate(db);
		Log.d("sqlite", "** MyDBHelper onUpgrade()...");
		

	}

}
