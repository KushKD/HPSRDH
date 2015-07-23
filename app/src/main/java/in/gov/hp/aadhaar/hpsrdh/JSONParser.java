package in.gov.hp.aadhaar.hpsrdh;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import java.io.InputStream;

/**
 * Created by kush on 17/07/15.
 */
public class JSONParser {

    static InputStream is = null;
    static String verify = null;
   public  String varification = "";


    public JSONParser() {

    }

    /**
     * Verification LOGIN
     * @author Kush Kumar Dhawan
     */

    public String getDataRest(String url) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        try {

            response = httpclient.execute(new HttpGet(url));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                is = httpEntity.getContent();
            } else {
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 32);
            //    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 16);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                //System.out.println("This is the RESPONSE I'm Getting"+line);
            }
            is.close();
            varification = sb.toString();
            System.out.println(varification);

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            return "";
        }

        return varification;
    }



    /**
     *  Logout
     * @author Kush Kumar Dhawan
     */

    public String LogOut(String url) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        try {

            response = httpclient.execute(new HttpGet(url));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                is = httpEntity.getContent();
            } else {
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 32);
            //    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 16);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                //System.out.println("This is the RESPONSE I'm Getting"+line);
            }
            is.close();
            varification = sb.toString();
            System.out.println(varification);

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
            return "";
        }

        return varification;
    }



}
