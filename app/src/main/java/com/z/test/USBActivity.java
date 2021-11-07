package com.z.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class USBActivity extends AppCompatActivity {

    private UsbManager usbManager;
    private Context context;
    //代表USB设备的一个接口
    private UsbInterface mInterface;
    private UsbDeviceConnection mDeviceConnection;
    //找到的USB设备
    private UsbDevice mUsbDevice;
    //代表一个接口的某个节点的类:写数据节点
    private UsbEndpoint usbEpOut;
    //代表一个接口的某个节点的类:读数据节点
    private UsbEndpoint usbEpIn;
    //要发送信息字节
    private byte[] sendbytes;
    //接收到的信息字节
    private byte[] receiveytes;
    private TextView tv_info;
    private Button btn_getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usbactivity);

        tv_info = findViewById(R.id.usb_info);

        btn_getdata = findViewById(R.id.get_data);
        btn_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromUsb();
            }
        });

        context = this;
        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        tv_info.setText("设备数：" + deviceList.size());
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        tv_info.append("\n");
        PendingIntent permissionIntent = PendingIntent.
                getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        while (deviceIterator.hasNext()) {
            mUsbDevice = deviceIterator.next();
            tv_info.append(mUsbDevice.getDeviceName());
            tv_info.append("\n");
            tv_info.append("ID:"+mUsbDevice.getProductId());
            tv_info.append("\n");
            usbManager.requestPermission(mUsbDevice, permissionIntent);
        }

        getSerial_Ports_name();

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(usbReceiver, filter);

        //获取设备接口
        for (int i = 0; i < mUsbDevice.getInterfaceCount(); ) {
            // 一般来说一个设备都是一个接口，你可以通过getInterfaceCount()查看接口的个数
            // 这个接口上有两个端点，分别对应OUT 和 IN
            tv_info.append("接口数 = "+mUsbDevice.getInterfaceCount());
            mInterface = mUsbDevice.getInterface(i);
            break;
        }
        //用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
        if (mInterface.getEndpoint(1) != null) {
            usbEpOut = mInterface.getEndpoint(1);
        }
        if (mInterface.getEndpoint(0) != null) {
            usbEpIn = mInterface.getEndpoint(0);
            tv_info.append("in max="+usbEpIn.getMaxPacketSize());
            tv_info.append("\n");
        }
        if (mInterface!=null) {
            tv_info.append("has interface");
            tv_info.append("\n");
            if (usbManager.hasPermission(mUsbDevice)) {
                tv_info.append("has permission");
                tv_info.append("\n");
                mDeviceConnection = usbManager.openDevice(mUsbDevice);
                if (mDeviceConnection == null) {
                    tv_info.append("no connection");
                    tv_info.append("\n");
                }
                else {
                    tv_info.append("connected："+mDeviceConnection.getSerial());
                    tv_info.append("\n");
                }
            }else {
                tv_info.append("no permission");
                tv_info.append("\n");
            }
        }else {
            tv_info.append("no interface");
            tv_info.append("\n");
        }
    }

    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if(mUsbDevice != null){
                            //call method to set up device communication
                            tv_info.append("获得权限");
                        }
                    }
                    else {
                        Log.d("usb", "permission denied for device " + mUsbDevice);
                        tv_info.append("未获得权限");
                    }
                }
            }
        }
    };
    private void readFromUsb() {
        //读取数据2
        int outMax = usbEpOut.getMaxPacketSize();
        int inMax = usbEpIn.getMaxPacketSize();
        ByteBuffer byteBuffer = ByteBuffer.allocate(inMax);
        UsbRequest usbRequest = new UsbRequest();
        usbRequest.initialize(mDeviceConnection, usbEpIn);
        usbRequest.queue(byteBuffer, inMax);
        if (mDeviceConnection.requestWait() == usbRequest) {
            byte[] retData = byteBuffer.array();
            tv_info.append("收到数据："+retData.length);
            tv_info.append("\n");
            tv_info.append("内容："+new String(retData));
            tv_info.append("\n");
        }
    }

    //获取串口
    private String[] getSerial_Ports_name() {
        List<String> Serial_Ports_number = new ArrayList<String>();
        String[] type = null;
        File file = new File("/dev/");
        File[] fs = file.listFiles();
        if (fs == null) return type;
        for (File f : fs) {
            if (f.getName().contains("tty")) {
                Serial_Ports_number.add(f.getName());
                tv_info.append("地址："+f.getName());
            }
        }
        type = Serial_Ports_number.toArray(new String[Serial_Ports_number.size()]);
        return type;
    }
}