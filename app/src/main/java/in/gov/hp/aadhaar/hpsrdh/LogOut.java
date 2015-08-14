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

public class LogOut extends Activity {

    EditText et;
    Button b;
    String user = null;
    private static final String url_loginService = "";  //not working

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        et = (EditText)findViewById(R.id.et_logout);
        b = (Button)findViewById(R.id.logoutbutton);

        System.out.print("We are Here ....");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Button is Working" , Toast.LENGTH_LONG).show();
            user = et.getText().toString().trim();

                if(user.length()!=0 && user!= null) {
                    Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), user , Toast.LENGTH_LONG).show();
                    Logout lo = new Logout();
                     lo.execute(user);
                }else{
                    Toast.makeText(getApplicationContext(), "User Name cannot be empty ..", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    class Logout extends AsyncTask<String,String,String>{


        String url = null;
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LogOut.this);
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String UserID = params[0];

            EncryptData EC  = new EncryptData();
            String Encrypted_UserID = EC.Encrypt_String(UserID);

            StringBuilder sb = new StringBuilder();
            sb.append(url_loginService);
            sb.append("/api/UserLogin?username=");sb.append(Encrypted_UserID);


            url = sb.toString();
            Log.d("URL" , sb.toString());


            JSONParser jParser = new JSONParser();
            String result  = jParser.LogOut(url);

            sb.delete(0, sb.length());

            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            this.dialog.dismiss();

            // System.out.print("Here is the ... two..."+s);
            String value_server  = s;
            // System.out.print("Here is the ... three ..."+value_server);
            if(value_server.trim().equalsIgnoreCase("true")) {
                Intent i_2 = new Intent(LogOut.this, SignIn.class);
                startActivity(i_2);
                LogOut.this.finish();
            }else {
                Toast.makeText(getApplicationContext(), "Something Went Wrong . Please Check Your Network Connectivity . ", Toast.LENGTH_LONG).show();
                //System.out.print("Here is the ... four ..." + value_server);
            }
        }
    }


}
