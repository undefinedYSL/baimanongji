package com.example.fr.baimanongji;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class TestUIActivity extends TabActivity implements OnGestureListener, OnTouchListener {
	private String starttime;
	String path="http://192.168.43.232:8888/myApps";
//	String path="http://192.168.1.200:8888/myApps";
//	String path="http://192.168.0.100:8888/myApps";
	URL url=null;
	HttpURLConnection urlConn=null;
	int m=0; 	
	private static final int TIMEOUT =10000;   //设置socket连接超时
	private InetSocketAddress isa = null;  		
	private MyHandler mHandler1; 
	private Handler handler1;	
	public String receive_str="";
	public int count=0;  //数据长度存放接受数据数组
	private int count1 = 0;
	private String[] arr;//存放接受数据数组
	DatabaseHelper dbHelper;	
	private TextView tempnum1;
	private TextView guangzhaonum1;
	private TextView shidunum1;
	private TextView co2num1;
	private TextView tempnum2;
	private TextView guangzhaonum2;
	private TextView shidunum2;
	private TextView co2num2;
	private TextView st;
	private TextView st1;
	private TextView sh;
	private TextView sh1;
	
	private ListView lv;
	private SharedPreferences sp;	
	private TextView date_TextView;
	private ImageButton title_logo_img,set_ImageButton;
	private ViewFlipper viewFlipper;
	private boolean showNext = true;
	private boolean isRun = true;
	private int currentPage = 0;
	private final int SHOW_NEXT = 0011;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	private GestureDetector mGestureDetector;
	private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout, 
						 shopping_img_bn_layout, show_img_bn_layout;  
	private RelativeLayout relativewendu,relativeshidu,relativeco2,relativeguangzhao,relativesearch,relativezxing,relativesearch1,relativesearch2;
	private TabWidget tabWidget;
	 private TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        sp = this.getSharedPreferences("setting", Context.MODE_PRIVATE);
        sp.edit().putBoolean("IFHUADONG", false).commit();
		TabHost tabHost = getTabHost();
		tabWidget = tabHost.getTabWidget();
		TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1").setIndicator("节点一").setContent(R.id.tab01);
		tabHost.addTab(tab1);
		TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2").setIndicator("节点二").setContent(R.id.tab02);
		tabHost.addTab(tab2);
//		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("温室三").setContent(R.id.tab03));
		for (int i =0; i < tabWidget.getChildCount(); i++) {
			//修改Tabhost高度和宽度
			tabWidget.getChildAt(i).getLayoutParams().height = 100;
			tabWidget.getChildAt(i).getLayoutParams().width =330;
			//修改显示字体大小
			TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
			tv.setTextSize(12);
//        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
//        tabHost.setup();
//        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("�ڵ�һ")
//        		.setContent(R.id.tab01));
//
//        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("�ڵ��")
//                .setContent(R.id.tab02));
//        tabWidget = tabHost.getTabWidget();//��ȡTabHost��ͷ��
//        for (int i = 0; i < tabWidget.getChildCount(); i++) {
        	 View view = tabWidget.getChildAt(i);
        	 view.setBackgroundResource(R.drawable.tab_indicator_ab_mmstyle);
//           tabWidget.getChildAt(i).getLayoutParams().height = 160;
//           tabWidget.getChildAt(i).getLayoutParams().width = 330;
//
//           TextView textView = (TextView) view.findViewById(android.R.id.title);
//
//           RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//           params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
//           params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//           textView.getPaint().setFakeBoldText(true);
//
        
         
        }

        date_TextView = (TextView) findViewById(R.id.home_date_tv);
        date_TextView.setText(getDate());
        
        title_logo_img = (ImageButton) findViewById(R.id.title_logo_img);//温室管理终端按钮
        set_ImageButton = (ImageButton) findViewById(R.id.title_set_bn);
        //"设置"按钮监听
        set_ImageButton.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        	AlertDialog dlg = new AlertDialog.Builder(TestUIActivity.this).setTitle("设置").setMultiChoiceItems(
					     new String[] { "滑动切换页面" }, null , new OnMultiChoiceClickListener()
	                       {
	                           @Override
	                           public void onClick(DialogInterface dialog,
	                                   int which, boolean isChecked)
	                           {
	                              // TODO Auto-generated method stub
	                           }
	                       })
					     .setPositiveButton("确定", new DialogInterface.OnClickListener()
					     {
					    	 public void onClick(DialogInterface dialog, int which)
					    	 {
					    		 if (lv.getCheckedItemPositions().size() > 0)
			                      {
					    			 sp.edit().putBoolean("IFHUADONG", true).commit();
			                      } 
					    		 else
					    			 sp.edit().putBoolean("IFHUADONG", false).commit();
					    	 }
					     })
					     .setNegativeButton("取消", new DialogInterface.OnClickListener()
					     {
					    	 public void onClick(DialogInterface dialog, int which)
					    	 {
					    		 
					    	 } 
					     }).show();
				lv = dlg.getListView();
			}
		});
        title_logo_img.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setData(Uri.parse("http://www.njau.edu.cn/"));
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
				//finish();
				//toastInfo("��������");
			}
		});
                
        relativewendu = (RelativeLayout) findViewById(R.id.relativewendu1);
        relativeshidu = (RelativeLayout) findViewById(R.id.relativeshidu1);
        relativeco2 = (RelativeLayout) findViewById(R.id.relativeco21);
        relativeguangzhao = (RelativeLayout) findViewById(R.id.relativeguangzhao1);
        relativesearch = (RelativeLayout) findViewById(R.id.relativesearch1);
