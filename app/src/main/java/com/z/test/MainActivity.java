package com.z.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.suke.widget.SwitchButton;
import com.z.fileselectorlib.FileSelectorActivity;
import com.z.fileselectorlib.FileSelectorSettings;
import com.z.fileselectorlib.Objects.BasicParams;
import com.z.fileselectorlib.Objects.FileInfo;
import com.z.test.Utils.JsonUtil;
import com.z.test.bean.myBook;
import com.z.test.myView.customButton;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private OnClick onclick=new OnClick();
    private LinearLayout ll_bottom;
    private LinearLayout ll_top;
    private Activity activity;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_fs=findViewById(R.id.file_selector);
        final Button btn_cl=findViewById(R.id.client_login);
        final Button btn_slv=findViewById(R.id.scroll_layout_view);
        final Button btn_anim = findViewById(R.id.anim_test);
        final Button btn_anim_quit = findViewById(R.id.anim_quit);
        final customButton btn_cus=findViewById(R.id.custom_btn);
        final Button btn_usb=findViewById(R.id.usb_test);
        ll_bottom = findViewById(R.id.translate_anim_test);
        ll_bottom.setVisibility(View.INVISIBLE);
        ll_top = findViewById(R.id.translate_anim_test2);
        ll_top.setVisibility(View.INVISIBLE);
        context = this;
        activity = this;
        btn_cl.setOnClickListener(onclick);
        btn_fs.setOnClickListener(onclick);
        btn_slv.setOnClickListener(onclick);
        btn_anim.setOnClickListener(onclick);
        btn_anim_quit.setOnClickListener(onclick);
        btn_usb.setOnClickListener(onclick);
        btn_cus.setOnBtnSelectListener(new customButton.OnBtnSelectListener() {

            @Override
            public void onBtnSelect() {
                Toast.makeText(activity, "select", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBtnCancel() {
                Toast.makeText(activity, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        SwitchButton switchButton=findViewById(R.id.mswitch);
        switchButton.setTag(R.id.mswitch,5);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                int tag = (int) view.getTag(R.id.mswitch);
                Toast.makeText(activity, ""+tag, Toast.LENGTH_SHORT).show();
            }
        });
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.file_selector:{
                    launchFileSelector();
                }
                    break;
                case R.id.client_login:
                    startActivity(new Intent(MainActivity.this,LoginClientActivity.class));
                    break;
                case R.id.scroll_layout_view:
                    startActivity(new Intent(MainActivity.this,ScrollActivity.class));
                    break;
                case R.id.usb_test:
                    startActivity(new Intent(MainActivity.this,USBActivity.class));
                    break;
                case R.id.anim_test:
                    //        fromXType：动画开始前的X坐标类型。取值范围为ABSOLUTE（绝对位置）、RELATIVE_TO_SELF（以自身宽或高为参考）、RELATIVE_TO_PARENT（以父控件宽或高为参考）。
                    //        fromXValue：动画开始前的X坐标值。当对应的Type为ABSOLUTE时，表示绝对位置；否则表示相对位置，1.0表示100%。
                    //        toXType：动画结束后的X坐标类型。
                    //        toXValue：动画结束后的X坐标值。
                    //        fromYType：动画开始前的Y坐标类型。
                    //        fromYValue：动画开始前的Y坐标值。
                    //        toYType：动画结束后的Y坐标类型。
                    //        toYValue：动画结束后的Y坐标值。
                    TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
                    translateAnimation.setDuration(200);
                    //        Interpolator interpolator = new LinearInterpolator();//匀速
                    //        Interpolator interpolator = new AccelerateInterpolator();//先慢后快
                    //        Interpolator interpolator = new AnticipateInterpolator();//开始回弹效果
                    //        Interpolator interpolator = new BounceInterpolator();//结束回弹效果
                    //        Interpolator interpolator = new CycleInterpolator(2);//跳一跳效果
                    //        Interpolator interpolator = new OvershootInterpolator(1);//动画结束时向前弹一定距离再回到原来位置
                    //        Interpolator interpolator = new AccelerateDecelerateInterpolator();//系统默认的动画效果，先加速后减速
                    //        Interpolator interpolator = new AnticipateOvershootInterpolator();//开始之前向前甩，结束的时候向后甩
                    //        Interpolator interpolator = new DecelerateInterpolator();//开始加速再减速
                    translateAnimation.setInterpolator(new DecelerateInterpolator());
                    //Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_up);
                    ll_bottom.startAnimation(translateAnimation);
                    ll_bottom.setVisibility(View.VISIBLE);
                    break;
                case R.id.anim_quit:
                    TranslateAnimation translateAnimation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f);
                    translateAnimation2.setDuration(200);
                    translateAnimation2.setInterpolator(new AccelerateInterpolator());
                    ll_top.startAnimation(translateAnimation2);
                    ll_top.setVisibility(View.VISIBLE);
                    ll_bottom.startAnimation(translateAnimation2);
                    ll_bottom.setVisibility(View.INVISIBLE);
                    break;
                default:
            }
        }
    }

    private void launchFileSelector() {
        FileSelectorSettings settings=new FileSelectorSettings();
        settings.setRootPath(FileSelectorSettings.getSystemRootPath()+"/Android/data")
                .setMaxFileSelect(2)
                .setTitle("请选择文件夹")
                .setThemeColor("#3700B3")
                .setFileTypesToSelect(FileInfo.FileType.Folder)
                .setMoreOPtions(new String[]{"新建文件夹", "删除文件"},
                        new BasicParams.OnOptionClick() {
                            @Override
                            public void onclick(Activity activity, int position, String currentPath, ArrayList<String> FilePathSelected) {
                                File Folder =new File(currentPath,"新文件夹");
                                if(!Folder.exists()){
                                    Folder.mkdir();
                                }
                            }
                        }, new BasicParams.OnOptionClick() {
                            @Override
                            public void onclick(Activity activity, int position, String currentPath, ArrayList<String> FilePathSelected) {
                                if (FilePathSelected!=null){
                                    for (String path :
                                            FilePathSelected) {
                                        File delFile=new File(path);
                                        delFile.delete();
                                    }
                                }
                            }
                        })
                .show(MainActivity.this);
    }
}
