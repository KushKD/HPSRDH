package in.gov.hp.Aadhaar_HP.SRDH;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LoginActivity extends Activity {


    TextView tv_IMEI ;
    RelativeLayout button_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tv_IMEI = (TextView)findViewById(R.id.tv_imei);
        button_main = (RelativeLayout)findViewById(R.id.button_main);

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
