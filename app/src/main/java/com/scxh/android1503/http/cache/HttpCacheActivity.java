package com.scxh.android1503.http.cache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

import cz.msebera.android.httpclient.Header;


public class HttpCacheActivity extends Activity {
    private String CONFIG_URL = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
    private TextView mCacheTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_cache_layout);
        mCacheTxt = (TextView) findViewById(R.id.cache_textview);

        ConfigCache.mSdcardDataDir = getFilesDir().getAbsolutePath();
    }

    public void onCacheClickLisenter(View v){
        getConfig();
    }

    void getConfig() {
        //首先尝试读取缓存
        String cacheConfigString = ConfigCache.getUrlCache(CONFIG_URL);
        //根据结果判定是读取缓存，还是重新读取
        if (cacheConfigString != null) {
            showConfig(cacheConfigString);
        } else {
            //如果缓存结果是空，说明需要重新加载
            //缓存为空的原因可能是1.无缓存;2. 缓存过期;3.读取缓存出错
            AsyncHttpClient client = new AsyncHttpClient();

            client.get(CONFIG_URL, new TextHttpResponseHandler() {

                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                    //根据失败原因，考虑是显示加载失败，还是再读取缓存
                    Toast.makeText(HttpCacheActivity.this, "连接网络出错", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {
                    //成功下载，则保存到本地作为后面缓存文件
                    ConfigCache.setUrlCache(s, CONFIG_URL);
                    //后面可以是UI更新，仅供参考
                    showConfig(s);
                }
            });
        }
    }

    public void showConfig(String msg){
        Logs.e(msg);
        mCacheTxt.setText(msg);
    }

}
