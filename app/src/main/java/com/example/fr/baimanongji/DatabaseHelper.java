package com.example.fr.baimanongji;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int VERSION=1;
	public DatabaseHelper(Context context,String name,CursorFactory factory,int version)
	{
		super(context,name,factory,version);
	}

	public DatabaseHelper(Context context,String name)
	{
		this(context,name,VERSION);
	}
	public DatabaseHelper(Context context,String name,int version)
	{
		this(context,name,null,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("cteate a Database");
		db.execSQL("create table temp(id int,Temp varchar(20),time varchar(20))");
		db.execSQL("create table humi(id int,Humi varchar(20),time varchar(20))");
		db.execSQL("create table lumi(id int,Lumi varchar(20),time varchar(20))");
		db.execSQL("create table co2(id int,Co2 varchar(20),time varchar(20))");
		db.execSQL("create table soiltemp(id int,soilT varchar(20),time varchar(20))");
		db.execSQL("create table soilhumi(id int,soilH varchar(20),time varchar(20))");
		/*
		* 以下为新建的数据库
		* 看看就行了
		* 记住表明和各字段
		 */
		db.execSQL("create table hum(id int,hum1 varchar(20),time varchar(20))");
		db.execSQL("create table tmp(id int,tmp1 varchar(20),time varchar(20))");
		db.execSQL("create table ec(id int,ec1 varchar(20),time varchar(20))");
		db.execSQL("create table flow(id int,flow1 varchar(20),time varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a Database");
	}

}
