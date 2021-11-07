package com.z.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.srtp.diffpositioncalculator.DiffPositioning;
import com.srtp.diffpositioncalculator.NavigationMessageRaw;
import com.srtp.diffpositioncalculator.SatSentMsg;

import java.util.ArrayList;

public class GnssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnss);
        //判断是否可以定位
//        DiffPositioning.Can_POS();
        //获取单点定位结果
//        DiffPositioning.Get_SPP_POS(...);
        //获取单点定位结果（考虑高度角）
//        DiffPositioning.Get_SPP_POS_Ele(...);
        //获取差分定位结果
        double[] BLH = DiffPositioning.
                Position_Calculator(new ArrayList<NavigationMessageRaw>(),
                        new ArrayList<SatSentMsg>(),
                        604800,
                        10,
                        true);
        //XYZ转BLH
//        DiffPositioning.OnXYZtoBLH(x,y,z,blh[]);
    }
}
