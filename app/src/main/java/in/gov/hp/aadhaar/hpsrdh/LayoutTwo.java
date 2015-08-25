
package in.gov.hp.aadhaar.hpsrdh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LayoutTwo extends Fragment {

	EditText UID_ET , Phone_number_ET ;
	Button BSearch;


	public static Fragment newInstance(Context context) {
		LayoutTwo f = new LayoutTwo();


		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_two, null);

		UID_ET = (EditText)root.findViewById(R.id.edit_text_search_by_uid);
		Phone_number_ET = (EditText)root.findViewById(R.id.edit_text_search_by_phone_number);
		BSearch = (Button)root.findViewById(R.id.searchButton);

		BSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try{
					Search_Data();
				}catch (Exception e){
					Toast.makeText(getActivity(),e.getMessage().toString().trim(),Toast.LENGTH_LONG).show();
				}
			}
		});


		return root;
	}

	private void Search_Data() {

		String UID_Service, EID_Service;
		//EncryptData ED = new EncryptData();
		UID_Service = UID_ET.getText().toString().trim();
		EID_Service = Phone_number_ET.getText().toString().trim();

		if (UID_Service.length() != 0 && UID_Service != null && EID_Service.length() != 0 && EID_Service != null) {

				if (UID_Service.length() != 0 && UID_Service != null && UID_Service.length() == 12) {
				//Encrypt UID Service
				//String Crypt_UID = ED.Encrypt_String(UID_Service)

					//Start Search By UID
					Intent i = new Intent(getActivity() , UID_Search_List.class);
					i.putExtra("UID", UID_Service);
					startActivity(i);


			}else if (EID_Service.length() != 0 && EID_Service != null && EID_Service.length() == 14){

					//Start Search By EID
					Intent i = new Intent(getActivity() , EID_Search_List.class);
					i.putExtra("EID", EID_Service);
					startActivity(i);

				}
				else {

				Toast.makeText(getActivity(), "Please enter a valid Aadhaar ID or Enroll ID", Toast.LENGTH_LONG).show();

			}

		} else {
			Toast.makeText(getActivity(), "Please enter your Aadhaar ID or Enroll ID", Toast.LENGTH_LONG).show();
		}
	}

}
