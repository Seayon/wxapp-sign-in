package com.cl.ytsignin.utils;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Version 1.0
 * @author: Seayon
 * @Date: 2018/3/29
 */
@Service
public class WeChatUtils {
	private final static Logger logger = Logger.getLogger(WeChatUtils.class);
	public static String ACCESS_TOKEN = null;
	private final static String DEFAULT_COLOR = "#18BC9C";

	static {
		updateAccessToken();
	}

	public static String builderOauthUrl(String targetPage) {
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WxConfig.APPID + "&redirect_uri=http://" + WxConfig.OAUTH_DOMAIN + "/oauth&response_type=code&scope=snsapi_userinfo&state=" + targetPage + "#wechat_redirect";
	}

	public static String updateAccessToken() {
		logger.debug("开始更新AccessToken");
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (responseBody != null) {
				responseBody.close();
			}
		}
		logger.debug("更新AccessToken结束");
		return ACCESS_TOKEN;
	}

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

}
