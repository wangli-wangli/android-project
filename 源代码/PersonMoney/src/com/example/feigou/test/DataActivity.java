package com.example.feigou.test;

import java.text.Format;
import java.util.Calendar;

import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DataActivity extends Activity {
   private int gyear,gmonth,gday,hour,minute;
   private TimePicker tp;
  private DatePicker dp;
  private Button btn;
 
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//获取当前时间
	  Calendar calendar=Calendar.getInstance();
	  gyear=calendar.get(Calendar.DAY_OF_MONTH);
	  
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_data);
	  btn=(Button) findViewById(R.id.btn_data_sub);
	  dp=(DatePicker) findViewById(R.id.calendarView1);
	  dp.init(gyear, gmonth, gday, new OnDateChangedListener() {

		@Override
		public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			gyear=year;
			gmonth=monthOfYear;
			gday=dayOfMonth;
			
		}
		  
	  });
	  
	  btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			updateDisplay();
		}
	});
	}
  
    private void updateDisplay() {
    	String ss=gyear+"/"+format(gmonth+1)+"/"+format(gday);
    	Intent intent=new Intent(DataActivity.this,IncomeActivity.class);
    	Bundle bundle=new Bundle();
    	bundle.putString("time", ss);
    	intent.putExtras(bundle);
    	DataActivity.this.setResult(RESULT_OK,intent);
    	DataActivity.this.finish();
    	
    }
    
    private String format(int x) {
    	String str=""+x;
    	if(str.length()==1)
    		str="0"+str;
    	return str;
    }
}
