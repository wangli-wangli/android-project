package com.example.feigou.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.personmoney.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {
	private ImageView headImage;
	private TextView tvNickName;
	private TextView tvWeiXinNum;
	private Button setting_photo;
	
	private ImageView ivHead;//ͷ����ʾ
	private Button btnTakephoto;//����
	private Button btnphoto;//���
	private Bitmap head;//ͷ��
	private static String path="storage/emulated/DCIM/Camera/";//�����Ƭ·��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/**	 
			 * ������һ�仰����
			 * �����������򵥷����ɲ���������
			 * @param s ˵����������
			 * @return ˵������ֵ����
			 * @throws IOException ˵���������쳣������
			 * @throws NullPointerException ˵���������쳣������
			 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		init();
	}

	public void init() {
		setting_photo = (Button) findViewById(R.id.setting_photo);
		setting_photo.setOnClickListener(this);
		headImage = (ImageView) findViewById(R.id.main_me_headphoto);
		
		
		//findViewById(R.id.main_me_setting).setOnClickListener(this);
		findViewById(R.id.main_me_expression).setOnClickListener(this);// ����
		findViewById(R.id.main_me_collect).setOnClickListener(this);// �ղ�
		
		//����ͷ��
		btnphoto=(Button) findViewById(R.id.btn_photo);
		btnTakephoto=(Button) findViewById(R.id.btn_takephoto);
		ivHead=(ImageView) findViewById(R.id.main_me_headphoto);
		//��Sd������ͷ��ת����Bitmap
		Bitmap bt=BitmapFactory.decodeFile(path+"head.jpg");
		if(bt!=null) {
			Drawable drawable=new BitmapDrawable(bt);//ת����drawerable
			ivHead.setImageDrawable(drawable);
		}else {//���û����Ƭ�ٴӷ�����ȡͷ�񣬴洢ȡ������ͷ��
			
		}
		 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting_photo: {
			Toast.makeText(this, "����DƬ��棬��ʱ��ûʵ��",
					Toast.LENGTH_SHORT).show();
			break;

		}
		case R.id.main_me_collect: {
			Toast.makeText(this, "�����ղؽ��棬��ʱ��ûʵ��",
					Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.main_me_expression: {
			Toast.makeText(this, "���������棬��ʱ��ûʵ��",
					Toast.LENGTH_SHORT).show();
			break;
		}
		
		case R.id.btn_photo:{//�������ȡ��Ƭ
		   Intent intent1=new Intent(Intent.ACTION_PICK,null);
		   intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "imag/*");
		   startActivityForResult(intent1, 1);
		   break;
		}
		case R.id.btn_takephoto:{//�������ȡ��Ƭ
			   Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			  intent2.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"head.jpg")));
			   startActivityForResult(intent2, 2);
			   break;
			}
		
		/*case R.id.main_me_setting: {
			//�����޸��Լ����^��
			Toast.makeText(this, "���낀���O�ý��棬��ʱ��ûʵ��",
					Toast.LENGTH_SHORT).show();
			break;
		}*/

		}
	}		
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch(requestCode) {
		case 1:
			if(resultCode==RESULT_OK) {
				cropPhoto(data.getData());//�ü�ͼƬ
				
			}
			break;
		case 2:
			if(resultCode==RESULT_OK) {
				File temp=new File(Environment.getExternalStorageDirectory()+"/head.jpg");
				cropPhoto(Uri.fromFile(temp));//�ü�ͼƬ
			}
			break;
		case 3:
			if(data!=null) {
				Bundle extras=data.getExtras();
				head=extras.getParcelable("data");
				if(headImage!=null) {
					//�ϴ�������
					setPicToView(head);//������SD����
				ivHead.setImageBitmap(head);//��ImageView ��ʾ����
				}
			}
			break;
		default:
			break;
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	//����ϵͳ�Ĳü�
	public void cropPhoto(Uri uri) {
		Intent intent=new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "imag/*");
		intent.putExtra("crop", "true");
		//aspectX aspectY�ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		//outputX outputY�ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return_data", true);
		startActivityForResult(intent, 3);
			
	}
	//������SD��
	
	private void setPicToView(Bitmap mBitmap) {
		String sdStatus=Environment.getExternalStorageState();
		if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {//���sd�Ƿ����
		  return;	
		}
		FileOutputStream b=null;
		File file=new File(path);
		file.mkdirs();//�����ļ���
		String fileName=path+"head.jpg";//ͼƬ����
		try {
			b=new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				//�ر���
				b.flush();
				b.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	

}
