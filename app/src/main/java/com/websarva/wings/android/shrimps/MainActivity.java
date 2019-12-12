package com.websarva.wings.android.shrimps;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.io.InputStream;


public class MainActivity extends Activity {
    ImageButton rankbutton, paintbutton, camerabutton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ここで1秒間スリープし、スプラッシュを表示させたままにする。
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
        }
        // スプラッシュthemeを通常themeに変更する
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        ImageView imageView3 = findViewById(R.id.imageView3);

        AssetManager assets = getResources().getAssets();
        // try-with-resources
        try (InputStream istream = assets.open("img_3.jpg")){
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView3.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        camerabutton = findViewById(R.id.camerabutton);
        rankbutton = findViewById(R.id.rankbutton);
        paintbutton = findViewById(R.id.paintbutton);

        camerabutton.setOnClickListener(Click);
        rankbutton.setOnClickListener(Click);
        paintbutton.setOnClickListener(Click);
    }

    private View.OnClickListener Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.camerabutton:
                    Intent intent1 = new Intent(MainActivity.this, Camera.class);
                    startActivity(intent1);
                    break;
                case R.id.rankbutton:
                    Log.i("bbbbb","bbb");
                    break;
                case R.id.paintbutton:
                    Intent intent3 = new Intent(MainActivity.this, Paint.class);
                    startActivity(intent3);
                    break;

            }
        }
    };
}
/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, Paint.class);
        startActivity(intent);
    }
}*/
