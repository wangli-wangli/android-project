package com.example.feigou.test;

import com.example.feigou.activity.MainActivity;
import com.example.feigou.db.ComeModle;
import com.example.feigou.db.MyDBHelper;
import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPreActivity extends Activity {

	private static final String[] style={"现金","银行卡","兑换券"};
	private static final String[] fenlei= {"生活用品","工作学习","交通工具","业余爱好","其他"};
	
	private EditText etNum ,etNote;
	private Spinner spStyle, spFenlei;
	private TextView tvTime, timeshow;
	private Button btnRest, btnSubmit;
	
	private ArrayAdapter<String> adapterStyle;
	private ArrayAdapter<String> adapterFenlei;
	
	private String money,note,sStyle,sFenlei;
	String strTime; 
	
	//数据库
		private MyDBHelper DBsqlite;
		private SQLiteDatabase sqlite;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_addpre);
			initview();
		}
	
		public void initview() {
			btnRest = (Button) findViewById(R.id.income_btn_reset);
			btnSubmit = (Button) findViewById(R.id.income_btn_submit);
			// 支出金额
			etNum = (EditText) findViewById(R.id.etincome01);
			// 支出备注
			etNote = (EditText) findViewById(R.id.etincomenote);
			// 重置
			btnRest = (Button) findViewById(R.id.income_btn_reset);
			// 确定
			btnSubmit = (Button) findViewById(R.id.income_btn_submit);
			// 支出时间
			tvTime = (TextView) findViewById(R.id.tvincome04);
			
			// 显示时间
			timeshow = (TextView) findViewById(R.id.tvtime);
			// 支出方式
			spStyle = (Spinner) findViewById(R.id.spinner01);
			//支出分类
			spFenlei = (Spinner) findViewById(R.id.spinner02);
			// 设置监听是否有输入金额
			etNum.addTextChangedListener(new TextWatcher() {

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
					// TODO Auto-generated method stub

					if (s == null || s.length() <= 0) {
						btnSubmit.setEnabled(false);
					} else {
						btnSubmit.setEnabled(true);
					}
				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}

			});
			// 收入方式适配器添加
			adapterStyle = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, style);

			adapterStyle.setDropDownViewResource(R.layout.myspinner_dropdown);

			spStyle.setAdapter(adapterStyle);

			spStyle.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(AddPreActivity.this, "选择的是:" + style[position],
							Toast.LENGTH_SHORT).show();
					sStyle = style[position];
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
			// 收入分类添加适配器
			adapterFenlei = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, fenlei);

			adapterFenlei.setDropDownViewResource(R.layout.myspinner_dropdown);
			spFenlei.setAdapter(adapterFenlei);
			spFenlei.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(AddPreActivity.this, "选择的是:" + fenlei[position],
							Toast.LENGTH_SHORT).show();
					sFenlei = fenlei[position];
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
			// 重置
			btnRest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					etNum.setText("");
					etNote.setText("");
				}
			});
			// 确定提交
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					money = etNum.getText().toString();
					note = etNote.getText().toString();
					
					if(new ComeModle(AddPreActivity.this).save("addpre",money,sStyle,sFenlei,strTime,note)){
						Toast.makeText(AddPreActivity.this, "保存成功！！", Toast.LENGTH_SHORT).show();
						Intent intent  = new Intent(AddPreActivity.this,MainActivity.class);
						startActivity(intent);
						AddPreActivity.this.finish();
					}else{
						Toast.makeText(AddPreActivity.this, "保存失败，请重新操作！", Toast.LENGTH_SHORT).show();
					}
					
				}
			});

			// 输入时间
			tvTime.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(AddPreActivity.this,
							DataActivity.class);
					startActivityForResult(intent, 0);
				}
			});
		}
		
		// 覆盖onacticityResult方法
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			// super.onActivityResult(requestCode, resultCode, data);
			switch (resultCode) {
			case RESULT_OK:
				Bundle b = data.getExtras();
				strTime = b.getString("time");
				timeshow.setText(strTime);

				break;

			default:
				break;
			}
		}

}
