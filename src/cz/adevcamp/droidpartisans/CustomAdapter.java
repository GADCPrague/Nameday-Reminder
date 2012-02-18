package cz.adevcamp.droidpartisans;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter{
	Activity mContext;
	List<Contact> lContacts;
	CustomAdapter(Activity context, List<Contact> lContacts){
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
		View v=convertView;
		if(v==null){
			LayoutInflater lIinflater=mContext.getLayoutInflater();
			v=lIinflater.inflate(R.layout.rowitem, null);
			TextView tv_date=(TextView) v.findViewById(R.id.tv_date);
			TextView tv_appname=(TextView) v.findViewById(R.id.tv_contactName);
			ImageView iv_iconCall=(ImageView) v.findViewById(R.id.iv_iconCall);
			ImageView iv_iconMess=(ImageView) v.findViewById(R.id.iv_iconMess);
			tv_date.setText("datum");
			tv_appname.setText(((Contact)lContacts.get(position)).getName());
			iv_iconCall.setImageResource(R.drawable.ic_launcher);
			iv_iconMess.setImageResource(R.drawable.ic_launcher);
		}
		
		return v;
	}

}
