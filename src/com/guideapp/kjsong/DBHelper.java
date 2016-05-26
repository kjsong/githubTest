package com.guideapp.kjsong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	String sql;

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		sql = "CREATE TABLE test ( _id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT, number TEXT);";
		db.execSQL(sql);

		db.execSQL("INSERT INTO TEST VALUES(NULL, '서울서예박물관', '01');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '음악당', '02');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '오페라하우스', '03');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '한가람디자인', '04');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '700(홍보관)', '05');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '신세계스퀘어 야외무대', '06');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '비타민스테이션', '07');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '한가람미술관', '08');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 버전이 업그레이드 됐을 경우 작업할 내용을 작성합니다.
	}
}
