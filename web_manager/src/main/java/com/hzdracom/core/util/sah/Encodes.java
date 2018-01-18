package com.hzdracom.core.util.sah;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


public class Encodes
{
	private static final int HASH_INTERATIONS = 1024;
	
	/**
	 * Hex解码
	 * 
	 * @param input
	 * @return
	 *         byte[] 返回类型
	 * @throws
	 * @date 2017年1月11日 下午1:56:24
	 */
	public static byte[] decodeHex(String input) {
		try
		{
			return Hex.decodeHex(input.toCharArray());
		}
		catch (DecoderException e)
		{
			//e.printStackTrace();
			return null;
		}
	}
	
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}
	
	
	
	/**
	 *   业主密码加密
	 *   @param plainPassword  明文
	 */
	public static String encodePwd(String plainPassword){
		byte[] salt =  Digests.generateSalt(8);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		String pwd =  Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
		return pwd;
	}
	
	/**
	 * 校验密码是否正确
	 * 
	 * @param plainPassword
	 *            密码明文
	 * @param pwd
	 *            密码密文
	 * @return
	 *         boolean 返回类型
	 * @throws
	 * @date 2017年1月11日 下午2:25:53
	 */
	public static boolean validatePassword(String plainPassword, String pwd) {
		byte[] salt = Encodes.decodeHex(pwd.substring(0, 16));
		System.out.println(salt.length);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return pwd.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}
	
	public static void main(String[] args) {
		
		
		String plainPassword = "12345678";

		System.out.println(validatePassword(plainPassword,encodePwd(plainPassword)));
	}
	
}
