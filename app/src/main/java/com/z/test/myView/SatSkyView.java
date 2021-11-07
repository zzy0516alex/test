package com.z.test.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.z.test.R;

public class SatSkyView extends View {

    private int specSize;//控件大小
    private float radius;//极坐标基准圆半径
    private float center;//画布中心

    //绘制参数
    private Paint LineAndCirclePaint;
    private Paint TextPaint;
    private int text_size;
    private int text_color;

    public SatSkyView(Context context) {
        this(context,null);
    }

    public SatSkyView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SatSkyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray att_list = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SatSkyView, defStyleAttr, 0);
        text_size = att_list.getDimensionPixelSize(R.styleable.SatSkyView_text_size, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        text_color = att_list.getColor(R.styleable.SatSkyView_text_color, Color.BLACK);

        //初始化绘制背景线条和圆的画笔
        LineAndCirclePaint = new Paint();
        LineAndCirclePaint.setColor(ContextCompat.getColor(context, R.color.gray));
        LineAndCirclePaint.setStyle(Paint.Style.STROKE);
        LineAndCirclePaint.setAntiAlias(true);

        TextPaint = new Paint();
        TextPaint.setTextSize(text_size);
        TextPaint.setColor(text_color);
        Typeface font = Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL);
        TextPaint.setTypeface(font);

        //回收
        att_list.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        specSize = MeasureSpec.getSize(widthMeasureSpec);
        radius = (specSize-80)/2.0f;
        center = specSize/2.0f;
        setMeasuredDimension(specSize,specSize);//设为正方形区域
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制原点在左上角
        drawBackgroundCircle(canvas,center,radius);
        drawDividingLine(canvas,center,radius);
        drawScale(canvas,center,radius);
    }

    /**
     * 绘制背景圆环
     * 中心90° 圆环由外向内依次代表高度角0°、30°、60°
     * @param canvas 画布
     * @param r 基准圆半径
     * @param center 圆心
     */
    private void drawBackgroundCircle(Canvas canvas, float center, float r) {
        canvas.drawCircle(center, center, elevationToRadius(r, 60.0f), LineAndCirclePaint);
        canvas.drawCircle(center, center, elevationToRadius(r, 30.0f), LineAndCirclePaint);
        canvas.drawCircle(center, center, elevationToRadius(r, 0.0f), LineAndCirclePaint);
    }

    /**
     * 高度角转极坐标半径
     * @param r 基准圆(外圆)半径
     * @param elev 高度角
     * @return 极坐标下对应半径
     */
    private float elevationToRadius(float r, float elev) {
        return r * (1.0f - (elev / 90.0f));
    }

    /**
     * 绘制背景圆环划分线
     * @param canvas 画布
     * @param center 中心
     * @param radius 圆半径
     */
    private void drawDividingLine(Canvas canvas, float center, float radius) {
        //竖线
        canvas.drawLine(center, center - radius, center, center + radius, LineAndCirclePaint);
        //横线
        canvas.drawLine(center - radius, center, center + radius, center, LineAndCirclePaint);

        //斜45°线
        final float cos45 = (float) Math.cos(Math.PI / 4);
        float d1 = center - radius * cos45;//近点坐标
        float d2 = center + radius * cos45;//远点坐标
        canvas.drawLine(d1, d1, d2, d2, LineAndCirclePaint);
        canvas.drawLine(d2, d1, d1, d2, LineAndCirclePaint);
    }

    private void drawScale(Canvas canvas, float center, float radius) {
        for (int i = 0; i < 360; i += 15) {
//            if (i == 45 || i == 135 || i == 225 || i == 315) {
//                canvas.drawText(String.valueOf(i), center, 40, TextPaint);
//            }
            if (i == 0) {
                canvas.drawText("N", center, center-radius+40, TextPaint);
            } else if (i == 90) {
                canvas.drawText("E", center, center-radius+40, TextPaint);
            } else if (i == 180) {
                canvas.drawText("S", center, center-radius+40, TextPaint);
            } else if (i == 270) {
                canvas.drawText("W", center, center-radius+40, TextPaint);
            } else {
                canvas.drawLine(center, center-radius, center, center-radius+20, LineAndCirclePaint);
            }

            canvas.rotate(15, center, center);
        }
    }
}
