package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KD on 7/29/2015.
 */
public class AdvancedListFive extends Activity {

    String District_Service = null;
    String Name_Service = null;
    String FHName_Service = null;
    String DOB_Service = null;
    String Pincode_service = null;
    ProgressBar pb;
    List<FiveParameters_Async> tasks;
    List<UserPojo> userlist;


    ListView listv;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        District_Service = bundle.getString(EConstants.D);
        Name_Service = bundle.getString(EConstants.N);
        FHName_Service = bundle.getString(EConstants.FH);
        DOB_Service = bundle.getString(EConstants.DOB);
        Pincode_service = bundle.getString(EConstants.P);
        listv = (ListView) findViewById(R.id.list);
        context = this;
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
        if (isOnline()) {
            FiveParameters_Async asy_five = new FiveParameters_Async();
            asy_five.execute(District_Service,Name_Service,FHName_Service,DOB_Service,Pincode_service );
        } else {
            Toast.makeText(this, EConstants.NetworkError, Toast.LENGTH_LONG).show();
        }

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                UserPojo userDetails = (UserPojo)	parent.getItemAtPosition(position);
                Intent userSearch = new Intent();
                userSearch.putExtra("Details", userDetails);
                userSearch.setClass(AdvancedListFive.this, UserDetailsSearch.class);
                startActivity(userSearch);

            }
        });
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    protected void updateDisplay() {

        UserAdapter adapter = new UserAdapter(this, R.layout.item_flower, userlist);
        listv.setAdapter(adapter);

    }

 class FiveParameters_Async extends AsyncTask<String,String,String> {

        String District_s5 = null;
        String Dob_S5 = null;
        String Name_S5 = null;
        String FHNAme_S5= null;
        String Pincode_S5 = null;
        String url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);

        }

        @Override
        protected String doInBackground(String... params) {

            District_s5 = params[0];
            Name_S5 = params[1];
            FHNAme_S5 = params[2];
            Dob_S5 = params[3];
            Pincode_S5 = params[4];



            StringBuilder sb_search = new StringBuilder();
            sb_search.append(EConstants.url_Generic); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(EConstants.methord_fiveParams); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(District_s5); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(Name_S5); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(Dob_S5); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(FHNAme_S5); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(Pincode_S5);
            url = sb_search.toString();
            String content = HttpManager.getData(url);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            userlist = UserJson_FiveParameters.parseFeed(result);
            if(userlist.isEmpty()){
                Toast.makeText(getApplicationContext(),EConstants.ListEmpty,Toast.LENGTH_LONG).show();
            }else{
                updateDisplay();
            }


            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }


           // System.out.print(result);
        }
    }
}

