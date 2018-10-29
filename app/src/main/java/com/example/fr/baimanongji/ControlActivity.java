package com.example.fr.baimanongji;



import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class ControlActivity extends TabActivity implements OnTouchListener, OnGestureListener{
	private GestureDetector mGestureDetector;
	boolean flag;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	private SharedPreferences sp;
	String path="http://192.168.43.232:8888/myApps";
//	String path="http://192.168.1.200:8888/myApps";
//	String path="http://192.168.0.100:8888/myApps";
	URL url=null;
	HttpURLConnection urlConn=null;
	private MyHandler mHandler1; 
	
	 private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout, 
	  shopping_img_bn_layout, show_img_bn_layout;
	 
	 public String send_Commnd;//发送的指令
	//温室控制权限判断
	//温室编号

	//温室管理等级
	 public String greenhouse_admin=BMWActivity.admin;
	//温室1
		private TextView tv01;  
	    private TextView tv02;  
	    private TextView tv03;  
		private TextView tv04;  
	    private TextView tv05;  
	    private TextView tv06;	    private TextView tv07;
	    private TextView tv08;
	    private TextView tv09;
	    private TextView tv10;
	      
	    private LinearLayout layout01;  
	    private LinearLayout layout02;  
	    private LinearLayout layout03;  
	    private LinearLayout layout04;  
	    private LinearLayout layout05;  
	    private LinearLayout layout06;
	    private LinearLayout layout07;
	    private LinearLayout layout08;
	    private LinearLayout layout09;
	    private LinearLayout layout10;
	//温室2
	    private TextView tv11;  
	    private TextView tv12;  
	    private TextView tv13;  
		private TextView tv14;  
	    private TextView tv15;  
	    private TextView tv16;
	    private TextView tv17;
	    private TextView tv18;
	    private TextView tv19;
	    private TextView tv20;
	      
	    private LinearLayout layout11;  
	    private LinearLayout layout12;  
	    private LinearLayout layout13;  
	    private LinearLayout layout14;  
	    private LinearLayout layout15;  
	    private LinearLayout layout16;
	    private LinearLayout layout17;
	    private LinearLayout layout18; 
	    private LinearLayout layout19;
	    private LinearLayout layout20;
	//温室3
	    private TextView tv21;  
	    private TextView tv22;  
	    private TextView tv23;  
		private TextView tv24;  
	    private TextView tv25;  
	    private TextView tv26;
	    private TextView tv27;
	    private TextView tv28;
	    private TextView tv29;
	    private TextView tv30;
	      
	    private LinearLayout layout21;  
	    private LinearLayout layout22;  
	    private LinearLayout layout23;  
	    private LinearLayout layout24;  
	    private LinearLayout layout25;  
	    private LinearLayout layout26;
	    private LinearLayout layout27;
	    private LinearLayout layout28;
	    private LinearLayout layout29;
	    private LinearLayout layout30;


	//温室1 手自动指令
		private String shoudongcommand01_on="MCMD@02010002";
	  	private String zidongcommand01_on="MCMD@02010001";
	//温室一手自动按钮
		private RadioButton shoudongbutton_01_on=null; 
		private RadioButton zidongbutton_01_on=null;

	//1温室外遮阳指令
		private String waizheyangcommand01_on="MCMD@02010101";
		private String waizheyangcommand01_off="MCMD@02010102";
		private String waizheyangcommand01_back="MCMD@02010103";
		private String waizheyangcommand01_backoff="MCMD@02010104";
				
		private RadioButton waizheyang_01_on=null;  
		private RadioButton waizheyang_01_off=null;
		private RadioButton waizheyang_01_back=null;  
		private RadioButton waizheyang_01_backoff=null;

	//1号温室顶开窗
		private String dingkaichuangcommand01_on="MCMD@02010201";//开�
		private String dingkaichuangcommand01_back="MCMD@02010203";//关
		private String dingkaichuangcommand01_off="MCMD@02010202";//开停
		private String dingkaichuangcommand01_backstop="MCMD@02010204";//关停
	//顶开窗电机组
		private RadioButton dingkaichuang_01_on=null; 
		private RadioButton dingkaichuang_01_back=null;
		private RadioButton dingkaichuang_01_off=null;
		private RadioButton dingkaichuang_01_backoff=null;

	//温室一内保温
		private String neibaowencommand01_on="MCMD@02010301";//开
		private String neibaowencommand01_off="MCMD@02010302";//开停
		private String neibaowencommand01_back="MCMD@02010303";//关
		private String neibaowencommand01_backstop="MCMD@02010304";//关停
	//内保温电机组
		private RadioButton neibaowen_01_on=null; 
		private RadioButton neibaowen_01_back=null;
		private RadioButton neibaowen_01_off=null;
		private RadioButton neibaowen_01_backstop=null;

	//温室一湿帘外翻
	private String shilianwaifancommand01_on="MCMD@02010401";//开
	private String shilianwaifancommand01_off="MCMD@02010402";//关
	private String shilianwaifancommand01_back="MCMD@02010403";//关
	private String shilianwaifancommand01_backoff="MCMD@02010404";//关停
	//温室一湿帘外翻
	private RadioButton  shilianwaifan_01_on =null;
	private RadioButton  shilianwaifan_01_off =null;
	private RadioButton shilianwaifan_01_back=null;
	private RadioButton shilianwaifan_01_backoff=null;

	//温室一轴流风机
	private String zhouliufengjicommand01_on="MCMD@02010701";//开
	private String zhouliufengjicommand01_off="MCMD@02010702";//关
	//温室一轴流风机电机
	private RadioButton  zhouliufengji_01_on =null;
	private RadioButton  zhouliufengji_01_off =null;

	//温室一补光灯
	private String buguangcommand01_on="MCMD@02010801";
	private String buguangcommand01_off="MCMD@02010802";
	private String buguangcommand02_on="MCMD@02010803";
	private String buguangcommand02_off="MCMD@02010804";
	private String buguangcommand03_on="MCMD@02010805";
	private String buguangcommand03_off="MCMD@02010806";
	//温室一补光灯
	private RadioButton buguangdeng_01_on=null;
	private RadioButton buguangdeng_01_off=null;
	private RadioButton buguangdeng_02_on=null;
	private RadioButton buguangdeng_02_off=null;
	private RadioButton buguangdeng_03_on=null;
	private RadioButton buguangdeng_03_off=null;

	//温室一湿帘水泵
	private String shilianshuibengcommand01_on="MCMD@02010501";
	private String shilianshuibengcommand01_off="MCMD@02010502";
	//温室一湿帘水泵初始化
	private RadioButton shilianshuibeng_01_on=null;
	private RadioButton shilianshuibeng_01_off=null;

	//温室一泵站水泵
	private String bengzhanshuibengcommand01_on="MCMD@02010601";
	private String bengzhanshuibengcommand01_off="MCMD@02010602";
	private String bengzhanshuibengcommand02_on="MCMD@02010603";
	private String bengzhanshuibengcommand02_off="MCMD@02010604";
	//温室一泵站水泵初始化
	private RadioButton bengzhanshuibeng_01_on=null;
	private RadioButton bengzhanshuibeng_01_off=null;
	private RadioButton bengzhanshuibeng_02_on=null;
	private RadioButton bengzhanshuibeng_02_off=null;
	//温室一电磁阀
	private String diancifacommand01_on="MCMD@02010901";
	private String diancifacommand01_off="MCMD@02010902";
	private String diancifacommand02_on="MCMD@02010903";
	private String diancifacommand02_off="MCMD@02010904";
	private String diancifacommand03_on="MCMD@02010905";
	private String diancifacommand03_off="MCMD@02010906";
	private String diancifacommand04_on="MCMD@02010907";
	private String diancifacommand04_off="MCMD@02010908";
	private String diancifacommand05_on="MCMD@02010909";
	private String diancifacommand05_off="MCMD@02010910";
	private String diancifacommand06_on="MCMD@02010911";
	private String diancifacommand06_off="MCMD@02010912";
	private String diancifacommand07_on="MCMD@02010913";
	private String diancifacommand07_off="MCMD@02010914";
	private String diancifacommand08_on="MCMD@02010915";
	private String diancifacommand08_off="MCMD@02010916";
	private String diancifacommand09_on="MCMD@02010917";
	private String diancifacommand09_off="MCMD@02010918";
	private String diancifacommand010_on="MCMD@02010919";
	private String diancifacommand010_off="MCMD@02010920";
	private String diancifacommand011_on="MCMD@02010921";
	private String diancifacommand011_off="MCMD@02010922";
	private String diancifacommand012_on="MCMD@02010923";
	private String diancifacommand012_off="MCMD@02010924";
	private String diancifacommand013_on="MCMD@02010925";
	private String diancifacommand013_off="MCMD@02010926";
	private String diancifacommand014_on="MCMD@02010927";
	private String diancifacommand014_off="MCMD@02010928";
	private String diancifacommand015_on="MCMD@02010929";
	private String diancifacommand015_off="MCMD@02010930";
	private String diancifacommand016_on="MCMD@02010931";
	private String diancifacommand016_off="MCMD@02010932";
	//温室一电磁阀初始化
	private RadioButton diancifa_01_on=null;
	private RadioButton diancifa_01_off=null;
	private RadioButton diancifa_02_on=null;
	private RadioButton diancifa_02_off=null;
	private RadioButton diancifa_03_on=null;
	private RadioButton diancifa_03_off=null;
	private RadioButton diancifa_04_on=null;
	private RadioButton diancifa_04_off=null;
	private RadioButton diancifa_05_on=null;
	private RadioButton diancifa_05_off=null;
	private RadioButton diancifa_06_on=null;
	private RadioButton diancifa_06_off=null;
	private RadioButton diancifa_07_on=null;
	private RadioButton diancifa_07_off=null;
	private RadioButton diancifa_08_on=null;
	private RadioButton diancifa_08_off=null;
	private RadioButton diancifa_09_on=null;
	private RadioButton diancifa_09_off=null;
	private RadioButton diancifa_010_on=null;
	private RadioButton diancifa_010_off=null;
	private RadioButton diancifa_011_on=null;
	private RadioButton diancifa_011_off=null;
	private RadioButton diancifa_012_on=null;
	private RadioButton diancifa_012_off=null;
	private RadioButton diancifa_013_on=null;
	private RadioButton diancifa_013_off=null;
	private RadioButton diancifa_014_on=null;
	private RadioButton diancifa_014_off=null;
	private RadioButton diancifa_015_on=null;
	private RadioButton diancifa_015_off=null;
	private RadioButton diancifa_016_on=null;
	private RadioButton diancifa_016_off=null;


	//温室2 手自动指令
	private String shoudongcommand11_on="MCMD@02010002";
	private String zidongcommand11_on="MCMD@02010001";
	//温室2手自动按钮
	private RadioButton shoudongbutton_11_on=null;
	private RadioButton zidongbutton_11_on=null;

	//2温室外遮阳指令
	private String waizheyangcommand11_on="MCMD@02010105";
	private String waizheyangcommand11_off="MCMD@02010106";
	private String waizheyangcommand11_back="MCMD@02010107";
	private String waizheyangcommand11_backoff="MCMD@02010108";

	private RadioButton waizheyang_11_on=null;
	private RadioButton waizheyang_11_off=null;
	private RadioButton waizheyang_11_back=null;
	private RadioButton waizheyang_11_backoff=null;

	//2号温室顶开窗
	private String dingkaichuangcommand11_on="MCMD@02010205";//开
	private String dingkaichuangcommand11_back="MCMD@02010207";//关
	private String dingkaichuangcommand11_off="MCMD@02010206";//开停
	private String dingkaichuangcommand11_backstop="MCMD@02010208";//关停
	//顶开窗电机组
	private RadioButton dingkaichuang_11_on=null;
	private RadioButton dingkaichuang_11_back=null;
	private RadioButton dingkaichuang_11_off=null;
	private RadioButton dingkaichuang_11_backoff=null;

	//温室2内保温
	private String neibaowencommand11_on="MCMD@02010305";//开
	private String neibaowencommand11_off="MCMD@02010306";//开停
	private String neibaowencommand11_back="MCMD@02010307";//关
	private String neibaowencommand11_backstop="MCMD@02010308";//关停
	//内保温电机组
	private RadioButton neibaowen_11_on=null;
	private RadioButton neibaowen_11_back=null;
	private RadioButton neibaowen_11_off=null;
	private RadioButton neibaowen_11_backstop=null;

	//温室2湿帘外翻
	private String shilianwaifancommand11_on="MCMD@02010405";//开
	private String shilianwaifancommand11_off="MCMD@02010406";//关
	private String shilianwaifancommand11_back="MCMD@02010407";//关
	private String shilianwaifancommand11_backoff="MCMD@02010408";//关停
	//温室2湿帘外翻
	private RadioButton  shilianwaifan_11_on =null;
	private RadioButton  shilianwaifan_11_off =null;
	private RadioButton shilianwaifan_11_back=null;
	private RadioButton shilianwaifan_11_backoff=null;

	//温室2轴流风机
	private String zhouliufengjicommand11_on="MCMD@02010703";//开
	private String zhouliufengjicommand11_off="MCMD@02010704";//关
	//温室2轴流风机电机
	private RadioButton  zhouliufengji_11_on =null;
	private RadioButton  zhouliufengji_11_off =null;

	//温室2补光灯
	private String buguangcommand11_on="MCMD@02010807";
	private String buguangcommand11_off="MCMD@02010808";
	private String buguangcommand12_on="MCMD@02010809";
	private String buguangcommand12_off="MCMD@02010810";
	private String buguangcommand13_on="MCMD@02010811";
	private String buguangcommand13_off="MCMD@02010812";
	//温室2补光灯
	private RadioButton buguangdeng_11_on=null;
	private RadioButton buguangdeng_11_off=null;
	private RadioButton buguangdeng_12_on=null;
	private RadioButton buguangdeng_12_off=null;
	private RadioButton buguangdeng_13_on=null;
	private RadioButton buguangdeng_13_off=null;

	//温室2湿帘水泵
	private String shilianshuibengcommand11_on="MCMD@02010501";
	private String shilianshuibengcommand11_off="MCMD@02010502";
	//温室2湿帘水泵初始化
	private RadioButton shilianshuibeng_11_on=null;
	private RadioButton shilianshuibeng_11_off=null;

	//温室2泵站水泵
	private String bengzhanshuibengcommand11_on="MCMD@02010601";
	private String bengzhanshuibengcommand11_off="MCMD@02010602";
	private String bengzhanshuibengcommand12_on="MCMD@02010603";
	private String bengzhanshuibengcommand12_off="MCMD@02010604";
	//温室2泵站水泵初始化
	private RadioButton bengzhanshuibeng_11_on=null;
	private RadioButton bengzhanshuibeng_11_off=null;
	private RadioButton bengzhanshuibeng_12_on=null;
	private RadioButton bengzhanshuibeng_12_off=null;
	//温室2电磁阀
	private String diancifacommand11_on="MCMD@02010901";
	private String diancifacommand11_off="MCMD@02010902";
	private String diancifacommand12_on="MCMD@02010903";
	private String diancifacommand12_off="MCMD@02010904";
	private String diancifacommand13_on="MCMD@02010905";
	private String diancifacommand13_off="MCMD@02010906";
	private String diancifacommand14_on="MCMD@02010907";
	private String diancifacommand14_off="MCMD@02010908";
	private String diancifacommand15_on="MCMD@02010909";
	private String diancifacommand15_off="MCMD@02010910";
	private String diancifacommand16_on="MCMD@02010911";
	private String diancifacommand16_off="MCMD@02010912";
	private String diancifacommand17_on="MCMD@02010913";
	private String diancifacommand17_off="MCMD@02010914";
	private String diancifacommand18_on="MCMD@02010915";
	private String diancifacommand18_off="MCMD@02010916";
	private String diancifacommand19_on="MCMD@02010917";
	private String diancifacommand19_off="MCMD@02010918";
	private String diancifacommand110_on="MCMD@02010919";
	private String diancifacommand110_off="MCMD@02010920";
	private String diancifacommand111_on="MCMD@02010921";
	private String diancifacommand111_off="MCMD@02010922";
	private String diancifacommand112_on="MCMD@02010923";
	private String diancifacommand112_off="MCMD@02010924";
	private String diancifacommand113_on="MCMD@02010925";
	private String diancifacommand113_off="MCMD@02010926";
	private String diancifacommand114_on="MCMD@02010927";
	private String diancifacommand114_off="MCMD@02010928";
	private String diancifacommand115_on="MCMD@02010929";
	private String diancifacommand115_off="MCMD@02010930";
	private String diancifacommand116_on="MCMD@02010931";
	private String diancifacommand116_off="MCMD@02010932";
	//温室2电磁阀初始化
	private RadioButton diancifa_11_on=null;
	private RadioButton diancifa_11_off=null;
	private RadioButton diancifa_12_on=null;
	private RadioButton diancifa_12_off=null;
	private RadioButton diancifa_13_on=null;
	private RadioButton diancifa_13_off=null;
	private RadioButton diancifa_14_on=null;
	private RadioButton diancifa_14_off=null;
	private RadioButton diancifa_15_on=null;
	private RadioButton diancifa_15_off=null;
	private RadioButton diancifa_16_on=null;
	private RadioButton diancifa_16_off=null;
	private RadioButton diancifa_17_on=null;
	private RadioButton diancifa_17_off=null;
	private RadioButton diancifa_18_on=null;
	private RadioButton diancifa_18_off=null;
	private RadioButton diancifa_19_on=null;
	private RadioButton diancifa_19_off=null;
	private RadioButton diancifa_110_on=null;
	private RadioButton diancifa_110_off=null;
	private RadioButton diancifa_111_on=null;
	private RadioButton diancifa_111_off=null;
	private RadioButton diancifa_112_on=null;
	private RadioButton diancifa_112_off=null;
	private RadioButton diancifa_113_on=null;
	private RadioButton diancifa_113_off=null;
	private RadioButton diancifa_114_on=null;
	private RadioButton diancifa_114_off=null;
	private RadioButton diancifa_115_on=null;
	private RadioButton diancifa_115_off=null;
	private RadioButton diancifa_116_on=null;
	private RadioButton diancifa_116_off=null;


	//温室3 手自动指令
	private String shoudongcommand21_on="MCMD@02010002";
	private String zidongcommand21_on="MCMD@02010001";
	//温室3手自动按钮
	private RadioButton shoudongbutton_21_on=null;
	private RadioButton zidongbutton_21_on=null;

	//3温室外遮阳指令
	private String waizheyangcommand21_on="MCMD@02010109";
	private String waizheyangcommand21_off="MCMD@02010110";
	private String waizheyangcommand21_back="MCMD@02010111";
	private String waizheyangcommand21_backoff="MCMD@02010112";

	private RadioButton waizheyang_21_on=null;
	private RadioButton waizheyang_21_off=null;
	private RadioButton waizheyang_21_back=null;
	private RadioButton waizheyang_21_backoff=null;

	//3号温室顶开窗
	private String dingkaichuangcommand21_on="MCMD@02010209";//开
	private String dingkaichuangcommand21_back="MCMD@02010211";//关
	private String dingkaichuangcommand21_off="MCMD@02010210";//开停
	private String dingkaichuangcommand21_backstop="MCMD@02010212";//关停
	//顶开窗电机组
	private RadioButton dingkaichuang_21_on=null;
	private RadioButton dingkaichuang_21_back=null;
	private RadioButton dingkaichuang_21_off=null;
	private RadioButton dingkaichuang_21_backoff=null;

	//温室3内保温
	private String neibaowencommand21_on="MCMD@02010309";//开
	private String neibaowencommand21_off="MCMD@02010310";//开停
	private String neibaowencommand21_back="MCMD@02010311";//关
	private String neibaowencommand21_backstop="MCMD@02010312";//关停
	//内保温电机组
	private RadioButton neibaowen_21_on=null;
	private RadioButton neibaowen_21_back=null;
	private RadioButton neibaowen_21_off=null;
	private RadioButton neibaowen_21_backstop=null;

	//温室3湿帘外翻
	private String shilianwaifancommand21_on="MCMD@02010409";//开
	private String shilianwaifancommand21_off="MCMD@02010410";//关
	private String shilianwaifancommand21_back="MCMD@02010411";//关
	private String shilianwaifancommand21_backoff="MCMD@02010412";//关停
	//温室3湿帘外翻
	private RadioButton  shilianwaifan_21_on =null;
	private RadioButton  shilianwaifan_21_off =null;
	private RadioButton shilianwaifan_21_back=null;
	private RadioButton shilianwaifan_21_backoff=null;

	//温室3轴流风机
	private String zhouliufengjicommand21_on="MCMD@02010705";//开
	private String zhouliufengjicommand21_off="MCMD@02010706";//关
	//温室3轴流风机电机
	private RadioButton  zhouliufengji_21_on =null;
	private RadioButton  zhouliufengji_21_off =null;

	//温室3补光灯
	private String buguangcommand21_on="MCMD@02010813";
	private String buguangcommand21_off="MCMD@02010814";
	private String buguangcommand22_on="MCMD@02010815";
	private String buguangcommand22_off="MCMD@02010816";
	private String buguangcommand23_on="MCMD@02010817";
	private String buguangcommand23_off="MCMD@02010818";
	//温室3补光灯
	private RadioButton buguangdeng_21_on=null;
	private RadioButton buguangdeng_21_off=null;
	private RadioButton buguangdeng_22_on=null;
	private RadioButton buguangdeng_22_off=null;
	private RadioButton buguangdeng_23_on=null;
	private RadioButton buguangdeng_23_off=null;

	//温室3湿帘水泵
	private String shilianshuibengcommand21_on="MCMD@02010501";
	private String shilianshuibengcommand21_off="MCMD@02010502";
	//温室3湿帘水泵初始化
	private RadioButton shilianshuibeng_21_on=null;
	private RadioButton shilianshuibeng_21_off=null;

	//温室3泵站水泵
	private String bengzhanshuibengcommand21_on="MCMD@02010601";
	private String bengzhanshuibengcommand21_off="MCMD@02010602";
	private String bengzhanshuibengcommand22_on="MCMD@02010603";
	private String bengzhanshuibengcommand22_off="MCMD@02010604";
	//温室3泵站水泵初始化
	private RadioButton bengzhanshuibeng_21_on=null;
	private RadioButton bengzhanshuibeng_21_off=null;
	private RadioButton bengzhanshuibeng_22_on=null;
	private RadioButton bengzhanshuibeng_22_off=null;
	//温室3电磁阀
	private String diancifacommand21_on="MCMD@02010901";
	private String diancifacommand21_off="MCMD@02010902";
	private String diancifacommand22_on="MCMD@02010903";
	private String diancifacommand22_off="MCMD@02010904";
	private String diancifacommand23_on="MCMD@02010905";
	private String diancifacommand23_off="MCMD@02010906";
	private String diancifacommand24_on="MCMD@02010907";
	private String diancifacommand24_off="MCMD@02010908";
	private String diancifacommand25_on="MCMD@02010909";
	private String diancifacommand25_off="MCMD@02010910";
	private String diancifacommand26_on="MCMD@02010911";
	private String diancifacommand26_off="MCMD@02010912";
	private String diancifacommand27_on="MCMD@02010913";
	private String diancifacommand27_off="MCMD@02010914";
	private String diancifacommand28_on="MCMD@02010915";
	private String diancifacommand28_off="MCMD@02010916";
	private String diancifacommand29_on="MCMD@02010917";
	private String diancifacommand29_off="MCMD@02010918";
	private String diancifacommand210_on="MCMD@02010919";
	private String diancifacommand210_off="MCMD@02010920";
	private String diancifacommand211_on="MCMD@02010921";
	private String diancifacommand211_off="MCMD@02010922";
	private String diancifacommand212_on="MCMD@02010923";
	private String diancifacommand212_off="MCMD@02010924";
	private String diancifacommand213_on="MCMD@02010925";
	private String diancifacommand213_off="MCMD@02010926";
	private String diancifacommand214_on="MCMD@02010927";
	private String diancifacommand214_off="MCMD@02010928";
	private String diancifacommand215_on="MCMD@02010929";
	private String diancifacommand215_off="MCMD@02010930";
	private String diancifacommand216_on="MCMD@02010931";
	private String diancifacommand216_off="MCMD@02010932";
	//温室3电磁阀初始化
	private RadioButton diancifa_21_on=null;
	private RadioButton diancifa_21_off=null;
	private RadioButton diancifa_22_on=null;
	private RadioButton diancifa_22_off=null;
	private RadioButton diancifa_23_on=null;
	private RadioButton diancifa_23_off=null;
	private RadioButton diancifa_24_on=null;
	private RadioButton diancifa_24_off=null;
	private RadioButton diancifa_25_on=null;
	private RadioButton diancifa_25_off=null;
	private RadioButton diancifa_26_on=null;
	private RadioButton diancifa_26_off=null;
	private RadioButton diancifa_27_on=null;
	private RadioButton diancifa_27_off=null;
	private RadioButton diancifa_28_on=null;
	private RadioButton diancifa_28_off=null;
	private RadioButton diancifa_29_on=null;
	private RadioButton diancifa_29_off=null;
	private RadioButton diancifa_210_on=null;
	private RadioButton diancifa_210_off=null;
	private RadioButton diancifa_211_on=null;
	private RadioButton diancifa_211_off=null;
	private RadioButton diancifa_212_on=null;
	private RadioButton diancifa_212_off=null;
	private RadioButton diancifa_213_on=null;
	private RadioButton diancifa_213_off=null;
	private RadioButton diancifa_214_on=null;
	private RadioButton diancifa_214_off=null;
	private RadioButton diancifa_215_on=null;
	private RadioButton diancifa_215_off=null;
	private RadioButton diancifa_216_on=null;
	private RadioButton diancifa_216_off=null;





	private static final String TAG = null;

	private Handler handler;


	//					private String host_ip="10.45.187.40";//本机
//					private String host_ip="218.4.112.124";//本机
//					private String host_ip="127.0.0.1";
//					private String host_ip="172.20.4.145";
//					private int port=51236;
	private static final int TIMEOUT =1000;   //设置socket连接超时
	private InetSocketAddress isa = null;
	public String receive_str="";
	class MyHandler extends Handler
	{
		public MyHandler(Looper looper)
		{
			super(looper);
		}
		@Override
		public void handleMessage(Message msg)
		{   //处理消息
			//updata_device_status(msg.obj.toString());  更新电机状态2016.8.24
		}
	}//接收到其它线程发送过来的msg时会调用，在这里用来更新控件状态
	MyHandler mHandler;


	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Looper looper = Looper.myLooper();//取得当前线程里的looper,这是主线程的looper
		mHandler1 = new MyHandler(looper);//构造一个handler使之可与looper通信
		TabHost tabs = getTabHost();   //设置页眉Tab标签
		LayoutInflater.from(this).inflate(R.layout.control_cmd,tabs.getTabContentView(), true);
		TabSpec tab0 = tabs.newTabSpec("tab0");
		tab0.setIndicator("单栋温室 1");      // 设置tab0的名称
		tab0.setContent(R.id.layout_tab0);      // 关联控件
		tabs.addTab(tab0);               // 添加tab0
		TabSpec tab1 = tabs.newTabSpec("tab1");
		tab1.setIndicator("单栋温室 2");
		tab1.setContent(R.id.layout_tab1);
		tabs.addTab(tab1);
		TabSpec tab2 = tabs.newTabSpec("tab2");
		tab2.setIndicator("单栋温室 3");
		tab2.setContent(R.id.layout_tab2);
		tabs.addTab(tab2);
		tabs.setCurrentTab(0);
		tabs.setup();
		tabs.setPadding(0, -15, 0, 0);

		//取得温室句柄 温室1
		shoudongbutton_01_on=(RadioButton)findViewById(R.id.shoudongkongzhi_01_on);
		zidongbutton_01_on=(RadioButton)findViewById(R.id.zidongkongzhi_01_on);
		waizheyang_01_on=(RadioButton)findViewById(R.id.waizheyang_01_on);
		waizheyang_01_off=(RadioButton)findViewById(R.id.waizheyang_01_off);
		waizheyang_01_back=(RadioButton)findViewById(R.id.waizheyang_01_back);
		waizheyang_01_backoff=(RadioButton)findViewById(R.id.waizheyang_01_backoff);
		dingkaichuang_01_on=(RadioButton)findViewById(R.id.dingkaichuang_01_on);
		dingkaichuang_01_off=(RadioButton)findViewById(R.id.dingkaichuang_01_off);
		dingkaichuang_01_back=(RadioButton)findViewById(R.id.dingkaichuang_01_back);
		dingkaichuang_01_backoff=(RadioButton)findViewById(R.id.dingkaichuang_01_backoff);
		neibaowen_01_on=(RadioButton)findViewById(R.id.neibaowen_01_on);
		neibaowen_01_off=(RadioButton)findViewById(R.id.neibaowen_01_off);
		neibaowen_01_back=(RadioButton)findViewById(R.id.neibaowen_01_back);
		neibaowen_01_backstop=(RadioButton)findViewById(R.id.neibaowen_01_backstop);
		shilianwaifan_01_on=(RadioButton)findViewById(R.id.shilianwaifan_01_on);
		shilianwaifan_01_off=(RadioButton)findViewById(R.id.shilianwaifan_01_off);
		shilianwaifan_01_back=(RadioButton)findViewById(R.id.shilianwaifan_01_back);
		shilianwaifan_01_backoff=(RadioButton)findViewById(R.id.shilianwaifan_01_backoff);
		zhouliufengji_01_on=(RadioButton)findViewById(R.id.zhouliufengji_01_on);
		zhouliufengji_01_off=(RadioButton)findViewById(R.id.zhouliufengji_01_off);
		buguangdeng_01_on=(RadioButton)findViewById(R.id.buguang_01_on);
		buguangdeng_01_off=(RadioButton)findViewById(R.id.buguang_01_off);
		buguangdeng_02_on=(RadioButton)findViewById(R.id.buguang_02_on);
		buguangdeng_02_off=(RadioButton)findViewById(R.id.buguang_02_off);
		buguangdeng_03_on=(RadioButton)findViewById(R.id.buguang_03_on);
		buguangdeng_03_off=(RadioButton)findViewById(R.id.buguang_03_off);
		shilianshuibeng_01_on=(RadioButton)findViewById(R.id.shilianshuibeng_01_on);
		shilianshuibeng_01_off=(RadioButton)findViewById(R.id.shilianshuibeng_01_off);
		bengzhanshuibeng_01_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_01_on);
		bengzhanshuibeng_01_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_01_off);
		bengzhanshuibeng_02_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_02_on);
		bengzhanshuibeng_02_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_02_off);
		diancifa_01_on=(RadioButton)findViewById(R.id.diancifa_01_on);
		diancifa_01_off=(RadioButton)findViewById(R.id.diancifa_01_off);
		diancifa_02_on=(RadioButton)findViewById(R.id.diancifa_02_on);
		diancifa_02_off=(RadioButton)findViewById(R.id.diancifa_02_off);
		diancifa_03_on=(RadioButton)findViewById(R.id.diancifa_03_on);
		diancifa_03_off=(RadioButton)findViewById(R.id.diancifa_03_off);
		diancifa_04_on=(RadioButton)findViewById(R.id.diancifa_04_on);
		diancifa_04_off=(RadioButton)findViewById(R.id.diancifa_04_off);
		diancifa_05_on=(RadioButton)findViewById(R.id.diancifa_05_on);
		diancifa_05_off=(RadioButton)findViewById(R.id.diancifa_05_off);
		diancifa_06_on=(RadioButton)findViewById(R.id.diancifa_06_on);
		diancifa_06_off=(RadioButton)findViewById(R.id.diancifa_06_off);
		diancifa_07_on=(RadioButton)findViewById(R.id.diancifa_07_on);
		diancifa_07_off=(RadioButton)findViewById(R.id.diancifa_07_off);
		diancifa_08_on=(RadioButton)findViewById(R.id.diancifa_08_on);
		diancifa_08_off=(RadioButton)findViewById(R.id.diancifa_08_off);
		diancifa_09_on=(RadioButton)findViewById(R.id.diancifa_09_on);
		diancifa_09_off=(RadioButton)findViewById(R.id.diancifa_09_off);
		diancifa_010_on=(RadioButton)findViewById(R.id.diancifa_010_on);
		diancifa_010_off=(RadioButton)findViewById(R.id.diancifa_010_off);
		diancifa_011_on=(RadioButton)findViewById(R.id.diancifa_011_on);
		diancifa_011_off=(RadioButton)findViewById(R.id.diancifa_011_off);
		diancifa_012_on=(RadioButton)findViewById(R.id.diancifa_012_on);
		diancifa_012_off=(RadioButton)findViewById(R.id.diancifa_012_off);
		diancifa_013_on=(RadioButton)findViewById(R.id.diancifa_013_on);
		diancifa_013_off=(RadioButton)findViewById(R.id.diancifa_013_off);
		diancifa_014_on=(RadioButton)findViewById(R.id.diancifa_014_on);
		diancifa_014_off=(RadioButton)findViewById(R.id.diancifa_014_off);
		diancifa_015_on=(RadioButton)findViewById(R.id.diancifa_015_on);
		diancifa_015_off=(RadioButton)findViewById(R.id.diancifa_015_off);
		diancifa_016_on=(RadioButton)findViewById(R.id.diancifa_016_on);
		diancifa_016_off=(RadioButton)findViewById(R.id.diancifa_016_off);
		//温室二取得句柄语句
		shoudongbutton_11_on=(RadioButton)findViewById(R.id.shoudongkongzhi_11_on);
		zidongbutton_11_on=(RadioButton)findViewById(R.id.zidongkongzhi_11_on);
		waizheyang_11_on=(RadioButton)findViewById(R.id.waizheyang_11_on);
		waizheyang_11_off=(RadioButton)findViewById(R.id.waizheyang_11_off);
		waizheyang_11_back=(RadioButton)findViewById(R.id.waizheyang_11_back);
		waizheyang_11_backoff=(RadioButton)findViewById(R.id.waizheyang_11_backoff);
		dingkaichuang_11_on=(RadioButton)findViewById(R.id.dingkaichuang_11_on);
		dingkaichuang_11_off=(RadioButton)findViewById(R.id.dingkaichuang_11_off);
		dingkaichuang_11_back=(RadioButton)findViewById(R.id.dingkaichuang_11_back);
		dingkaichuang_11_backoff=(RadioButton)findViewById(R.id.dingkaichuang_11_backoff);
		neibaowen_11_on=(RadioButton)findViewById(R.id.neibaowen_11_on);
		neibaowen_11_off=(RadioButton)findViewById(R.id.neibaowen_11_off);
		neibaowen_11_back=(RadioButton)findViewById(R.id.neibaowen_11_back);
		neibaowen_11_backstop=(RadioButton)findViewById(R.id.neibaowen_11_backstop);
		shilianwaifan_11_on=(RadioButton)findViewById(R.id.shilianwaifan_11_on);
		shilianwaifan_11_off=(RadioButton)findViewById(R.id.shilianwaifan_11_off);
		shilianwaifan_11_back=(RadioButton)findViewById(R.id.shilianwaifan_11_back);
		shilianwaifan_11_backoff=(RadioButton)findViewById(R.id.shilianwaifan_11_backoff);
		zhouliufengji_11_on=(RadioButton)findViewById(R.id.zhouliufengji_11_on);
		zhouliufengji_11_off=(RadioButton)findViewById(R.id.zhouliufengji_11_off);
		buguangdeng_11_on=(RadioButton)findViewById(R.id.buguang_11_on);
		buguangdeng_11_off=(RadioButton)findViewById(R.id.buguang_11_off);
		buguangdeng_12_on=(RadioButton)findViewById(R.id.buguang_12_on);
		buguangdeng_12_off=(RadioButton)findViewById(R.id.buguang_12_off);
		buguangdeng_13_on=(RadioButton)findViewById(R.id.buguang_13_on);
		buguangdeng_13_off=(RadioButton)findViewById(R.id.buguang_13_off);
		shilianshuibeng_11_on=(RadioButton)findViewById(R.id.shilianshuibeng_11_on);
		shilianshuibeng_11_off=(RadioButton)findViewById(R.id.shilianshuibeng_11_off);
		bengzhanshuibeng_11_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_11_on);
		bengzhanshuibeng_11_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_11_off);
		bengzhanshuibeng_12_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_12_on);
		bengzhanshuibeng_12_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_12_off);
		diancifa_11_on=(RadioButton)findViewById(R.id.diancifa_11_on);
		diancifa_11_off=(RadioButton)findViewById(R.id.diancifa_11_off);
		diancifa_12_on=(RadioButton)findViewById(R.id.diancifa_12_on);
		diancifa_12_off=(RadioButton)findViewById(R.id.diancifa_12_off);
		diancifa_13_on=(RadioButton)findViewById(R.id.diancifa_13_on);
		diancifa_13_off=(RadioButton)findViewById(R.id.diancifa_13_off);
		diancifa_14_on=(RadioButton)findViewById(R.id.diancifa_14_on);
		diancifa_14_off=(RadioButton)findViewById(R.id.diancifa_14_off);
		diancifa_15_on=(RadioButton)findViewById(R.id.diancifa_15_on);
		diancifa_15_off=(RadioButton)findViewById(R.id.diancifa_15_off);
		diancifa_16_on=(RadioButton)findViewById(R.id.diancifa_16_on);
		diancifa_16_off=(RadioButton)findViewById(R.id.diancifa_16_off);
		diancifa_17_on=(RadioButton)findViewById(R.id.diancifa_17_on);
		diancifa_17_off=(RadioButton)findViewById(R.id.diancifa_17_off);
		diancifa_18_on=(RadioButton)findViewById(R.id.diancifa_18_on);
		diancifa_18_off=(RadioButton)findViewById(R.id.diancifa_18_off);
		diancifa_19_on=(RadioButton)findViewById(R.id.diancifa_19_on);
		diancifa_19_off=(RadioButton)findViewById(R.id.diancifa_19_off);
		diancifa_110_on=(RadioButton)findViewById(R.id.diancifa_110_on);
		diancifa_110_off=(RadioButton)findViewById(R.id.diancifa_110_off);
		diancifa_111_on=(RadioButton)findViewById(R.id.diancifa_111_on);
		diancifa_111_off=(RadioButton)findViewById(R.id.diancifa_111_off);
		diancifa_112_on=(RadioButton)findViewById(R.id.diancifa_112_on);
		diancifa_112_off=(RadioButton)findViewById(R.id.diancifa_112_off);
		diancifa_113_on=(RadioButton)findViewById(R.id.diancifa_113_on);
		diancifa_113_off=(RadioButton)findViewById(R.id.diancifa_113_off);
		diancifa_114_on=(RadioButton)findViewById(R.id.diancifa_114_on);
		diancifa_114_off=(RadioButton)findViewById(R.id.diancifa_114_off);
		diancifa_115_on=(RadioButton)findViewById(R.id.diancifa_115_on);
		diancifa_115_off=(RadioButton)findViewById(R.id.diancifa_115_off);
		diancifa_116_on=(RadioButton)findViewById(R.id.diancifa_116_on);
		diancifa_116_off=(RadioButton)findViewById(R.id.diancifa_116_off);


