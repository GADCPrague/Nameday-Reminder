package cz.adevcamp.droidpartisans;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseExpandableListAdapter {
	ExpandableListActivity mContext;
	List<Contact> lContacts;
	Vector<NamedayCsvLoader.Day> vDay;
	LayoutInflater lIinflater;
	View v;
	String[][] children;

	CustomAdapter(ExpandableListActivity context, List<Contact> lContacts,
			Vector<NamedayCsvLoader.Day> vDay) {
		this.mContext = context;
		this.lContacts = lContacts;
		this.vDay = vDay;
		children = new String[][] { { "Arnold", "Barry", "Chuck", "David" },
				{ "Ace", "Bandit", "Cha-Cha", "Deuce" },
				{ "Fluffy", "Snuggles" }, { "Goldy", "Bubbles" } };
		lIinflater = mContext.getLayoutInflater();

	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		v = convertView;
		if (v == null)
			v = lIinflater.inflate(R.layout.rowitem, null);

		TextView tv_date = (TextView) v.findViewById(R.id.tv_date);
		TextView tv_name = (TextView) v.findViewById(R.id.tv_contactName);
		ImageView iv_iconCall = (ImageView) v.findViewById(R.id.iv_iconCall);
		ImageView iv_iconMess = (ImageView) v.findViewById(R.id.iv_iconMess);

		tv_date.setText(vDay.get(groupPosition).date);

		// tv_name.setText(((Contact)lContacts.get(position)).getName());
		tv_name.setText(vDay.get(groupPosition).dateName);
		iv_iconCall.setImageResource(R.drawable.phone_icon);
		iv_iconMess.setImageResource(R.drawable.sms_icon);
		iv_iconCall.setOnClickListener(new mListener(mContext));// listener na
																// event click
																// ikony phone
		iv_iconMess.setOnClickListener(new mListener(mContext));// listener na
																// event click
																// ikony message
		return v;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return children[0][0];
	}

	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}


	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view=convertView;
		if(view==null)
			//nevim proc vyhazuje vyjimku ?
			view=lIinflater.inflate(R.layout.rowitem, parent);
		return view;
	}

	public int getChildrenCount(int groupPosition) {
		return children.length;
	}

	public Object getGroup(int groupPosition) {
		return vDay.get(groupPosition);
	}

	public int getGroupCount() {
		return vDay.size();
	}

	public long getGroupId(int groupPosition) { 
		return 0;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
