package com.example.lin.volley_kuanjia;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url="http://apis.baidu.com/heweather/weather/free";
    private String arg="city=beijing";
    private String mUrl=url+"?"+arg;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView) findViewById(R.id.text);
        imageView= (ImageView) findViewById(R.id.image);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            textView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            textView.setText("error");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String ,String> map=new HashMap<>();
                map.put("apikey","af02a3e0e93ef6cbb85eada01a1c40ae");
                return map;
            }
        };

        ImageRequest imae=new ImageRequest("http://img0.imgtn.bdimg.com/it/u=3952510902,1852039876&fm=11&gp=0.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(stringRequest);
        requestQueue.add(imae);
    }
}
