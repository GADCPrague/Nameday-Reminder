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
		String dateName = vDay.get(groupPosition).dateName;

		// display number of known contacts for day name in the calendar
		if (false == vDay.get(groupPosition).contacts.isEmpty()) {
			dateName += " (" + vDay.get(groupPosition).contacts.size() + ")";
		}

//		tv_name.setText(((Contact) lContacts.get(position)).getName());
		tv_date.setText(vDay.get(groupPosition).date);
		tv_name.setText(dateName);
		
//		iv_iconMess.setImageResource(R.drawable.icon_idroid_sms);
//		iv_iconCall.setImageResource(R.drawable.icon_idroid_phone);
//		iv_iconMess.setImageResource(R.drawable.icon_j_sms);
//		iv_iconCall.setImageResource(R.drawable.icon_j_phone);
		iv_iconMess.setImageResource(R.drawable.icon_nixus_sms);
//		iv_iconCall.setImageResource(R.drawable.icon_nixus_phone);
//		iv_iconMess.setImageResource(R.drawable.icon_ww_sms);
		iv_iconCall.setImageResource(R.drawable.icon_ww_phone);
		// click listener - phone icon
		iv_iconCall.setOnClickListener(new mListener(mContext));
		// click listener - sms icon
		iv_iconMess.setOnClickListener(new mListener(mContext));
		
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
		View view = convertView;
		if (view == null)
			// nevim proc vyhazuje vyjimku ?
			view = lIinflater.inflate(R.layout.rowitem, parent);
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
