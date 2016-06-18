package in.gov.hp.Aadhaar_HP.SRDH;

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
        UserPojo u = userlist.get(position);
        TextView tv1 = (TextView)view.findViewById(R.id.textView1_aadhaar);
        TextView tv2 = (TextView)view.findViewById(R.id.textView2_name);
        tv1.setText(u.getAadhaar());
        tv2.setText(u.getResident_Name_user());
        return view;
    }


}
