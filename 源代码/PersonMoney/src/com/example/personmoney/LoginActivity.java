package com.example.personmoney;

import java.util.Calendar;

import javax.security.auth.PrivateCredentialPermission;

import com.example.feigou.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;


public class LoginActivity extends Activity {
	
	private ImageView loginImage;
	private Button loginBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initview();
		
	}
	
	public void initview() {
		loginImage=(ImageView) findViewById(R.id.ivlogin);
		loginBtn=(Button) findViewById(R.id.btn_begin);
		
		//��㶯��Ч��
		Animation alphaAnimation=new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(3000);//���ö�������ʱ��
		alphaAnimation.setFillAfter(true);//���ö��������󱣳ֵ�ǰ��λ��
		//������Ч����ӵ�ͼƬ��
		loginImage.startAnimation(alphaAnimation);
		
		
		
		loginBtn.setOnClickListener(new OnClickListener(){
			
		  @Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(LoginActivity.this, LoginActivity2.class);
				startActivity(intent);
				LoginActivity.this.finish();
				
			}
		});
		
	}
	

}
