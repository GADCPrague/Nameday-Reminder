package cz.adevcamp.droidpartisans;

import android.app.ExpandableListActivity;
import android.os.Bundle;

public class NamedayReminderActivity extends ExpandableListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchContacts(this).execute();
    }
}