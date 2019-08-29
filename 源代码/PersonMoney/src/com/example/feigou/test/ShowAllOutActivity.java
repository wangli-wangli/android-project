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

public class ShowAllOutActivity extends Activity {

	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listcome_showoutcome);
		btn = (Button) findViewById(R.id.btnlistin);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ShowAllOutActivity.this,
						ListComeActivity.class);
				startActivity(intent);
				ShowAllOutActivity.this.finish();

			}
		});
		// 向TableLayout中添加数据库数据
		addDates();
	}
	
	public void addDates() {
		MyDBHelper database = new MyDBHelper(ShowAllOutActivity.this,
				"jizhang.db", null, 2);// 这段代码放到Activity类中才用this
		SQLiteDatabase db = database.getReadableDatabase();
		TableLayout table = (TableLayout) findViewById(R.id.tlListCome);
		
		//只显示支出收入
		Cursor c = db.rawQuery("select * from come where inorout=? ",
				new String[]{"outcome"});
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
				TableRow tablerow = new TableRow(ShowAllOutActivity.this);
				

				TextView tvText1 = new TextView(ShowAllOutActivity.this);
				//汉化
				if(inorout.equalsIgnoreCase("income")){
					inorout="收入";
				}else if(inorout.equalsIgnoreCase("outcome")){
					inorout="支出";
				}else{
					inorout="预算";
					
				}
				tvText1.setText(inorout);
				
				TextView tvText2 = new TextView(ShowAllOutActivity.this);
				tvText2.setText(money);
				TextView tvText3 = new TextView(ShowAllOutActivity.this);
				tvText3.setText(style);
				TextView tvText4 = new TextView(ShowAllOutActivity.this);
				tvText4.setText(fenlei);
				TextView tvText5 = new TextView(ShowAllOutActivity.this);
				tvText5.setText(time);
				TextView tvText6 = new TextView(ShowAllOutActivity.this);
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
