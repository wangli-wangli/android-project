package com.example.personmoney;

import java.util.Map;

import com.example.feigou.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;




public class LoginActivity2 extends Activity {

	private EditText et_name;
	private EditText et_userpassword;
	private CheckBox cb_ischeck;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login2);
		
		
		//1.找到我们关心的控件
		
		 et_name=(EditText)findViewById(R.id.et_username);
		et_userpassword=(EditText)findViewById(R.id.et_userpassword);
		cb_ischeck=(CheckBox)findViewById(R.id.cb_ischeck);
		
		//在config.xml文件中把我们关心的数据取出来 然后显示在Edittext控件上
		//使用SharedPreferences 去保存数据  那到sp的实例
		SharedPreferences sPreferences=getSharedPreferences("config", 0);
		String name=sPreferences.getString("name", "");
		String password=sPreferences.getString("pwd", "");
		boolean status=sPreferences.getBoolean("status", false);
		
		et_name.setText(name);
		et_userpassword.setText(password);
		cb_ischeck.setChecked(status);
	
	}
	
	
	//写点击事件
	public void login(View v) {
		
		
		//2.1获取用户名和密码SS
		
		String name=et_name.getText().toString().trim();
		String password=et_userpassword.getText().toString().trim();
		System.out.println("cb_ischeck:"+cb_ischeck.isChecked());
		//2.2判断name和password是否为空
		if(TextUtils.isEmpty(name)||TextUtils.isEmpty(password)) {
			Toast.makeText(LoginActivity2.this, "用户名和密码不能为空", 1).show();
		}
		else {
			//进行登录的逻辑
			Intent intent=new Intent();
			intent.setClass(LoginActivity2.this, MainActivity.class);
			startActivity(intent);
			if(cb_ischeck.isChecked()) {
				
				
				//使用SharedPreferences 去保存数据  那到sp的实例
				SharedPreferences sPreferences=getSharedPreferences("config", 0);
				//获取sp的编辑器
				Editor editor=sPreferences.edit();
				editor.putString("name", name);
				editor.putString("pwd", password);
				editor.putBoolean("status", true);
				//提交
				editor.commit();
			
			}
			else {
				Toast.makeText(LoginActivity2.this, "请勾选'记住用户名密码'", 1).show();
			}
		}
	}

	
}
