package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UserListFour extends Activity {

    String District_Service = null;
    String Name_Service = null;
    String FHName_Service = null;
    String DOB_Service = null;
    ProgressBar pb;
    List<FourParameters_Async> tasks;
    List<UserPojo> userlist;


    ListView listv;
    Context context;
    private static final String url_SearchService = "http://10.241.9.72/aadhaarweb/RestServiceImpl.svc/searchfour/" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        District_Service = bundle.getString("District");
        Name_Service = bundle.getString("Name");
        FHName_Service = bundle.getString("FHName");
        DOB_Service = bundle.getString("Dob");

        Toast.makeText(getApplicationContext(),District_Service + Name_Service + FHName_Service + DOB_Service , Toast.LENGTH_LONG ).show();

        listv = (ListView) findViewById(R.id.list);
        context = this;

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        if (isOnline()) {
             FourParameters_Async asy_four = new FourParameters_Async();
             asy_four.execute(District_Service,Name_Service,FHName_Service,DOB_Service);
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }



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

//        if (guestHouseList != null) {
//            for (GuestHousePojo guesthouse : guestHouseList) {
//                output.append(guesthouse.getRoomNo() +"\t" + guesthouse.getOrientation() +"\t"+ guesthouse.getType_Of_Room() + "\n");
//            }
//        }

        UserAdapter adapter = new UserAdapter(this, R.layout.item_flower, userlist);
        listv.setAdapter(adapter);

    }



    /**
     * Four Parameters
     */

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
//UriTemplate = "searchfour/{District}/{DOB}/{Name}/{FName}")]
            StringBuilder sb_search = new StringBuilder();
            sb_search.append(url_SearchService);
            sb_search.append( District_s4 + "/");
            sb_search.append(Dob_S4+ "/");
            sb_search.append(Name_S4  + "/");
            sb_search.append(FHNAme_S4 );

            url = sb_search.toString();

            String content = HttpManager.getData(url);

            //Json Parsing Goes Here



            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d("######", result);
            Toast.makeText(UserListFour.this,result,Toast.LENGTH_LONG).show();

            userlist = UserJson2.parseFeed(result);
            updateDisplay();

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
                // output.setText(result);
            }


            System.out.print(result);
        }
    }

}
