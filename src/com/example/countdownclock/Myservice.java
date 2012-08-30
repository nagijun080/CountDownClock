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
   //�K���ȃA�N�V������

	//private static Integer[] numImage = new Integer[]{ R.drawable.bk0, R.drawable.bk1, R.drawable.bk2, R.drawable.bk3, 
	//	R.drawable.bk4, R.drawable.bk5, R.drawable.bk6, R.drawable.bk7, R.drawable.bk8, R.drawable.bk9, };
	private static String[] weekName = new String[] { "���j��", "���j��", "�Ηj��", "���j��", "�ؗj��", "���j��", "�y�j��", };
		
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
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
		//���N�̎n�߂���P�P���P���܂ł̓���
		Integer releaseDay = countDown.get(Calendar.DAY_OF_YEAR);
		//���N�̎n�߂��猻�݂܂ł̓���
		Integer nowDay = calendar.get(Calendar.DAY_OF_YEAR);
		releaseDay -= nowDay; //���݂̓��ɂ����甭�����܂ł̓�����releaseDay�ɑ��
		Log.d("test count down",releaseDay.toString());
		
		//�N�̏�����
		Integer yearInt = calendar.get(Calendar.YEAR);
		String year = new String(yearInt + "�N");
		//���t�̏�����
		Integer dateMonth = calendar.get(Calendar.MONTH);
		Integer dateDay = calendar.get(Calendar.DATE);
		String date = new String(dateMonth + "��" + dateDay + "��");
		//�j���̏�����
		Integer week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		//���̏�����
		Integer minute = calendar.get(Calendar.MINUTE);
		Integer minUD = minute % 10;
		Integer minTD = minute / 10;
		
		String minUDSt = minUD.toString();
		String minTDSt = minTD.toString();
		//���Ԃ̏�����
		Integer hour = calendar.get(Calendar.HOUR);
		Integer hrUD = hour % 10;
		Integer hrTD = hour / 10;
		
		String hrUDSt = hrUD.toString();
		String hrTDSt = hrTD.toString();
		//�N��R.id.year�ɕ\��
		remoteViews.setTextViewText(R.id.year, year);
		//���t��R.id.day�ɕ\��
		remoteViews.setTextViewText(R.id.day, date);
		//�j����widget_main.xml��week�ɕ\��
		remoteViews.setTextViewText(R.id.week, weekName[week]);
		
		//�\�̈ʂ̎�����hrTenthsDigit�ɕ\��
		remoteViews.setTextViewText(R.id.hrTenthsDigit, hrTDSt);
		//��̈ʂ̎�����hrUnitDigit�ɕ\��
		remoteViews.setTextViewText(R.id.hrUnitDigit, hrUDSt);
				
		
		//�\�̈ʂ̕�����minTenthsDigit�ɕ\��
		remoteViews.setTextViewText(R.id.minTenthsDigit, minTDSt);
		//��̈ʂ̕�����minUnitDigit�ɕ\��
		remoteViews.setTextViewText(R.id.minUnitDigit, minUDSt);
		
		
		//�\�̈ʂ̕b����imageView2�ɕ\��
		/*remoteViews.setImageViewResource(R.id.secTenthsDigit, numImage[secTD]);
		//��̈ʂ̕b����imageView1�ɕ\��
		/*remoteViews.setImageViewResource(R.id.secUnitDigit, numImage[secUD]);
		*/
		
		ComponentName thisWidget = new ComponentName(this, CountDownClock.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, remoteViews);
	}

	


	@Override
	public IBinder onBind(Intent arg0) {
	// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
	
}
