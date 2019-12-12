package com.websarva.wings.android.shrimps;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.ByteBuffer;


public class Paint extends AppCompatActivity implements View.OnClickListener {
    private DrawingView drawingView;
    Button red_button,blue_button,yellow_button,green_button,save_button;
    byte[] byby;
    static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_paint);
        blue_button= findViewById(R.id.blue_button);
        red_button= findViewById(R.id.red_button);
        yellow_button= findViewById(R.id.yellow_button) ;
        green_button= findViewById(R.id.green_button) ;
        save_button= findViewById(R.id.save_button) ;
        save_button.setOnClickListener(this);
        green_button.setOnClickListener(this);
        blue_button.setOnClickListener(this);
        red_button.setOnClickListener(this);
        yellow_button.setOnClickListener(this);

        this.drawingView = findViewById(R.id.drawing_view);

        findViewById(R.id.delete_button).setOnClickListener(deleteDrawing);



    }
    View.OnClickListener deleteDrawing = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            drawingView.delete();
        }
    };


    public void onClick(View v){
        switch(v.getId()){
            case R.id.blue_button:
                drawingView.setPen(Color.BLUE);
                Toast.makeText(this,"青",Toast.LENGTH_SHORT).show();
                break;
            case R.id.red_button:
                drawingView.setPen(Color.RED);
                Toast.makeText(this,"赤",Toast.LENGTH_SHORT).show();
                break;
            case R.id.yellow_button:
                drawingView.setPen(Color.YELLOW);
                Toast.makeText(this,"黄",Toast.LENGTH_SHORT).show();
                break;
            case R.id.green_button:
                drawingView.setPen(Color.GREEN);
                Toast.makeText(this,"緑",Toast.LENGTH_SHORT).show();
                break;
            case R.id.save_button:
                ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
                bitmap.copyPixelsToBuffer(byteBuffer);
                byte[] bmparr = byteBuffer.array();
                Intent intent = new Intent(Paint.this, Start.class);
                Start.imageInByte= bmparr;
                startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
