package com.example.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

public class StringUtil {
	public static void main(String[] args) {
//		String s = deleteRepeat("1,2,3,2,3,4,5,5");
	}
	
	/**
	 * 【异常处理】Incorrect string value: '\xF0\x90\x8D\x83...' for column... 
	 * Emoji表情字符过滤的Java实现
	 * 替换4个字节的utf-8编码
	 * @param msg
	 * @return
	 */
	public static String IncorrectString(String msg){
		if(msg!=null)
			msg = msg.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
//			msg = msg.replaceAll("[^\\u0000-\\uFFFF]", "");
		return msg;
	}
	public static String getImageStr(String imgFile) {
//        String imgFile = "e:/11.png";
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
//        encoder.encode(data).replaceAll("\r\n", "")
        return encoder.encode(data);
    }
	
	public static String encode(String str){
		if(str==null) return str;
		try {
			str = URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	public static String decode(String str){
		if(str==null) return str;
		try {
			str = URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 
	 */
	public static String formatStr(String str){
		if(str==null) return str;
		if(str != null) {
			try {
				str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	public static int getStart(String start){
		int s = 0;
		try {
			s = Integer.parseInt(start);
		} catch (NumberFormatException e) {
			System.out.println("getStart java.lang.NumberFormatException");
		}
		return s;
	}
	public static int getLimit(String limit){
		int s = 15;
		try {
			s = Integer.parseInt(limit);
		} catch (NumberFormatException e) {
			System.out.println("getLimit java.lang.NumberFormatException");
		}
		return s;
	}
	/**
	 * 字符串去掉重复 1,2,3,2,3,4,5,5
	 * @param str
	 * @return
	 */
	public static String deleteRepeat(String str){
		if(str==null) return str;
		String ag[] = str.split(",");
		Set<String> set = new HashSet<String>();
		for(int i=0;i<ag.length;i++){
			set.add(ag[i]);
		}
		StringBuffer sb = new StringBuffer();
		for(String r : set) {
		      sb.append(r);
		      sb.append(",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	/**
	 * 字符串首字母大写
	 * @param 字符串
	 * @return 首字母大写的字符串
	 */
	public static String toFirstUpperCase(String str) {
		if(str == null || str.length() < 1) {
			return "";
		}
		String start = str.substring(0,1).toUpperCase();
		String end = str.substring(1, str.length());
		return start + end;
	}
	/**
	 * 类型转换
	 */
	public static Object castString(String value, Class<?> cls) {
		String name = cls.getSimpleName();
		Object cast = value;
		if(name.equalsIgnoreCase("Integer")) {
			cast = Integer.parseInt(cast.toString().equals("")?"0":cast.toString());
		}
		if(name.equalsIgnoreCase("Long")) {
			cast = Long.parseLong(cast.toString());
		}
		if(name.equalsIgnoreCase("Short")) {
			cast = Short.parseShort(cast.toString());
		}
		if(name.equalsIgnoreCase("Float")) {
			cast = Float.parseFloat(cast.toString());
		}
		if(name.equalsIgnoreCase("Double")) {
			cast = Double.parseDouble(cast.toString());
		}
		if(name.equalsIgnoreCase("Boolean")) {
			cast = Boolean.parseBoolean(cast.toString());
		}
		return cast;
	}
	
	/**
	 * 去掉空格
	 * @param str
	 * @return
	 */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
