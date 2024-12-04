package jp.co.EmployeeInfo;	


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jp.co.EmployeeInfo.common.MD5PasswordEncoder;
import jp.co.EmployeeInfo.model.ManageAddUser;

@SpringBootApplication
@MapperScan("jp.co.EmployeeInfo.mapper")
public class EiaamApplication {

	public static void main(String[] args) {
		SpringApplication.run(EiaamApplication.class, args);
	}

}

