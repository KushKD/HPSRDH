package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {

    String IMIE_Number = null;
    TextView tv_IMEI ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_IMEI = (TextView)findViewById(R.id.tv_imei);

      IMIE_Number = getIMEI(LoginActivity.this);
        tv_IMEI.setText(IMIE_Number);
    }



    public String getIMEI(Context context){


        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        return imei;

    }

}
