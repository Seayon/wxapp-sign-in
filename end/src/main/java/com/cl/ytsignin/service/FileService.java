package com.cl.ytsignin.service;

import com.cl.ytsignin.dao.po.User;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/29
 */
@Component
public class FileService {
	private final static Logger logger = Logger.getLogger(FileService.class);

	public List<User> extractUserFromExcel(InputStream inputStream) throws InvalidFormatException, IOException {
		List<User> userList = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Row row;
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			User user = new User();
			Cell sID = null;
			try {
				sID = row.getCell(0);
			} catch (NullPointerException e) {
				logger.debug("空行结束");
				break;
			}
			user.setsID(sID.toString());
			user.setName(row.getCell(1).toString());
			user.setClazzNo(row.getCell(2).toString());
			user.setSex(row.getCell(3).toString());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			userList.add(user);
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
