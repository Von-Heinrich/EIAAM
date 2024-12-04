package jp.co.EmployeeInfo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5PasswordEncoder {
	
	public static String encode(String password) {
        try {
            // 创建MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // 将密码转换为字节数组，并进行MD5哈希计算
            byte[] digest = md.digest(password.getBytes());
            
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
	}
	


}
