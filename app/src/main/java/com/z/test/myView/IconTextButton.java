package com.z.test.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.z.test.R;

public class IconTextButton extends View {

    //读取用户自定义属性
    private Bitmap icon;
    private String text;
    private int text_size;
    private int text_color;
    private int theme_color;

    //绘图区域
    private Paint painter;
    private Rect text_bound;
    private Rect icon_canvas;

    //控件宽高
    private int button_width;
    private int button_height;

    //按键状态（触摸，选中）
    private boolean action_down;
    private boolean isSelected;

    //下面三个构造函数，强制使用第三个三参构造
    public IconTextButton(Context context) {
        this(context,null);
    }

    public IconTextButton(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public IconTextButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取用户定义的属性
        TypedArray att_list = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IconTextButton, defStyleAttr, 0);
        icon= BitmapFactory.decodeResource(getResources(), att_list.getResourceId(R.styleable.IconTextButton_icon, 0));
        text=att_list.getString(R.styleable.customButton_text);
        text_size=att_list.getDimensionPixelSize(R.styleable.IconTextButton_text_size, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        theme_color =att_list.getColor(R.styleable.IconTextButton_theme_color, Color.BLACK);
        text_color =att_list.getColor(R.styleable.IconTextButton_text_color, Color.BLACK);
        //回收
        att_list.recycle();

        //初始化绘图区
        painter=new Paint();
        text_bound=new Rect();
        icon_canvas=new Rect();
        //提前设置部分属性
        painter.setTextSize(text_size);
        painter.getTextBounds(text,0,text.length(),text_bound);
    }

    //该回调用于设置控件大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            button_width = specSize;
        } else
        {
            // 由图片决定的宽
            int icon_width =icon.getWidth();
            // 由字体决定的宽
            int text_width = text_bound.width();

            //总宽度=图片或文字中较宽的宽度+左右边距
            int total_width=Math.max(icon_width,text_width)+getPaddingLeft()+getPaddingRight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                button_width =total_width;
            }
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            button_height = specSize;
        } else
        {
            // 由图片决定的高
            int icon_height =icon.getHeight();
            // 由字体决定的高
            int text_height = text_bound.height();

            //总高度=图片高+文字高+上下边距
            int total_height=icon_height+text_height+getPaddingBottom()+getPaddingTop();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                button_height = total_height;
            }
        }
        //设置长宽
        setMeasuredDimension(button_width, button_height);
    }

    //该回调是主要绘图函数，该部分可根据需求添加内容
    @Override
    protected void onDraw(Canvas canvas) {
        painter.setShader(null);

        //画 icon
            //设置icon颜色
        painter.setAntiAlias(true);
        painter.setFilterBitmap(true);
        painter.setStyle(Paint.Style.FILL);
            //选中时变为用户设置的theme_color
        if (isSelected)painter.setColorFilter(new PorterDuffColorFilter(theme_color, PorterDuff.Mode.SRC_IN));
            //icon画布居中
        icon_canvas.top=getPaddingTop();
        icon_canvas.bottom=icon_canvas.top+icon.getHeight();
        icon_canvas.left=button_width/2-icon.getWidth()/2;
        icon_canvas.right=icon_canvas.left+icon.getWidth();
        canvas.drawBitmap(icon,null,icon_canvas,painter);


        //画文字
        painter.setTextSize(text_size);
        if (!isSelected)painter.setColor(text_color);
        else painter.setColor(theme_color);
        painter.setColorFilter(null);
        Typeface font = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        painter.setTypeface(font);
        canvas.drawText(text, button_width*1.0f/2-text_bound.width()*1.0f/2, icon_canvas.bottom+text_bound.height(), painter);

        //画蒙版,按钮被点击时显示半透明遮罩层
        painter.setColor(Color.WHITE);
        painter.setAlpha(150);
        if (action_down)canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),painter);
        painter.setAlpha(255);

    }

    //用户手势识别
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action_down=true;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                if (click_listener!=null)click_listener.onClick();
                action_down=false;
                float x=event.getX();
                float y = event.getY();
                boolean x_inside=x>0 && x<button_width;
                boolean y_inside=y>0 && y<button_height;
                if (x_inside&&y_inside){
                    isSelected=!isSelected;
                    if (select_listener !=null){
                        if (isSelected) select_listener.onBtnSelect();
                        else select_listener.onBtnCancel();
                    }
                }else action_down=false;
                invalidate();
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }

    private OnBtnSelectListener select_listener;
    private OnBtnClickListener click_listener;

    //获得接口对象的方法。
    public void setOnBtnSelectListener(OnBtnSelectListener listener) {
        this.select_listener = listener;
    }

    public void setClickListener(OnBtnClickListener click_listener) {
        this.click_listener = click_listener;
    }

    //定义接口
    public interface  OnBtnSelectListener{
        void onBtnSelect();
        void onBtnCancel();
    }
    public interface  OnBtnClickListener{
        void onClick();
    }

    public void setIs_selected(boolean is_selected) {
        this.isSelected = is_selected;
        invalidate();
    }

    public OnBtnSelectListener getSelect_listener() {
        return select_listener;
    }

    public boolean IsSelected() {
        return isSelected;
    }
}
