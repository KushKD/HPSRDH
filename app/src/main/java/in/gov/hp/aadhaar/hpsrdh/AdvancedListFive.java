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

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    URL url_;
    HttpURLConnection conn_;
    StringBuilder sb = new StringBuilder();

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


            try {
                url_ =new URL(EConstants.url_Generic_Search);
                conn_ = (HttpURLConnection)url_.openConnection();
                conn_.setDoOutput(true);
                conn_.setRequestMethod("POST");
                conn_.setUseCaches(false);
                conn_.setConnectTimeout(20000);
                conn_.setReadTimeout(20000);
                conn_.setRequestProperty("Content-Type", "application/json");
                conn_.connect();

                JSONStringer userJson = new JSONStringer()
                        .object().key("HPSRDH_Search")
                        .object()
                        .key("District").value(params[0])
                        .key("Name").value(params[1])
                        .key("F_H_Name").value(params[2])
                        .key("DOB").value(params[3])
                        .key("Pincode").value(params[4])
                        .endObject()
                        .endObject();

                System.out.println("#############"+userJson.toString());
                OutputStreamWriter out = new   OutputStreamWriter(conn_.getOutputStream());
                out.write(userJson.toString());
                out.close();

                int HttpResult =conn_.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    System.out.println(HttpResult+ "@@@@@@");
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                        System.out.println(sb.toString()+ "@@@@@@");
                    }
                    br.close();


                }else{
                    System.out.println(conn_.getResponseMessage());
                }

             } catch (MalformedURLException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     } catch (JSONException e) {
         e.printStackTrace();
     }finally{
         if(conn_!=null)
             conn_.disconnect();
     }
     return sb.toString();



        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            userlist = UserJson_FiveParameters.parseFeed(result);
            if(userlist.isEmpty()){
                Toast.makeText(getApplicationContext(),EConstants.ListEmpty,Toast.LENGTH_LONG).show();
            }if(userlist.size()>=100) {
                updateDisplay();
                Toast.makeText(getApplicationContext(),"Only the top 100 results are being displayed. Please be more specific. ",Toast.LENGTH_LONG).show();
            }else
             {
                updateDisplay();
            }
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
        }
    }
}

