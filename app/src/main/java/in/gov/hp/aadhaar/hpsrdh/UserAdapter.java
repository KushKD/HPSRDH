package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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

        TextView tv1 = (TextView)view.findViewById(R.id.textView1_aadhaar);
        TextView tv2 = (TextView)view.findViewById(R.id.textView2_name);






        tv1.setText(u.getAadhaar());
        Log.d("Aadhaar is", u.getAadhaar());


        tv2.setText(u.getResident_Name_user());
        Log.d("getResident_Name_user is", u.getResident_Name_user());





        return view;
    }


}
