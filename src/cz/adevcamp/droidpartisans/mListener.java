package cz.adevcamp.droidpartisans;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 *  Handle click events on phone and message buttons
 */
public class mListener implements OnClickListener {
	Activity mActivity;
	String   mNumber;
	
	/* Constructor - remember telephone number of contact */
	public mListener(Activity activity, String number) {
		this.mActivity = activity;
		this.mNumber = number;
	}

	/* handle request for telephone call or sms */
	public void onClick(View v) {
		
		// distinguish event according to ID of clicked element
		switch(v.getId()) {
		
			case R.id.iv_iconCall:
				call();
				Toast.makeText(mActivity, "Zavolejte oslavenci", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.iv_iconMess:
				write();
				Toast.makeText(mActivity, "Napište přání k svátku", Toast.LENGTH_LONG).show();
				break;
		}
	}

	/* send SMS message to contact */
	private void write() {
		mActivity.startActivity(new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + mNumber)));
	}

	/* make telephone call to contact */
	private void call() {
		mActivity.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + mNumber)));
	}

}
