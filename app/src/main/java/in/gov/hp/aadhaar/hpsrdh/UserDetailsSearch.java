package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class UserDetailsSearch extends Activity {


    private TextView Aadhaar_User;
    private TextView Resident_Name_user,DOB_user;
    private TextView EnrollID_User,Address_Landmark_User,Address_Locality_User,Address_Street_User;
    private TextView District_user,Gender_User,Address_Pincode_User,State_Name_User,Care_OFF_User,Address_Building_User;
    private Button back;
    ImageView  IV;

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

        //Check The DOB Length
        if(userDetails.getDOB_user().length() > 9) {
            String DOB = userDetails.getDOB_user();
            DOB_user.setText(DOB.substring(0, 8));
            Log.d("DOB",DOB.substring(0, 8));

        }if(userDetails.getDOB_user().length() < 9){
            DOB_user.setText(userDetails.getDOB_user());
            Log.d("DOB", userDetails.getDOB_user());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsSearch.this.finish();
            }
        });


        IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Getting the Image",Toast.LENGTH_LONG).show();
            }
        });


    }


}
