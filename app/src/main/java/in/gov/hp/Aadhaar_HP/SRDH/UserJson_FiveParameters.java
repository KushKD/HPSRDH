package in.gov.hp.Aadhaar_HP.SRDH;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KD on 7/28/2015.
 */
public class UserJson_FiveParameters {

    public static List<UserPojo> parseFeed(String content) {

        try {

            String g_Table = null;
            Object json = new JSONTokener(content).nextValue();
            if (json instanceof JSONObject){
              //  Log.d("Json ", "Object");
                JSONObject obj = new JSONObject(content);
                g_Table = obj.optString("SearchResult");
                Log.d("Table===",g_Table);
            }
            //you have an object
            else if (json instanceof JSONArray){
            }

            JSONArray ar = new JSONArray(g_Table);
            List<UserPojo>userList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                UserPojo user = new UserPojo();
                user.setAadhaar(obj.getString("Aadhaar"));
                user.setResident_Name_user(obj.getString("Resident_Name"));
                user.setEnrollID_User(obj.getString("Enroll_ID"));
                user.setDistrict_User(obj.getString("Addr_District"));
                user.setGender_User(obj.getString("Gender"));
                user.setAddress_Pincode_User(obj.getString("addr_pincode"));
                user.setState_Name_User(obj.getString("addr_state_name"));
                user.setAddress_Building_User(obj.getString("Address_Building"));
                user.setCare_OFF_User(obj.getString("Care_of"));
                user.setAddress_Landmark_User(obj.getString("Addr_Landmark"));
                user.setAddress_Locality_User(obj.getString("Addr_Locality"));
                user.setAddress_Street_User(obj.getString("Addr_Street"));
                user.setDOB_user(obj.getString("DOB"));
             userList.add(user);
            }
            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
