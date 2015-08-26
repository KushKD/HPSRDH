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

public class UID_Search_List extends Activity {

    String UID_Service = null;
    ProgressBar pb;
    List<UID_Async> tasks;
    List<UserPojo> userlist;
    ListView listv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        UID_Service = bundle.getString(EConstants.UID);
        listv = (ListView) findViewById(R.id.list);
        context = this;

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        if (isOnline()) {
            UID_Async EID = new UID_Async();
            EID.execute(UID_Service);
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                UserPojo userDetails = (UserPojo)	parent.getItemAtPosition(position);

                Intent userSearch = new Intent();
                userSearch.putExtra("Details", userDetails);
                userSearch.setClass(UID_Search_List.this, UserDetailsSearch.class);
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

    class UID_Async extends AsyncTask<String,String,String> {

        String EID_S = null;
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

            EID_S = params[0];

            StringBuilder sb_search = new StringBuilder();
            sb_search.append(EConstants.url_Generic);sb_search.append(EConstants.url_Delemetre);
            sb_search.append(EConstants.methord_SearchAadaar);sb_search.append(EConstants.url_Delemetre);
            sb_search.append( EID_S );
            url = sb_search.toString();
            String content = HttpManager.getData(url);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            userlist = UserJson_UID.parseFeed(result);
            if(userlist.isEmpty()){
                Toast.makeText(getApplicationContext(),EConstants.ListEmpty,Toast.LENGTH_LONG).show();
            }else{
                updateDisplay();
            }

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
            System.out.print(result);
        }
    }



}
