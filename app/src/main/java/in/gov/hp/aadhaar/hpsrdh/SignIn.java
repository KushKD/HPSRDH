package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class SignIn extends Activity {

   private Button login;
   private EditText username , password;
   private String IMIE_Number ;
    public String UserName_SH ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        IMIE_Number = getIMEI(SignIn.this);
        login = (Button)findViewById(R.id.button_login);
        username = (EditText)findViewById(R.id.edit_text_username);
        password = (EditText)findViewById(R.id.editText_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_Name = username.getText().toString().trim();
                String pass_Word = password.getText().toString().trim();

                if ( IMIE_Number.length() != 0) {
                    if (user_Name != null && user_Name.length() != 0) {
                        if (pass_Word != null && pass_Word.length() != 0){
                            logIn login_server = new logIn();
                            login_server.execute(user_Name, pass_Word,IMIE_Number);
                        }else{
                            Toast.makeText(getApplicationContext(),EConstants.ErrorPasswordEmpty, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), EConstants.ErrorUserEmpty, Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), EConstants.ErrorMessageUnknow, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getIMEI(Context context){
  //Check Weather the IMIE Number is null or not
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();

        if(imei==null){
            return Integer.toString(0);
        }
        return imei;

    }

  protected class logIn extends AsyncTask<String,String,Boolean>{

      private ProgressDialog dialog;
      Boolean Server_value = false;

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
          dialog = new ProgressDialog(SignIn.this);
          this.dialog.setMessage(EConstants.ProgressDialog_Message);
          this.dialog.show();
          this.dialog.setCancelable(false);

      }

      @Override
      protected Boolean doInBackground(String... params) {
          String username_service =  params[0];
          String password_service = params[1];
          String imei_service = params[2];
          String url;
          String userAuth;

           //Save Username in shared Prefrences here
          SharedPreferences sharedpreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedpreferences.edit();
          editor.putString("USERNAME", username_service);
          editor.commit();

          sharedpreferences = getSharedPreferences("UserName",Context.MODE_PRIVATE);

          if (sharedpreferences.contains("USERNAME")) {
              UserName_SH =  sharedpreferences.getString("USERNAME", "");
          }


          EncryptData crypt = new EncryptData();
          String crypt_username = crypt.Encrypt_String(username_service);
          String crypt_password = crypt.Encrypt_String(password_service);

          StringBuilder sb = new StringBuilder();
          sb.append(EConstants.url_Generic);sb.append(EConstants.url_Delemetre);
          sb.append(EConstants.methord_Login);sb.append(EConstants.url_Delemetre);
          sb.append(crypt_username);sb.append(EConstants.url_Delemetre);
          sb.append(crypt_password);sb.append(EConstants.url_Delemetre);
          sb.append(EConstants.IMEINumber);

          url = sb.toString();
          JSONParser jParser = new JSONParser();
          userAuth = jParser.checkLogin(url);
          sb.delete(0, sb.length());
          Object json;
          try {
              json = new JSONTokener(userAuth).nextValue();

              if (json instanceof JSONObject){
                  JSONObject obj = new JSONObject(userAuth);
                  Server_value = obj.optBoolean(EConstants.UserLoginResult);
              }
          } catch (JSONException e) {
              e.printStackTrace();
              Server_value = false;
          }
          return Server_value ;
      }

      @Override
      protected void onPostExecute(Boolean server_value) {
          super.onPostExecute(server_value);
          this.dialog.dismiss();
          if (server_value) {
             // Toast.makeText(getApplicationContext(),UserName_SH,Toast.LENGTH_SHORT).show();
              Intent i_2 = new Intent(SignIn.this, ViewPagerStyle1Activity.class);
              startActivity(i_2);
              SignIn.this.finish();
          }
          else
          {
             // Toast.makeText(getApplicationContext(),UserName_SH,Toast.LENGTH_SHORT).show();
              Toast.makeText(getApplicationContext(),EConstants.ErrorMessageLoginScreen,Toast.LENGTH_SHORT).show();
              Intent i_3 = new Intent(SignIn.this, LogOut.class);
              startActivity(i_3);
              SignIn.this.finish();
          }


      }
  }

}
