package com.liangchenbufu.mujirenben.checkurl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    String[] urls={"adeust7.cn",
            "ixectas3.cn",
            "exectas2.cn",
            "exectas3.cn",
            "exectas8.cn",
            "fxectas7.cn",
            "gxectas1.cn",
            "gxectas2.cn",
            "exectas4.cn",
            "fxectas2.cn",
            "ixectas4.cn",
            "gxectas6.cn",
            "fxectas5.cn",
            "exectas5.cn",
            "gxectas8.cn",
            "kh201.cn",
            "256sz.cn",
            "kh273.cn",
            "279sz.cn",
            "191sz.cn",
            "ax136.cn",
            "281xe.cn",
            "126sz.cn",
            "kh122.cn",
            "kh277.cn",
            "ex096.cn",
            "133xb.cn",
            "243xe.cn",
            "kh267.cn",
            "sr185.cn",
            "164sz.cn",
            "101xe.cn",
            "kh203.cn",
            "185sz.cn",
            "ex133.cn"
    };
    List<String> userList=new ArrayList<>();
    List<String> nouserList=new ArrayList<>();
    private TextView nouserid;
    private TextView userid;
    private TextView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nouserid = (TextView)findViewById(R.id.nouserid);
        userid = (TextView)findViewById(R.id.userid);


        for (int i = 0; i <urls.length; i++) {
            getUrl(urls[i]);
        }
        Log.i("TAG", "正常的url\t" + userList.toString());
        Log.i("TAG", "异常的url\t" + nouserList.toString());
        userid.setText(userList.toString());
        nouserid.setText(nouserList.toString());
    }






    public void getUrl(final String url)
    {
        Thread thread= new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("http:api.kindling.top/wechat/check?url=http://"+url)
                        .build();
                Response response=null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response!=null)
                    {
                        if (response.isSuccessful())
                        {
                            ResponseBody body = response.body();
                            String string = body.string();
                            JSONObject jsonObject=new JSONObject(string);
                            Log.i("hongrenzhuang", "状态\t"+jsonObject.get("status")+"");
                            if (jsonObject.get("status").equals("ok"))
                            {
                                userList.add(url);
                            }
                            else
                            {
                                nouserList.add(url);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }









}
