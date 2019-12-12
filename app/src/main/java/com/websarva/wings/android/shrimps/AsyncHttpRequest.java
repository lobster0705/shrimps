package com.websarva.wings.android.shrimps;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AsyncHttpRequest extends AsyncTask<String, Void, String> {
    private Activity mActivity;

    public AsyncHttpRequest(Activity activity) {
        mActivity = activity;
    }
    String line;


    @Override
    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        Log.e("ddd",params[0]);

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            //ここにpythonからレスポンスのな
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            line = reader.readLine();//com.example.start E/bbb: Hello from do_GET


            while (line != null) {
                Log.e("aaa",line);
                RegistActivity.stampId = line;
                line = reader.readLine();
                sb.append(line);
            }


            is.close();
        } catch (IOException e) {
            Log.e("ccc",e.toString());
            e.printStackTrace();
        } finally{
            assert connection != null;
        }



        return sb.toString();
    }

    public void onPostExecute(String string) {
        ((TextView)mActivity.findViewById(R.id.touroku)).setText(string);


    }


}