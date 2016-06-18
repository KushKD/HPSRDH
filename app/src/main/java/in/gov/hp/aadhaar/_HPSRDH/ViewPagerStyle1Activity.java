package in.gov.hp.aadhaar._HPSRDH;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class ViewPagerStyle1Activity extends FragmentActivity {
	private ViewPager _mViewPager;
	private ViewPagerAdapter _adapter;
	SignIn SI = new SignIn();
	Logout LO = new Logout();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_main);
        setUpView();
        setTab();
    }
    private void setUpView(){    	
   	 _mViewPager = (ViewPager) findViewById(R.id.viewPager);
     _adapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());
     _mViewPager.setAdapter(_adapter);
	 _mViewPager.setCurrentItem(0);
    }
    private void setTab(){
		//Initial State
		findViewById(R.id.first_text).setActivated(true); //Added
		findViewById(R.id.textView1).setActivated(true); //Added
			_mViewPager.setOnPageChangeListener(new OnPageChangeListener(){
			    		
						@Override
						public void onPageScrollStateChanged(int position) {}
						@Override
						public void onPageScrolled(int arg0, float arg1, int arg2) {}
						@Override
						public void onPageSelected(int position) {
							// TODO Auto-generated method stub
							switch(position){
								case 0:
									//findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
									//findViewById(R.id.first_text).setActivated(true); //Added
									findViewById(R.id.first_text).setActivated(true); //Added
									findViewById(R.id.textView1).setActivated(true); //Added
									//findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
									//findViewById(R.id.second_tab).setActivated(false); //Added
									findViewById(R.id.second_text).setActivated(false); //Added
									findViewById(R.id.textView2).setActivated(false); //Added
									break;
								
							case 1:
								//findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
								//findViewById(R.id.first_tab).setActivated(false); //Added
								findViewById(R.id.first_text).setActivated(false); //Added
								findViewById(R.id.textView1).setActivated(false); //Added
								//findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
								//findViewById(R.id.second_tab).setActivated(true); //Added
								findViewById(R.id.second_text).setActivated(true); //Added
								findViewById(R.id.textView2).setActivated(true);  //Added
								break;
							}
						}
						
					});

    }

	@Override
	public void onBackPressed() {
	//	super.onBackPressed();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit the HPSRDH application?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								//Toast.makeText(getApplicationContext(),"Logout Functionality Goes Here",Toast.LENGTH_SHORT).show();
								//Toast.makeText(getApplicationContext(),SI.UserName_SH,Toast.LENGTH_SHORT).show();
								   //Async Task For LogOut Goes Here

								SharedPreferences sharedpreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);
								String Username_SH=null;
								if (sharedpreferences.contains("USERNAME")) {
									Username_SH =  sharedpreferences.getString("USERNAME", "");
									Log.d("User Name is: ",Username_SH);
								}
								LO.execute(Username_SH);


							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}


	public class Logout extends AsyncTask<String,String,Boolean> {

		String url = null;
		private ProgressDialog dialog;
		Boolean Server_value= false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(ViewPagerStyle1Activity.this);
			this.dialog.setMessage("Logging Out,Please wait..");
			this.dialog.show();
			this.dialog.setCancelable(false);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			String UserID = params[0];

			EncryptData EC  = new EncryptData();
			String Encrypted_UserID = EC.Encrypt_String(UserID);

			StringBuilder sb = new StringBuilder();
			sb.append(EConstants.url_Generic);
			sb.append(EConstants.url_Delemetre);
			sb.append(EConstants.methord_Logout);
			sb.append(EConstants.url_Delemetre);
			sb.append(Encrypted_UserID);
			url = sb.toString();
			JSONParser jParser = new JSONParser();
			String result  = jParser.LogOut(url);
			sb.delete(0, sb.length());
			Object json ;
			try {
				json = new JSONTokener(result).nextValue();

				if (json instanceof JSONObject){
					JSONObject obj = new JSONObject(result);
					Server_value = obj.optBoolean(EConstants.UserLogoutResult);
				}

			} catch (JSONException e) {
				e.printStackTrace();
				Server_value = false;
			}
			return Server_value ;
		}

		@Override
		protected void onPostExecute(Boolean s) {
			super.onPostExecute(s);
			this.dialog.dismiss();



			if(s) {
				Log.d("Result Exit", s.toString());
				ViewPagerStyle1Activity.this.finish();
				int pid = android.os.Process.myPid();
				android.os.Process.killProcess(pid);

			}else {
				Toast.makeText(getApplicationContext(), EConstants.ErrorMessageUnknow, Toast.LENGTH_LONG).show();
			}
		}
	}
}