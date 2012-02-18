package cz.adevcamp.droidpartisans;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomAdapter extends BaseExpandableListAdapter{
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
	
	public View getView(final int position, View convertView, ViewGroup parent) {
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

	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return vDay.get(groupPosition).contacts.size();
	}

	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return vDay.get(groupPosition).contacts;
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return vDay.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
