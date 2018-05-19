package com.cl.ytsignin.configuration;

import com.cl.ytsignin.utils.CommonUtils;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/3/29
 */
@Component
public class WxConfig {
	public final static String ENCRYPT_SALT = CommonUtils.getRandomString(16);
	public final static String APPID = CommonUtils.getProperty("wechat.properties", "appid");
	public final static String APPSERCET = CommonUtils.getProperty("wechat.properties", "appSercet");
	public final static String WXAPP_API_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APPSERCET;
}
