package com.example.feigou.test;

import com.example.feigou.db.MyDBHelper;
import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import android.widget.TextView;
import android.widget.Toast;

public class ListComeActivity extends Activity {

	private Button btnShowIn,btnShowOut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcome);
		btnShowIn=(Button) findViewById(R.id.btnlistin);
		btnShowOut=(Button) findViewById(R.id.btnlistout);
		//向TableLayout添加数据库数据
		addDates();
		
		//为显示所有收入按钮添加事件
		btnShowIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(ListComeActivity.this, "本功能还未实现",Toast.LENGTH_SHORT ).show();
				Intent intent=new Intent(ListComeActivity.this,ShowAllIncomeActivity.class);
				startActivity(intent);
			}
		});
		
		//为显示所有收入按钮添加事件
				btnShowOut.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//Toast.makeText(ListComeActivity.this, "本功能还未实现",Toast.LENGTH_SHORT ).show();
						Intent intent=new Intent(ListComeActivity.this,ShowAllOutActivity.class);
						startActivity(intent);
					}
				});
	}
	
	public void addDates() {
		MyDBHelper database = new MyDBHelper(ListComeActivity.this,
				"jizhang.db", null, 2);// 这段代码放到Activity类中才用this
		SQLiteDatabase db = database.getReadableDatabase();
		TableLayout table = (TableLayout) findViewById(R.id.tlListCome);
		
		//只显示支出收入
		Cursor c = db.rawQuery("select * from come where inorout=? or inorout=?",
				new String[]{"income","outcome"});
		//显示全部的情況
		
		if (c.moveToFirst()) {

			do {
				Log.i("c.getcount()=", c.getCount() + "");

				String inorout = c.getString(c.getColumnIndex("inorout"));
				String money = c.getString(c.getColumnIndex("money"));
				String style = c.getString(c.getColumnIndex("style"));
				String fenlei = c.getString(c.getColumnIndex("fenlei"));
				String time = c.getString(c.getColumnIndex("time"));
				String note = c.getString(c.getColumnIndex("note"));
				Log.i("inorout:", inorout);
				Log.i("fenlei:", fenlei);
				Log.i("time", time);
				TableRow tablerow = new TableRow(ListComeActivity.this);
				

				TextView tvText1 = new TextView(ListComeActivity.this);
				//汉化
				if(inorout.equalsIgnoreCase("income")){
					inorout="收入";
				}else if(inorout.equalsIgnoreCase("outcome")){
					inorout="支出";
				}else{
					inorout="預算";
					
				}
				tvText1.setText(inorout);
				
				TextView tvText2 = new TextView(ListComeActivity.this);
				tvText2.setText(money);
				TextView tvText3 = new TextView(ListComeActivity.this);
				tvText3.setText(style);
				TextView tvText4 = new TextView(ListComeActivity.this);
				tvText4.setText(fenlei);
				TextView tvText5 = new TextView(ListComeActivity.this);
				tvText5.setText(time);
				TextView tvText6 = new TextView(ListComeActivity.this);
				tvText6.setText(note);
				tablerow.addView(tvText1);
				tablerow.addView(tvText2);
				tablerow.addView(tvText3);
				tablerow.addView(tvText4);
				tablerow.addView(tvText5);
				tablerow.addView(tvText6);
				table.addView(tablerow, new TableLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			} while (c.moveToNext());

		}
	}
}
