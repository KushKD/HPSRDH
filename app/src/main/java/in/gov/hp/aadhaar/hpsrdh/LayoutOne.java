
package in.gov.hp.aadhaar.hpsrdh;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LayoutOne extends Fragment {

	ArrayAdapter<String> districts = null;
	EditText  name , fHname , pincode;
	TextView date_of_birth;
	Spinner district;
	Button SearchService;

	// variables to store the selected date and time
	private int mSelectedYear;
	private int mSelectedMonth;
	private int mSelectedDay;

	// CallBacks for date and time pickers
	private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// update the current variables ( year, month and day)
			mSelectedDay = dayOfMonth;
			mSelectedMonth = monthOfYear;
			mSelectedYear = year;

			// update txtDate with the selected date
			updateDateUI();
		}
	};



	public static Fragment newInstance(Context context) {
		LayoutOne f = new LayoutOne();	
		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_one, null);

		district = (Spinner)root.findViewById(R.id.edit_text_district);
        SearchService = (Button)root.findViewById(R.id.button_search);


		date_of_birth = (TextView)root.findViewById(R.id.edit_text_date_of_birth);
		name = (EditText)root.findViewById(R.id.edit_text_name);
		fHname = (EditText)root.findViewById(R.id.edit_text_father_husband_name);
		pincode = (EditText)root.findViewById(R.id.edit_text_pincode);

		districts = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, getResources().getStringArray(R.array.district));
		district.setAdapter(districts);


		date_of_birth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDatePickerDialog(mSelectedYear, mSelectedMonth, mSelectedDay, mOnDateSetListener);
			}
		});

		SearchService.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try{
					//Check Internet Connectivity Weather Connecting or not.
				Get_Data();
				}catch (Exception e){
					Toast.makeText(getActivity(),e.getMessage().toString().trim() ,Toast.LENGTH_LONG).show();
				}

			}
		});


		return root;
	}

	private void Get_Data() {

		String District_Service , DOB_Service , Name_Service , FHName_Service , PinCode_Service;

		District_Service = district.getSelectedItem().toString().trim();
		DOB_Service = date_of_birth.getText().toString().trim();
		Name_Service = name.getText().toString().trim();
		FHName_Service = fHname.getText().toString().trim();
		PinCode_Service = pincode.getText().toString().trim();

		EncryptData ED = new EncryptData();

		if(District_Service.length()!=0 && District_Service!=null){
			//Encrypt District
			String District_Crypt_service = ED.Encrypt_String(District_Service);

			if (DOB_Service.length()!=0 && DOB_Service!= null){
				//Encrypt Dob
				String DOB_Crypt_Service = ED.Encrypt_String(DOB_Service);

				if(Name_Service.length()!= 0 && Name_Service!= null){
					//Encrypt Name
					String Name_Crypt_Service = ED.Encrypt_String(Name_Service);

					if(FHName_Service.length()!= 0 && FHName_Service!= null){
						//Encrypt Fathers name or Husband name
						String FH_Crypt_Service = ED.Encrypt_String(FHName_Service);

						if(PinCode_Service.length()!= 0 && PinCode_Service!= null){
							//Encrypt Pin Code
							String PinCode_Crypt_Service = ED.Encrypt_String(PinCode_Service);

							//Start Async Task For Four Parameters
							Toast.makeText(getActivity(),District_Crypt_service +"==="+ DOB_Crypt_Service +"==="+Name_Crypt_Service +"==="+ FH_Crypt_Service +"==="+ PinCode_Crypt_Service,Toast.LENGTH_LONG).show();
							Toast.makeText(getActivity(),"Async Task For Five Parameters Started",Toast.LENGTH_LONG).show();
						}else{

							Toast.makeText(getActivity(),"Async Task For Four Parameters Started",Toast.LENGTH_LONG).show();
							Toast.makeText(getActivity(),District_Crypt_service +"==="+ DOB_Crypt_Service +"==="+Name_Crypt_Service +"==="+ FH_Crypt_Service ,Toast.LENGTH_LONG).show();

							//Start Async Task for Five Parameters
						}
					}else{
						Toast.makeText(getActivity(), "Please enter Your Father's / Husband's Name",Toast.LENGTH_LONG).show();
					}

				}else{
					Toast.makeText(getActivity(), "Name cannot be empty",Toast.LENGTH_LONG).show();
				}
			}else{
				Toast.makeText(getActivity(), "Invalid Date OF Birth",Toast.LENGTH_LONG).show();
			}

		}else{
			Toast.makeText(getActivity(), "Please Select District",Toast.LENGTH_LONG).show();
		}

		/**
		 * Encrypt Data Here
		 */

	}

	// initialize the DatePickerDialog
	private DatePickerDialog showDatePickerDialog(int initialYear, int initialMonth, int initialDay, DatePickerDialog.OnDateSetListener listener) {
		DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, initialYear, initialMonth, initialDay);
		dialog.setTitle("Please Select Your Date Of Birth");
		dialog.show();
		return dialog;
	}

	private void updateDateUI() {
		String month = ((mSelectedMonth+1) > 9) ? ""+(mSelectedMonth+1): "0"+(mSelectedMonth+1) ;
		String day = ((mSelectedDay) < 10) ? "0"+mSelectedDay: ""+mSelectedDay ;
		date_of_birth.setText(day + "/" + month + "/" + mSelectedYear);
	}
}
