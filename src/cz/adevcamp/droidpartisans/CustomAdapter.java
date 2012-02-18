package cz.adevcamp.droidpartisans;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomAdapter extends BaseAdapter{
	Context mContext;
	List<Contact> lContacts;
	CustomAdapter(Context context, List<Contact> lContacts){
		this.mContext=context;
		this.lContacts=lContacts;
	}

	public int getCount() {
		return lContacts.size();
	}


	public Object getItem(int position) {
		return lContacts.get(position);
	}

	public long getItemId(int position) {
		return lContacts.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//TODO getView adapter
		
		return null;
	}

}
