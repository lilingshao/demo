package com.example.demo.redisQuene;

import java.io.*;

public class ObjectUtil {

	/**
	 * 对象转byte[]
	 * @param boj
	 * @return
	 * @throws IOException
	 */
	public static byte[] object2Bytes(Object boj) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(boj);
		byte[] bytes = bo.toByteArray();
		bo.close();
		oo.close();
		return bytes;
	}

	/**
	 * byte[]转对象
	 * @param bytes
	 * @return
	 */
	public static Object bytes2Object(byte[] bytes) throws Exception{
		ByteArrayInputStream in=new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		return oi.readObject();
	}
}
