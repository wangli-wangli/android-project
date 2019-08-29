package com.example.feigou.activity;


import com.example.feigou.test.AddPreActivity;
import com.example.feigou.test.IncomeActivity;
import com.example.feigou.test.ListComeActivity;
import com.example.feigou.test.ListPreActivity;
import com.example.feigou.test.OutcomeActivity;
import com.example.feigou.test.SettingActivity;
import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout llIncome,llOutcome,llList,llSeting,llModle,llBiaoge,llFenlei;
	private ImageView iv01;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
		
	}
	public void initView() {
		iv01=(ImageView) findViewById(R.id.ivHomeIncome);
		llIncome=(LinearLayout) findViewById(R.id.llincome);
		llOutcome=(LinearLayout) findViewById(R.id.lloutcome);
		llList=(LinearLayout) findViewById(R.id.lllist);
		llSeting=(LinearLayout) findViewById(R.id.llsetting);
		llFenlei=(LinearLayout) findViewById(R.id.llfenlei);
		llModle=(LinearLayout) findViewById(R.id.llmodle);
		
		//设置监听器
		llIncome.setOnClickListener(this);
        llOutcome.setOnClickListener(this);
		llList.setOnClickListener(this);
		llSeting.setOnClickListener(this);
		llFenlei.setOnClickListener(this);
		llModle.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		//判断是否有支出
		case R.id.llincome:
			Log.i("test", "aaa");
			Intent intentIncome=new Intent(MainActivity.this,IncomeActivity.class);
			startActivity(intentIncome);
			break;
			
		case R.id.lloutcome://支出
			Intent intentOutcome=new Intent(MainActivity.this,OutcomeActivity.class);
			startActivity(intentOutcome);
			break;
	    case R.id.lllist://收支列表
			Intent intentList=new Intent(MainActivity.this,ListComeActivity.class);
			startActivity(intentList);
			break;
	    case R.id.llsetting://设置列表
			Intent intentSetting=new Intent(MainActivity.this,SettingActivity.class);
			startActivity(intentSetting);
			break;
			
		case R.id.llfenlei://预算列表
			Intent intentFenlei=new Intent(MainActivity.this,ListPreActivity.class);
			startActivity(intentFenlei);
			break;
		case R.id.llmodle://收支列表
			Intent intentModle=new Intent(MainActivity.this,AddPreActivity.class);
			startActivity(intentModle);
			break;
		default:
			break;
		}

	}

}
