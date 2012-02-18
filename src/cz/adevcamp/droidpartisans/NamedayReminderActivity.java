package cz.adevcamp.droidpartisans;

import android.app.ListActivity;
import android.os.Bundle;

public class NamedayReminderActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchContacts(this).execute();
    }
}