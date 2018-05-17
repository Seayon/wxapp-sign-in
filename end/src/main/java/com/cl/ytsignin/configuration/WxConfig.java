package com.cl.ytsignin.configuration;

import com.cl.ytsignin.utils.CommonUtils;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @author: Seayon
 * @Date: 2018/3/29
 */
@Component
public class WxConfig {
	public final static String GRADE_PUSH_TEMP_ID = CommonUtils.getProperty("wechat.properties", "gradePushTempInfoId");
	public final static String OAUTH_DOMAIN = CommonUtils.getProperty("wechat.properties", "oauthDomain");
	public final static String APPID = CommonUtils.getProperty("wechat.properties", "appid");
	public final static String APPSERCET = CommonUtils.getProperty("wechat.properties", "appSercet");
	public final static String WXAPP_API_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APPSERCET;
}
