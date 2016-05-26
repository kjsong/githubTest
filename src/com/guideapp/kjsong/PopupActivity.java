package com.guideapp.kjsong;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PopupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup);

		String data = getIntent().getStringExtra("Data");
		int resId = getResources().getIdentifier("Data_" + data, "array", getPackageName());
		String[] arData = getResources().getStringArray(resId);

		TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
		// txtTitle.setText(arData[0]);
		txtTitle.setText(String.valueOf(arData[0]));

		ImageView image = (ImageView) findViewById(R.id.image);
		image.setImageResource(getResources().getIdentifier("image_" + getIntent().getStringExtra("Data"), "drawable", getPackageName()));

		TextView txtTitle2 = (TextView) findViewById(R.id.txtTitle2);
		txtTitle2.setText(String.valueOf(arData[1]));
		txtTitle2.setMovementMethod(new ScrollingMovementMethod());

		TextView txtContent = (TextView) findViewById(R.id.txtContent);
		txtContent.setText(String.valueOf(arData[2]));
		txtContent.setMovementMethod(new ScrollingMovementMethod());

		findViewById(R.id.btnYes).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