//        relativesearch1 = (RelativeLayout) findViewById(R.id.relativesearch2);
//        relativesearch2 = (RelativeLayout) findViewById(R.id.relativesearch3);
        relativesearch.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
           	{
        		Intent intent = new Intent();
				intent.setClass(TestUIActivity.this, LineActivity.class);
				startActivity(intent);
           	}
        }); 
//        relativesearch1.setOnClickListener(new OnClickListener()
//        {
//        	public void onClick(View v)
//           	{
//        		Intent intent = new Intent();
//				intent.setClass(TestUIActivity.this, LineActivity1.class);
//				startActivity(intent);
//           	}
//        }); 
//        relativesearch2.setOnClickListener(new OnClickListener()
//        {
//        	public void onClick(View v)
//           	{
//        		Intent intent = new Intent();
//				intent.setClass(TestUIActivity.this, LineActivity2.class);
//				startActivity(intent);
//           	}
//        });   
        dbHelper=new DatabaseHelper(TestUIActivity.this,"information");
		
		SQLiteDatabase sql=dbHelper.getWritableDatabase();
		Cursor cursor=sql.rawQuery("select time from temp", null); 
       //cursor.moveToLast()是指查询数据库结果的最后一条
		if(cursor.moveToLast()){   
		
    	starttime = cursor.getString(cursor.getColumnIndex("time"));
//    	Log.v(ACTIVITY_SERVICE, starttime);
		}else{
			starttime=null;	}
				
        home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
        home_img_bn_Layout.setOnClickListener(clickListener_home);

        style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
        style_img_bn_layout.setOnClickListener(clickListener_style);
        
        cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
        cam_img_bn_layout.setOnClickListener(clickListener_cam);
        
        shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
        shopping_img_bn_layout.setOnClickListener(clickListener_shopping);
        
        show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
        show_img_bn_layout.setOnClickListener(clickListener_show);
        
        Looper looper = Looper.myLooper();//取得当前线程里的looper,这是主线程的looper
        mHandler1 = new MyHandler(looper);//构造一个handler使之可与looper通信
        HandlerThread handlerThread1 =new HandlerThread("myHandlerThread1");//����scoket�����߳�
        handlerThread1.start();
        handler1 = new Handler(handlerThread1.getLooper());
        handler1.post(new MyRunnable());           
        viewFlipper = (ViewFlipper) findViewById(R.id.mViewFliper_vf);
        mGestureDetector = new GestureDetector(this);
        viewFlipper.setOnTouchListener(this);
        viewFlipper.setLongClickable(true);
        viewFlipper.setOnClickListener(clickListener);
        displayRatio_selelct(currentPage);
        
