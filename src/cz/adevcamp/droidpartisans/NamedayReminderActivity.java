package cz.adevcamp.droidpartisans;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NamedayReminderActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("Foo", "Create");
    }
    

    @Override
    public void onResume() {
        super.onResume();
        
        Log.d("Foo", "Resume");
    }


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
        
        Log.d("Foo", "Destroy");
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
        
        Log.d("Foo", "Pause");
	}
    
}