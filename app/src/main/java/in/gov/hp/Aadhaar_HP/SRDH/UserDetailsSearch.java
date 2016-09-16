package in.gov.hp.Aadhaar_HP.SRDH;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class UserDetailsSearch extends Activity {


    private TextView Aadhaar_User;
    private TextView Resident_Name_user,DOB_user;
    private TextView EnrollID_User,Address_Landmark_User,Address_Locality_User,Address_Street_User;
    private TextView District_user,Gender_User,Address_Pincode_User,State_Name_User,Care_OFF_User,Address_Building_User;
    private Button back;
    ImageView  IV;
    private String Aadhaar_Photo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_search);

        Intent getRoomDetailsIntent = getIntent();
        UserPojo userDetails =  (UserPojo) getRoomDetailsIntent.getSerializableExtra("Details");


        Aadhaar_User  = (TextView)findViewById(R.id.ud_aadhaarid);
        Resident_Name_user  = (TextView)findViewById(R.id.ud_name);
        EnrollID_User = (TextView)findViewById(R.id.ud_enrollid);
        District_user = (TextView)findViewById(R.id.ud_District);
        Gender_User = (TextView)findViewById(R.id.ud_gender);
        Address_Pincode_User = (TextView)findViewById(R.id.ud_pincode);
        State_Name_User = (TextView)findViewById(R.id.ud_state);
        Care_OFF_User = (TextView)findViewById(R.id.ud_careoff);
        Address_Building_User = (TextView)findViewById(R.id.ud_addressbuilding);
        Address_Landmark_User  = (TextView)findViewById(R.id.ud_adresslandmark);
        Address_Locality_User  = (TextView)findViewById(R.id.ud_addresslocality);
        Address_Street_User  = (TextView)findViewById(R.id.ud_street);
        DOB_user = (TextView)findViewById(R.id.ud_dateofbirth);

         back = (Button)findViewById(R.id.back);
         IV = (ImageView)findViewById(R.id.ImageUser);

        Aadhaar_User.setText(userDetails.getAadhaar());
        Resident_Name_user.setText(userDetails.getResident_Name_user());
        EnrollID_User.setText(userDetails.getEnrollID_User());
        District_user.setText(userDetails.getDistrict_User());
        Gender_User.setText(userDetails.getGender_User());
        Address_Pincode_User.setText(userDetails.getAddress_Pincode_User());
        State_Name_User.setText(userDetails.getState_Name_User());
        Care_OFF_User.setText(userDetails.getCare_OFF_User());
        Address_Building_User.setText(userDetails.getAddress_Building_User());
        Address_Landmark_User.setText(userDetails.getAddress_Landmark_User());
        Address_Locality_User.setText(userDetails.getAddress_Locality_User());
        Address_Street_User.setText(userDetails.getAddress_Street_User());
        DOB_user.setText(userDetails.getDOB_user());
            Log.d("DOB", userDetails.getDOB_user());


        getPhoto GP = new getPhoto();
        GP.execute("http://10.241.9.72/awwservicelatest/AWW.svc/getphoto/"+ Aadhaar_User.getText().toString().trim());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsSearch.this.finish();
            }
        });





     /*   IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Getting the Image",Toast.LENGTH_LONG).show();
                //Get Aadhaar Number
                Aadhaar_Photo =  Aadhaar_User.getText().toString().trim();

                //Start Async Task
                getPhoto GP = new getPhoto();
                GP.execute("http://10.241.9.72/awwservicelatest/AWW.svc/getphoto/"+ Aadhaar_Photo);

            }
        });*/


    }


    public class getPhoto extends AsyncTask<String,String,String>{

        String Data_From_Server = null;
        HttpManager http_manager = null;
        String Image_Server_value = null;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Log.e("URL",params[0]);
                http_manager = new HttpManager();
                Data_From_Server = http_manager.getData(params[0]);

                return Data_From_Server;
            }catch(Exception e){
                return e.getLocalizedMessage().toString().trim();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Object json ;
            try {
                json = new JSONTokener(s).nextValue();

                if (json instanceof JSONObject){
                    JSONObject obj = new JSONObject(s);
                    Image_Server_value = obj.optString("GetPhotoResult");
                  //  Log.e("Photu", Image_Server_value);
                    if(Image_Server_value!=null&&Image_Server_value.length()!=0){
                        //InputStream stream = new ByteArrayInputStream(Base64.decode(Image_Server_value.getBytes(), Base64.DEFAULT));
                        try {
                            byte[] decodedString = Base64.decode(Image_Server_value, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            // Log.e("Photu", s);
                            IV.setImageBitmap(decodedByte);
                            Log.e("Photu without exception", Image_Server_value);
                        }catch(Exception e){
                            Log.e("Photu with exception", Image_Server_value);
                        }

                    }else{

                        IV.setImageDrawable(getDrawable(R.drawable.username));

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Image_Server_value = null;
            }


        }
    }


}
