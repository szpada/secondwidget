package pl.looksok.widgetdemo;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider{

	private static Timer timer = null;

	private double money = 0.0;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
	    update(context);

	    Log.i("MyWidgetProvider", "dodaje pending listenery");
	    for (int i = 0; i < appWidgetIds.length; i++) {
	    	//Log.i("MyWidgetProvider", "listener : " + i);
	    	Log.i("MyWidgetProvider", "INFO : " + appWidgetManager.getAppWidgetInfo(appWidgetIds[i]));
	    	
	        int appWidgetId = appWidgetIds[i];

	        Intent intent = new Intent(context, SettingsActivity.class);
	        //PendingIntent pendingIntent = buildButtonPendingIntent(context);
	        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

	        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
	        views.setOnClickPendingIntent(R.id.imageView1, pendingIntent);
	        appWidgetManager.updateAppWidget(appWidgetId, views);
	    }
	    
		if (timer == null){
	      timer = new Timer();
	      Calendar cal = Calendar.getInstance();
	      cal.add(Calendar.SECOND, 1);
	      cal.set(Calendar.MILLISECOND, 0);

	      timer.scheduleAtFixedRate(new MyTime(context,this),
	                                          cal.getTime(), 1000);
		}
		
//		Intent intent = new Intent(context, SettingsActivity.class);
//		PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);
		
		//Intent launchActivity = new Intent(context,SettingsActivity.class);
		//PendingIntent pendingIntent = PendingIntent.getActivity(context,0, launchActivity, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
		//remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
		
		pushWidgetUpdate(context, remoteViews);
	}
	
	public static PendingIntent buildButtonPendingIntent(Context context) {
		Intent intent = new Intent();
	    intent.setAction("pl.looksok.intent.action.CHANGE_PICTURE");
	    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName myWidget = new ComponentName(context, MyWidgetProvider.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(myWidget, remoteViews);		
	}
	
	public void update(Context context){
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
	    //widgetManager.updateAppWidget(appWidgetIds, views);
		updateMoney(context);
	}	
	
	private void updateMoney(Context context){
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
		
		money += 0.01;
		String formatedMoney = String.format("%.2f", money);
		remoteViews.setTextViewText(R.id.money, formatedMoney + " PLN");
		//Log.d("MyWidgetProvider", "dodaje kase!");
		//REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		//remoteViews.setOnClickPendingIntent(R.id.widget_button, MyWidgetProvider.buildButtonPendingIntent(context));
		
		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}
	
	private class MyTime extends TimerTask{
	    MyWidgetProvider parent;
	    Context context;

	    public MyTime(Context context, MyWidgetProvider parent){
	    	this.parent = parent;
	    	this.context = context;
	    }

	    @Override
	    public void run(){
	    	try{
	    		parent.update(context);
	    	}
	    	catch (Exception e){
	    		Log.d("MyWidgetProvider", "timmer RUN error!");
	       }
	    }
	}
}
