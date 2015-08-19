package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class SignIn extends Activity {

    Button login;
    EditText username , password;
    private static final String url_loginService ="http://10.241.9.72/aadhaarweb/RestServiceImpl.svc/login/";
    public String IMIE_Number ;


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

                //Check Weather Internet is there or not
                if ( IMIE_Number.length() != 0) {
                    if (user_Name != null && user_Name.length() != 0) {
                        if (pass_Word != null && pass_Word.length() != 0){
                            logIn login_server = new logIn();
                            login_server.execute(user_Name, pass_Word,IMIE_Number);
                        }else{
                            Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter your Username", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Something Went Wrong..", Toast.LENGTH_LONG).show();
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
          this.dialog.setMessage("Please wait .......");
          this.dialog.show();

      }

      @Override
      protected Boolean doInBackground(String... params) {


          String username_service =  params[0];
          String password_service = params[1];
          String imei_service = params[2];
          String url = null;
          String userAuth = "";

          EncryptData crypt = new EncryptData();
          String crypt_username = crypt.Encrypt_String(username_service);
          String crypt_password = crypt.Encrypt_String(password_service);

          StringBuilder sb = new StringBuilder();
          sb.append(url_loginService);
          sb.append(crypt_username);sb.append("/");
          sb.append(crypt_password);sb.append("/");
          sb.append("0");

         url = sb.toString();
          System.out.println(url);
          JSONParser jParser = new JSONParser();
          userAuth = jParser.checkLogin(url);
          sb.delete(0, sb.length());
        //  System.out.print("userAuth is" + userAuth);


          Object json = null;
          try {
              json = new JSONTokener(userAuth).nextValue();

              if (json instanceof JSONObject){

                 // Log.d("Json ", "Object");
                  JSONObject obj = new JSONObject(userAuth);
                  Server_value = obj.optBoolean("CheckUserResult");
                 // Log.d("CheckUserResult===",Boolean.toString(Server_value));

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
              Intent i_2 = new Intent(SignIn.this, ViewPagerStyle1Activity.class);
              startActivity(i_2);
              SignIn.this.finish();
          }
          else
          {
              Toast.makeText(getApplicationContext(), "Either the username/password is not valid or you are already logged in ",Toast.LENGTH_SHORT).show();
              Intent i_3 = new Intent(SignIn.this, LogOut.class);
              startActivity(i_3);
              SignIn.this.finish();
          }


      }
  }

}
