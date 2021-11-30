package com.z.test.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.GnssStatus;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.z.test.R;
import com.z.test.Utils.ScreenUtil;
import com.z.test.bean.Sat;

import java.util.ArrayList;
import java.util.HashMap;

public class SatSkyView extends View {
    /**
     * include Sat.java,ScreenUtil.java,attrs.xml,mipmap-xhdpi[*.png]
     */

    private int specSize;//控件大小
    private float radius;//极坐标基准圆半径
    private float center;//画布中心
    private int satIconRadius = 36;//卫星图标半径(pixel)

    //绘制参数
    private HashMap<Integer,Bitmap> sat_type_icon = new HashMap<>();
    private Paint LineAndCirclePaint;
    private int line_color;
    private Paint TextPaint;
    private int text_size;
    private int text_color;

    private ArrayList<Sat> sats = new ArrayList<>();

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
        line_color = att_list.getColor(R.styleable.SatSkyView_line_color, Color.GRAY);
        int icon_size_dip = att_list.getDimensionPixelSize(R.styleable.SatSkyView_icon_size, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics()));
        satIconRadius = Math.round((float) ScreenUtil.dip2px(context, icon_size_dip)/3.0f);

        //初始化绘制背景线条和圆的画笔
        LineAndCirclePaint = new Paint();
        LineAndCirclePaint.setColor(line_color);
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
        for (Sat sat : sats) {
            drawSatellite(canvas,sat);
        }
    }

    public void setSats(ArrayList<Sat> sats) {
        this.sats = sats;
    }

    public void addSats(Sat sat){
        this.sats.add(sat);
    }

    public void setSats(GnssStatus gnssStatus){
        this.sats.clear();
        for (int i = 0; i < gnssStatus.getSatelliteCount(); i++) {
            Sat sat = new Sat(gnssStatus.getSvid(i),
                    gnssStatus.getConstellationType(i),
                    gnssStatus.getElevationDegrees(i),
                    gnssStatus.getAzimuthDegrees(i));
            addSats(sat);
        }
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
            if (i == 45 || i == 135 || i == 225 || i == 315) {
                canvas.drawText(String.valueOf(i), center, center-radius+40, TextPaint);
            }
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

    private void drawSatellite(Canvas c, Sat s) {
        if (sat_type_icon.isEmpty())
            sat_type_icon = getDefaultSatIcon();

        double r, a;
        float x, y;

        Bitmap satMap;
        satMap = getResizedSatIcon(s);

        String satText;
        satText = getSatelliteText(s);

        r = elevationToRadius(radius, (float) s.getE());
        a = (float) Math.toRadians(s.getA());

        x = (float) (center + (r * Math.sin(a)));
        y = (float) (center - (r * Math.cos(a)));

        c.drawBitmap(satMap, x - satIconRadius, y - satIconRadius, null);

        c.drawText(satText, x - satIconRadius, y + satIconRadius * 2, TextPaint);
    }

    private Bitmap getResizedSatIcon(Sat sat) {
        Bitmap origin_icon = sat_type_icon.get(sat.getType());
        int width = origin_icon.getWidth();
        int height = origin_icon.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((satIconRadius * 2.0f) / width,
                (satIconRadius * 2.0f) / height); //长和宽放大缩小的比例
        return Bitmap.createBitmap(origin_icon, 0, 0, width, height, matrix, true);
    }

    private HashMap<Integer,Bitmap> getDefaultSatIcon() {
        HashMap<Integer,Bitmap> default_icons = new HashMap<>();
        default_icons.put(GnssStatus.CONSTELLATION_UNKNOWN,
                BitmapFactory.decodeResource(getResources(),R.mipmap.unknown));
        default_icons.put(GnssStatus.CONSTELLATION_GPS,
                BitmapFactory.decodeResource(getResources(),R.mipmap.usa));
        default_icons.put(GnssStatus.CONSTELLATION_GLONASS,
                BitmapFactory.decodeResource(getResources(),R.mipmap.russia));
        default_icons.put(GnssStatus.CONSTELLATION_QZSS,
                BitmapFactory.decodeResource(getResources(),R.mipmap.japan));
        default_icons.put(GnssStatus.CONSTELLATION_BEIDOU,
                BitmapFactory.decodeResource(getResources(),R.mipmap.china));
        default_icons.put(GnssStatus.CONSTELLATION_GALILEO,
                BitmapFactory.decodeResource(getResources(),R.mipmap.europ));
        return default_icons;
    }

    private String getSatelliteText(Sat sat) {
        StringBuilder builder = new StringBuilder();
        switch (sat.getType()) {
            case GnssStatus.CONSTELLATION_BEIDOU:
                builder.append("C");
                break;
            case GnssStatus.CONSTELLATION_GPS:
                builder.append("G");
                break;
            case GnssStatus.CONSTELLATION_GALILEO:
                builder.append("E");
                break;
            case GnssStatus.CONSTELLATION_GLONASS:
                builder.append("R");
                break;
            case GnssStatus.CONSTELLATION_QZSS:
                builder.append("Q");
                break;
            default:
                builder.append("S");
        }
        builder.append(sat.getPRN());
        return builder.toString();
    }

}
