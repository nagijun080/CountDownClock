package com.example.countdownclock;

import java.util.Calendar;
import java.util.Date;



import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class Myservice extends Service {
   //適当なアクション名

	//private static Integer[] numImage = new Integer[]{ R.drawable.bk0, R.drawable.bk1, R.drawable.bk2, R.drawable.bk3, 
	//	R.drawable.bk4, R.drawable.bk5, R.drawable.bk6, R.drawable.bk7, R.drawable.bk8, R.drawable.bk9, };
	private static String[] weekName = new String[] { "日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日", };
		
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO 自動生成されたメソッド・スタブ
		RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.widget_main);
		//remoteViews.setTextViewText(R.id.textView1, new Date().toLocaleString());
		//Log.d("Myservice",new Date().toLocaleString());
		Calendar calendar = Calendar.getInstance();
		Calendar countDown = Calendar.getInstance();
		/*Integer second = date.get(Calendar.SECOND);
		**Integer secUD = second % 10;
		**Integer secTD = second / 10;
		*/
		countDown.set(2012, Calendar.NOVEMBER, 1);
		//今年の始めから１１月１日までの日数
		Integer releaseDay = countDown.get(Calendar.DAY_OF_YEAR);
		//今年の始めから現在までの日数
		Integer nowDay = calendar.get(Calendar.DAY_OF_YEAR);
		releaseDay -= nowDay; //現在の日にちから発売日までの日数をreleaseDayに代入
		Log.d("test count down",releaseDay.toString());
		
		//年の初期化
		Integer yearInt = calendar.get(Calendar.YEAR);
		String year = new String(yearInt + "年");
		//日付の初期化
		Integer dateMonth = calendar.get(Calendar.MONTH);
		Integer dateDay = calendar.get(Calendar.DATE);
		String date = new String(dateMonth + "月" + dateDay + "日");
		//曜日の初期化
		Integer week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		//分の初期化
		Integer minute = calendar.get(Calendar.MINUTE);
		Integer minUD = minute % 10;
		Integer minTD = minute / 10;
		
		String minUDSt = minUD.toString();
		String minTDSt = minTD.toString();
		//時間の初期化
		Integer hour = calendar.get(Calendar.HOUR);
		Integer hrUD = hour % 10;
		Integer hrTD = hour / 10;
		
		String hrUDSt = hrUD.toString();
		String hrTDSt = hrTD.toString();
		//年をR.id.yearに表示
		remoteViews.setTextViewText(R.id.year, year);
		//日付をR.id.dayに表示
		remoteViews.setTextViewText(R.id.day, date);
		//曜日をwidget_main.xmlのweekに表示
		remoteViews.setTextViewText(R.id.week, weekName[week]);
		
		//十の位の時数をhrTenthsDigitに表示
		remoteViews.setTextViewText(R.id.hrTenthsDigit, hrTDSt);
		//一の位の時数をhrUnitDigitに表示
		remoteViews.setTextViewText(R.id.hrUnitDigit, hrUDSt);
				
		
		//十の位の分数をminTenthsDigitに表示
		remoteViews.setTextViewText(R.id.minTenthsDigit, minTDSt);
		//一の位の分数をminUnitDigitに表示
		remoteViews.setTextViewText(R.id.minUnitDigit, minUDSt);
		
		
		//十の位の秒数をimageView2に表示
		/*remoteViews.setImageViewResource(R.id.secTenthsDigit, numImage[secTD]);
		//一の位の秒数をimageView1に表示
		/*remoteViews.setImageViewResource(R.id.secUnitDigit, numImage[secUD]);
		*/
		
		ComponentName thisWidget = new ComponentName(this, CountDownClock.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, remoteViews);
	}

	


	@Override
	public IBinder onBind(Intent arg0) {
	// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
