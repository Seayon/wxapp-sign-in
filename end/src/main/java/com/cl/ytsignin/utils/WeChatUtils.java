package com.cl.ytsignin.utils;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/3/29
 */
@Service
public class WeChatUtils {
	private final static Logger logger = Logger.getLogger(WeChatUtils.class);
	public static String ACCESS_TOKEN = null;
	public static Timestamp ACCESS_TOKEN_EXPIRE_TIME = null;
	private final static String DEFAULT_COLOR = "#18BC9C";

	public static JSONObject code2Session(String code) {
		JSONObject jsonObject = new JSONObject();
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder()
				.url(WxConfig.WXAPP_API_CODE2SESSION + "&js_code=" + code + "&grant_type=authorization_code")
				.build();
		ResponseBody responseBody = null;
		try {
			responseBody = okHttpClient.newCall(request).execute().body();
			jsonObject = JSONObject.parseObject(responseBody.string());
			logger.debug(jsonObject.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (responseBody != null) {
				responseBody.close();
			}
		}
		return jsonObject;
	}

	private WeChatUtils() {

	}

	private final static String PUT_TEMP_INFO_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

	/**
	 * 获取access_token
	 *
	 * @return
	 */
	public static String getAccessToken() {
		synchronized (WeChatUtils.class) {
			if (ACCESS_TOKEN_EXPIRE_TIME==null||ACCESS_TOKEN_EXPIRE_TIME.getTime() - System.currentTimeMillis() > 600) {
				Request request = new Request.Builder()
						.url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WxConfig.APPID + "&secret=" + WxConfig.APPSERCET)
						.build();
				ResponseBody responseBody = null;
				OkHttpClient okHttpClient = new OkHttpClient();
				try {
					responseBody = okHttpClient.newCall(request).execute().body();
					JSONObject jsonObject = JSONObject.parseObject(responseBody.string());
					if (jsonObject.containsKey("access_token")) {
						ACCESS_TOKEN = jsonObject.getString("access_token");
						ACCESS_TOKEN_EXPIRE_TIME = new Timestamp(System.currentTimeMillis() + jsonObject.getInteger("expires_in"));
						System.out.println("ACCESS_TOKEN_EXPIRE_TIME" + ACCESS_TOKEN_EXPIRE_TIME);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (responseBody != null) {
						responseBody.close();
					}
				}
				return ACCESS_TOKEN;
			} else {
				return ACCESS_TOKEN;
			}
		}
	}

	public static String getQr(String eventId) {
		ResponseBody responseBody = null;
		RequestBody requestBody = RequestBody.create(JSON, "{\n" +
				"\t\"path\": \"pages/index/index?eventId=4701\",\n" +
				"\t\"width\": 430,\n" +
				"\t\"auto_color\": true,\n" +
				"\t\"line_color\": {\n" +
				"\t\t\"r\": \"0\",\n" +
				"\t\t\"g\": \"0\",\n" +
				"\t\t\"b\": \"0\"\n" +
				"\t},\n" +
				"\t\"is_hyaline\": false\n" +
				"}");
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + getAccessToken())
				.post(requestBody)
				.build();
		OkHttpClient okHttpClient = new OkHttpClient();
		try {
			responseBody = okHttpClient.newCall(request).execute().body();
			CommonUtils.saveImageFromStream("E://", "4701.png", responseBody.byteStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getUserInfo(String openId) {
		Request request = new Request.Builder()
				.url("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getAccessToken()+"&openid="+openId+"&lang=zh_CN")
				.build();
		OkHttpClient okHttpClient = new OkHttpClient();
		try {
			System.out.println(okHttpClient.newCall(request).execute().body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args){
		getUserInfo("oA7eG5F1DCF7vW_OnRK5p0B7chHo");
	}
}
