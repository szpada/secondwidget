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

import com.widget.zapis.SaveService;
import com.widget.zapis.UserSettings;

public class SettingsActivity extends Activity{
	
	private Button ok;
	private EditText salary;
	
	private SaveService saver;
	
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
   	saveState();
//    	resuming=true; //so that on resume you can read in last state
	}
	



	protected void onResume() {
        super.onResume();

        Log.d("GameActivity", "!jestem w GameActivity.onResume()");
        loadSettings();
}
	
	
	private void loadSettings() {
		 UserSettings saved = saver.readSettings("user_settings");
		 if (saved==null) {
			 saved = new UserSettings();	
		 }
		 salary.setText(Integer.toString(saved.getSalary()));  
		 Log.d("OptionsTab", "wczytando" );
		 
	
	}
	
	private void saveState() {
		UserSettings saved = new UserSettings();
		saved.setSalary(Integer.parseInt(this.salary.getText().toString()));
       saver.saveSettings(saved, "user_settings");
       Log.d("OptionsTab", "SAVED SETTINGS");
       
	}
	
	
}
