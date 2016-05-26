package com.guideapp.kjsong;

import java.util.ArrayList;

import com.guideapp.kjsong.R;
import com.guideapp.kjsong.R.drawable;
import com.guideapp.kjsong.R.id;
import com.guideapp.kjsong.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends Activity {

	ListView listView;
	ArrayList<String> items;
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);

		listView = (ListView) this.findViewById(R.id.listView);

		items = new ArrayList<>();
		items.add("01 ���Ｍ���ڹ���");
		items.add("02 ���Ǵ�");
		items.add("03 ������Ͽ콺");
		items.add("04 �Ѱ���������");
		items.add("05 700(ȫ����)");
		items.add("06 �߿ܹ���");
		items.add("07 ��Ÿ�ν����̼�");
		items.add("08 �Ѱ����̼���");

		CustomAdapter adapter = new CustomAdapter(this, 0, items);
		listView.setAdapter(adapter);
		
	//	listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(new ListViewItemLongClickListener());
		
//		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
//			
//			Toast.makeText(getBaseContext(), items.get(position), Toast.LENGTH_LONG).show();
//		}
		
	}
	
	class ListViewItemLongClickListener implements AdapterView.OnItemLongClickListener{
		
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
			alert.setTitle("Ȯ��");
			alert.setMessage(items.get(position));
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			alert.show();
			return false;
		}
		
		
	}

	private class CustomAdapter extends ArrayAdapter<String> {
		private ArrayList<String> items;

		public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
			super(context, textViewResourceId, objects);
			this.items = objects;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.listitem_data, null);
			}

			// ImageView �ν��Ͻ�
			ImageView imageView = (ImageView) v.findViewById(R.id.icon);

			// ����Ʈ���� �����ۿ� �̹����� �����Ѵ�.
			if ("01 ���Ｍ���ڹ���".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_1);
			else if ("02 ���Ǵ�".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_2);
			else if ("03 ������Ͽ콺".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_3);
			else if ("04 �Ѱ���������".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_4);
			else if ("05 700(ȫ����)".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_5);
			else if ("06 �߿ܹ���".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_6);
			else if ("07 ��Ÿ�ν����̼�".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_7);
			else if ("08 �Ѱ����̼���".equals(items.get(position)))
				imageView.setImageResource(R.drawable.image_8);

			TextView textView = (TextView) v.findViewById(R.id.userId);
			textView.setText(items.get(position));

			final String text = items.get(position);
			Button button = (Button) v.findViewById(R.id.button);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					 Toast.makeText(ListViewActivity.this, text,
					 Toast.LENGTH_SHORT).show();
				}
			});

			return v;
		}
	}

	public void myOnClick(View view) {
		Intent intent = new Intent(ListViewActivity.this, PopupActivity.class);
		intent.putExtra("Data", (String) view.getTag());
		startActivity(intent);
	}
}
