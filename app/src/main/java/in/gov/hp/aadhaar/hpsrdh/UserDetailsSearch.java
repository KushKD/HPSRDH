package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class UserDetailsSearch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_search);

        Intent getRoomDetailsIntent = getIntent();
        UserPojo guestHouseDetails =  (UserPojo) getRoomDetailsIntent.getSerializableExtra("Details");
        Toast.makeText(getApplicationContext(),"Got IT",Toast.LENGTH_LONG).show();
    }


}
