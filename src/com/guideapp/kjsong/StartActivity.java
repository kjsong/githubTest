package com.guideapp.kjsong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

	private View mainActivity;
	private View listViewActivity;
	private View cursorAdapterActivity;
	private View serviceActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		layoutCreate();
	}

	public void layoutCreate(){
		mainActivity = findViewById(R.id.button);
		mainActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartActivity.this, MainActivity.class));
			}
		});
		
		listViewActivity = findViewById(R.id.button_listView);
		listViewActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartActivity.this, ListViewActivity.class));
			}
		});
		
		cursorAdapterActivity = findViewById(R.id.button_db);
		cursorAdapterActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartActivity.this, CursorAdapterActivity.class));
			}
		});
		
		serviceActivity = findViewById(R.id.button_service);
		serviceActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartActivity.this, ServiceActivity.class));
			}
		});
		
	}

	//0510 xml과 java file로 나눠져 있던 intent 호출해서 다른 activity로 넘어가는 부분 수정
//	public void startActivity(View view){
//		startActivity(new Intent(StartActivity.this, MainActivity.class));
//	}
	
//	public void listViewActivity(View view){
//		startActivity(new Intent(StartActivity.this, ListViewActivity.class));
//	}
	
//	public void dbListViewActivity(View view){
//		startActivity(new Intent(StartActivity.this, CursorAdapterActivity.class));
//	}
	
//	public void serviceActivity(View view){
//		startActivity(new Intent(StartActivity.this, ServiceActivity.class));
//	}
}
