package cz.adevcamp.droidpartisans;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class mListener implements OnClickListener {
	Activity mActivity;
	String   mNumber;
	public mListener(Activity activity, String number) {
		this.mActivity = activity;
		this.mNumber = number;
	}

	public void onClick(View v) {
		switch(v.getId()){//rozliseni eventu podle id
		case R.id.iv_iconCall:call(); Toast.makeText(mActivity, "Zavolejte", Toast.LENGTH_LONG).show();break;
		case R.id.iv_iconMess:write();Toast.makeText(mActivity, "Napište SMS", Toast.LENGTH_LONG).show();break;
		}
	}

	private void write() {
		mActivity.startActivity(new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + mNumber)));
	}

	private void call() {
		mActivity.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + mNumber)));
	}

}
