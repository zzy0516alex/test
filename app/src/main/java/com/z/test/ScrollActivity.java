package com.z.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yinglan.scrolllayout.ScrollLayout;
import com.z.test.Utils.ScreenUtil;

import java.io.BufferedReader;

public class ScrollActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        final ScrollFragment fragment= new ScrollFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_view,fragment).commitAllowingStateLoss();
        Button open=findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.mScrollLayout.setToOpen();
            }
        });
    }


}
