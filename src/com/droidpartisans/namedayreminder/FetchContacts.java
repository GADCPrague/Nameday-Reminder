package com.droidpartisans.namedayreminder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.droidpartisans.namedayreminder.NamedayCsvLoader.Day;


import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.ExpandableListView;

public class FetchContacts extends AsyncTask<String, Integer, Boolean> {
	ExpandableListActivity mActivity;
	ProgressDialog pdDialog;
	List<Contact> lPeople;
	Vector<Day> vDay;

	public FetchContacts(ExpandableListActivity activity) {
		this.mActivity = activity;
		lPeople = new ArrayList<Contact>();
		vDay = new Vector<NamedayCsvLoader.Day>();
	}

	@Override
	protected void onPreExecute() {
		pdDialog = new ProgressDialog(mActivity);
		pdDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// pdDialog.setMax(100);
		pdDialog.show();

	}

	@Override
	protected Boolean doInBackground(String... params) {

		Cursor phones = mActivity.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		
		pdDialog.setMax(phones.getCount());

		while (phones.moveToNext()) {
			String[] sname = phones
					.getString(
							phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
					.split(" ");
			String name = sname[0];
			String surname = "";
			if (sname.length > 1)
				surname = sname[1];
			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			lPeople.add(new Contact(1, name, surname, phoneNumber));
			publishProgress(1);
		}
		InputStream is = mActivity.getResources().openRawResource(
				R.raw.namedays_cz_rev);
		vDay = NamedayCsvLoader.getCalendar(new Vector<Contact>(lPeople), is);

		return true;

	}

	@Override
	protected void onProgressUpdate(Integer... i) {
		pdDialog.incrementProgressBy(i[0]);

	}

	@Override
	protected void onPostExecute(final Boolean state) {
		pdDialog.dismiss();
		CustomAdapter adapter = new CustomAdapter(mActivity, lPeople, vDay);
		mActivity.setListAdapter(adapter);
		
		/* select current day in the calendar */
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1;
		ExpandableListView view = mActivity.getExpandableListView();
		
		/* scroll to current day */
		view.setSelectedGroup(currentDay);
		
		/* expand 10 non-empty calendar entries */
		for (int i = currentDay; i < vDay.size() && i < currentDay + 10; i++) {
			if (!vDay.get(i).contacts.isEmpty()) {	// check whether calendar entry does contain any contacts
				view.expandGroup(i); 	
			}
		}

		/* hide default group indicator - we are drawing custom one */
		view.setGroupIndicator(null);
	}

}
