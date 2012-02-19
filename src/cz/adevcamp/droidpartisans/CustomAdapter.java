package cz.adevcamp.droidpartisans;

import java.util.List;
import java.util.Vector;

import android.app.ExpandableListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Populate entries in the calendar with data (both days and individual
 * contacts)
 */
public class CustomAdapter extends BaseExpandableListAdapter {
	ExpandableListActivity mContext;
	List<Contact> lContacts;
	Vector<NamedayCsvLoader.Day> vDay; // < name day calendar populated with
										// contacts
	LayoutInflater lIinflater;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param lContacts
	 *            - list of known contacts from address book
	 * @param vDay
	 *            - name day calendar populated with contacts with known
	 *            calendar names
	 */
	CustomAdapter(ExpandableListActivity context, List<Contact> lContacts,
			Vector<NamedayCsvLoader.Day> vDay) {
		this.mContext = context;
		this.lContacts = lContacts;
		this.vDay = vDay;

		lIinflater = mContext.getLayoutInflater();

	}

	/**
	 * Upper level calendar entry - it is entry with date and name day e.g.
	 * "21.2. Lenka"
	 */
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null)
			view = lIinflater.inflate(R.layout.rowitem, null);

		TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_contactName);
		String dateName = vDay.get(groupPosition).dateName;

		// display number of known contacts for day name in the calendar
		if (false == vDay.get(groupPosition).contacts.isEmpty()) {
			dateName += " (" + vDay.get(groupPosition).contacts.size() + ")";
		} else {
		}

		/* populate date and name day */
		tv_date.setText(vDay.get(groupPosition).date);
		tv_name.setText(dateName);

		return view;
	}

	/**
	 * Getter of data for child row - row with contact
	 */
	public Object getChild(int groupPosition, int childPosition) {

		return vDay.get(groupPosition).contacts.get(childPosition);
	}

	/**
	 * Unique identifier of child row
	 */
	public long getChildId(int groupPosition, int childPosition) {
		return 100000 + 100 * groupPosition + childPosition;
	}

	/**
	 * Lower level calendar entry - it is entry with contact details and
	 * call/sms buttons e.g. "Jan Novak <call> <sms>"
	 */
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null)

			view = lIinflater.inflate(R.layout.rowsubitem, null);

		/* find contact data and create label with contact nam */
		Contact contact = (Contact) getChild(groupPosition, childPosition);
		String name = contact.getName() + " " + contact.getSurname();

		TextView tv_name = (TextView) view.findViewById(R.id.tv_contactName);
		ImageView iv_iconCall = (ImageView) view.findViewById(R.id.iv_iconCall);
		ImageView iv_iconMess = (ImageView) view.findViewById(R.id.iv_iconMess);

		/* populate contact name and install handlers for call/sms */
		tv_name.setText(name);
		iv_iconCall.setImageResource(R.drawable.icon_ww_phone);
		iv_iconMess.setImageResource(R.drawable.icon_nixus_sms);
		iv_iconCall.setOnClickListener(new mListener(mContext, contact.getNumber())); // handle call
		iv_iconMess.setOnClickListener(new mListener(mContext, contact.getNumber())); // handle sms

		return view;
	}

	/**
	 * Number of contacts with name day in the specified position of calendar
	 */
	public int getChildrenCount(int groupPosition) {

		return vDay.get(groupPosition).contacts.size();
	}

	/**
	 * Data for upper level calendar entry
	 */
	public Object getGroup(int groupPosition) {
		return vDay.get(groupPosition);
	}

	/**
	 * Number of upper level calendar entries
	 */
	public int getGroupCount() {
		return vDay.size();
	}

	/**
	 * Unique identifier of upper level calendar entry
	 */
	public long getGroupId(int groupPosition) {
		return 100000 + 100 * groupPosition;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
