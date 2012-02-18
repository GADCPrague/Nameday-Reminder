package cz.adevcamp.droidpartisans;

import android.app.Activity;
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
		case R.id.iv_iconCall:Toast.makeText(mActivity, "call", Toast.LENGTH_LONG).show();break;
		case R.id.iv_iconMess:Toast.makeText(mActivity, "mess", Toast.LENGTH_LONG).show();break;
		}
	}

}
