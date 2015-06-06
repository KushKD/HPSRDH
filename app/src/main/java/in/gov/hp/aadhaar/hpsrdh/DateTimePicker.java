package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.Toast;

import in.gov.hp.aadhaar.presentation.DatePickerFragment;
import in.gov.hp.aadhaar.presentation.DateSelectListener;

/**
 * Created by admin on 06/06/2015.
 */
public class DateTimePicker extends ActionBarActivity {

    private DatePickerFragment mDatePickerFragment;
    private DatePickerFragment mDatePickerDialogFragment;

    private int mSelectedMonth;
    private int mSelectedYear;
    private int mSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.datepicker);

        setUpTitleBar();

        /* Code for dialog*/
    /*   mDatePickerDialogFragment=new DatePickerFragment();
       FragmentManager manager=getSupportFragmentManager();
        if(savedInstanceState!=null){

                mDatePickerDialogFragment.restoreStatesFromKey(savedInstanceState,"CALENDAR_SAVED_STATE");
        }

    else
       mDatePickerDialogFragment.show(manager,"Dialog");  */


 // Code for normal fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mDatePickerFragment=new DatePickerFragment();
        if(savedInstanceState!=null){

            mDatePickerFragment.restoreStatesFromKey(savedInstanceState,"CALENDAR_SAVED_STATE");
        }

        transaction.replace(R.id.container, mDatePickerFragment);
        transaction.commit();



        DateSelectListener dateSelectListener=new DateSelectListener() {
            @Override
            public void onSelectDate(int date, int month, int year) {

                mSelectedDate=date;
                mSelectedMonth=month;
                mSelectedYear=year;

               // Toast.makeText(DateTimePicker.this, "" + month + " " + year + " " + date, Toast.LENGTH_LONG).show();

               // Intent i = new Intent(DateTimePicker.this,MainActivity.class);
               // setResult(2, i);
               // i.putExtra("name",Integer.toString(year));
               // startActivityForResult(i,2);
               // finish();

                Intent intent = new Intent();
                intent.putExtra("year",Integer.toString(year));
                setResult(RESULT_OK, intent);
                finish();


            }
        };

        mDatePickerFragment.setDateSelectListener(dateSelectListener);
        

    }

    private void setUpTitleBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_color)));

        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (mDatePickerFragment != null) {
            mDatePickerFragment.saveStatesToKey(outState, "CALENDAR_SAVED_STATE");
        }

        if (mDatePickerDialogFragment != null) {
            mDatePickerDialogFragment.saveStatesToKey(outState,
                    "DIALOG_CALENDAR_SAVED_STATE");
        }
    }

}
