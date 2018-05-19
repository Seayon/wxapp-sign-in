package com.cl.ytsignin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import com.cl.ytsignin.utils.AES;
import com.cl.ytsignin.utils.WeChatUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject getToken(@RequestBody JSONObject jsonObject) throws Exception {
		JSONObject result = new JSONObject();
		String openId = WeChatUtils.code2Session(jsonObject.getString("code")).getString("openid");
		JSONObject tokenJson = new JSONObject();
		tokenJson.put("openId", openId);
		tokenJson.put("time", System.currentTimeMillis());
		tokenJson.put("salt", WxConfig.ENCRYPT_SALT);
		result.put("token", AES.Encrypt(tokenJson.toJSONString()));
		result.put("code", 0);
		return result;
	}
}
