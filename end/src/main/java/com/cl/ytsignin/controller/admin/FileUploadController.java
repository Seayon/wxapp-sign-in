package com.cl.ytsignin.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.dao.po.User;
import com.cl.ytsignin.service.FileService;
import com.cl.ytsignin.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/28
 */
@RestController
@RequestMapping(value = "admin/fileUpload")
public class FileUploadController {
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;
	private final static Logger logger = Logger.getLogger(FileUploadController.class);

	@RequestMapping(value = "", method = RequestMethod.POST)
	public JSONObject upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws InvalidFormatException, IOException {
		JSONObject result = new JSONObject();
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			int iIndex = fileName.lastIndexOf(".");
			String ext = (iIndex < 0) ? "" : fileName.substring(iIndex + 1).toLowerCase();
			if (!"xls,xlsx".contains(ext) || "".contains(ext)) {
				logger.debug(fileName + " 文件类型不是Excel,已忽略");
				result.put("code", -2);
				result.put("msg", "文件类型错误");
			} else {
				List<User> userList = fileService.extractUserFromExcel(file.getInputStream());
				if (userService.insertList(userList)){
					result.put("code", 0);
					result.put("msg", "解析成功");
				}else{
					result.put("code", 0);
					result.put("msg", "解析遇到问题");
				}
			}
		}
		return result;
	}
}
