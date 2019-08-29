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
		
		
		//1.�ҵ����ǹ��ĵĿؼ�
		
		 et_name=(EditText)findViewById(R.id.et_username);
		et_userpassword=(EditText)findViewById(R.id.et_userpassword);
		cb_ischeck=(CheckBox)findViewById(R.id.cb_ischeck);
		
		//��config.xml�ļ��а����ǹ��ĵ�����ȡ���� Ȼ����ʾ��Edittext�ؼ���
		//ʹ��SharedPreferences ȥ��������  �ǵ�sp��ʵ��
		SharedPreferences sPreferences=getSharedPreferences("config", 0);
		String name=sPreferences.getString("name", "");
		String password=sPreferences.getString("pwd", "");
		boolean status=sPreferences.getBoolean("status", false);
		
		et_name.setText(name);
		et_userpassword.setText(password);
		cb_ischeck.setChecked(status);
	
	}
	
	
	//д����¼�
	public void login(View v) {
		
		
		//2.1��ȡ�û���������SS
		
		String name=et_name.getText().toString().trim();
		String password=et_userpassword.getText().toString().trim();
		System.out.println("cb_ischeck:"+cb_ischeck.isChecked());
		//2.2�ж�name��password�Ƿ�Ϊ��
		if(TextUtils.isEmpty(name)||TextUtils.isEmpty(password)) {
			Toast.makeText(LoginActivity2.this, "�û��������벻��Ϊ��", 1).show();
		}
		else {
			//���е�¼���߼�
			Intent intent=new Intent();
			intent.setClass(LoginActivity2.this, MainActivity.class);
			startActivity(intent);
			if(cb_ischeck.isChecked()) {
				
				
				//ʹ��SharedPreferences ȥ��������  �ǵ�sp��ʵ��
				SharedPreferences sPreferences=getSharedPreferences("config", 0);
				//��ȡsp�ı༭��
				Editor editor=sPreferences.edit();
				editor.putString("name", name);
				editor.putString("pwd", password);
				editor.putBoolean("status", true);
				//�ύ
				editor.commit();
			
			}
			else {
				Toast.makeText(LoginActivity2.this, "�빴ѡ'��ס�û�������'", 1).show();
			}
		}
	}

	
}
