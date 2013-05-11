package pl.looksok.widgetdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity{
	
	private Button ok;
	private EditText salary;
	
	private Context baseContext;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);            
        
        if(getIntent().getStringExtra("widget")!=null){
        	Log.i("INDEX", getIntent().getStringExtra("widget"));
        	//widget = Integer.parseInt(getIntent().getStringExtra("widget"));
        }
        else{
         Log.i("INDEX", "NULL");
        }
        
        setContentView(R.layout.main);
        
        baseContext = getBaseContext();
        
        salary = (EditText) findViewById(R.id.main_salary);
        
        ok = (Button) findViewById(R.id.main_ok);
        ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(baseContext, MyWidgetProvider.class);
				startActivity(i);
			}
		});
	}
	
	
	protected void onStop() 
    {
        super.onStop();
        
        
        Log.d("GameActivity", "MYonStop is called");
        
        finish();
    }
    
    
    protected void onPause() {
    	super.onPause();
//    	if(view != null){
//    		view.releaseSounds();
//    	}
   	Log.d("GameActivity", "MYonPause is called");
//    	saveState();
//    	resuming=true; //so that on resume you can read in last state
	}
	
	
	protected void onResume() {
        super.onResume();
//        if(view != null){
//        	view.prepareSounds();
//        }
        Log.d("GameActivity", "!jestem w GameActivity.onResume()");
//        if (resuming) { //only if the game is being resumed, o/w the game that's sitting in the save file is not relevant
//        	readSavedState();
//        	Log.d("GameActivity", "read last saved state");
//            
        }
	
}
