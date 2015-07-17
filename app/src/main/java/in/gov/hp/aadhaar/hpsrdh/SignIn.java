package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignIn extends Activity {

    Button login;
    EditText username , password;
    public static final String url_loginService = "" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.button_login);
        username = (EditText)findViewById(R.id.edit_text_username);
        password = (EditText)findViewById(R.id.editText_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_Name = username.getText().toString().trim();
                String pass_Word = password.getText().toString().trim();

                logIn login_server = new logIn();
                login_server.execute(user_Name ,pass_Word);


            }
        });



    }

  protected class logIn extends AsyncTask<String,String,String>{

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
      }

      @Override
      protected String doInBackground(String... params) {

          String username_service =  params[0];
          String password_service = params[1];

          /*
            Encryption goes here
           */
          EncryptData crypt = new EncryptData();
          String crypt_username = crypt.Encrypt_String(username_service);
          String crypt_password = crypt.Encrypt_String(password_service);

          StringBuilder sb = new StringBuilder();
          sb.append(url_loginService);
          sb.append("/");
          sb.append(crypt_username);
          sb.append("/");
          sb.append(crypt_password);

         Log.d("Service is" , sb.toString());

            return  null;
      }

      @Override
      protected void onPostExecute(String s) {
          super.onPostExecute(s);

           Intent i_2 = new Intent(SignIn.this , ViewPagerStyle1Activity.class);
           startActivity(i_2);
           SignIn.this.finish();
      }
  }

}