//温室三取得句柄语句
		shoudongbutton_21_on=(RadioButton)findViewById(R.id.shoudongkongzhi_21_on);
		zidongbutton_21_on=(RadioButton)findViewById(R.id.zidongkongzhi_21_on);
		waizheyang_21_on=(RadioButton)findViewById(R.id.waizheyang_21_on);
		waizheyang_21_off=(RadioButton)findViewById(R.id.waizheyang_21_off);
		waizheyang_21_back=(RadioButton)findViewById(R.id.waizheyang_21_back);
		waizheyang_21_backoff=(RadioButton)findViewById(R.id.waizheyang_21_backoff);
		dingkaichuang_21_on=(RadioButton)findViewById(R.id.dingkaichuang_21_on);
		dingkaichuang_21_off=(RadioButton)findViewById(R.id.dingkaichuang_21_off);
		dingkaichuang_21_back=(RadioButton)findViewById(R.id.dingkaichuang_21_back);
		dingkaichuang_21_backoff=(RadioButton)findViewById(R.id.dingkaichuang_21_backoff);
		neibaowen_21_on=(RadioButton)findViewById(R.id.neibaowen_21_on);
		neibaowen_21_off=(RadioButton)findViewById(R.id.neibaowen_21_off);
		neibaowen_21_back=(RadioButton)findViewById(R.id.neibaowen_21_back);
		neibaowen_21_backstop=(RadioButton)findViewById(R.id.neibaowen_21_backstop);
		shilianwaifan_21_on=(RadioButton)findViewById(R.id.shilianwaifan_21_on);
		shilianwaifan_21_off=(RadioButton)findViewById(R.id.shilianwaifan_21_off);
		shilianwaifan_21_back=(RadioButton)findViewById(R.id.shilianwaifan_21_back);
		shilianwaifan_21_backoff=(RadioButton)findViewById(R.id.shilianwaifan_21_backoff);
		zhouliufengji_21_on=(RadioButton)findViewById(R.id.zhouliufengji_21_on);
		zhouliufengji_21_off=(RadioButton)findViewById(R.id.zhouliufengji_21_off);
		buguangdeng_21_on=(RadioButton)findViewById(R.id.buguang_21_on);
		buguangdeng_21_off=(RadioButton)findViewById(R.id.buguang_21_off);
		buguangdeng_22_on=(RadioButton)findViewById(R.id.buguang_22_on);
		buguangdeng_22_off=(RadioButton)findViewById(R.id.buguang_22_off);
		buguangdeng_23_on=(RadioButton)findViewById(R.id.buguang_23_on);
		buguangdeng_23_off=(RadioButton)findViewById(R.id.buguang_23_off);
		shilianshuibeng_21_on=(RadioButton)findViewById(R.id.shilianshuibeng_21_on);
		shilianshuibeng_21_off=(RadioButton)findViewById(R.id.shilianshuibeng_21_off);
		bengzhanshuibeng_21_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_21_on);
		bengzhanshuibeng_21_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_21_off);
		bengzhanshuibeng_22_on=(RadioButton)findViewById(R.id.bengzhanshuibeng_22_on);
		bengzhanshuibeng_22_off=(RadioButton)findViewById(R.id.bengzhanshuibeng_22_off);
		diancifa_21_on=(RadioButton)findViewById(R.id.diancifa_21_on);
		diancifa_21_off=(RadioButton)findViewById(R.id.diancifa_21_off);
		diancifa_22_on=(RadioButton)findViewById(R.id.diancifa_22_on);
		diancifa_22_off=(RadioButton)findViewById(R.id.diancifa_22_off);
		diancifa_23_on=(RadioButton)findViewById(R.id.diancifa_23_on);
		diancifa_23_off=(RadioButton)findViewById(R.id.diancifa_23_off);
		diancifa_24_on=(RadioButton)findViewById(R.id.diancifa_24_on);
		diancifa_24_off=(RadioButton)findViewById(R.id.diancifa_24_off);
		diancifa_25_on=(RadioButton)findViewById(R.id.diancifa_25_on);
		diancifa_25_off=(RadioButton)findViewById(R.id.diancifa_25_off);
		diancifa_26_on=(RadioButton)findViewById(R.id.diancifa_26_on);
		diancifa_26_off=(RadioButton)findViewById(R.id.diancifa_26_off);
		diancifa_27_on=(RadioButton)findViewById(R.id.diancifa_27_on);
		diancifa_27_off=(RadioButton)findViewById(R.id.diancifa_27_off);
		diancifa_28_on=(RadioButton)findViewById(R.id.diancifa_28_on);
		diancifa_28_off=(RadioButton)findViewById(R.id.diancifa_28_off);
		diancifa_29_on=(RadioButton)findViewById(R.id.diancifa_29_on);
		diancifa_29_off=(RadioButton)findViewById(R.id.diancifa_29_off);
		diancifa_210_on=(RadioButton)findViewById(R.id.diancifa_210_on);
		diancifa_210_off=(RadioButton)findViewById(R.id.diancifa_210_off);
		diancifa_211_on=(RadioButton)findViewById(R.id.diancifa_211_on);
		diancifa_211_off=(RadioButton)findViewById(R.id.diancifa_211_off);
		diancifa_212_on=(RadioButton)findViewById(R.id.diancifa_212_on);
		diancifa_212_off=(RadioButton)findViewById(R.id.diancifa_212_off);
		diancifa_213_on=(RadioButton)findViewById(R.id.diancifa_213_on);
		diancifa_213_off=(RadioButton)findViewById(R.id.diancifa_213_off);
		diancifa_214_on=(RadioButton)findViewById(R.id.diancifa_214_on);
		diancifa_214_off=(RadioButton)findViewById(R.id.diancifa_214_off);
		diancifa_215_on=(RadioButton)findViewById(R.id.diancifa_215_on);
		diancifa_215_off=(RadioButton)findViewById(R.id.diancifa_215_off);
		diancifa_216_on=(RadioButton)findViewById(R.id.diancifa_216_on);
		diancifa_216_off=(RadioButton)findViewById(R.id.diancifa_216_off);



		sp = this.getSharedPreferences("setting", Context.MODE_PRIVATE);

		home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
		home_img_bn_Layout.setOnClickListener(clickListener_home);

		style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
		style_img_bn_layout.setOnClickListener(clickListener_style);


		cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
		cam_img_bn_layout.setOnClickListener(clickListener_cam);

		shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
		shopping_img_bn_layout.setOnClickListener(clickListener_shopping);
		shopping_img_bn_layout.setSelected(true);

		show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
		show_img_bn_layout.setOnClickListener(clickListener_show);

		mGestureDetector = new GestureDetector(this);

		OnTabChangeListener changeLis=new OnTabChangeListener(){
			public void onTabChanged(String tabId) {
				if(tabId.equals("tab0"))
				{
					try {
						//   connecttoserver("Room1");

						//    ReceiveMsg(socket);
						if(receive_str!="")
						{
//				    					  Message m = mHandler.obtainMessage(1, 1, 1, receive_str);//构造要传递的消息
//				            	          mHandler.sendMessage(m);//发送给mHandler的事件处理函数进行处理
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else if(tabId.equals("tab1"))
				{
					try
					{
						//  connecttoserver("Room2");
						//  ReceiveMsg(socket);
						if(receive_str!="")
						{
//				    					  Message m = mHandler.obtainMessage(1, 1, 1, receive_str);//构造要传递的消息
//				            	          mHandler.sendMessage(m);//发送给mHandler的事件处理函数进行处理
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else if(tabId.equals("tab2"))
				{
					try {
						// connecttoserver("Room3");
						// ReceiveMsg(socket);
						if(receive_str!="")
						{
//				    					  Message m = mHandler.obtainMessage(1, 1, 1, receive_str);//构造要传递的消息
//				            	          mHandler.sendMessage(m);//发送给mHandler的事件处理函数进行处理
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}

				else
				{
					try {
						//    connecttoserver("Room1");
						//   ReceiveMsg(socket);
						if(receive_str!="")
						{
//				    					  Message m = mHandler.obtainMessage(1, 1, 1, receive_str);//构造要传递的消息
//				            	          mHandler.sendMessage(m);//发送给mHandler的事件处理函数进行处理
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
			}
		};
		tabs.setOnTabChangedListener(changeLis);
		HandlerThread handlerThread = new HandlerThread("myHandlerThread");//线程调用函数
		handlerThread.start();
		handler = new Handler(handlerThread.getLooper());
		// handler.post(new MyRunnable());
		setonchecked1();
		setonchecked2();
		setonchecked3();
		setupView1(); //1号温室抽屉布局
		setupView2();
		setupView3();


	}
	private void setupView1()
	{
		tv01 =(TextView) findViewById(R.id.tv01);
		tv02 =(TextView) findViewById(R.id.tv02);
		tv03 =(TextView) findViewById(R.id.tv03);
		tv04 =(TextView) findViewById(R.id.tv04);
		tv05 =(TextView) findViewById(R.id.tv05);
		tv06 =(TextView) findViewById(R.id.tv06);
		tv07 =(TextView) findViewById(R.id.tv07);
		tv08 =(TextView) findViewById(R.id.tv08);
		tv09 =(TextView) findViewById(R.id.tv09);
		tv10 =(TextView) findViewById(R.id.tv10);

		layout01 = (LinearLayout)findViewById(R.id.layout01);
		layout02 = (LinearLayout)findViewById(R.id.layout02);
		layout03 = (LinearLayout)findViewById(R.id.layout03);
		layout04 = (LinearLayout)findViewById(R.id.layout04);
		layout05 = (LinearLayout)findViewById(R.id.layout05);
		layout06 = (LinearLayout)findViewById(R.id.layout06);
		layout07 = (LinearLayout)findViewById(R.id.layout07);
		layout08 = (LinearLayout)findViewById(R.id.layout08);
		layout09 = (LinearLayout)findViewById(R.id.layout09);
		layout10 = (LinearLayout)findViewById(R.id.layout10);

		layout01.setVisibility(View.GONE);
		layout02.setVisibility(View.GONE);
		layout03.setVisibility(View.GONE);
		layout04.setVisibility(View.GONE);
		layout05.setVisibility(View.GONE);
		layout06.setVisibility(View.GONE);
		layout07.setVisibility(View.GONE);
		layout08.setVisibility(View.GONE);
		layout09.setVisibility(View.GONE);
		layout10.setVisibility(View.GONE);

		tv01.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag){
					layout01.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout01.setVisibility(View.GONE);
					flag=true;
				}
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv02.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				if(flag){
					layout02.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout02.setVisibility(View.GONE);
					flag=true;
				}

				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv03.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				if(flag){
					layout03.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout03.setVisibility(View.GONE);
					flag=true;
				}

				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv04.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				if(flag){
					layout04.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout04.setVisibility(View.GONE);
					flag=true;
				}
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv05.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				if(flag){
					layout05.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout05.setVisibility(View.GONE);
					flag=true;
				}

				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv06.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				if(flag){
					layout06.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout06.setVisibility(View.GONE);
					flag=true;
				}

				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);

			}});
		tv07.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				if(flag){
					layout07.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout07.setVisibility(View.GONE);
					flag=true;
				}

				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);
			}
		});
		tv08.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				if(flag){
					layout08.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout08.setVisibility(View.GONE);
					flag=true;
				}
				layout09.setVisibility(View.GONE);
				layout10.setVisibility(View.GONE);
			}
		});
		tv09.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				if(flag){
					layout09.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout09.setVisibility(View.GONE);
					flag=true;
				}
				layout10.setVisibility(View.GONE);
			}
		});
		tv10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout01.setVisibility(View.GONE);
				layout02.setVisibility(View.GONE);
				layout03.setVisibility(View.GONE);
				layout04.setVisibility(View.GONE);
				layout05.setVisibility(View.GONE);
				layout06.setVisibility(View.GONE);
				layout07.setVisibility(View.GONE);
				layout08.setVisibility(View.GONE);
				layout09.setVisibility(View.GONE);
				if(flag){
					layout10.setVisibility(View.VISIBLE);
					flag=false;
				}else{
					layout10.setVisibility(View.GONE);
					flag=true;
				}
			}
		});
	}
	private void setupView2()
	{
		tv11=(TextView) findViewById(R.id.tv11);
		tv12=(TextView) findViewById(R.id.tv12);
		tv13=(TextView) findViewById(R.id.tv13);
		tv14=(TextView) findViewById(R.id.tv14);
		tv15=(TextView) findViewById(R.id.tv15);
		tv16=(TextView) findViewById(R.id.tv16);
		tv17=(TextView) findViewById(R.id.tv17);
		tv18=(TextView) findViewById(R.id.tv18);
		tv19=(TextView) findViewById(R.id.tv19);
		tv20=(TextView) findViewById(R.id.tv20);
		layout11 = (LinearLayout)findViewById(R.id.layout11);
		layout12 = (LinearLayout)findViewById(R.id.layout12);
		layout13 = (LinearLayout)findViewById(R.id.layout13);
		layout14 = (LinearLayout)findViewById(R.id.layout14);
		layout15 = (LinearLayout)findViewById(R.id.layout15);
		layout16 = (LinearLayout)findViewById(R.id.layout16);
		layout17 = (LinearLayout)findViewById(R.id.layout17);
		layout18 = (LinearLayout)findViewById(R.id.layout18);
		layout19 = (LinearLayout)findViewById(R.id.layout19);
		layout20 = (LinearLayout)findViewById(R.id.layout20);

		layout11.setVisibility(View.GONE);
		layout12.setVisibility(View.GONE);
		layout13.setVisibility(View.GONE);
		layout14.setVisibility(View.GONE);
		layout15.setVisibility(View.GONE);
		layout16.setVisibility(View.GONE);
		layout17.setVisibility(View.GONE);
		layout18.setVisibility(View.GONE);
		layout19.setVisibility(View.GONE);
		layout20.setVisibility(View.GONE);
		tv11.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag){
					layout11.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout11.setVisibility(View.GONE);
					flag=true;
				}
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv12.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				if(flag){
					layout12.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout12.setVisibility(View.GONE);
					flag=true;
				}
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv13.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				if(flag){
					layout13.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout13.setVisibility(View.GONE);
					flag=true;
				}
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv14.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				if(flag){
					layout14.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout14.setVisibility(View.GONE);
					flag=true;
				}
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv15.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				if(flag){
					layout15.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout15.setVisibility(View.GONE);
					flag=true;
				}
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv16.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				if(flag){
					layout16.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout16.setVisibility(View.GONE);
					flag=true;
				}
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv17.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				if(flag){
					layout17.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout17.setVisibility(View.GONE);
					flag=true;
				}
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv18.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				if(flag){
					layout18.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout18.setVisibility(View.GONE);
					flag=true;
				}
				layout19.setVisibility(View.GONE);
				layout20.setVisibility(View.GONE);

			}});
		tv19.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				if(flag){
					layout19.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout19.setVisibility(View.GONE);
					flag=true;
				}
				layout20.setVisibility(View.GONE);

			}});
		tv20.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout11.setVisibility(View.GONE);
				layout12.setVisibility(View.GONE);
				layout13.setVisibility(View.GONE);
				layout14.setVisibility(View.GONE);
				layout15.setVisibility(View.GONE);
				layout16.setVisibility(View.GONE);
				layout17.setVisibility(View.GONE);
				layout18.setVisibility(View.GONE);
				layout19.setVisibility(View.GONE);
				if(flag){
					layout20.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout20.setVisibility(View.GONE);
					flag=true;
				}
			}});

	}

	private void setupView3()
	{
		tv21=(TextView) findViewById(R.id.tv21);
		tv22=(TextView) findViewById(R.id.tv22);
		tv23=(TextView) findViewById(R.id.tv23);
		tv24=(TextView) findViewById(R.id.tv24);
		tv25=(TextView) findViewById(R.id.tv25);
		tv26=(TextView) findViewById(R.id.tv26);
		tv27=(TextView) findViewById(R.id.tv27);
		tv28=(TextView) findViewById(R.id.tv28);
		tv29=(TextView) findViewById(R.id.tv29);
		tv30=(TextView) findViewById(R.id.tv30);


		layout21 = (LinearLayout)findViewById(R.id.layout21);
		layout22 = (LinearLayout)findViewById(R.id.layout22);
		layout23 = (LinearLayout)findViewById(R.id.layout23);
		layout24 = (LinearLayout)findViewById(R.id.layout24);
		layout25 = (LinearLayout)findViewById(R.id.layout25);
		layout26 = (LinearLayout)findViewById(R.id.layout26);
		layout27 = (LinearLayout)findViewById(R.id.layout27);
		layout28 = (LinearLayout)findViewById(R.id.layout28);
		layout29 = (LinearLayout)findViewById(R.id.layout29);
		layout30 = (LinearLayout)findViewById(R.id.layout30);

		layout21.setVisibility(View.GONE);
		layout22.setVisibility(View.GONE);
		layout23.setVisibility(View.GONE);
		layout24.setVisibility(View.GONE);
		layout25.setVisibility(View.GONE);
		layout26.setVisibility(View.GONE);
		layout27.setVisibility(View.GONE);
		layout28.setVisibility(View.GONE);
		layout29.setVisibility(View.GONE);
		layout30.setVisibility(View.GONE);
		tv21.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag){
					layout21.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout21.setVisibility(View.GONE);
					flag=true;
				}
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv22.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				if(flag){
					layout22.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout22.setVisibility(View.GONE);
					flag=true;
				}
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv23.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				if(flag){
					layout23.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout23.setVisibility(View.GONE);
					flag=true;
				}
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv24.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				if(flag){
					layout24.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout24.setVisibility(View.GONE);
					flag=true;
				}
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv25.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				if(flag){
					layout25.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout25.setVisibility(View.GONE);
					flag=true;
				}
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv26.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				if(flag){
					layout26.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout26.setVisibility(View.GONE);
					flag=true;
				}
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});

		tv27.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				if(flag){
					layout27.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout27.setVisibility(View.GONE);
					flag=true;
				}
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv28.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				if(flag){
					layout28.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout28.setVisibility(View.GONE);
					flag=true;
				}
				layout29.setVisibility(View.GONE);
				layout30.setVisibility(View.GONE);

			}});
		tv29.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				if(flag){
					layout29.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout29.setVisibility(View.GONE);
					flag=true;
				}
				layout30.setVisibility(View.GONE);

			}});
		tv30.setOnClickListener(new View.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout21.setVisibility(View.GONE);
				layout22.setVisibility(View.GONE);
				layout23.setVisibility(View.GONE);
				layout24.setVisibility(View.GONE);
				layout25.setVisibility(View.GONE);
				layout26.setVisibility(View.GONE);
				layout27.setVisibility(View.GONE);
				layout28.setVisibility(View.GONE);
				layout29.setVisibility(View.GONE);
				if(flag){
					layout30.setVisibility(View.VISIBLE);
					flag=false;}
				else{
					layout30.setVisibility(View.GONE);
					flag=true;
				}

			}});
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
	public void  sendMessage_toServer(String str){
		final String command_String =str;
		Thread myThread=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try{
					url =new URL(path);
					urlConn=(HttpURLConnection) url.openConnection();
					urlConn.setConnectTimeout(5000);
					urlConn.setDoOutput(true);
					urlConn.setDoInput(true);
					urlConn.setRequestMethod("GET");
					// TODO Auto-generated method stub
					OutputStream out =urlConn.getOutputStream();
					out.write(command_String.getBytes());
					//count--;
					out.flush();

					while(urlConn.getContentLength()!=-1){
						int code=urlConn.getResponseCode();
						if(code==200)
						{
							Toast.makeText(ControlActivity.this, "控制指令已发送",Toast.LENGTH_LONG ).show();
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
		});
		myThread.start();

	}
	public void setonchecked1()
	{
		shoudongbutton_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shoudongcommand01_on);
					//Toast.makeText(ControlActivity.this, "手动",Toast.LENGTH_LONG).show();
				}
			}
		});
		zidongbutton_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zidongcommand01_on);
					//Toast.makeText(ControlActivity.this, "自动",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand01_on);
					//Toast.makeText(ControlActivity.this, "外遮阳打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand01_off);
					//Toast.makeText(ControlActivity.this, "外遮阳打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_01_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand01_back);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_01_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand01_backoff);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand01_on);
					//Toast.makeText(ControlActivity.this, "顶开窗打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand01_off);
					//Toast.makeText(ControlActivity.this, "顶开窗打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_01_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand01_back);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_01_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand01_backstop);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand01_on);
					//Toast.makeText(ControlActivity.this, "内保温打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand01_off);
					//Toast.makeText(ControlActivity.this, "内保温打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_01_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand01_back);
					//Toast.makeText(ControlActivity.this, "内保温关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_01_backstop.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand01_backstop);
					//Toast.makeText(ControlActivity.this, "内保温关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand01_on);
					//Toast.makeText(ControlActivity.this, "湿帘外翻打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand01_off);
					//Toast.makeText(ControlActivity.this, "湿帘外翻停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_01_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand01_back);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_01_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand01_backoff);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand01_on);
					//Toast.makeText(ControlActivity.this, "轴流风机打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand01_off);
					//Toast.makeText(ControlActivity.this, "轴流风机关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand01_on);
					//Toast.makeText(ControlActivity.this, "补光灯1打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand01_off);
					//Toast.makeText(ControlActivity.this, "补光灯1关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_02_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand02_on);
					//Toast.makeText(ControlActivity.this, "补光灯2打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_02_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand02_off);
					//Toast.makeText(ControlActivity.this, "补光灯2关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_03_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand03_on);
					//Toast.makeText(ControlActivity.this, "补光3开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_03_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand03_off);
					//Toast.makeText(ControlActivity.this, "补光3关",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand01_on);
					//Toast.makeText(ControlActivity.this, "湿帘水泵打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand01_off);
					//Toast.makeText(ControlActivity.this, "湿帘水泵关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand01_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵打1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand01_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_02_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand02_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵02开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_02_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand02_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵02关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_01_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand01_on);
					//Toast.makeText(ControlActivity.this, "电磁阀1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_01_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand01_off);
					//Toast.makeText(ControlActivity.this, "电磁阀1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_02_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand02_on);
					//Toast.makeText(ControlActivity.this, "电磁阀2开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_02_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand02_off);
					//Toast.makeText(ControlActivity.this, "电磁阀2关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_03_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand03_on);
					//Toast.makeText(ControlActivity.this, "电磁阀3开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_03_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand03_off);
					//Toast.makeText(ControlActivity.this, "电磁阀3关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_04_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand04_on);
					//Toast.makeText(ControlActivity.this, "电磁阀4开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_04_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand04_off);
					//Toast.makeText(ControlActivity.this, "电磁阀4关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_05_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand05_on);
					//Toast.makeText(ControlActivity.this, "电磁阀5开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_05_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand05_off);
					//Toast.makeText(ControlActivity.this, "电磁阀5关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_06_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand06_on);
					//Toast.makeText(ControlActivity.this, "电磁阀6开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_06_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand06_off);
					//Toast.makeText(ControlActivity.this, "电磁阀6关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_07_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand07_on);
					//Toast.makeText(ControlActivity.this, "电磁阀7开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_07_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand07_off);
					//Toast.makeText(ControlActivity.this, "电磁阀7关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_08_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand08_on);
					//Toast.makeText(ControlActivity.this, "电磁阀8开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_08_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand08_off);
					//Toast.makeText(ControlActivity.this, "电磁阀8关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_09_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand09_on);
					//Toast.makeText(ControlActivity.this, "电磁阀9开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_09_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand09_off);
					//Toast.makeText(ControlActivity.this, "电磁阀9关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_010_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand010_on);
					//Toast.makeText(ControlActivity.this, "电磁阀10开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_010_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand010_off);
					//Toast.makeText(ControlActivity.this, "电磁阀10关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_011_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand011_on);
					//Toast.makeText(ControlActivity.this, "电磁阀11开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_011_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand011_off);
					//Toast.makeText(ControlActivity.this, "电磁阀11关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_012_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand012_on);
					//Toast.makeText(ControlActivity.this, "电磁阀12开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_012_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand012_off);
					//Toast.makeText(ControlActivity.this, "电磁阀12关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_013_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand013_on);
					//Toast.makeText(ControlActivity.this, "电磁阀13开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_013_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand013_off);
					//Toast.makeText(ControlActivity.this, "电磁阀13关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_014_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand014_on);
					//Toast.makeText(ControlActivity.this, "电磁阀14开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_014_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand014_off);
					//Toast.makeText(ControlActivity.this, "电磁阀14关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_015_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand015_on);
					//Toast.makeText(ControlActivity.this, "电磁阀15开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_015_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand015_off);
					//Toast.makeText(ControlActivity.this, "电磁阀15关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_016_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand016_on);
					//Toast.makeText(ControlActivity.this, "电磁阀16开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_016_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand016_off);
					//Toast.makeText(ControlActivity.this, "电磁阀16关",Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	public void setonchecked2()
	{
		shoudongbutton_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shoudongcommand11_on);
					//Toast.makeText(ControlActivity.this, "手动",Toast.LENGTH_LONG).show();
				}
			}
		});
		zidongbutton_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zidongcommand11_on);
					//Toast.makeText(ControlActivity.this, "自动",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand11_on);
					//Toast.makeText(ControlActivity.this, "外遮阳打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand11_off);
					//Toast.makeText(ControlActivity.this, "外遮阳打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_11_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand11_back);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_11_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand11_backoff);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand11_on);
					//Toast.makeText(ControlActivity.this, "顶开窗打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand11_off);
					//Toast.makeText(ControlActivity.this, "顶开窗打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_11_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand11_back);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_11_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand11_backstop);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand11_on);
					//Toast.makeText(ControlActivity.this, "内保温打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand11_off);
					//Toast.makeText(ControlActivity.this, "内保温打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_11_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand11_back);
					//Toast.makeText(ControlActivity.this, "内保温关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_11_backstop.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand11_backstop);
					//Toast.makeText(ControlActivity.this, "内保温关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand11_on);
					//Toast.makeText(ControlActivity.this, "湿帘外翻打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand11_off);
					//Toast.makeText(ControlActivity.this, "湿帘外翻停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_11_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand11_back);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_11_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand11_backoff);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand11_on);
					//Toast.makeText(ControlActivity.this, "轴流风机打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand11_off);
					//Toast.makeText(ControlActivity.this, "轴流风机关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand11_on);
					//Toast.makeText(ControlActivity.this, "补光灯4打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand11_off);
					//Toast.makeText(ControlActivity.this, "补光灯4关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_12_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand12_on);
					//Toast.makeText(ControlActivity.this, "补光灯5打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_12_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand12_off);
					//Toast.makeText(ControlActivity.this, "补光灯5关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_13_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand13_on);
					//Toast.makeText(ControlActivity.this, "补光6开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_13_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand13_off);
					//Toast.makeText(ControlActivity.this, "补光6关",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand11_on);
					//Toast.makeText(ControlActivity.this, "湿帘水泵打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand11_off);
					//Toast.makeText(ControlActivity.this, "湿帘水泵关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand11_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵打1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand11_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_12_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand12_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵02开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_12_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand12_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵02关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_11_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand11_on);
					//Toast.makeText(ControlActivity.this, "电磁阀1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_11_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand11_off);
					//Toast.makeText(ControlActivity.this, "电磁阀1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_12_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand12_on);
					//Toast.makeText(ControlActivity.this, "电磁阀2开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_12_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand12_off);
					//Toast.makeText(ControlActivity.this, "电磁阀2关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_13_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand13_on);
					//Toast.makeText(ControlActivity.this, "电磁阀3开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_13_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand13_off);
					//Toast.makeText(ControlActivity.this, "电磁阀3关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_14_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand14_on);
					//Toast.makeText(ControlActivity.this, "电磁阀4开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_14_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand14_off);
					//Toast.makeText(ControlActivity.this, "电磁阀4关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_15_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand15_on);
					//Toast.makeText(ControlActivity.this, "电磁阀5开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_15_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand15_off);
					//Toast.makeText(ControlActivity.this, "电磁阀5关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_16_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand16_on);
					//Toast.makeText(ControlActivity.this, "电磁阀6开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_16_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand16_off);
					//Toast.makeText(ControlActivity.this, "电磁阀6关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_17_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand17_on);
					//Toast.makeText(ControlActivity.this, "电磁阀7开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_17_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand17_off);
					//Toast.makeText(ControlActivity.this, "电磁阀7关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_18_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand18_on);
					//Toast.makeText(ControlActivity.this, "电磁阀8开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_18_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand18_off);
					//Toast.makeText(ControlActivity.this, "电磁阀8关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_19_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand19_on);
					//Toast.makeText(ControlActivity.this, "电磁阀9开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_19_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand19_off);
					//Toast.makeText(ControlActivity.this, "电磁阀9关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_110_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand110_on);
					//Toast.makeText(ControlActivity.this, "电磁阀10开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_110_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand110_off);
					//Toast.makeText(ControlActivity.this, "电磁阀10关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_111_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand111_on);
					//Toast.makeText(ControlActivity.this, "电磁阀11开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_111_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand111_off);
					//Toast.makeText(ControlActivity.this, "电磁阀11关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_112_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand112_on);
					//Toast.makeText(ControlActivity.this, "电磁阀12开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_112_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand112_off);
					//Toast.makeText(ControlActivity.this, "电磁阀12关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_113_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand113_on);
					//Toast.makeText(ControlActivity.this, "电磁阀13开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_113_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand113_off);
					//Toast.makeText(ControlActivity.this, "电磁阀13关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_114_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand114_on);
					//Toast.makeText(ControlActivity.this, "电磁阀14开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_114_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand114_off);
					//Toast.makeText(ControlActivity.this, "电磁阀14关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_115_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand115_on);
					//Toast.makeText(ControlActivity.this, "电磁阀15开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_115_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand115_off);
					//Toast.makeText(ControlActivity.this, "电磁阀15关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_116_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand116_on);
					//Toast.makeText(ControlActivity.this, "电磁阀16开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_116_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand116_off);
					//Toast.makeText(ControlActivity.this, "电磁阀16关",Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void setonchecked3()
	{
		shoudongbutton_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shoudongcommand21_on);
					//Toast.makeText(ControlActivity.this, "手动",Toast.LENGTH_LONG).show();
				}
			}
		});
		zidongbutton_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zidongcommand21_on);
					//Toast.makeText(ControlActivity.this, "自动",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand21_on);
					//Toast.makeText(ControlActivity.this, "外遮阳打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand21_off);
					//Toast.makeText(ControlActivity.this, "外遮阳打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_21_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand21_back);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		waizheyang_21_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(waizheyangcommand21_backoff);
					//Toast.makeText(ControlActivity.this, "外遮阳收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand21_on);
					//Toast.makeText(ControlActivity.this, "顶开窗打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand21_off);
					//Toast.makeText(ControlActivity.this, "顶开窗打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_21_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand21_back);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩",Toast.LENGTH_LONG).show();
				}
			}
		});
		dingkaichuang_21_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(dingkaichuangcommand21_backstop);
					//Toast.makeText(ControlActivity.this, "顶开窗收缩停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand21_on);
					//Toast.makeText(ControlActivity.this, "内保温打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand21_off);
					//Toast.makeText(ControlActivity.this, "内保温打开停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_21_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand21_back);
					//Toast.makeText(ControlActivity.this, "内保温关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		neibaowen_21_backstop.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(neibaowencommand21_backstop);
					//Toast.makeText(ControlActivity.this, "内保温关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand21_on);
					//Toast.makeText(ControlActivity.this, "湿帘外翻打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand21_off);
					//Toast.makeText(ControlActivity.this, "湿帘外翻停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_21_back.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand21_back);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianwaifan_21_backoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianwaifancommand21_backoff);
					//Toast.makeText(ControlActivity.this, "湿帘外翻关闭停止",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand21_on);
					//Toast.makeText(ControlActivity.this, "轴流风机打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		zhouliufengji_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(zhouliufengjicommand21_off);
					//Toast.makeText(ControlActivity.this, "轴流风机关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand21_on);
					//Toast.makeText(ControlActivity.this, "补光灯6打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand21_off);
					//Toast.makeText(ControlActivity.this, "补光灯7关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_22_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand22_on);
					//Toast.makeText(ControlActivity.this, "补光灯8打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_22_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand22_off);
					//Toast.makeText(ControlActivity.this, "补光灯8关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_23_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand23_on);
					//Toast.makeText(ControlActivity.this, "补光9开",Toast.LENGTH_LONG).show();
				}
			}
		});
		buguangdeng_23_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(buguangcommand23_off);
					//Toast.makeText(ControlActivity.this, "补光9关",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand21_on);
					//Toast.makeText(ControlActivity.this, "湿帘水泵打开",Toast.LENGTH_LONG).show();
				}
			}
		});
		shilianshuibeng_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(shilianshuibengcommand21_off);
					//Toast.makeText(ControlActivity.this, "湿帘水泵关闭",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand21_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵打1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand21_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_22_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand22_on);
					//Toast.makeText(ControlActivity.this, "泵站水泵02开",Toast.LENGTH_LONG).show();
				}
			}
		});
		bengzhanshuibeng_22_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(bengzhanshuibengcommand22_off);
					//Toast.makeText(ControlActivity.this, "泵站水泵02关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_21_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand21_on);
					//Toast.makeText(ControlActivity.this, "电磁阀1开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_21_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand21_off);
					//Toast.makeText(ControlActivity.this, "电磁阀1关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_22_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand22_on);
					//Toast.makeText(ControlActivity.this, "电磁阀2开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_22_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand22_off);
					//Toast.makeText(ControlActivity.this, "电磁阀2关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_23_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand23_on);
					//Toast.makeText(ControlActivity.this, "电磁阀3开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_23_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand23_off);
					//Toast.makeText(ControlActivity.this, "电磁阀3关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_24_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand24_on);
					//Toast.makeText(ControlActivity.this, "电磁阀4开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_24_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand24_off);
					//Toast.makeText(ControlActivity.this, "电磁阀4关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_25_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand25_on);
					//Toast.makeText(ControlActivity.this, "电磁阀5开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_25_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand25_off);
					//Toast.makeText(ControlActivity.this, "电磁阀5关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_26_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand26_on);
					//Toast.makeText(ControlActivity.this, "电磁阀6开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_26_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand26_off);
					//Toast.makeText(ControlActivity.this, "电磁阀6关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_27_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand27_on);
					//Toast.makeText(ControlActivity.this, "电磁阀7开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_27_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand27_off);
					//Toast.makeText(ControlActivity.this, "电磁阀7关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_28_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand28_on);
					//Toast.makeText(ControlActivity.this, "电磁阀8开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_28_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand28_off);
					//Toast.makeText(ControlActivity.this, "电磁阀8关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_29_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand29_on);
					//Toast.makeText(ControlActivity.this, "电磁阀9开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_29_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand29_off);
					//Toast.makeText(ControlActivity.this, "电磁阀9关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_210_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand210_on);
					//Toast.makeText(ControlActivity.this, "电磁阀10开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_210_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand210_off);
					//Toast.makeText(ControlActivity.this, "电磁阀10关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_211_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand211_on);
					//Toast.makeText(ControlActivity.this, "电磁阀11开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_211_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand211_off);
					//Toast.makeText(ControlActivity.this, "电磁阀11关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_212_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand212_on);
					//Toast.makeText(ControlActivity.this, "电磁阀12开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_212_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand212_off);
					//Toast.makeText(ControlActivity.this, "电磁阀12关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_213_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand213_on);
					//Toast.makeText(ControlActivity.this, "电磁阀13开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_213_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand213_off);
					//Toast.makeText(ControlActivity.this, "电磁阀13关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_214_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand214_on);
					//Toast.makeText(ControlActivity.this, "电磁阀14开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_214_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand214_off);
					//Toast.makeText(ControlActivity.this, "电磁阀14关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_215_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand215_on);
					//Toast.makeText(ControlActivity.this, "电磁阀15开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_215_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand215_off);
					//Toast.makeText(ControlActivity.this, "电磁阀15关",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_216_on.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand216_on);
					//Toast.makeText(ControlActivity.this, "电磁阀16开",Toast.LENGTH_LONG).show();
				}
			}
		});
		diancifa_216_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(buttonView.isChecked())
				{
					sendMessage_toServer(diancifacommand216_off);
					//Toast.makeText(ControlActivity.this, "电磁阀16关",Toast.LENGTH_LONG).show();
				}
			}
		});
	}



	class MyRunnable implements Runnable
	{
		@Override
		public void run() {

			//String teststr=new String("GET@"+greenhouse_num);
			try {
				url =new URL(path);
				urlConn=(HttpURLConnection) url.openConnection();
				urlConn.setConnectTimeout(5000);
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setRequestMethod("GET");

				//测试包内容
				//String teststr="NIHAO";

				OutputStream out =urlConn.getOutputStream();

				//out.write(teststr.getBytes());
				//count--;
				out.flush();

				while(urlConn.getContentLength()!=-1){
					int code=urlConn.getResponseCode();
					if(code==200)
					{
						//请求成功
						InputStream is=urlConn.getInputStream();
						String text=readInputStream(is);
						Message m = mHandler1.obtainMessage(1, 1, 1, text);//构造要传递的消息
						mHandler1.sendMessage(m);//发送给mHandler的事件处理函数进行处理
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
				//Thread.sleep(2000);


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


	public boolean onTouch(View v, MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
						   float velocityY) {
		// TODO Auto-generated method stub
		Log.e("view", "onFling");
		if (e1.getX() - e2.getX()> FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY ) {
			Log.e("fling", "left");
			if(sp.getBoolean("IFHUADONG", true))
			{
				Intent intent = new Intent();
				intent.setClass(ControlActivity.this, ContactActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
				finish();
			}
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY){
			Log.e("fling", "right");
			if(sp.getBoolean("IFHUADONG", true))
			{
				Intent intent = new Intent();
				intent.setClass(ControlActivity.this, CurveActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
				finish();
			}
		}
		return false;
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
				ControlActivity.this.finish();
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
	private OnClickListener clickListener_home = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			home_img_bn_Layout.setSelected(true);
			style_img_bn_layout.setSelected(false);
			cam_img_bn_layout.setSelected(false);
			shopping_img_bn_layout.setSelected(false);
			show_img_bn_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(ControlActivity.this, TestUIActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
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
			intent.setClass(ControlActivity.this, IrrigationActivity.class);
			startActivity(intent);
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
			intent.setClass(ControlActivity.this, CurveActivity.class);
			startActivity(intent);
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
//			Intent intent = new Intent();
//			intent.setClass(ControlActivity.this, ControlActivity.class);
//			startActivity(intent);
//			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
//			finish();
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
			intent.setClass(ControlActivity.this, ContactActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.zoommin, R.anim.zoomout);
			finish();
		}
	};

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
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
}
