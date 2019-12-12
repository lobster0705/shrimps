package com.websarva.wings.android.shrimps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class RegistActivity extends Activity {

    static String stampId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);



        setContentView(R.layout.activity_regist);
        TextView textView = findViewById(R.id.stampidddd);

// テキストを設定して表示
        textView.setText(stampId);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: ここで処理を実行する
                finish();

            }
        }, 5000);






    }
}
