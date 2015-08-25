package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LogOut extends Activity {

    EditText et;
    Button b;
    String user = null;
    private static final String url_loginService = "http://aadhaar.hp.gov.in/hpuidmobileservice/RestServiceImpl.svc/logout/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        et = (EditText)findViewById(R.id.et_logout);
        b = (Button)findViewById(R.id.logoutbutton);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            user = et.getText().toString().trim();

                if(user.length()!=0 && user!= null) {
                   // Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
                   // Toast.makeText(getApplicationContext(), user , Toast.LENGTH_LONG).show();
                    Logout lo = new Logout();
                     lo.execute(user);
                }else{
                    Toast.makeText(getApplicationContext(), "Username cannot be empty ..", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    class Logout extends AsyncTask<String,String,Boolean>{


        String url = null;
        private ProgressDialog dialog;
        Boolean Server_value= false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LogOut.this);
            this.dialog.setMessage("Please wait.... ");
            this.dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String UserID = params[0];

            EncryptData EC  = new EncryptData();
            String Encrypted_UserID = EC.Encrypt_String(UserID);

            StringBuilder sb = new StringBuilder();
            sb.append(url_loginService);sb.append(Encrypted_UserID);


            url = sb.toString();
           // Log.d("URL" , sb.toString());


            JSONParser jParser = new JSONParser();
            String result  = jParser.LogOut(url);
            System.out.print("::::::"+result);
            sb.delete(0, sb.length());
            Object json = null;
            try {
                json = new JSONTokener(result).nextValue();

                if (json instanceof JSONObject){

                  //  Log.d("Json ", "Object");
                    JSONObject obj = new JSONObject(result);
                    Server_value = obj.optBoolean("LogOutUserResult");
                   // Log.d("CheckUserResult===",Boolean.toString(Server_value));

                }

            } catch (JSONException e) {
                e.printStackTrace();
                Server_value = false;
            }


            return Server_value ;
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);

            this.dialog.dismiss();



            if(s) {
                Intent i_2 = new Intent(LogOut.this, SignIn.class);
                startActivity(i_2);
                LogOut.this.finish();
            }else {
                Toast.makeText(getApplicationContext(), "Something Went Wrong . Please Check Your Network Connectivity . ", Toast.LENGTH_LONG).show();

            }
        }
    }


}
