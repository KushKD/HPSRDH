package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HPZ231 on 24-07-2015.
 */
public class UserAdapter extends ArrayAdapter<UserPojo> {

    private Context context;
    private List<UserPojo> userlist;

    public UserAdapter(Context context, int resource, List<UserPojo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.userlist = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_flower, parent, false);

        //Display flower name in the TextView widget
        UserPojo u = userlist.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1_room_no);
        TextView tv2 = (TextView)view.findViewById(R.id.textView2_floor_entrance);
        TextView tv3 = (TextView)view.findViewById(R.id.textView3_name);
        TextView tv4 = (TextView)view.findViewById(R.id.textView4_DOB);
        TextView tv5 = (TextView)view.findViewById(R.id.textView5_gender);
        TextView tv6 = (TextView)view.findViewById(R.id.textView6_careof);
        TextView tv7 = (TextView)view.findViewById(R.id.textView7_address_building);
        TextView tv8 = (TextView)view.findViewById(R.id.textView8_address_street);
        TextView tv9 = (TextView)view.findViewById(R.id.textView9_address_landmark);
        TextView tv10 = (TextView)view.findViewById(R.id.textView10_address_locality);

        TextView tv11 = (TextView) view.findViewById(R.id.textView11_address_vtc);
        TextView tv12 = (TextView)view.findViewById(R.id.textView12_address_district);
        TextView tv13 = (TextView)view.findViewById(R.id.textView13_address_state_name);
        TextView tv14 = (TextView)view.findViewById(R.id.textView14_address_pin);
        TextView tv15 = (TextView)view.findViewById(R.id.textView15_address_gaurdian);
        TextView tv16 = (TextView)view.findViewById(R.id.textView16_address_subdistrict);
        TextView tv17 = (TextView)view.findViewById(R.id.textView17_address_po);



        tv.setText(u.getEnrollID_User());
        tv2.setText(u.getAadhaar_User());
        tv3.setText(u.getResident_Name_user());
        tv4.setText(u.getDOB_User());
        tv5.setText(u.getGender_User());
        tv6.setText(u.getCare_OFF_User());
        tv7.setText(u.getAddress_Building_User());
        tv8.setText(u.getAddress_Street_User());
        tv9.setText(u.getAddress_Landmark_User());
        tv10.setText(u.getAddress_Locality_User());

        tv11.setText(u.getVTC_User());
        tv12.setText(u.getDistrict_User());
        tv13.setText(u.getState_Name_User());
        tv14.setText(u.getAddress_Pincode_User());
        tv15.setText(u.getRes_Gauardian_User());
        tv16.setText(u.getRes_addr_subdistrict_name_User());
        tv17.setText(u.getRes_addr_po_name_User());



        return view;
    }


}