//        MyScrollView myScrollView = (MyScrollView) findViewById(R.id.viewflipper_scrollview);
//        myScrollView.setOnTouchListener(onTouchListener);
//        myScrollView.setGestureDetector(mGestureDetector);
        
        home_img_bn_Layout.setSelected(true);
        
        thread.start();
}
    
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
    	if(keyCode==KeyEvent.KEYCODE_BACK)
    	{
    		isRun = false;
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
			    	/*	try {
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}*/
					    TestUIActivity.this.finish();
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

    class MyRunnable implements Runnable
    {        
    	private int count=36;
    	private String[] teststr=new String[]{
//    			"data@GZ@020301",
//    			"data@CO@020301",
//    			"data@Humi@020301",
//    			"data@Temp@020301", 

				"JZ01DataList@humi1",
				"JZ01DataList@humi2",
				"JZ01DataList@humi3",
				"JZ01DataList@ec",
				"JZ01DataList@flow",
				"JZ02DataList@humi1",
				"JZ02DataList@humi2",
				"JZ02DataList@humi3",
				"JZ02DataList@ec",
				"JZ02DataList@flow",
				"JZ03DataList@humi1",
				"JZ03DataList@humi2",
				"JZ03DataList@humi3",
				"JZ03DataList@humi4",
				"JZ03DataList@ec",
				"JZ03DataList@flow",
				"SP11DataList@temp1",
				"SP11DataList@temp2",
				"SP11DataList@ec",
				"SP11DataList@flow",
				"SP12DataList@temp1",
				"SP12DataList@temp2",
				"SP12DataList@ec",
				"SP12DataList@flow",
			"soil@Temp@020201",
			"soil@Humi@020201",
			"data@GZ@020201",
			"data@CO@020201",
			"data@Humi@020201",
			"data@Temp@020201",
			"soil@Temp@020101",
			"soil@Humi@020101",
			"data@GZ@020101",
			"data@CO@020101",
			"data@Humi@020101",
			"data@Temp@020101",
    			};
    	       
		@Override
		public void run() {		
			 String result=null;
			for(;count>0;count--){
			try {
				url =new URL(path);
				urlConn=(HttpURLConnection) url.openConnection();
				urlConn.setConnectTimeout(5000);
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setRequestMethod("POST");				
					OutputStream out =urlConn.getOutputStream();			
					out.write(teststr[count-1].getBytes());
					out.flush();
				
					while(urlConn.getContentLength()!=-1){
						int code=urlConn.getResponseCode();
						if(code==200)
						{
							//请求成功
							InputStream is=urlConn.getInputStream();						
							String text=readInputStream(is);//得到向中间件发送请求成功后返回的数据信息
							Message m = mHandler1.obtainMessage(1, 1, 1, text);//构造要传递的消息
//							Log.v(ACTIVITY_SERVICE, text);
						    mHandler1.sendMessage(m);//�发送消息:系统会自动调用handleMessage方法来处理消息
						    try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}  
							is.close();
							
							urlConn.disconnect();
							break;
						}
					}
			}catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}

			}
		}
 }

			public String readInputStream(InputStream is) throws IOException
			{
				   String temp = null;				
					ByteArrayOutputStream baos=new ByteArrayOutputStream();
					int len=0;
					byte []buffer =new byte[1024];
					if((len=is.read(buffer))!=-1)
					{
						baos.write(buffer,0,len);
					}
					is.close();
					baos.close();
					byte[]result=baos.toByteArray();
					//试着解析result里面的
					temp=new String(result);
					return temp;
				
			}	
			

	class MyHandler extends Handler
 	 {
    	public MyHandler(Looper looper)
    	{
    	  super(looper);
    	}
    	@Override
    	public void handleMessage(Message msg) 
    	{   //处理消息  将获取数据存入数据库
			getdatatobase(msg.obj.toString());
//			send_ToService.send_Message("123");
    	}
    }//接收到其它线程发送过来的msg时会调用，在这里用来更新控件状态
	
	 public void getdatatobase(String str)
	 {
		 //Log.v("getdata", str);
		arr=str.split("@");//data@AT@5@0302@27.8,2014-01-01 11:11:11@32.5,2014-01-01 11:12:11@32.6,2014-01-01 11:14:11@32.7,2014-01-01 11:18:11@32.8,2014-01-01 11:22:11
		 String request = arr[0];
		String sensor=arr[1];
		String times=arr[2];//次数
		 String number = "";
		 if (request.equals("data")||request.equals("soil")){
			 number=arr[3];//节点编号
		 }
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());

		 count = Integer.parseInt(times); //将times的字符串变为int类型


	    if(count==0){
	    	Toast.makeText(TestUIActivity.this, "数据库中无新的环境参数数据!", Toast.LENGTH_SHORT);
	    }
		dbHelper=new DatabaseHelper(TestUIActivity.this,"information");
		
		SQLiteDatabase sql=dbHelper.getWritableDatabase();
		
		ContentValues values =new ContentValues(); 
		tempnum1=(TextView)findViewById(R.id.temp1); 
	    guangzhaonum1=(TextView)findViewById(R.id.sun1);     
	    shidunum1=(TextView)findViewById(R.id.humi1);	     
	    co2num1=(TextView)findViewById(R.id.co21);
	    sh=(TextView)findViewById(R.id.sh1);
	    st=(TextView)findViewById(R.id.st1);
	    tempnum2=(TextView)findViewById(R.id.temp2); 
	    guangzhaonum2=(TextView)findViewById(R.id.sun2);     
	    shidunum2=(TextView)findViewById(R.id.humi2);	     
	    co2num2=(TextView)findViewById(R.id.co22);
	    sh1=(TextView)findViewById(R.id.sh2);
	    st1=(TextView)findViewById(R.id.st2);
		if("Temp".equals(sensor)&&"020101".equals(number))
		{
			while(count>2)
			{					
				count--;
				String arrnum=arr[count+2];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Temp",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("temp", null, values);
				if(count==2){
				tempnum1.setText(strnum[0]+"°C");
				}
			}				
			
		}
		else if("Humi".equals(sensor)&&"020101".equals(number)){
			while(count>2)
			{	
				count--;
				String arrnum=arr[count+2];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Humi",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("humi", null, values);
				if(count==2){
				 shidunum1.setText(strnum[0]+" %");
				}
			}		
		}
	 else if("GZ".equals(sensor)&&"020101".equals(number)){
		
		 while(count>2)
			{	
				count--;
				String arrnum=arr[count+2];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Lumi",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("lumi", null, values);
				if(count==2){
				guangzhaonum1.setText(strnum[0]+" Lux");
				}
			}	
			
		}else if("CO".equals(sensor)&&"020101".equals(number)){
			
			while(count>2)
			{
				count--;
				String arrnum=arr[count+2];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Co2",strnum[0]);
				values.put("time",strnum[1]);
 				sql.insert("co2", null, values);
				if(count==2){
				co2num1.setText(strnum[0]+" ppm");
				}
			}
				
		}
		else if("Temp".equals(sensor)&&"020201".equals(number))
		{
			while(count>4)
			{					
				count--;
				String arrnum=arr[count];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Temp",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("temp", null, values);
				if(count==4){
				tempnum2.setText(strnum[0]+"°C");
				}
			}				
			
		}
		else if("Humi".equals(sensor)&&"020201".equals(number)){
			while(count>4)
			{	
				count--;
				String arrnum=arr[count];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Humi",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("humi", null, values);
				if(count==4){
				 shidunum2.setText(strnum[0]+" %");
				}
			}		
		}
	 else if("GZ".equals(sensor)&&"020201".equals(number)){
		
		 while(count>4)
			{	
				count--;
				String arrnum=arr[count];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Lumi",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("lumi", null, values);
				if(count==4){
				guangzhaonum2.setText(strnum[0]+" Lux");
				}
			}	
			
		}else if("CO".equals(sensor)&&"020201".equals(number)){
			
			while(count>4)
			{
				count--;
				String arrnum=arr[count];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("Co2",strnum[0]);
				values.put("time",strnum[1]);
 				sql.insert("co2", null, values);
				if(count==4){
				co2num2.setText(strnum[0]+" ppm");
				}
			}
				
		}
		else if("temp".equals(sensor)&&"020101".equals(number))
		{
			while(count>3)
			{					
				count--;
				String arrnum=arr[count+1];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("soilT",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("soiltemp", null, values);
				if(count==3){
				st.setText(strnum[0]+"°C");
				}
			}				
			
		}
		else if("humi".equals(sensor)&&"020101".equals(number)){
			while(count>3)
			{	
				count--;
				String arrnum=arr[count+1];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("soilH",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("soilhumi", null, values);
				if(count==3){
				 sh.setText(strnum[0]+" %");
				}
			}		
		}
	 else if("temp".equals(sensor)&&"020201".equals(number)){
		
		 while(count>3)
			{	
				count--;
				String arrnum=arr[count+1];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("soilT",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("soiltemp", null, values);
				if(count==3){
				st1.setText(strnum[0]+"°C");
				}
			}	
			
		}else if("humi".equals(sensor)&&"020201".equals(number)){
			
			while(count>3)
			{
				count--;
				String arrnum=arr[count+1];
				String []strnum=arrnum.split(",");
				values.put("id",number);
				values.put("soilH",strnum[0]);
				values.put("time",strnum[1]);
 				sql.insert("soilhumi", null, values);
				if(count==3){
				sh1.setText(strnum[0]+" %");
				}
			}
				
		}
		/*
		* 写30多个else if
		* 注意修改if判断，参考中间件返回值
		* 修改id，hum/tmp/ec/flow，这两个值
		* 修改Hum表名称Hum，其余不动
		 */
		else if ("JZ01DataList".equals(request)&&"humi1".equals(sensor)){
			while (count>=2){
				count--;
				Log.d("tag","count:"+Integer.toString(count));
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0101);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ01DataList".equals(request)&&"humi2".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0102);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ01DataList".equals(request)&&"humi3".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0103);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ01DataList".equals(request)&&"ec".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0104);
				values.put("ec1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("ec", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ01DataList".equals(request)&&"flow".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0105);
				values.put("flow1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("flow", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ02DataList".equals(request)&&"humi1".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0201);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ02DataList".equals(request)&&"humi2".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0202);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ02DataList".equals(request)&&"humi3".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0203);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ02DataList".equals(request)&&"ec".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0204);
				values.put("ec1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("ec", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ02DataList".equals(request)&&"flow".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0205);
				values.put("flow1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("flow", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"humi1".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0301);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"humi2".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0302);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"humi3".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0303);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"humi4".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0304);
				values.put("hum1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("hum", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"ec".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0305);
				values.put("ec1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("ec", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("JZ03DataList".equals(request)&&"flow".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0305);
				values.put("flow1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("flow", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP11DataList".equals(request)&&"temp1".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0401);
				values.put("tmp1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("tmp", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP11DataList".equals(request)&&"temp2".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0402);
				values.put("tmp1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("tmp", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP11DataList".equals(request)&&"ec".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0403);
				values.put("ec1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("ec", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP11DataList".equals(request)&&"flow".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0404);
				values.put("flow1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("flow", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP12DataList".equals(request)&&"temp1".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0501);
				values.put("tmp1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("tmp", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP12DataList".equals(request)&&"temp2".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0502);
				values.put("tmp1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("tmp", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP12DataList".equals(request)&&"ec".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0503);
				values.put("ec1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("ec", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else if ("SP12DataList".equals(request)&&"flow".equals(sensor)){
			while (count>=2){
				count--;
				String arrnum=arr[count+3];
				String[] strnum=arrnum.split(",");
				values.put("id",0504);
				values.put("flow1",strnum[0]);
				values.put("time",strnum[1]);
				sql.insert("flow", null, values);
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
		else{
		Toast.makeText(TestUIActivity.this, "数据出问题!", Toast.LENGTH_LONG);
		}
	 }

    
    private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//toastInfo("点击事件");
		}
	};
    private OnTouchListener onTouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			return mGestureDetector.onTouchEvent(event);
		}
	};
    
    Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case SHOW_NEXT:
				if (showNext) {
					 showNextView();
				} else {
					showPreviousView();
				}
				break;
			default:
				break;
			}
		}
    };
    private String getDate(){
    	Date date = new Date();
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    	int w = c.get(Calendar.DAY_OF_WEEK) - 1 ;
    	if (w < 0) {
			w = 0;
		}
    	int month=(int)c.get(Calendar.MONTH)+1;
    	String mDate = c.get(Calendar.YEAR)+"年" + month + "月" + c.get(Calendar.DATE) + "日" + weekDays[w];
    	return mDate;
    }

   private static String getDate1(){
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		String time=df.format(date);
		return time;
    }
    private OnClickListener clickListener_home = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				home_img_bn_Layout.setSelected(true);
				style_img_bn_layout.setSelected(false);
				cam_img_bn_layout.setSelected(false);
				shopping_img_bn_layout.setSelected(false);
				show_img_bn_layout.setSelected(false);
		}
	};
	private OnClickListener clickListener_style = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(true);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);			
			Intent intent = new Intent();
			intent.setClass(TestUIActivity.this, IrrigationActivity.class);
			startActivity(intent);
