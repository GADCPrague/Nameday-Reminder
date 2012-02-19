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
	public mListener(Activity activity) {
		this.mActivity=activity;
	}

	public void onClick(View v) {
		switch(v.getId()){//rozliseni eventu podle id
		case R.id.iv_iconCall:call(); Toast.makeText(mActivity, "call", Toast.LENGTH_LONG).show();break;
		case R.id.iv_iconMess:write();Toast.makeText(mActivity, "mess", Toast.LENGTH_LONG).show();break;
		}
	}

	private void write() {
		//TODO change static nb
		String defaultNb="123456789";
		mActivity.startActivity(new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + defaultNb)));
		
		
	}

	private void call() {
		//TODO change static nb
		String defaultNb="123456789";
		mActivity.startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+defaultNb)));
		
	}

}
