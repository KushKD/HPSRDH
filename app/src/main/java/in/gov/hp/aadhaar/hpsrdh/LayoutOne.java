
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

			}
		});


		return root;
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