//			try {
//				socket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
			
		}
	};
	private OnClickListener clickListener_cam = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(true);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(TestUIActivity.this, CurveActivity.class);
			startActivity(intent);
		/*	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_shopping = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(true);
			show_img_bn_layout.setSelected(false);			
			Intent intent = new Intent();
			intent.setClass(TestUIActivity.this, ControlActivity.class);
			startActivity(intent);
		/*	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};
	private OnClickListener clickListener_show = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(false);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(true);
			Intent intent = new Intent();
			intent.setClass(TestUIActivity.this, ContactActivity.class);
			startActivity(intent);
//			try {
//				socket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
//			//toastInfo("����ҵļ�����ת");
		}
	};
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	//���ƻ���,����e1����ָ��һ�ΰ�����Ļ����㣬e2��̧����ָ�뿪��Ļ���յ�;velocityX,velocityY��ָ���Ϲ���������X,Y�ϵ��ٶ�
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.e("view", "onFling");
		if (e1.getX() - e2.getX()> FLING_MIN_DISTANCE  
                && Math.abs(velocityX) > FLING_MIN_VELOCITY ) {
			Log.e("fling", "left");
			showNextView();
			if(sp.getBoolean("IFHUADONG", true))
			{
				Intent intent = new Intent();
				intent.setClass(TestUIActivity.this, VideoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out); 
				finish();
			}
			showNext = true;
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE  
                && Math.abs(velocityX) > FLING_MIN_VELOCITY){
			Log.e("fling", "right");
			showPreviousView();
			showNext = false;
		}
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	//���û��ڽ����Ϲ���ʱ�����÷���
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}
	
	Thread thread = new Thread(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRun){
				try {
					Thread.sleep(1000 * 60);
					Message msg = new Message();
					msg.what = SHOW_NEXT;
					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	};
	private void showNextView(){

		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
		viewFlipper.showNext();
		currentPage ++;
		if (currentPage == viewFlipper.getChildCount()) {
			displayRatio_normal(currentPage - 1);
			currentPage = 0;
			displayRatio_selelct(currentPage);
		} else {
			displayRatio_selelct(currentPage);
			displayRatio_normal(currentPage - 1);
		}
		Log.e("currentPage", currentPage + "");		
		
	}
	private void showPreviousView(){
		displayRatio_selelct(currentPage);
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
		viewFlipper.showPrevious();
		currentPage --;
		if (currentPage == -1) {
			displayRatio_normal(currentPage + 1);
			currentPage = viewFlipper.getChildCount() - 1;
			displayRatio_selelct(currentPage);
		} else {
			displayRatio_selelct(currentPage);
			displayRatio_normal(currentPage + 1);
		}
		Log.e("currentPage", currentPage + "");		
	}
	private void displayRatio_selelct(int id){
		int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(true);
	}
	private void displayRatio_normal(int id){
		int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(false);
	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		finish();
		super.onDestroy();
	}
	
	private void toastInfo(String string){
		Toast.makeText(TestUIActivity.this, string, Toast.LENGTH_SHORT).show();
	}

}