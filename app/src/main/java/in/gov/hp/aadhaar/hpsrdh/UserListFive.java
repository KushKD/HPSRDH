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

/**
 * Created by KD on 7/29/2015.
 */
public class UserListFive extends Activity {

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
    private static final String url_SearchService = ""; // not working

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
        Pincode_service = bundle.getString("Pincode");

        Toast.makeText(getApplicationContext(), District_Service + Name_Service + FHName_Service + DOB_Service + Pincode_service, Toast.LENGTH_LONG).show();

        listv = (ListView) findViewById(R.id.list);
        context = this;

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        if (isOnline()) {
            FiveParameters_Async asy_five = new FiveParameters_Async();
            asy_five.execute(District_Service,Name_Service,FHName_Service,Pincode_service);
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
            sb_search.append(url_SearchService);
            sb_search.append("District="+ District_s5 + "&");
            sb_search.append("Name="+ Name_S5 + "&");
            sb_search.append("FName="+ FHNAme_S5 + "&");
            sb_search.append("DOB="+ Dob_S5 + "&" );
            sb_search.append("PINCode="+ Pincode_S5);



            url = sb_search.toString();

            System.out.print("=====" + url);
            String content = HttpManager.getData(url);

            //Json Parsing Goes Here



            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d("######", result);
            Toast.makeText(UserListFive.this,result,Toast.LENGTH_LONG).show();

            userlist = UserJson.parseFeed(result);
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

