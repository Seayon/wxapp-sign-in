package com.cl.ytsignin.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/3/28
 */
public class CommonUtils {
	public static String getBeanJson(Object obj) {
		Field fields[] = obj.getClass().getDeclaredFields();
		String[] name = new String[fields.length];
		Object[] value = new Object[fields.length];
		StringBuffer strBean = new StringBuffer();
		//strBean.append("{\""+obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1, obj.getClass().getName().length())+"\":[{");
		strBean.append("{");
		try {
			Field.setAccessible(fields, true);
			for (int i = 0; i < name.length; i++) {
				name[i] = fields[i].getName();
				value[i] = fields[i].get(obj);
				strBean.append("\"" + name[i] + "\":\"" + value[i] + "\"");
				if (i < name.length - 1) {
					strBean.append(",");
				}
			}
			strBean.append("}");
			//strBean.append("}]}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strBean.toString();

	}
	/**
	 * 获得指定位数的随机字符串函数
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //随机生成字符串的,length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPKRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 取出文件中的properties值
	 * @param propertiesFile properties文件名称
	 * @param propertyName 需要取的值的key
	 * @return
	 */
	public static String getProperty(String propertiesFile, String propertyName) {
		Properties properties = new Properties();
		InputStream inputStream = CommonUtils.class.getClassLoader().getResourceAsStream(propertiesFile);
		try {
			properties.load(inputStream);
			inputStream.close();
			return properties.getProperty(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公众号的SHA校验使用到的
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static String getSHA1Verify(String a, String b, String c) {
		String digest = null;
		String str[] = {a, b, c};
		Arrays.sort(str);
		String bigStr = str[0] + str[1] + str[2];
		digest = CommonUtils.getSha1(bigStr);
		return digest;
	}

	/**
	 * 根据输入的字符串获取SHA1值
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		/**
		 * getSha1 根据输入的字符串获取SHA1值
		 * @return java.lang.String 返回的SHA1编码值
		 * @throw
		 * @since 2017-10-17 15:26

		 * @param str 需要SHA1计算的字符串
		 **/
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	public static String genHMAC(String data, String key) {
		byte[] result = null;
		try {
			//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
			SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
			//生成一个指定 Mac 算法 的 Mac 对象
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			//用给定密钥初始化 Mac 对象
			mac.init(signinKey);
			//完成 Mac 操作
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = Base64.encodeBase64(rawHmac);

		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		} catch (InvalidKeyException e) {
			System.err.println(e.getMessage());
		}
		if (null != result) {
			return new String(result);
		} else {
			return null;
		}
	}


	/**
	 * 计算一串字符串的MD5散列值
	 * @param plainText 需要计算的字符串
	 * @return
	 */
	public static String md5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

	/**
	 * 简单的正则匹配函数
	 * @param source
	 * @param pat
	 * @return
	 */
	public static String regex(String source, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}

	/**
	 * 优化的预编译Pattern的正则匹配函数
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String regex(String source, Pattern pattern) {
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}

	/**
	 * 流转字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	public static boolean saveImageFromStream(String filePath, String fileName, InputStream inputStream) {
		/**
		 * saveImageFromStream
		 * @return boolean
		 * @throw
		 * @since 2017-10-15 20:53
		 * @param filePath 文件存储路径
		 * @param fileName 文件名称
		 * @param inputStream 输入流参数

		 **/
		boolean saveFlag = false;
		if (inputStream == null) {
			return saveFlag;
		}
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(filePath, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] imgData = new byte[1024];
			int length = 0;
			fileOutputStream = new FileOutputStream(filePath + fileName);
			while ((length = inputStream.read(imgData)) != -1) {
				fileOutputStream.write(imgData, 0, length);
			}
			saveFlag = true;
		} catch (IOException e) {
			e.printStackTrace();
			saveFlag = false;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return saveFlag;
	}
}
