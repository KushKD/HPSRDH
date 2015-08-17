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

import org.json.JSONObject;


public class SignIn extends Activity {

    Button login;
    EditText username , password;
    private static final String url_loginService =""; // not working
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



  protected class logIn extends AsyncTask<String,JSONObject,Boolean>{

      private ProgressDialog dialog;
      Boolean Server_value = false;
      @Override
      protected void onPreExecute() {
          super.onPreExecute();

           dialog = new ProgressDialog(SignIn.this);
          this.dialog.setMessage("Please wait");
          this.dialog.show();

      }

      @Override
      protected Boolean doInBackground(String... params) {

          RestAPI HPSRDH_api = new RestAPI();
          String username_service =  params[0];
          String password_service = params[1];
          String imei_service = params[2];
          String url = null;
          boolean userAuth = false;

          /*
            Encryption goes here
           */
        /*  EncryptData crypt = new EncryptData();
          String crypt_username = crypt.Encrypt_String(username_service);
          String crypt_password = crypt.Encrypt_String(password_service);*/

         /* StringBuilder sb = new StringBuilder();
          sb.append(url_loginService);
          sb.append("/api/UserLogin?username=");sb.append(crypt_username);
          sb.append("&password=");sb.append(crypt_password);
          sb.append("&imei=");sb.append(imei_service);

         url = sb.toString();
         Log.d("Service is" , url);

          JSONParser jParser = new JSONParser();
         String result  = jParser.getDataRest(url);
          // Now call the Service

         // System.out.print("Here is the ... "+result);myStringBuilder.delete(0, myStringBuilder.length());
          sb.delete(0, sb.length());*/

          // Call the User Authentication Method in API
          try {
              JSONObject jsonObj = HPSRDH_api.UserAuthentication(username_service, password_service);
              //Parse the JSON Object to boolean
              JSONParser_New parser = new JSONParser_New();
              userAuth = parser.parseUserAuth(jsonObj);
              Log.d("Boolean Value is:" , Boolean.toString(userAuth));
             // userName=params[0];
          } catch (Exception e) {
              Log.d("AsyncLogin", e.getMessage());
          }

          return userAuth;
      }

      @Override
      protected void onPostExecute(Boolean result) {
          super.onPostExecute(result);

          this.dialog.dismiss();

          //Check user validity
          if (result) {
              Intent i_2 = new Intent(SignIn.this, ViewPagerStyle1Activity.class);
              //i.putExtra("username",userName);
              startActivity(i_2);
              SignIn.this.finish();
          }
          else
          {
              Toast.makeText(getApplicationContext(), "Not valid username/password ",Toast.LENGTH_SHORT).show();
              Intent i_3 = new Intent(SignIn.this, LogOut.class);
              startActivity(i_3);
              SignIn.this.finish();
          }

        /* // System.out.print("Here is the ... two..."+s);
          String value_server  = s;
         // System.out.print("Here is the ... three ..."+value_server);
          if(value_server.trim().equalsIgnoreCase("true")) {
              Intent i_2 = new Intent(SignIn.this, ViewPagerStyle1Activity.class);
              startActivity(i_2);
              SignIn.this.finish();
          }else {
             // Toast.makeText(getApplicationContext(),"Sorry, You are not a valid User. Please Try again",Toast.LENGTH_LONG).show();
              //System.out.print("Here is the ... four ..." + value_server);
              Intent i_3 = new Intent(SignIn.this, LogOut.class);
              startActivity(i_3);
              SignIn.this.finish();

          }*/
      }
  }

}
