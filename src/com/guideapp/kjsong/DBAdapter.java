package com.guideapp.kjsong;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DBAdapter extends CursorAdapter {

	public DBAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		//view���� ������ �ȵǰ� ���� ������ �־����
		final ImageView image = (ImageView) view.findViewById(R.id.image);
		final TextView name = (TextView) view.findViewById(R.id.name);
		final TextView number = (TextView) view.findViewById(R.id.number);

		String nm = name.getText().toString();

		if ("���Ｍ���ڹ���".equals(nm))
			image.setImageResource(R.drawable.image_1);
		else if ("���Ǵ�".equals(nm))
			image.setImageResource(R.drawable.image_2);
		else if ("������Ͽ콺".equals(nm))
			image.setImageResource(R.drawable.image_3);
		else if ("�Ѱ���������".equals(nm))
			image.setImageResource(R.drawable.image_4);
		else if ("700(ȫ����)".equals(nm))
			image.setImageResource(R.drawable.image_5);
		else if ("�߿ܹ���".equals(nm))
			image.setImageResource(R.drawable.image_6);
		else if ("��Ÿ�ν����̼�".equals(nm))
			image.setImageResource(R.drawable.image_7);
		else if ("�Ѱ����̼���".equals(nm))
			image.setImageResource(R.drawable.image_8);

		// image.setImageResource(R.drawable.ic_launcher);
		name.setText(cursor.getString(cursor.getColumnIndex("name")));
		number.setText(cursor.getString(cursor.getColumnIndex("number")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.db_itemdata, parent, false);
		return v;
	}
}
