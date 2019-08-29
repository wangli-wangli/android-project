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

	private static final String[] style={"�ֽ�","���п�","�һ�ȯ"};
	private static final String[] fenlei= {"������Ʒ","����ѧϰ","��ͨ����","ҵ�మ��","����"};
	
	private EditText etNum ,etNote;
	private Spinner spStyle, spFenlei;
	private TextView tvTime, timeshow;
	private Button btnRest, btnSubmit;
	
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
			setContentView(R.layout.activity_addpre);
			initview();
		}
	
		public void initview() {
			btnRest = (Button) findViewById(R.id.income_btn_reset);
			btnSubmit = (Button) findViewById(R.id.income_btn_submit);
			// ֧�����
			etNum = (EditText) findViewById(R.id.etincome01);
			// ֧����ע
			etNote = (EditText) findViewById(R.id.etincomenote);
			// ����
			btnRest = (Button) findViewById(R.id.income_btn_reset);
			// ȷ��
			btnSubmit = (Button) findViewById(R.id.income_btn_submit);
			// ֧��ʱ��
			tvTime = (TextView) findViewById(R.id.tvincome04);
			
			// ��ʾʱ��
			timeshow = (TextView) findViewById(R.id.tvtime);
			// ֧����ʽ
			spStyle = (Spinner) findViewById(R.id.spinner01);
			//֧������
			spFenlei = (Spinner) findViewById(R.id.spinner02);
			// ���ü����Ƿ���������
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
			// ���뷽ʽ���������
			adapterStyle = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, style);

			adapterStyle.setDropDownViewResource(R.layout.myspinner_dropdown);

			spStyle.setAdapter(adapterStyle);

			spStyle.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(AddPreActivity.this, "ѡ�����:" + style[position],
							Toast.LENGTH_SHORT).show();
					sStyle = style[position];
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
			// ����������������
			adapterFenlei = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, fenlei);

			adapterFenlei.setDropDownViewResource(R.layout.myspinner_dropdown);
			spFenlei.setAdapter(adapterFenlei);
			spFenlei.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(AddPreActivity.this, "ѡ�����:" + fenlei[position],
							Toast.LENGTH_SHORT).show();
					sFenlei = fenlei[position];
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}
			});
			// ����
			btnRest.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					etNum.setText("");
					etNote.setText("");
				}
			});
			// ȷ���ύ
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					money = etNum.getText().toString();
					note = etNote.getText().toString();
					
					if(new ComeModle(AddPreActivity.this).save("addpre",money,sStyle,sFenlei,strTime,note)){
						Toast.makeText(AddPreActivity.this, "����ɹ�����", Toast.LENGTH_SHORT).show();
						Intent intent  = new Intent(AddPreActivity.this,MainActivity.class);
						startActivity(intent);
						AddPreActivity.this.finish();
					}else{
						Toast.makeText(AddPreActivity.this, "����ʧ�ܣ������²�����", Toast.LENGTH_SHORT).show();
					}
					
				}
			});

			// ����ʱ��
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
		
		// ����onacticityResult����
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
