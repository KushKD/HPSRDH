package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LogOut extends Activity {

   private EditText et;
   private  Button b;
   private String user = null;


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
                    Logout lo = new Logout();
                     lo.execute(user);
                }else {
                    Toast.makeText(getApplicationContext(), EConstants.ErrorUserEmpty, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
   public class Logout extends AsyncTask<String,String,Boolean>{

        String url = null;
        private ProgressDialog dialog;
        Boolean Server_value= false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LogOut.this);
            this.dialog.setMessage(EConstants.ProgressDialog_Message);
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String UserID = params[0];

            EncryptData EC  = new EncryptData();
            String Encrypted_UserID = EC.Encrypt_String(UserID);

            StringBuilder sb = new StringBuilder();
            sb.append(EConstants.url_Generic);
            sb.append(EConstants.url_Delemetre);
            sb.append(EConstants.methord_Logout);
            sb.append(EConstants.url_Delemetre);
            sb.append(Encrypted_UserID);
            url = sb.toString();
            JSONParser jParser = new JSONParser();
            String result  = jParser.LogOut(url);
            sb.delete(0, sb.length());
            Object json ;
            try {
                json = new JSONTokener(result).nextValue();

                if (json instanceof JSONObject){
                    JSONObject obj = new JSONObject(result);
                    Server_value = obj.optBoolean(EConstants.UserLogoutResult);
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
                Toast.makeText(getApplicationContext(), EConstants.ErrorMessageUnknow, Toast.LENGTH_LONG).show();
            }
        }
    }


}
