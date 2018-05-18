package com.cl.ytsignin.utils;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/3/29
 */
@Service
public class WeChatUtils {
	private final static Logger logger = Logger.getLogger(WeChatUtils.class);
	public static String ACCESS_TOKEN = null;
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

}
