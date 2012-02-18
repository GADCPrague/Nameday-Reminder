package cz.adevcamp.droidpartisans;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;

public class FetchContacts extends AsyncTask<String, Integer, Boolean>{
	ListActivity mActivity;
	ProgressDialog pdDialog;
	List<Contact> lPeople;
	public FetchContacts(ListActivity activity) {
		this.mActivity=activity;
		lPeople=new ArrayList<Contact>();
	}

	protected void onPreExecute(){
		pdDialog=new ProgressDialog(mActivity);
		pdDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pdDialog.setMax(100);
		pdDialog.show();
		
	}
	@Override
	protected Boolean doInBackground(String... params) {
		Cursor phones = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);Cursor c=mActivity.getContentResolver().query(android.provider.ContactsContract.Contacts.CONTENT_URI,null,null,null,null); //vybrani vsech zaznamu
		
		while(phones.moveToNext()){
			String[] sname=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).split(" ");
			String name=sname[0];
			String surname="";
			if(sname.length>1)surname=sname[1];
			String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			   
			lPeople.add(new Contact(1, name, surname, phoneNumber));
			publishProgress(1);
		}
		
		return true;
		
	}
	
	protected void onProgressUpdate(Integer... i){
		pdDialog.incrementProgressBy(i[0]);
		
	}
	protected void onPostExecute(final Boolean state){
		pdDialog.dismiss();
		
		
	}
	
}
