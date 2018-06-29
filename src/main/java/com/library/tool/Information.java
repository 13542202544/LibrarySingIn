package com.library.tool;

//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.List;

/**
 * Created by LinTi on 2016/9/13.
 */
public class Information {
//
//    private HttpClient client;
//    private PostMethod post;
//    private String uid;
//    private String key;
//    private String result;
//    private Header[] headers;
//    private int statusCode;

    public Information(String uid, String key) {
//        client = new HttpClient();
//        post =  new PostMethod("http://gbk.sms.webchinese.cn");
//        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
//        this.uid = uid;
//        this.key = key;
    }

    public String doSend(List<String> list, String message) throws IOException {
//        NameValuePair data[] = new NameValuePair[(list.size() + 3)];
//        data[0] = new NameValuePair("Uid", uid);
//        data[1] = new NameValuePair("Key", key);
//        data[2] = new NameValuePair("smsText",message);
//        for (int i=3;i<list.size()+3;i++) {
//            data[i] = new NameValuePair("smsMob",list.get(i-3));
//        }
//
////        if (1==1){
////            for (NameValuePair n:data) {
////                System.out.println(n);
////            }
////            try {
////                System.out.printf("睡眠8秒开始");
////                Thread.sleep(8000);
////                System.out.printf("睡眠8秒结束");
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
////        return list.size() + "";
//
//
//        post.setRequestBody(data);
//
//        client.executeMethod(post);
//        headers = post.getResponseHeaders();
//        statusCode = post.getStatusCode();
//        result = new String(post.getResponseBodyAsString().getBytes("gbk")); //获取返回消息状态
//
//        post.releaseConnection();
//        return result;
        return null;
    }

//    public int getStatusCode() {
//        return statusCode;
//    }
//
//    public Header[] getHeaders() {
//        return headers;
//    }
//
//    public String getResult() {
//        return result;
//    }
}
