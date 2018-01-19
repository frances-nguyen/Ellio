package com.jenho.framework.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect; //Replacec java.awt.Rectangle
import android.graphics.RectF; //Store float-based positions rather than integer-based positions
import android.graphics.Typeface;

/* The purpose of this class is to make the rendering process in our Android
 * framework resemble that from our Java framework. Painter object can be used
 * like a Java Graphics object, and it will do the work of translating your draw
 * calls into Canvas draw calls.*/

public class Painter {

    private Canvas canvas; //Belongs to gameImage
    private Paint paint; //Use for styling options (TypeFace, font size, color, etc)
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;

    public Painter(Canvas canvas){
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public void setFont(Typeface typeface, float textSize){
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    public void drawString(String str, int x, int y){
        canvas.drawText(str, x, y, paint);
    }

    public void fillRect(int x, int y, int width, int height){
        dstRect.set(x, y, x+width, y+height);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(dstRect, paint);
    }

    public void drawImage(Bitmap bitmap, int x, int y){
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void drawImage(Bitmap bitmap, int x, int y, int width, int height){
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x+width, y+height);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }

    public void fillOval(int x, int y, int width, int height){
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x+width, y+height);
        canvas.drawOval(dstRectF, paint);
    }
}
