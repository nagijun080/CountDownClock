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
  
	private static String[] weekName = new String[] { "���j��", "���j��", "�Ηj��", "���j��", "�ؗj��", "���j��", "�y�j��", };
		
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.widget_main);
		
		Calendar calendar = Calendar.getInstance();
		Calendar countDown = Calendar.getInstance();
		
		countDown.set(2012, Calendar.NOVEMBER, 1);
		//���N�̎n�߂���P�P���P���܂ł̓���
		Integer releaseDay = countDown.get(Calendar.DAY_OF_YEAR);
		//���N�̎n�߂��猻�݂܂ł̓���
		Integer nowDay = calendar.get(Calendar.DAY_OF_YEAR);
		releaseDay -= nowDay; //���݂̓��ɂ����甭�����܂ł̓�����releaseDay�ɑ��
		remoteViews.setTextViewText(R.id.AnniDay, releaseDay.toString());
		
		//�N�̏�����
		Integer yearInt = calendar.get(Calendar.YEAR);
		String year = new String(yearInt + "�N");
		//���t�̏�����
		Integer dateMonth = calendar.get(Calendar.MONTH) + 1;
		Integer dateDay = calendar.get(Calendar.DATE);
		String date = new String(dateMonth.toString() + "��" + dateDay.toString() + "��");
		//�j���̏�����
		Integer week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		//���̏�����
		Integer minute = calendar.get(Calendar.MINUTE);
		Integer minUD = minute % 10;
		Integer minTD = minute / 10;
		
		String minUDSt = minUD.toString();
		String minTDSt = minTD.toString();
		//���Ԃ̏�����
		Integer hour,hrUD,hrTD;
		
		if (Calendar.AM == calendar.get(Calendar.AM_PM)) {
			hour = calendar.get(Calendar.HOUR);
			hrUD = hour % 10;
			hrTD = hour / 10;
		} else {
			hour = calendar.get(Calendar.HOUR) + 12;
			hrUD = hour % 10;
			hrTD = hour / 10;
		}
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
		
		remoteViews.setTextViewText(R.id.textAnniversary, getText(R.string.release_first));
		
		
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
