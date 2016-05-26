package com.guideapp.kjsong;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends Activity implements OnClickListener {

	private final String TAG = ServiceActivity.class.getSimpleName();

	private Button btnStart;
	private Button btnStop;

	boolean isBackGroundServiceBound = false;
	private Messenger messengerToService = null;
	private Messenger messengerFromService = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);

		initUI();
		initClass();
		initService();
	}

	@Override
	protected void onDestroy() {
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
		doUnbindService();
		Log.i(TAG, "onServiceConnected5");
		super.onDestroy();
	}

	private void initUI() {
		btnStart = (Button) findViewById(R.id.btnStart);
		Log.i(TAG, "onServiceConnected1");
		btnStop = (Button) findViewById(R.id.btnStop);

		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	private void initClass() {
		// Log.i(TAG, "onServiceConnected");//¾ÈÂïÈû
		messengerFromService = new Messenger(new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				handleMessageMainActivity(msg);
				Log.i(TAG, "onServiceConnected2");
				return false;
			}
		}));
	}

	private void initService() {
		doBindService();
		Log.i(TAG, "onServiceConnected3");
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			messengerToService = new Messenger(service);
			if (messengerToService != null) {
				try {
					Message msg = Message.obtain(null, MyService.BACKGROUND_SERVICE_MSG_START_INIT);
					Log.i(TAG, "onServiceConnected4");
					msg.replyTo = messengerFromService;
					messengerToService.send(msg);
				} catch (RemoteException e) {
				}
			}

		}

		public void onServiceDisconnected(ComponentName className) {
			messengerToService = null;
			Log.i(TAG, "onServiceConnected6"); // ÀÌ°Ç ¾ÈÂïÈ÷´Âµ¥?
		}
	};

	private void doBindService() {
		bindService(new Intent(this, MyService.class), mConnection, Context.BIND_AUTO_CREATE);
		isBackGroundServiceBound = true;
	}

	private void doUnbindService() {
		if (isBackGroundServiceBound) {
			if (messengerToService != null) {
				try {
					Message msg = Message.obtain(null, MyService.BACKGROUND_SERVICE_MSG_UNBIND);
					msg.replyTo = messengerFromService;
					messengerToService.send(msg);
				} catch (RemoteException e) {
				}
			}
			unbindService(mConnection);
			isBackGroundServiceBound = false;
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnStart:
			//startBackgroundScan();
			BackgroundScan(MyService.BACKGROUND_SERVICE_MSG_START_SCAN);
			break;

		case R.id.btnStop:
			//stopBackgroundScan();
			BackgroundScan(MyService.BACKGROUND_SERVICE_MSG_START_STOP);
			break;
		}

	}

	private void handleMessageMainActivity(Message msg) {
		switch (msg.what) {

		case 123: {
			Bundle b = msg.getData();
			int recvDataMe = b.getInt("MYVALUE");
			int recvDataYou = b.getInt("YOURVALUE");
			
			Log.d("", "recvDataMe: " + (recvDataMe));
			Log.d("", "recvDataYou: " + (recvDataYou));
			Log.d("", "recvDataTotal: " + (recvDataMe + recvDataYou));
		}

			break;

		case MyService.BACKGROUND_SERVICE_MSG_RESULT:

			String macAddress = "";
			String uuid = "";
			int major = 0;
			int minor = 0;
			int rssi = 0;

			Bundle b = msg.getData();
			if (b != null) {
				macAddress = b.getString(MyService.MSG_MAC_ADDRESS);
				uuid = b.getString(MyService.MSG_UUID);
				major = b.getInt(MyService.MSG_MAJOR);
				minor = b.getInt(MyService.MSG_MINOR);
				rssi = b.getInt(MyService.MSG_RSSI);

			}

			String logdata = String.format("[Result] mac: %s, uuid: %s, major: %d, minor: %d, rssi: %d", macAddress, uuid, major, minor, rssi);
			Log.d("TAG", logdata);

			break;
		}
	}
	
	private void BackgroundScan(int t){
		if (isBackGroundServiceBound) {
			if (messengerToService != null) {
				try {
					Message msg = Message.obtain(null, t);
					msg.replyTo = messengerFromService;
					messengerToService.send(msg);

				} catch (RemoteException e) {
				}
			}
		}
		
	}

//	private void startBackgroundScan() {
//		Log.d(TAG, "startBackgroundScan()");
//		if (isBackGroundServiceBound) {
//			if (messengerToService != null) {
//				try {
//
//					Log.d(TAG, "startBackgroundScan() send message");
//
//					Message msg = Message.obtain(null, MyService.BACKGROUND_SERVICE_MSG_START_SCAN);
//					msg.replyTo = messengerFromService;
//					messengerToService.send(msg);
//
//				} catch (RemoteException e) {
//				}
//			}
//		}
//	}

//	private void stopBackgroundScan() {
//		Log.d(TAG, "stopBackgroundScan()");
//		if (isBackGroundServiceBound) {
//			if (messengerToService != null) {
//				try {
//
//					Log.d(TAG, "stopBackgroundScan() send message");
//
//					Message msg = Message.obtain(null, MyService.BACKGROUND_SERVICE_MSG_START_STOP);
//					msg.replyTo = messengerFromService;
//					messengerToService.send(msg);
//				} catch (RemoteException e) {
//				}
//			}
//		}
//	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.service);
	// }

	// //Start Service
	// public void myStartService(View view) {
	// startService(new Intent(this,MyService.class));
	// }
	//
	// public void myStopService(View view){
	// stopService(new Intent(this,MyService.class));
	// }
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	//
	// //noinspection SimplifiableIfStatement
	// if (id == R.id.action_settings) {
	// return true;
	// }
	//
	// return super.onOptionsItemSelected(item);
	// }
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	//
	// }
}
