package com.example.fr.baimanongji;
/*
 * �б�,��ҳ�����ݲ�ѯ�������Ϣ
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LineActivity extends Activity{

	private List<HashMap<String, String>> data;
	private ListView listView;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase sdb;
	private Cursor cursor;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.line);
		//
		listView = (ListView)this.findViewById(R.id.listview);
		data = new ArrayList<HashMap<String, String>>();

		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		//信息查询
		final Button btn1=(Button)findViewById(R.id.ringagain001);
		//返回按钮
		Button title_set_bn=(Button)findViewById(R.id.title_set_bn);
		title_set_bn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				final CharSequence[] items =
						{ "空气温度", "空气湿度","光照","CO2浓度","土壤温度","土壤湿度"};
				AlertDialog dlg = new AlertDialog.Builder(LineActivity.this).setTitle("请选择").setItems(items,
						new DialogInterface.OnClickListener()
						{
							public void onClick ( DialogInterface dialog , int item )
							{
								if (item == 0)
								{
									showwendu();
									btn1.setText("            信息查询（历史空气温度信息）");
								}
								else if(item == 1)
								{
									showhumi();
									btn1.setText("            信息查询（历史空气湿度信息）");
								}
								else if(item == 2)
								{
									showlumi();
									btn1.setText("            信息查询（历史光照强度信息）");
								}
								else if(item == 3)
								{
									showco2();
									btn1.setText("            信息查询（历史CO2浓度信息）");
								}
								else if(item == 4)
								{
									showsoiltemp();
									btn1.setText("            信息查询（历史土壤温度信息）");
								}
								else
								{
									showsoilhumi();
									btn1.setText("            信息查询（历史土壤湿度信息）");
								}
							}
						}).create();
				dlg.show();
			}
		});

	}


	private void showwendu()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from temp order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("Temp"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("Temp", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "Temp", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}
	private void showhumi()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from humi order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("Humi"));
			String time = cursor.getString(cursor.getColumnIndex("time"));

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("Humi", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "Humi", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}
	private void showlumi()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from lumi order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("Lumi"));
			String time = cursor.getString(cursor.getColumnIndex("time"));

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("Lumi", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "Lumi", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}
	private void showco2()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from co2 order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("Co2"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("Co2", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "Co2", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}
	private void showsoiltemp()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from soiltemp order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("soilT"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("temp", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "temp", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}
	private void showsoilhumi()
	{
		data.clear();
		dbHelper=new DatabaseHelper(LineActivity.this,"information");
		sdb = dbHelper.getReadableDatabase();
		cursor=sdb.rawQuery("select * from soilhumi order by time desc", null);
		while(cursor.moveToNext())
		{
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String Temp = cursor.getString(cursor.getColumnIndex("soilH"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("humi", Temp);
			map.put("time", time);
			data.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(LineActivity.this, data, R.layout.list_row,
				new String[]{ "id", "humi", "time" } , new int[]{ R.id.dataid, R.id.datatemp, R.id.datatime});
		listView.setAdapter(simpleAdapter);
	}

}
