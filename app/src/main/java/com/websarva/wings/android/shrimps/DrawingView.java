package com.websarva.wings.android.shrimps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.websarva.wings.android.shrimps.Paint.bitmap;


public class DrawingView extends View {
    // 履歴
    private List<DrawLine> lines;
    // 現在、描いている線の情報
    private Paint paint;
    private Path path;
    private float oldx = 0f;
    private float oldy = 0f;
    private Canvas bmpCanvas;

    // 線の履歴(座標＋色)
    class DrawLine {
        private Paint paint;
        private Path path;

        DrawLine(Path path, Paint paint) {
            this.paint = new Paint(paint);
            //this.path = new Path(path);
        }

        void draw(Canvas canvas) {
            canvas.drawPath(this.path, this.paint);
        }
    }

    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //this.path = new Path();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);//線か塗りつぶしか
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);//線の太さ
        paint.setStrokeCap(Paint.Cap.ROUND);
        /*線の角の形は次から選ぶ事ができます。
        Paint.Cap.BUTT  何もしない
        Paint.Cap.ROUND 先を丸くします。
        Paint.Cap.SQUARE先を四角にします。*/

        this.lines = new ArrayList<DrawLine>();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bmpCanvas = new Canvas(bitmap);
    }


    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }




    public boolean onTouchEvent(MotionEvent e){
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN: //最初のポイント
                oldx = e.getX();
                oldy = e.getY();
                break;
            case MotionEvent.ACTION_MOVE: //途中のポイント
                bmpCanvas.drawLine(oldx, oldy, e.getX(), e.getY(), paint);
                oldx = e.getX();
                oldy = e.getY();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public byte[] save(Bitmap bit) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(bit.getByteCount());
        bit.copyPixelsToBuffer(byteBuffer);
        byte[] bmparr = byteBuffer.array();
        return bmparr;
    }

    public void delete() {
        // 履歴をクリア
        this.lines.clear();
        // 現在の線をクリア
        this.path.reset();
        invalidate();
    }




    public void setPen(int color){
        this.paint.setColor(color);
    }
}