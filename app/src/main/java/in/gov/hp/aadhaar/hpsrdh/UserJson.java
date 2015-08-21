package in.gov.hp.aadhaar.hpsrdh;


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
public class UserJson {

    public static List<UserPojo> parseFeed(String content) {

        try {

            /**
             * Check weather the string is json object or array
             */
            String g_Table = null;
            Object json = new JSONTokener(content).nextValue();
            if (json instanceof JSONObject){

                Log.d("Json ", "Object");
                // JSONArray arr = new JSONArray(content);
                // JSONObject jObj = arr.getJSONObject(0);
                // JSONArray ar = jObj.getJSONArray("Table");
                // Log.d("Table===",ar.toString());

                JSONObject obj = new JSONObject(content);

                g_Table = obj.optString("GetAllUsers5Result");
                Log.d("Table===",g_Table);

            }
            //you have an object
            else if (json instanceof JSONArray){

                Log.d("Json ", "Array");
            }


            JSONArray ar = new JSONArray(g_Table);
            List<UserPojo>userList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                UserPojo user = new UserPojo();


                user.setAadhaar(obj.getString("Aadhaar"));
                Log.d("Aadhaar is @@@", obj.getString("Aadhaar"));

                user.setResident_Name_user(obj.getString("Resident_Name"));
                Log.d("Resident_Name is @@@", obj.getString("Resident_Name"));

                user.setEnrollID_User(obj.getString("Enroll_ID"));
                Log.d("Enroll_ID is @@@", obj.getString("Enroll_ID"));

                user.setDistrict_User(obj.getString("Addr_District"));
                Log.d("Addr_District is @@@", obj.getString("Addr_District"));

                user.setGender_User(obj.getString("Gender"));
                Log.d("Gender is @@@", obj.getString("Gender"));

                user.setAddress_Pincode_User(obj.getString("addr_pincode"));
                Log.d("addr_pincode is @@@", obj.getString("addr_pincode"));

                user.setState_Name_User(obj.getString("addr_state_name"));
                Log.d("addr_state_name is @@@", obj.getString("addr_state_name"));

                user.setAddress_Building_User(obj.getString("Address_Building"));
                Log.d("Address_Building is @@@", obj.getString("Address_Building"));

                user.setCare_OFF_User(obj.getString("Care_of"));
                Log.d("Care_of is @@@", obj.getString("Care_of"));

                user.setAddress_Landmark_User(obj.getString("Addr_Landmark"));
                Log.d("Addr_Landmark is @@@", obj.getString("Addr_Landmark"));

                user.setAddress_Locality_User(obj.getString("Addr_Locality"));
                Log.d("Addr_Locality is @@@", obj.getString("Addr_Locality"));

                user.setAddress_Street_User(obj.getString("Addr_Street"));
                Log.d("Addr_Street is @@@", obj.getString("Addr_Street"));

                user.setDOB_user(obj.getString("DOB"));
                Log.d("Addr_Street is @@@", obj.getString("DOB"));

               /*

                user.setVTC_User(obj.getString("Addr_VTC"));
                Log.d("Addr_VTC is @@@", obj.getString("Addr_VTC"));

                user.setRes_addr_po_name_User(obj.getString("res_addr_po_name"));
                Log.d("res_addr_po_name is @@@", obj.getString("res_addr_po_name"));

                user.setRes_addr_subdistrict_name_User(obj.getString("res_addr_subdistrict_name"));
                Log.d("res_addr_subdistrict_name is @@@", obj.getString("res_addr_subdistrict_name"));

                user.setRes_Gauardian_User(obj.getString("res_gauardian_name"));
                Log.d("res_addr_po_name is @@@", obj.getString("res_addr_po_name"));*/





             userList.add(user);
            }

            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
