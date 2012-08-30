package com.example.countdownclock;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CountDownClock extends AppWidgetProvider {
	
	
	private static final String ACTION_START_MY_ALARM =
			"com.example.android.appwidget.countdownclock.ACTION_START_MY_ALARM";
	private final long interval = 60 * 1000;

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onReceive(context, intent);
		//Log.d("onReceive","super�̂���");
			if (ACTION_START_MY_ALARM.equals(intent.getAction())) {
				Intent serviceIntent = new Intent(context, Myservice.class);
				context.startService(serviceIntent);
				//Log.d("onReceive","if���̒�");
			}
			//Log.d("onReceive�̒�","setAlarm()�̑O");
			setAlarm(context);
		}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
			//Log.d("update","Update�̒�");
			setAlarm(context);
		}
	
	private void setAlarm(Context context) {
		Intent alarmIntent = new Intent(context, CountDownClock.class);
		alarmIntent.setAction(ACTION_START_MY_ALARM);
		PendingIntent operation = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		long now = System.currentTimeMillis() + 1;// + 1�͊m���ɖ��������ɂȂ�悤�ɂ���
		long oneSecondAfter = now + interval - now % (interval);
		am.set(AlarmManager.RTC, oneSecondAfter, operation);
	}
}
