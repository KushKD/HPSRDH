package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {


    TextView tv_IMEI ;
    RelativeLayout button_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tv_IMEI = (TextView)findViewById(R.id.tv_imei);
        button_main = (RelativeLayout)findViewById(R.id.button_main);


        //tv_IMEI.setText(IMIE_Number);

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this , SignIn.class );
                startActivity(i);
                LoginActivity.this.finish();

            }
        });
    }





}
