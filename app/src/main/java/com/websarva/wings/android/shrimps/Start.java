package com.websarva.wings.android.shrimps;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Start extends Activity {

    EditText editText;
    TextView textView;

    String urlName = "http://10.15.122.42:8000";
    String stampToroku = "Toroku";
    int typenamber;
    static byte[] imageInByte;



    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        editText = (EditText) findViewById(R.id.stamp_name);
        textView = (TextView) findViewById(R.id.test);
        //端末番号取得
        final String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        Button sendButton = findViewById(R.id.touroku);


        ByteBuffer byteBuffer = ByteBuffer.allocate(Paint.bitmap.getByteCount());
        Paint.bitmap.copyPixelsToBuffer(byteBuffer);
        imageInByte = byteBuffer.array();
        Log.i(TAG, imageInByte+"imageaaaaaaaaaa");
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Log.i(TAG, Paint.bitmap+"bmpaaaaaaaaaa");
        image.setImageBitmap(Bitmap.createScaledBitmap(Paint.bitmap, 127,
                200, false));

        // ラジオボタン変更時のイベント
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.face1) {
                    typenamber=1;
                    Log.i(TAG, typenamber+"が選択されました。");
                }
                if (checkedId == R.id.face2) {
                    typenamber=2;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face3) {
                    typenamber=3;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face4) {
                    typenamber=4;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face5) {
                    typenamber=5;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face6) {
                    typenamber=6;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face7) {
                    typenamber=7;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
                if (checkedId == R.id.face8) {
                    typenamber=8;
                    Log.i(TAG, typenamber+"が選択されました。");

                }
            }


        });
        radioGroup.check(R.id.RadioGroup1);

        sendButton.setOnClickListener(new View.OnClickListener() {



            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {


                //スタンプ名を保持


                Intent intent = new Intent(Start.this, RegistActivity.class);
                startActivity(intent);

                String text = editText.getText().toString();    //EditText(テキストボックス)から文字列を取得
                if (!text.equals("")) {
                    //TextViewに文字列をセット
                    textView.setText(text);
                    //textViewをストリング型に変換
                    String stamp_name = textView.getText().toString();


                    //画像をbyte型に変換

                    /*ImageView imageView = (ImageView) findViewById(R.id.stamp);
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    imageInByte = baos.toByteArray();*/

                    //byte配列→Bitmap→ImageViewにする。






                    //HTTPに接続
                    new AsyncHttpRequest(Start.this).execute(urlName+"?"+stampToroku+"&"+stamp_name+"&"+imageInByte+"&"+android_id+"&"+typenamber);
                    HttpURLConnection connection = null;





                }
//                finish();


            }

        });



    }
}

