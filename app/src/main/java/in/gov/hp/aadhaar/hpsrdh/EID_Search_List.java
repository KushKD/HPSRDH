package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class EID_Search_List extends Activity {

    String EID_Service = null;
    ProgressBar pb;
    List<EID_Async> tasks;
    List<UserPojo> userlist;
    private static final String url_SearchService = "http://aadhaar.hp.gov.in/hpuidmobileservice/RestServiceImpl.svc/???/" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__rooms_);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        EID_Service = bundle.getString("EID");
    }


    /**
     * EID Service
     */

    class EID_Async extends AsyncTask<String,String,String> {

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

            // Log.d("######", result);
            //Toast.makeText(UserListFour.this,result,Toast.LENGTH_LONG).show();

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
