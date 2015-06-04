package in.gov.hp.aadhaar.hpsrdh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class MainActivity extends Activity {

    ArrayAdapter<String> districts = null;
    ArrayAdapter<String> blocks = null;
    Spinner district_spinner , block_spinner ;
    LinearLayout layout_block;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize_components();
        districts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.district));
        district_spinner.setAdapter(districts);

        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);


                int id_district = district_spinner.getSelectedItemPosition();
                // Toast.makeText(getApplicationContext(),Integer.toString(id_district),Toast.LENGTH_LONG).show();

                SetData_Spinner SD = new SetData_Spinner();
                SD.execute(Integer.toString(id_district));
            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });
    }

    private void initialize_components(){
        district_spinner = (Spinner)findViewById(R.id.district_sp);
        block_spinner = (Spinner)findViewById(R.id.block_sp);
        layout_block = (LinearLayout)findViewById(R.id.layout_block);


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
