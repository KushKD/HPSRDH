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


public class AdvancedListFour extends Activity {

    String District_Service = null;
    String Name_Service = null;
    String FHName_Service = null;
    String DOB_Service = null;
    ProgressBar pb;
    List<FourParameters_Async> tasks;
    List<UserPojo> userlist;
    ListView listv;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        District_Service = bundle.getString(EConstants.D4);
        Name_Service = bundle.getString(EConstants.N4);
        FHName_Service = bundle.getString(EConstants.FH4);
        DOB_Service = bundle.getString(EConstants.DOB4);
        listv = (ListView) findViewById(R.id.list);
        context = this;

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        if (isOnline()) {
             FourParameters_Async asy_four = new FourParameters_Async();
             asy_four.execute(District_Service,Name_Service,FHName_Service,DOB_Service);
        } else {
            Toast.makeText(this, EConstants.NetworkError, Toast.LENGTH_LONG).show();
        }

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                UserPojo userDetails = (UserPojo)	parent.getItemAtPosition(position);

                Intent userSearch = new Intent();
                userSearch.putExtra("Details", userDetails);
                userSearch.setClass(AdvancedListFour.this, UserDetailsSearch.class);
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

    class FourParameters_Async extends AsyncTask<String,String,String> {

        String District_s4 = null;
        String Dob_S4 = null;
        String Name_S4 = null;
        String FHNAme_S4= null;
        String Pincode_S4 = null;
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

            District_s4 = params[0];
            Name_S4 = params[1];
            FHNAme_S4 = params[2];
            Dob_S4 = params[3];
            StringBuilder sb_search = new StringBuilder();
            sb_search.append(EConstants.url_Generic); sb_search.append(EConstants.url_Delemetre);
            sb_search.append(EConstants.methord_fourParams); sb_search.append(EConstants.url_Delemetre);
            sb_search.append( District_s4 + EConstants.url_Delemetre);
            sb_search.append(Dob_S4+ EConstants.url_Delemetre);
            sb_search.append(Name_S4  + EConstants.url_Delemetre);
            sb_search.append(FHNAme_S4 );
            url = sb_search.toString();
            String content = HttpManager.getData(url);
             return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
             userlist = UserJson_FourParameters.parseFeed(result);
            if(userlist.isEmpty()){
                Toast.makeText(getApplicationContext(),EConstants.ListEmpty,Toast.LENGTH_LONG).show();
            }else{
                updateDisplay();
            }
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
            //System.out.print(result);
        }
    }

}
