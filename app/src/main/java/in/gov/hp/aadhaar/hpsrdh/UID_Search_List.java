package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.List;

public class UID_Search_List extends Activity {

    String UID_Service = null;
    ProgressBar pb;
    List<UID_Async> tasks;
    List<UserPojo> userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        UID_Service = bundle.getString("UID");
    }


}
