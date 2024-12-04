package jp.co.EmployeeInfo.common;

import java.time.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class handleTime {

	 public static void main(String[] args) {
		 // 获取当前时间
	        LocalDateTime currentTime = LocalDateTime.now();
	        
	        // 创建日期时间格式化器
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        
	        // 格式化当前时间
	        String formattedTime = currentTime.format(formatter);
	        
	        // 打印格式化后的时间
	        System.out.println("Formatted Time: " + formattedTime);
	    }

	
}
