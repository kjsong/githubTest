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

		db.execSQL("INSERT INTO TEST VALUES(NULL, '���Ｍ���ڹ���', '01');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '���Ǵ�', '02');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '������Ͽ콺', '03');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '�Ѱ���������', '04');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '700(ȫ����)', '05');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '�ż��轺���� �߿ܹ���', '06');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '��Ÿ�ν����̼�', '07');");
		db.execSQL("INSERT INTO TEST VALUES(NULL, '�Ѱ����̼���', '08');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// ������ ���׷��̵� ���� ��� �۾��� ������ �ۼ��մϴ�.
	}
}
