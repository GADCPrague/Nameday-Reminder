package cz.adevcamp.droidpartisans;

import java.util.List;
import java.util.Vector;

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
	Vector<NamedayCsvLoader.Day> vDay;
	LayoutInflater lIinflater;
	View v;
	
	CustomAdapter(Activity context, List<Contact> lContacts, Vector<NamedayCsvLoader.Day> vDay){
		this.mContext=context;
		this.lContacts=lContacts;
		this.vDay=vDay;
		lIinflater=mContext.getLayoutInflater();
		
	}

	public int getCount() {
		return vDay.size();
	}


	public Object getItem(int position) {
		return vDay.get(position);
	}

	public long getItemId(int position) {
		return vDay.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		v=convertView;
		if(v==null)
			v=lIinflater.inflate(R.layout.rowitem, null);
		
		TextView tv_date=(TextView) v.findViewById(R.id.tv_date);
			TextView tv_name=(TextView) v.findViewById(R.id.tv_contactName);
			ImageView iv_iconCall=(ImageView) v.findViewById(R.id.iv_iconCall);
			ImageView iv_iconMess=(ImageView) v.findViewById(R.id.iv_iconMess);
			
		
		tv_date.setText(vDay.get(position).date);
		
		//tv_name.setText(((Contact)lContacts.get(position)).getName());
		tv_name.setText(vDay.get(position).dateName);
		iv_iconCall.setImageResource(R.drawable.ic_launcher);
		iv_iconMess.setImageResource(R.drawable.ic_launcher);
		
		return v;
	}

}
