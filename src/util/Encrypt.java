<<<<<<< HEAD
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encrypt {
	
	public static String getMd5(String chain){
		MessageDigest md5;
		String exitM = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(chain.getBytes());
			byte[] keys = md5.digest();
			exitM = new String(new BASE64Encoder().encode(keys));
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return exitM;
	}
}
=======
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encrypt {
	
	public static String getMd5(String chain){
		MessageDigest md5;
		String exitM = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(chain.getBytes());
			byte[] keys = md5.digest();
			exitM = new String(new BASE64Encoder().encode(keys));
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return exitM;
	}
}
>>>>>>> ccd3b86de4b22e78085efd46995965aa69e96ff9
