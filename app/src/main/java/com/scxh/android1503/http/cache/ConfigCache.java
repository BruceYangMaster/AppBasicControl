package com.scxh.android1503.http.cache;


import com.scxh.android1503.util.Logs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigCache {
    public static String mSdcardDataDir = "";

    public static String getUrlCache(String url) {
        if (url == null) {
            return null;
        }
        Logs.e("读缓存数据");
        String result = null;
        File file = new File(mSdcardDataDir + "/" + getCacheDecodeString(url));
        if (file.exists() && file.isFile()) {
            result = readTextFile(file);
        }
        return result;
    }

    public static void setUrlCache(String data, String url) {
        File file = new File(mSdcardDataDir + "/" + getCacheDecodeString(url));
        //创建缓存数据到磁盘，就是创建文件
        Logs.e("写缓存数据");
        writeTextFile(file, data);

    }

    public static String getCacheDecodeString(String url) {
        //1. 处理特殊字符
        //2. 去除后缀名带来的文件浏览器的视图凌乱(特别是图片更需要如此类似处理，否则有的手机打开图库，全是我们的缓存图片)
        if (url != null) {
            return url.replaceAll("[.:/,%?&amp;=]", "+").replaceAll("[+]+", "+");
        }
        return null;
    }

    public static void writeTextFile(File file, String data) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(data.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readTextFile(File file) {
        FileInputStream instream = null;
        StringBuilder sb = null;
        try {
            instream = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            sb = new StringBuilder();
            //分行读取
            while ((line = buffreader.readLine()) != null) {
                sb.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
