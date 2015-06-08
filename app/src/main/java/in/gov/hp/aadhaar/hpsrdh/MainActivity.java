package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import in.gov.hp.aadhaar.presentation.testtwo;


public class MainActivity extends Activity {

    ArrayAdapter<String> districts = null;
    ArrayAdapter<String> blocks = null;
    Spinner district_spinner , block_spinner ;
    LinearLayout layout_block;
    Button date_of_birth;
    TextView DOB;
    private int mRequestCode = 2;

    // variables to store the selected date and time
    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mSelectedHour;
    private int mSelectedMinutes;

    // CallBacks for date and time pickers
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // extras = getIntent().getExtras();


        initialize_components();
        districts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.district));
        district_spinner.setAdapter(districts);

        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);


                int id_district = district_spinner.getSelectedItemPosition();
                // Toast.makeText(getApplicationContext(),Integer.toString(id_district),Toast.LENGTH_LONG).show();

                // SetData_Spinner SD = new SetData_Spinner();
                // SD.execute(Integer.toString(id_district));
            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

        date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   //Intent i = new Intent(MainActivity.this , DateTimePicker.class);
                   // startActivityForResult(i,2);

                showDatePickerDialog(mSelectedYear, mSelectedMonth, mSelectedDay, mOnDateSetListener);
            }
        });


    }
    // initialize the DatePickerDialog
    private DatePickerDialog showDatePickerDialog(int initialYear, int initialMonth, int initialDay, OnDateSetListener listener) {
        DatePickerDialog dialog = new DatePickerDialog(this, listener, initialYear, initialMonth, initialDay);
        dialog.setTitle("Please Select Your Date Of Birth");
        dialog.show();
        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String main_date = data.getStringExtra("date");
                String main_month = data.getStringExtra("month");
                String main_year = data.getStringExtra("year");
                Toast.makeText(getApplicationContext(), main_date + "/" + main_month + "/" + main_year, Toast.LENGTH_LONG).show();

                StringBuilder sb = new StringBuilder();
                sb.append(main_date);
                sb.append("/");
                sb.append(main_month);
                sb.append("/");
                sb.append(main_year);

                String date = sb.toString();
                if (date.charAt(1) == '/') date = "0" + date;
                if (date.charAt(4) == '/') date = date.substring(0, 3) + "0" + date.substring(3);
                DOB.setText(date);
            }else{
                Toast.makeText(getApplicationContext(),"Where Am I",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Where Am I two", Toast.LENGTH_LONG).show();
        }
    }

    private void updateDateUI() {
        String month = ((mSelectedMonth+1) > 9) ? ""+(mSelectedMonth+1): "0"+(mSelectedMonth+1) ;
        String day = ((mSelectedDay) < 10) ? "0"+mSelectedDay: ""+mSelectedDay ;
        DOB.setText(day+"/"+month+"/"+mSelectedYear);
    }
    /*
    @Override
   protected void onResume(){

        if(extras != null){
            String name =extras.getString("name");
            //DOB.setText(name);
            Toast.makeText(getApplicationContext(),name + "kush",Toast.LENGTH_LONG).show();

        }
    }*/
    private void initialize_components(){
        district_spinner = (Spinner)findViewById(R.id.district_sp);
       // block_spinner = (Spinner)findViewById(R.id.block_sp);
        //layout_block = (LinearLayout)findViewById(R.id.layout_block);
        date_of_birth = (Button)findViewById(R.id.bt_dob_dialog);
        DOB = (TextView)findViewById(R.id.tv_dob_dialog);


    }



    class SetData_Spinner extends AsyncTask<String,String,String> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Loading...");
            this.dialog.setCancelable(false);
            this.dialog.show();


        }

        @Override
        protected String doInBackground(String... params) {

            int id = Integer.parseInt(params[0]);
            System.out.println(id);


            switch (id){

                case 0: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_bilaspur));
                    break;

                case 1:  blocks = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item, getResources().getStringArray(R.array.block_chamba));
                    break;

                case 2: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_hamirpur));
                    break;

                case 3: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_kangra));
                    break;

                case 4: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_kinnaur));
                    break;

                case 5: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_kullu));
                    break;

                case 6: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_lahul_and_spiti));
                    break;

                case 7: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_mandi));
                    break;

                case 8: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_shimla));
                    break;

                case 9: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_sirmaur));
                    break;

                case 10: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_solan));
                    break;

                case 11: blocks = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, getResources().getStringArray(R.array.block_una));
                    break;

                default:
                    break;

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           layout_block.setVisibility(View.VISIBLE);
            block_spinner.setAdapter(blocks);
            dialog.dismiss();

        }
    }



}
