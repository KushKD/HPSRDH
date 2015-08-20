package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class UserDetailsSearch extends Activity {

    private TextView EnrollID_User;
    private TextView Aadhaar_User;
    private TextView Resident_Name_user;
    private TextView DOB_User;
    private TextView Gender_User;
    private TextView Care_OFF_User;
    private TextView Address_Building_User;
    private TextView Address_Street_User;
    private TextView Address_Landmark_User;
    private TextView Address_Locality_User;
    private TextView District_User;
    private TextView State_Name_User;
    private TextView Address_Pincode_User;
    private TextView Res_Gauardian_User;
    private TextView res_addr_subdistrict_name_User;
    private TextView res_addr_po_name_User;
    private TextView VTC_User;
    private Button back;
    ImageView  IV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_search);

        Intent getRoomDetailsIntent = getIntent();
        UserPojo userDetails =  (UserPojo) getRoomDetailsIntent.getSerializableExtra("Details");
       // Toast.makeText(getApplicationContext(),"Got IT",Toast.LENGTH_LONG).show();

         EnrollID_User = (TextView)findViewById(R.id.ud_enrollid);
         Aadhaar_User  = (TextView)findViewById(R.id.ud_aadhaarid);
         Resident_Name_user  = (TextView)findViewById(R.id.ud_name);
         DOB_User  = (TextView)findViewById(R.id.ud_dateofbirth);
         Gender_User  = (TextView)findViewById(R.id.ud_gender);
         Care_OFF_User  = (TextView)findViewById(R.id.ud_careoff);
         Address_Street_User  = (TextView)findViewById(R.id.ud_street);
         Address_Landmark_User  = (TextView)findViewById(R.id.ud_adresslandmark);
         Address_Locality_User  = (TextView)findViewById(R.id.ud_addresslocality);
         District_User  = (TextView)findViewById(R.id.ud_District);
         State_Name_User  = (TextView)findViewById(R.id.ud_state);
         Address_Pincode_User  = (TextView)findViewById(R.id.ud_pincode);
         Res_Gauardian_User  = (TextView)findViewById(R.id.ud_res_gauardian_);
         res_addr_subdistrict_name_User  = (TextView)findViewById(R.id.ud_res_addr_subdistrict);
         res_addr_po_name_User  = (TextView)findViewById(R.id.ud_res_addr_po_name);
         VTC_User  = (TextView)findViewById(R.id.ud_VTC_User);
         back = (Button)findViewById(R.id.back);
         IV = (ImageView)findViewById(R.id.ImageUser);

        /**
         * Setting the Text to the TextViews
         */

        EnrollID_User.setText(userDetails.getEnrollID_User());
        Resident_Name_user.setText(userDetails.getResident_Name_user());
        Aadhaar_User.setText(userDetails.getAadhaar_User());
        DOB_User.setText(userDetails.getDOB_User());
        Gender_User.setText(userDetails.getGender_User());
        Care_OFF_User.setText(userDetails.getCare_OFF_User());
        Address_Street_User.setText(userDetails.getAddress_Street_User());
        Address_Landmark_User.setText(userDetails.getAddress_Landmark_User());
        Address_Locality_User.setText(userDetails.getAddress_Locality_User());
        District_User.setText(userDetails.getDistrict_User());
        State_Name_User.setText(userDetails.getState_Name_User());
        Address_Pincode_User.setText(userDetails.getAddress_Pincode_User());
        Res_Gauardian_User.setText(userDetails.getRes_Gauardian_User());
        res_addr_subdistrict_name_User.setText(userDetails.getRes_addr_subdistrict_name_User());
        res_addr_po_name_User.setText(userDetails.getRes_addr_po_name_User());
        VTC_User.setText(userDetails.getVTC_User());





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsSearch.this.finish();
            }
        });


        IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Getting the Image",Toast.LENGTH_LONG).show();
            }
        });


    }


}
