
package in.gov.hp.aadhaar.hpsrdh;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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

public class AdvancedSearch extends Fragment {

	ArrayAdapter<String> districts = null;
	private EditText  name , fHname , pincode;
	private TextView date_of_birth;
	private EditText district;
	private Button SearchService;
	private int mSelectedYear;
	private int mSelectedMonth;
	private int mSelectedDay;

	private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mSelectedDay = dayOfMonth;
			mSelectedMonth = monthOfYear;
			mSelectedYear = year;
			updateDateUI();
		}
	};



	public static Fragment newInstance(Context context) {
		AdvancedSearch f = new AdvancedSearch();
		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_one, null);

		district = (EditText)root.findViewById(R.id.edit_text_district);
        SearchService = (Button)root.findViewById(R.id.button_search);


		date_of_birth = (TextView)root.findViewById(R.id.edit_text_date_of_birth);
		name = (EditText)root.findViewById(R.id.edit_text_name);
		fHname = (EditText)root.findViewById(R.id.edit_text_father_husband_name);
		pincode = (EditText)root.findViewById(R.id.edit_text_pincode);

		//districts = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, getResources().getStringArray(R.array.district));
		//district.setAdapter(districts);


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
				Get_Data();
				}catch (Exception e){
					Toast.makeText(getActivity(),EConstants.ErrorMessageUnknow ,Toast.LENGTH_LONG).show();
				}
			}
		});
		return root;
	}

	private void Get_Data() {
		String District_Service , DOB_Service , Name_Service , FHName_Service , PinCode_Service;
		District_Service = district.getText().toString().trim();
		DOB_Service = date_of_birth.getText().toString().trim();
		Name_Service = name.getText().toString().trim();
		FHName_Service = fHname.getText().toString().trim();
		PinCode_Service = pincode.getText().toString().trim();


		if( District_Service.length()!=0 || DOB_Service.length()!=0 || Name_Service.length()!=0|| FHName_Service.length() !=0 || PinCode_Service.length()!=0){



					//	if(PinCode_Service.length()!= 0 && PinCode_Service!= null){
							Intent i = new Intent(getActivity() , AdvancedListFive.class);
							i.putExtra(EConstants.D, District_Service);
							i.putExtra(EConstants.N, Name_Service);
							i.putExtra(EConstants.FH, FHName_Service);
							i.putExtra(EConstants.DOB, DOB_Service);
							i.putExtra(EConstants.P,PinCode_Service);
							startActivity(i);

		}else{
			Toast.makeText(getActivity(), " Our Central System is as big as the universe. Please provide some parameter.  " ,Toast.LENGTH_LONG).show();
		}
	}

	private DatePickerDialog showDatePickerDialog(int initialYear, int initialMonth, int initialDay, DatePickerDialog.OnDateSetListener listener) {
		DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, initialYear, initialMonth, initialDay);
		dialog.setTitle(EConstants.DateOFBirthDialog);
		dialog.show();
		return dialog;
	}

	private void updateDateUI() {
		String month = ((mSelectedMonth+1) > 9) ? ""+(mSelectedMonth+1): "0"+(mSelectedMonth+1) ;
		String day = ((mSelectedDay) < 10) ? "0"+mSelectedDay: ""+mSelectedDay ;
		date_of_birth.setText(day + "-" + month + "-" + mSelectedYear);
	}

}
