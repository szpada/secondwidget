package pl.looksok.widgetdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetIntentReceiver extends BroadcastReceiver {
	
	private static int clickCount = 0;
	
	private double money = 0.0;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("MyWidgetIntentReceiver", "otrzymalem " + intent);
		if(intent.getAction().equals("pl.looksok.intent.action.CHANGE_PICTURE")){
			//updateMoney(context);
			
			//Intent i = new Intent(context, SettingsActivity.class);
		    //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    //context.startActivity(i); 
		}
	}

	/*
	 * TEST SPRAWDZAJACY CZY TO GOWNMO DZIALA 
	 * 
	 */
	
	private void updateMoney(Context context){
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
		
		money+= 0.1;
		remoteViews.setTextViewText(R.id.money, Double.toString(money) + " PLN");
		
		//REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
		//remoteViews.setOnClickPendingIntent(R.id.widget_button, MyWidgetProvider.buildButtonPendingIntent(context));
		
		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}
	
	private int getImageToSet() {
		clickCount++;
		return clickCount % 2 == 0 ? R.drawable.me : R.drawable.wordpress_icon;
	
	}
}
