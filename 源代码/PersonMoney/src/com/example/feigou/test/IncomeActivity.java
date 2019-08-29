package com.example.feigou.test;

import javax.security.auth.PrivateCredentialPermission;

import com.example.feigou.activity.MainActivity;
import com.example.feigou.db.ComeModle;
import com.example.feigou.db.MyDBHelper;
import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeActivity extends Activity {

	private static final String[] style = { "现金", "储蓄卡", "信用卡","支付宝","微信" };
	private static final String[] fenlei = { "基本工资", "兼职", "生意收入","借入款" };

	private EditText etNUm, etNote;
	private Spinner spStyle, spFenlei;
	private TextView tvTime, timeshow;
	private Button btnRest, btnSubmit;

	private ArrayAdapter<String> adapterStyle;
	private ArrayAdapter<String> adapterFenlei;

	private String money, note, sStyle, sFenlei;
	String strTime;

	// 数据库
	private MyDBHelper DBsqlite;
	private SQLiteDatabase sqlite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//去掉软件上面的黑色的条
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_income);
		initview();

	}

	public void initview() {

		btnRest = (Button) findViewById(R.id.income_btn_reset);
		btnSubmit = (Button) findViewById(R.id.income_btn_submit);
		// 收入金额
		etNUm = (EditText) findViewById(R.id.etincome01);
		// 收入备注
		etNote = (EditText) findViewById(R.id.etincomenote);
		// 收入时间
		tvTime = (TextView) findViewById(R.id.tvincome04);
		// 显示时间
		timeshow = (TextView) findViewById(R.id.tvtime);
		// 收入方式
		spStyle = (Spinner) findViewById(R.id.spinner01);
		// 收入分类
		spFenlei = (Spinner) findViewById(R.id.spinner02);
		// 设置监听器是否有输入金额、
		
		//设置 填入收入金额时弹出数字键盘
		etNUm.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		//设置时间
		Time  time=new Time();
		time.setToNow();
		int year=time.year;
		int month=time.month;
		int day=time.monthDay;
		strTime=year+"/"+month+"/"+day;
		timeshow.setText("今天");
		
		
		
		etNUm.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (s == null || s.length() <= 0) {
					btnSubmit.setEnabled(false);
				} else {
					btnSubmit.setEnabled(true);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		// 收入方式适配器添加
		adapterStyle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, style);
		adapterStyle.setDropDownViewResource(R.layout.myspinner_dropdown);
		spStyle.setAdapter(adapterStyle);
		spStyle.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(IncomeActivity.this, "选择的是：" + style[position], Toast.LENGTH_SHORT).show();
				 sStyle= style[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

		// 收入分类添加适配器
		adapterFenlei = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fenlei);

		adapterFenlei.setDropDownViewResource(R.layout.myspinner_dropdown);
		spFenlei.setAdapter(adapterFenlei);
		spFenlei.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(IncomeActivity.this, "选择的是：" + fenlei[position], Toast.LENGTH_SHORT).show();
				sFenlei = fenlei[position];
			}
		});
        
		
		// 输入时间
	    tvTime.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
									
		// TODO Auto-generated method stub
			
		     Intent intent = new Intent(IncomeActivity.this, DataActivity.class);
		     startActivityForResult(intent, 0);
			
		}
	}); 
			 

		// 重置
		btnRest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				etNUm.setText("");
				etNote.setText("");

			}
		});
		
		
		// 确定提交
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				money = etNUm.getText().toString();
				note = etNote.getText().toString();
	
				if(new ComeModle(IncomeActivity.this).save("income",money,sStyle,sFenlei,strTime,note)){
					Toast.makeText(IncomeActivity.this, "保存成功！！", Toast.LENGTH_SHORT).show();
					Intent intent  = new Intent(IncomeActivity.this,MainActivity.class);
					startActivity(intent);
					IncomeActivity.this.finish();
				}else{
					Toast.makeText(IncomeActivity.this, "保存失败，请重新操作！", Toast.LENGTH_SHORT).show();
				}
				
			}
		});

		
		
	}

	// 覆盖onactivityResult方法

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
			Bundle bundle = data.getExtras();
			strTime = bundle.getString("time");
			System.out.println(strTime);
			timeshow.setText(strTime);
			
	}

}
