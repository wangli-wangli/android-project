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
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class OutcomeActivity extends Activity {
 
   private static final String[] style= {"�ֽ�", "���", "���ÿ�","֧����","΢��"};
   private static final String[] fenlei= {"����","����","��ͨ","ˮ���","����","ҽ��","����","����","����"};
   
   private EditText etNum,etNote;
   private Spinner spStyle,spFenlei;
   private TextView tvTime,timeshow;
   private Button btnRest,btnSubmit;
   private ArrayAdapter<String> adapterStyle;
   private ArrayAdapter<String> adapterFenlei;
   
   private String money,note,sStyle,sFenlei;
   String strTime;
   
   //���ݿ�
   private MyDBHelper DBsqlite;
   private SQLiteDatabase sqlite;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//ȥ���������ĺ�ɫ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_outcome);
		init();
	}
  public void init() {
	  btnRest=(Button) findViewById(R.id.income_btn_reset);
	  btnSubmit=(Button) findViewById(R.id.income_btn_submit);
	  //֧�����
	  etNum=(EditText) findViewById(R.id.etincome01);
	  //֧����ע
	  etNote=(EditText) findViewById(R.id.etincomenote);
	  //֧��ʱ��
	  tvTime=(TextView) findViewById(R.id.tvincome04);
	  //��ʾʱ��
	  timeshow=(TextView)findViewById(R.id.tvtime);
	  //֧����ʽ
	  spStyle=(Spinner) findViewById(R.id.spinner01);
	  //֧������
	  spFenlei=(Spinner) findViewById(R.id.spinner02);
	  
	//����ʱ��
			Time  time=new Time();
			time.setToNow();
			int year=time.year;
			int month=time.month;
			int day=time.monthDay;
			strTime=year+"/"+month+"/"+day;
			timeshow.setText("����");
	
	  etNum.addTextChangedListener(new TextWatcher() {

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
	// ���뷽ʽ���������
			adapterStyle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, style);
			adapterStyle.setDropDownViewResource(R.layout.myspinner_dropdown);
			spStyle.setAdapter(adapterStyle);
			spStyle.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(OutcomeActivity.this, "ѡ����ǣ�" + style[position], Toast.LENGTH_SHORT).show();
					 sStyle= style[position];
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}

			});

			// ����������������
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
					Toast.makeText(OutcomeActivity.this, "ѡ����ǣ�" + fenlei[position], Toast.LENGTH_SHORT).show();
					sFenlei = fenlei[position];
				}
			});
	        
			// ����ʱ��
		    tvTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
										
			// TODO Auto-generated method stub
				
			     Intent intent = new Intent(OutcomeActivity.this, DataActivity.class);
			     startActivityForResult(intent, 0);
				
			}
		}); 
				 

			// ����
			btnRest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					etNum.setText("");
					etNote.setText("");

				}
			});
			
			//strTime="2017--12-12";
			// ȷ���ύ
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					money = etNum.getText().toString();
					note = etNote.getText().toString();
		
					if(new ComeModle(OutcomeActivity.this).save("outcome",money,sStyle,sFenlei,strTime,note)){
						Toast.makeText(OutcomeActivity.this, "����ɹ�����", Toast.LENGTH_SHORT).show();
						Intent intent  = new Intent(OutcomeActivity.this,MainActivity.class);
						startActivity(intent);
						OutcomeActivity.this.finish();
					}else{
						Toast.makeText(OutcomeActivity.this, "����ʧ�ܣ������²�����", Toast.LENGTH_SHORT).show();
					}
					
				}
			});

			
			
  }
//����onactivityResult����

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
			Bundle bundle = data.getExtras();
			strTime = bundle.getString("time");
			timeshow.setText(strTime);
			
	}


}
	  
			
	  
  

  
   
   
		   
		   

