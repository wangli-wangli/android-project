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
	
	private ImageView ivHead;//头像显示
	private Button btnTakephoto;//拍照
	private Button btnphoto;//相册
	private Bitmap head;//头像
	private static String path="storage/emulated/DCIM/Camera/";//存放照片路径
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/**	 
			 * 方法的一句话概述
			 * 方法详述（简单方法可不必详述）
			 * @param s 说明参数含义
			 * @return 说明返回值含义
			 * @throws IOException 说明发生此异常的条件
			 * @throws NullPointerException 说明发生此异常的条件
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
		findViewById(R.id.main_me_expression).setOnClickListener(this);// 表情
		findViewById(R.id.main_me_collect).setOnClickListener(this);// 收藏
		
		//设置头像
		btnphoto=(Button) findViewById(R.id.btn_photo);
		btnTakephoto=(Button) findViewById(R.id.btn_takephoto);
		ivHead=(ImageView) findViewById(R.id.main_me_headphoto);
		//从Sd卡中找头像，转换成Bitmap
		Bitmap bt=BitmapFactory.decodeFile(path+"head.jpg");
		if(bt!=null) {
			Drawable drawable=new BitmapDrawable(bt);//转换成drawerable
			ivHead.setImageDrawable(drawable);
		}else {//如果没有照片再从服务器取头像，存储取回来的头像
			
		}
		 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting_photo: {
			Toast.makeText(this, "进入圖片頁面，暂时还没实现",
					Toast.LENGTH_SHORT).show();
			break;

		}
		case R.id.main_me_collect: {
			Toast.makeText(this, "进入收藏界面，暂时还没实现",
					Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.main_me_expression: {
			Toast.makeText(this, "进入表情界面，暂时还没实现",
					Toast.LENGTH_SHORT).show();
			break;
		}
		
		case R.id.btn_photo:{//从相册里取照片
		   Intent intent1=new Intent(Intent.ACTION_PICK,null);
		   intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "imag/*");
		   startActivityForResult(intent1, 1);
		   break;
		}
		case R.id.btn_takephoto:{//从相册里取照片
			   Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			  intent2.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"head.jpg")));
			   startActivityForResult(intent2, 2);
			   break;
			}
		
		/*case R.id.main_me_setting: {
			//可以修改自己的頭像
			Toast.makeText(this, "进入個人設置界面，暂时还没实现",
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
				cropPhoto(data.getData());//裁剪图片
				
			}
			break;
		case 2:
			if(resultCode==RESULT_OK) {
				File temp=new File(Environment.getExternalStorageDirectory()+"/head.jpg");
				cropPhoto(Uri.fromFile(temp));//裁剪图片
			}
			break;
		case 3:
			if(data!=null) {
				Bundle extras=data.getExtras();
				head=extras.getParcelable("data");
				if(headImage!=null) {
					//上传服务器
					setPicToView(head);//保存在SD卡中
				ivHead.setImageBitmap(head);//用ImageView 显示出来
				}
			}
			break;
		default:
			break;
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	//调用系统的裁剪
	public void cropPhoto(Uri uri) {
		Intent intent=new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "imag/*");
		intent.putExtra("crop", "true");
		//aspectX aspectY是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		//outputX outputY是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return_data", true);
		startActivityForResult(intent, 3);
			
	}
	//保存在SD卡
	
	private void setPicToView(Bitmap mBitmap) {
		String sdStatus=Environment.getExternalStorageState();
		if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {//检测sd是否可用
		  return;	
		}
		FileOutputStream b=null;
		File file=new File(path);
		file.mkdirs();//创建文件夹
		String fileName=path+"head.jpg";//图片名字
		try {
			b=new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				//关闭流
				b.flush();
				b.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	

}
