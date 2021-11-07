package com.z.test.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.z.test.R;

public class customButton extends View {

    private Bitmap icon;
    private String text;
    private int text_size;
    private int text_color;

    private int background_width;
    private int background_height;

    private Paint painter;
    private Rect text_bound;
    private Rect icon_canvas;

    private boolean action_down;
    private boolean is_selected;

    private OnBtnSelectListener listener;
    //获得接口对象的方法。
    public void setOnBtnSelectListener(OnBtnSelectListener listener) {
        this.listener = listener;
    }
    //定义一个接口
    public interface  OnBtnSelectListener{
        void onBtnSelect();
        void onBtnCancel();
    }

    //使得所有调用都是用三参构造
    public customButton(Context context) {
        this(context,null);
    }

    public customButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public customButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray att_list = context.getTheme().obtainStyledAttributes(attrs, R.styleable.customButton, defStyleAttr, 0);
        icon= BitmapFactory.decodeResource(getResources(), att_list.getResourceId(R.styleable.customButton_icon, 0));
        text=att_list.getString(R.styleable.customButton_text);
        text_size=att_list.getDimensionPixelSize(R.styleable.customButton_text_size, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        text_color=att_list.getColor(R.styleable.customButton_text_color, Color.BLACK);

        //回收
        att_list.recycle();

        //init params
        painter=new Paint();
        text_bound=new Rect();
        icon_canvas=new Rect();
        //pre measure
        painter.setTextSize(text_size);
        painter.getTextBounds(text,0,text.length(),text_bound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            background_width = specSize;
        } else
        {
            // 由图片决定的宽
            int icon_width =icon.getWidth();
            // 由字体决定的宽
            int text_width = text_bound.width();

            //总宽度
            int total_width=icon_width+text_width+getPaddingLeft()+getPaddingRight()+10;

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                background_width=Math.min(total_width,specSize);
            }
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            background_height = specSize;
        } else
        {
            // 由图片决定的高
            int icon_height =icon.getHeight()+getPaddingTop()+getPaddingBottom();
            // 由字体决定的高
            int text_height = text_bound.height()+getPaddingTop()+getPaddingBottom();

            //总高度
            int total_height=Math.max(icon_height,text_height);
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                background_height = Math.min(total_height, specSize);
            }
        }
        setMeasuredDimension(background_width,background_height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画 background
        painter.setColor(Color.WHITE);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),painter);
        //画 icon
        painter.setAntiAlias(true);
        painter.setStyle(Paint.Style.FILL);
        icon_canvas.top=background_height/2-icon.getHeight()/2;
        icon_canvas.bottom=background_height/2+icon.getHeight()/2;
        icon_canvas.left=getPaddingLeft();
        icon_canvas.right=getPaddingLeft()+icon.getWidth();
        canvas.drawBitmap(icon,null,icon_canvas,painter);
        //画文字
        painter.setTextSize(text_size);
        painter.setColor(text_color);
        Typeface font = Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL);
        painter.setTypeface(font);
        Paint.FontMetrics fontMetrics = painter.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int baseLineY = (int) (icon_canvas.centerY() - top/2 - bottom/2);//基线中间点的y轴计算公式
        canvas.drawText(text, icon_canvas.right+10, baseLineY, painter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action_down=true;
                Log.d("button", "onTouchEvent: down");
                return true;
            case MotionEvent.ACTION_UP:
                action_down=false;
                float x=event.getX();
                float y = event.getY();
                boolean x_inside=x>0 && x<background_width;
                boolean y_inside=y>0 && y<background_height;
                if (x_inside&&y_inside){
                    is_selected=!is_selected;
                    invalidate();
                    if (is_selected)listener.onBtnSelect();
                    else listener.onBtnCancel();
                    Log.d("button", "onTouchEvent: up");
                }else {
                    Log.d("button", "onTouchEvent: outside");
                }
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }
}
