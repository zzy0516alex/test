package com.z.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

public class LoginClientActivity extends AppCompatActivity {
    final static String Base_URL="http://192.168.0.138:8088/HttpServer_zbase/";
    private String Username;
    private String Password;
    private TextView result_txt;
    private ImageView result_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
        final EditText username=findViewById(R.id.username);
        final EditText password=findViewById(R.id.password);
        Button confirm=findViewById(R.id.login_confirm);
        result_txt =findViewById(R.id.result_txt);
        result_img=findViewById(R.id.result_img);

        getCheckCode();

        result_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCheckCode();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Username=username.getText().toString();
                Password=password.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url=new URL(Base_URL+"httploginservlet");
                            //trustAllHosts();
                            HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                            connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”
                            connection.setConnectTimeout(8000); // 设置连接建立的超时时间
                            connection.setReadTimeout(8000); // 设置网络报文收发超时时间
                            //只有post命令需要用到下面两个设置
                            connection.setDoOutput(true);
                            connection.setDoInput(true);

                            DataOutputStream outputStream=new DataOutputStream(connection.getOutputStream());
                            HashMap<String,String> param=new HashMap<>();
                            param.put("username",Username);
                            param.put("password",Password);
                            JSONObject json = new JSONObject(param);
                            outputStream.write(json.toString().getBytes());
                            outputStream.flush();

                            final int responseCode = connection.getResponseCode();
                            final StringBuilder response = new StringBuilder();
                            InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result_txt.setText("code="+responseCode+"\nresponse="+response);
                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    private void getCheckCode() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String date=new Date().toString();
                    URL check_code_url=new URL(Base_URL+"check_code?width=300&height=150&theme=light&date="+date);

                    HttpURLConnection connection= (HttpURLConnection) check_code_url.openConnection();

                    connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”
                    connection.setConnectTimeout(8000); // 设置连接建立的超时时间
                    connection.setReadTimeout(8000); // 设置网络报文收发超时时间

                    connection.connect();

                    final int responseCode = connection.getResponseCode();
                    InputStream inputStream = connection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result_txt.setText("code="+responseCode);
                            result_img.setImageBitmap(bitmap);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
