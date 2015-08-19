
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
public class UserJson2 {

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

                g_Table = obj.optString("GetAllUsers4Result");
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


                user.setAadhaar_User(obj.getString("Aadhaar"));
                user.setDistrict_User(obj.getString("Addr_District"));
                user.setAddress_Landmark_User(obj.getString("Addr_Landmark"));
                user.setAddress_Locality_User(obj.getString("Addr_Locality"));
                user.setAddress_Street_User(obj.getString("Addr_Street"));
                user.setVTC_User(obj.getString("Addr_VTC"));
                user.setAddress_Building_User(obj.getString("Address_Building"));
                user.setCare_OFF_User(obj.getString("Care_of"));
                user.setAadhaar_User(obj.getString("DOB"));
                user.setEnrollID_User(obj.getString("Enroll_ID"));
                user.setGender_User(obj.getString("Gender"));
                user.setResident_Name_user(obj.getString("Resident_Name"));
                user.setAddress_Pincode_User(obj.getString("addr_pincode"));
                user.setState_Name_User(obj.getString("addr_state_name"));
                user.setRes_addr_po_name_User(obj.getString("res_addr_po_name"));
                user.setRes_addr_subdistrict_name_User(obj.getString("res_addr_subdistrict_name"));
                user.setRes_Gauardian_User(obj.getString("res_gauardian_name"));





                userList.add(user);
            }

            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
