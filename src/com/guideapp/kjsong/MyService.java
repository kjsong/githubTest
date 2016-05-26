package com.guideapp.kjsong;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private final static String TAG = MyService.class.getSimpleName();

	static final int BACKGROUND_SERVICE_MSG_START_INIT = 50000;
	static final int BACKGROUND_SERVICE_MSG_START_SCAN = 50001;
	static final int BACKGROUND_SERVICE_MSG_START_STOP = 50002;
	static final int BACKGROUND_SERVICE_MSG_RESULT = 50003;
	static final int BACKGROUND_SERVICE_MSG_UNBIND = 50004;

	static final String MSG_MAC_ADDRESS = "MSG_MAC_ADDRESS";
	static final String MSG_UUID = "MSG_UUID";
	static final String MSG_MAJOR = "MSG_MAJOR";
	static final String MSG_MINOR = "MSG_MINOR";
	static final String MSG_RSSI = "MSG_RSSI";

	@Override
	public void onCreate() {
		Log.d(TAG, "BackgroundService - onCreate()");
		Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
		super.onCreate();
		// initBLE();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY; // run until explicitly stopped.
	}

	private Messenger messengerToActivity = null;
	final Messenger messengerFromActivity = new Messenger(new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			messengerToActivity = msg.replyTo;

			switch (msg.what) {

			case BACKGROUND_SERVICE_MSG_START_SCAN:
				Log.d("", "BACKGROUND_SERVICE_MSG_START_SCAN");

				Message msg1 = Message.obtain(null, 123);
				Bundle b = new Bundle();
				b.putInt("MYVALUE", 2);
				b.putInt("YOURVALUE", 1);
				msg1.setData(b);
				try {
					messengerToActivity.send(msg1);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				break;

			case BACKGROUND_SERVICE_MSG_START_STOP:
				Log.d("", "BACKGROUND_SERVICE_MSG_START_STOP");
				break;

			}
			return false;
		}

	}));

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "BackgroundService - onBind()");
		return messengerFromActivity.getBinder();

	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "BackgroundService - onUnbind()");
		stopSelf();
		return super.onUnbind(intent);
	}
	// @Override
	// public void onCreate(){
	// Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
	// }
	// @Override
	// public void onDestroy() {
	// Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	// }

}