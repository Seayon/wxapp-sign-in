package com.cl.ytsignin.controller;

import com.cl.ytsignin.dao.po.Signevent;
import com.cl.ytsignin.service.DepartService;
import com.cl.ytsignin.service.SignEventService;
import com.cl.ytsignin.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@RestController
@RequestMapping(value = "/signevent")
public class SigneventController {
	@Autowired
	SignEventService signEventService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	List<Signevent> getUserSignevent(HttpServletRequest request) throws Exception {
		return signEventService.getSignevent(AES.Decrypt(request.getHeader("token")));
	}
}
