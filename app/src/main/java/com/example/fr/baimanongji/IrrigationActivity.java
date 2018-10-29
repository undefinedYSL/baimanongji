package com.example.fr.baimanongji;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IrrigationActivity extends Activity implements View.OnClickListener {
	private SharedPreferences sp;
	private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout,
			shopping_img_bn_layout, show_img_bn_layout;
	private LinearLayout ll_devive_1,ll_devive_2,ll_devive_3,ll_devive_4,ll_devive_5;
	private ImageView iv_device1,iv_device2,iv_device3,iv_device4,iv_device5;
	private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_name;
	private TextView tv_name1,tv_name2,tv_name3,tv_name4,tv_name5,tv_name6;
	private Button btn_control;
	private String name,hum1,hum2,hum3,hum4,tmp1,tmp2,ec,flow;
	private String[] rawstr;
	Animation rotate;
	Myrunnable myrunnable;
	private SelfDialog selfDialog;
	private int flag = 1;

	private String strurl = "http://192.168.43.232:8888/myApps";
//	private String strurl = "http://39.104.87.35:8888/myApps";
	private String irr_str;
	private String mark;
	private IrrigationInfo irrinfo;


	private Handler myhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what){
				case 0x666:
					tv_name.setText(name);
					switch (flag){
						case 1:
							tv_name1.setText("  ▶基质水分1：");
							tv_1.setText("     "+hum1+"%");
							tv_name2.setText("  ▶基质水分2：");
							tv_2.setText("     "+hum2+"%");
							tv_name3.setText("  ▶基质水分3：");
							tv_3.setText("     "+hum3+"%");
							tv_name4.setText("  ▶水肥浓度：");
							tv_4.setText("     "+ec+"S/m");
							tv_name5.setText("  ▶灌溉流量：");
							tv_5.setText("     "+flow+"ml");
							tv_name6.setText("");
							tv_6.setText("");
							btn_control.setText("开启滴灌装置1控制");
							break;
						case 2:
							tv_name1.setText("  ▶基质水分1：");
							tv_1.setText("     "+hum1+"%");
							tv_name2.setText("  ▶基质水分2：");
							tv_2.setText("     "+hum2+"%");
							tv_name3.setText("  ▶基质水分3：");
							tv_3.setText("     "+hum3+"%");
							tv_name4.setText("  ▶水肥浓度：");
							tv_4.setText("     "+ec+"S/m");
							tv_name5.setText("  ▶灌溉流量：");
							tv_5.setText("     "+flow+"ml");
							tv_name6.setText("");
							tv_6.setText("");
							btn_control.setText("开启滴灌装置2控制");
							break;
						case 3:
							tv_name1.setText("  ▶基质水分1：");
							tv_1.setText("     "+hum1+"%");
							tv_name2.setText("  ▶基质水分2：");
							tv_2.setText("     "+hum2+"%");
							tv_name3.setText("  ▶基质水分3：");
							tv_3.setText("     "+hum3+"%");
							tv_name4.setText("  ▶基质水分4：");
							tv_4.setText("     "+hum4+"%");
							tv_name5.setText("  ▶水肥浓度：");
							tv_5.setText("     "+ec+"S/m");
							tv_name6.setText("  ▶灌溉流量：");
							tv_6.setText("     "+flow+"ml");
							btn_control.setText("开启滴灌装置3控制");
							break;
						case 4:
							tv_name1.setText("  ▶水温1：");
							tv_1.setText("     "+tmp1+"℃");
							tv_name2.setText("  ▶水温2：");
							tv_2.setText("     "+tmp2+"℃");
							tv_name3.setText("  ▶水肥浓度：");
							tv_3.setText("     "+ec+"S/m");
							tv_name4.setText("  ▶灌溉流量：");
							tv_4.setText("     "+flow+"ml");
							tv_name5.setText("");
							tv_5.setText("");
							tv_name6.setText("");
							tv_6.setText("");
							btn_control.setText("开启滴灌装置4控制");
							break;
						case 5:
							tv_name1.setText("  ▶水温1：");
							tv_1.setText("     "+tmp1+"℃");
							tv_name2.setText("  ▶水温2：");
							tv_2.setText("     "+tmp2+"℃");
							tv_name3.setText("  ▶水肥浓度：");
							tv_3.setText("     "+ec+"S/m");
							tv_name4.setText("  ▶灌溉流量：");
							tv_4.setText("     "+flow+"ml");
							tv_name5.setText("");
							tv_5.setText("");
							tv_name6.setText("");
							tv_6.setText("");
							btn_control.setText("开启滴灌装置5控制");
							break;
					}
//                case 0x888:
//                    tv_cond.setText(cond_txt);
//                    tv_tmp.setText(tmp+"℃");
//                    tv_pcpn.setText(pcpn+"mm");
//                case 0x999:
//                    Toast.makeText(MainActivity.this,"信息已更新",Toast.LENGTH_SHORT).show();
			}
		}
	};

	private class Myrunnable extends Thread{

		@Override
		public void run() {
			myhandler.sendEmptyMessage(0x666);
//            myhandler.sendEmptyMessage(0x888);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.irrigation_activity);

		sp = this.getSharedPreferences("setting", Context.MODE_PRIVATE);

		home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
		home_img_bn_Layout.setOnClickListener(clickListener_home);

		style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
		style_img_bn_layout.setOnClickListener(clickListener_style);
		style_img_bn_layout.setSelected(true);

		cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
		cam_img_bn_layout.setOnClickListener(clickListener_cam);

		shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
		shopping_img_bn_layout.setOnClickListener(clickListener_shopping);

		show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
		show_img_bn_layout.setOnClickListener(clickListener_show);

		rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);

		myrunnable = new Myrunnable();

		//图片及动画注册
		iv_device1 = (ImageView)findViewById(R.id.iv_c1);
		iv_device1.setAnimation(rotate);
		iv_device2 = (ImageView)findViewById(R.id.iv_c2);
		iv_device2.setAnimation(rotate);
		iv_device3 = (ImageView)findViewById(R.id.iv_c3);
		iv_device3.setAnimation(rotate);
		iv_device4 = (ImageView)findViewById(R.id.iv_c4);
		iv_device4.setAnimation(rotate);
		iv_device5 = (ImageView)findViewById(R.id.iv_c5);
		iv_device5.setAnimation(rotate);

		//布局点击事件注册
		ll_devive_1 = (LinearLayout)findViewById(R.id.LL_c1);
		ll_devive_1.setOnClickListener(this);

		ll_devive_2 = (LinearLayout)findViewById(R.id.LL_c2);
		ll_devive_2.setOnClickListener(this);

		ll_devive_3 = (LinearLayout)findViewById(R.id.LL_c3);
		ll_devive_3.setOnClickListener(this);

		ll_devive_4 = (LinearLayout)findViewById(R.id.LL_c4);
		ll_devive_4.setOnClickListener(this);

		ll_devive_5 = (LinearLayout)findViewById(R.id.LL_c5);
		ll_devive_5.setOnClickListener(this);

		//文本信息注册
		tv_name = (TextView)findViewById(R.id.tv_name);
		tv_1 = (TextView)findViewById(R.id.tv_box1);
		tv_2 = (TextView)findViewById(R.id.tv_box2);
		tv_3 = (TextView)findViewById(R.id.tv_box3);
		tv_4 = (TextView)findViewById(R.id.tv_box4);
		tv_5 = (TextView)findViewById(R.id.tv_box5);
		tv_6 = (TextView)findViewById(R.id.tv_box6);

		//名称信息注册
		tv_name1 = (TextView)findViewById(R.id.tv_b1);
		tv_name2 = (TextView)findViewById(R.id.tv_b2);
		tv_name3 = (TextView)findViewById(R.id.tv_b3);
		tv_name4 = (TextView)findViewById(R.id.tv_b4);
		tv_name5 = (TextView)findViewById(R.id.tv_b5);
		tv_name6 = (TextView)findViewById(R.id.tv_b6);

		btn_control = (Button)findViewById(R.id.btn_control);
		btn_control.setOnClickListener(this);


	}

	//OKHttp请求
	private void getRequestWithOkhttp(final String sendMessage) {
//		switch (flag){
//			case 1:
//				final String sendMessage = "getBoardroom";
//				break;
//		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					OkHttpClient client = new OkHttpClient();
					//表单数据
//                    FormBody.Builder builder = new FormBody.Builder();
//                    builder.add("inform",sendMessage);
//                    RequestBody formBody = builder.build();
					RequestBody formBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),sendMessage);

					//发送请求
					Request request = new Request.Builder()
							.url(strurl)
							.post(formBody)
							.build();
					Response response = client.newCall(request).execute();
					String responseData = response.body().string();
					handleResponse(responseData);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void handleResponse(final String responseData) {
		rawstr = responseData.split("@");
		mark = rawstr[0];
		if (mark.equals("JZ01Data")||mark.equals("JZ02Data")){
			hum1 = rawstr[1];
			hum2 = rawstr[2];
			hum3 = rawstr[3];
			ec = rawstr[4];
			flow = rawstr[5];
//			irrinfo = new IrrigationInfo(rawstr[0],rawstr[1],rawstr[2],rawstr[3],rawstr[4],rawstr[5]);
		}else if (mark.equals("JZ03Data")){
			hum1 = rawstr[1];
			hum2 = rawstr[2];
			hum3 = rawstr[3];
			hum4 = rawstr[4];
			ec = rawstr[5];
			flow = rawstr[6];
//			irrinfo = new IrrigationInfo(rawstr[0],rawstr[1],rawstr[2],rawstr[3],rawstr[4],rawstr[5],rawstr[6]);
		}else if (mark.equals("SP12Data")||mark.equals("SP11Data")){
			tmp1 = rawstr[1];
			tmp1 = rawstr[2];
			ec = rawstr[3];
			flow = rawstr[4];
//			irrinfo = new IrrigationInfo(rawstr[0],rawstr[1],rawstr[2],rawstr[3],rawstr[4]);
		};
		myrunnable.start();
	}

	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			dialog();
			return true;
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	protected void dialog()
	{
		Dialog dialog = new AlertDialog.Builder(this).setTitle("温室管理终端").setMessage(
				"确认退出应用程序？").setPositiveButton("退出",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				IrrigationActivity.this.finish();
			}
		}).setNegativeButton("取消",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).create();
		dialog.show();
	}

	private View.OnClickListener clickListener_home = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(true);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(IrrigationActivity.this, TestUIActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private View.OnClickListener clickListener_style = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(true);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
		}
	};
	private View.OnClickListener clickListener_cam = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(true);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(IrrigationActivity.this, CurveActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private View.OnClickListener clickListener_shopping = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(true);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(IrrigationActivity.this, ControlActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private View.OnClickListener clickListener_show = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(true);
			Intent intent = new Intent();
			intent.setClass(IrrigationActivity.this, ContactActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.LL_c1:
				flag = 1;
				name = "     单层椰糠条基质栽培";
				irr_str = "JZ01Data";
				getRequestWithOkhttp(irr_str);
				iv_device1.startAnimation(rotate);
				break;

			case R.id.LL_c2:
				flag = 2;
				name = "     错层椰糠条基质栽培";
				irr_str = "JZ02Data";
				getRequestWithOkhttp(irr_str);
				iv_device2.startAnimation(rotate);
				break;

			case R.id.LL_c3:
				flag = 3;
				name = "     半圆槽多层基质栽培";
				irr_str = "JZ03Data";
				getRequestWithOkhttp(irr_str);
				iv_device3.startAnimation(rotate);
				break;

			case R.id.LL_c4:
				flag = 4;
				name = "     平铺水培";
				irr_str = "SP11Data";
				getRequestWithOkhttp(irr_str);
				iv_device4.startAnimation(rotate);
				break;

			case R.id.LL_c5:
				flag = 5;
				name = "     A字架水培";
				irr_str = "SP12Data";
				getRequestWithOkhttp(irr_str);
				iv_device5.startAnimation(rotate);
				break;

			case  R.id.btn_control:
				selfDialog = new SelfDialog(IrrigationActivity.this);
				selfDialog.show();
		}
	}

}
