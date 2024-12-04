package jp.co.EmployeeInfo.mapper;

import jp.co.EmployeeInfo.model.EmployeeModel;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.pojo.EmployeePojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

	
@Mapper
public interface EmployeeMapper {
	
	  /*@Select("SELECT * FROM employee WHERE employeeNo = #{employeeNo}") 
	  EmployeeModel getEmployeeByNo(String employeeNo);*/
	  
	  //ログイン画面
		/*
		 * @Select("SELECT * FROM employee WHERE employeeNo = #{employeeNo} and password = #{password}"
		 * ) Employee getUser(String employeeNo, String password);
		 */
	  //@Select("SELECT * FROM employee WHERE employeeNo = #{employeeNo} and password = #{password}")
	  //EmployeePojo getUser(@Param("employeeName") String employeeName,@Param("password") String password);
	  
	  
	  @Select("SELECT * FROM employee WHERE employeeNo = #{employeeNo} and password = #{password}")
	  EmployeePojo getUser(@Param("employeeNo") String employeeNo,@Param("password") String password);
	  
	  
	  //authorityPropertiesを検索
	  @Select("SELECT authorityProperties FROM authority_master JOIN employee ON authority_master.authorityNo = employee.authorityCode WHERE employeeNo = #{username} and password = #{password}")
	  EmployeeModel findByUsername(String username,String password );
	  
	  //@Update("UPDATE employee SET password = #{newPassword} ,updateTime = NOW() WHERE employeeNo = #{employeeNo}")
	  int passwordReset(@Param("employeeNo") String employeeNo,@Param("newPassword") String newPassword);
	  
	  @Select("SELECT authorityProperties FROM authority_master WHERE authorityNo = #{authorityCode}") 
	  String getauthorityProperties(int authorityCode);
	  
	  
	  @Select("SELECT CONCAT(LEFT(employeeNo, 3), LPAD(SUBSTRING(employeeNo, 4) + 1, LENGTH(employeeNo) - 3, '0') ) AS newEmployeeNo FROM employee ORDER BY employeeNo DESC LIMIT 1")
	  String getMaxEmployeeNO();
	  
	  @Select("SELECT bankBranchName FROM bank_branch_master WHERE bankCode = #{bankNo}")
	  String getBranchNameByBankCode(String bankNo);
	  
	  @Select("SELECT bankBranchNo FROM bank_branch_master WHERE bankBranchName = #{branchName}")
	  String getBranchNo(String branchName);
	  
	  
	  @Select("SELECT bankBranchNo FROM bank_branch_master WHERE bankCode = #{bankNo}")
	  String checkBranchNoByBankCode(String bankNo);
	  
	  @Select("SELECT bankBranchName FROM bank_branch_master WHERE bankBranchNo = #{branchNo}")
	  String getBranchName(String branchNo);
	  //追加
		 /*@Insert("INSERT INTO employee (employeeNo, employeeName, password, authorityCode, updateTime, createTime) VALUES "
		  		+ "(#{employeeNo}, #{employeeName}, #{firstPwd}, #{authorityCode}, NOW(), NOW())") */
	  
		  //employee
		  @Insert( "INSERT INTO employee (employeeNo,employeeName,password,authorityCode,updateTime,createTime) "+
			        "VALUES (#{employeeNo}, #{employeeName}, #{firstPwd}, #{authorityCode}, NOW(), NOW());")
		  int employeeInsert(@Param("employeeNo") String employeeNo, @Param("employeeName") String employeeName, @Param("firstPwd") String firstPwd,
				   @Param("authorityCode") int authorityCode);
		  //employeeDetail
		  @Insert("INSERT INTO employee_detail (employeeNo,genderCode, age, JoiningCompanyOfYear, intoCompanyOfMonth,companyMail,personalMail,phoneNo,dependentsPerson,salary,updateTime,createTime) "+
			        "VALUES (#{employeeNo},#{genderCode}, #{age}, #{joinConpanyYear}, #{joinConpanyMonth},#{companyMail},#{personalMail},#{phoneNo},#{dependent},#{salary},NOW(), NOW());")
		  int employeeDetailInsert(@Param("employeeNo")String employeeNo,@Param("genderCode")int genderCode,@Param("age")int age,@Param("joinConpanyYear")String joinConpanyYear,
				  @Param("joinConpanyMonth")String joinConpanyMonth,@Param("companyMail")String companyMail,@Param("personalMail")String personalMail,@Param("phoneNo")String phoneNo,@Param("dependent")int dependent,@Param("salary")BigDecimal salary);
		  //account
		  @Insert(  "INSERT INTO account_information (employeeNo,bankCode,bankBranchCode,accountNo,accountName,updateTime,createTime) "+
				  "VALUES (#{employeeNo}, #{bankNo}, #{branchNo}, #{accountNo},#{accountName},NOW(), NOW());")
		  int accountInsert(@Param("employeeNo")String employeeNo,@Param("bankNo")String bankNo,@Param("branchNo")String branchNo,@Param("accountNo") String accountNo,@Param("accountName")String accountName);
		  //address
		  @Insert("INSERT INTO address_information (employeeNo,postalCode,firstHalfOfAddress, secondHalfOfAddress,nearestStation,updateTime,createTime) "+
			        "VALUES (#{employeeNo}, #{postalCode}, #{prefectures},#{address},#{station}, NOW(), NOW());")
		  int addressInsert(@Param("employeeNo")String employeeNo ,@Param("postalCode")String postalCode,@Param("prefectures")String prefectures,@Param("address")String address,@Param("station")String station);
		//修正の検索employeeNo
		  @Select("""
			        SELECT 
			            e.employeeNo,e.employeeName,e.password AS firstPwd,e.authorityCode,e.updateTime,e.createTime,  
			            ed.genderCode,ed.age,ed.JoiningCompanyOfYear AS joinConpanyYear,ed.intoCompanyOfMonth AS joinConpanyMonth,ed.companyMail,
			            ed.personalMail,ed.phoneNo,ed.dependentsPerson AS dependent,ed.salary,
			            ai.postalCode,ai.firstHalfOfAddress AS prefectures,ai.secondHalfOfAddress AS address,ai.nearestStation AS station,
			            ac.bankCode AS bankNo,ac.bankBranchCode AS branchNo,ac.accountNo,ac.accountName,
			            bm.bankName,
			            bbm.bankBranchName AS branchName
			        FROM employee e
			        LEFT JOIN employee_detail ed ON e.employeeNo = ed.employeeNo
			        LEFT JOIN address_information ai ON e.employeeNo = ai.employeeNo
			        LEFT JOIN account_information ac ON e.employeeNo = ac.employeeNo
			        LEFT JOIN bank_master bm ON ac.bankCode = bm.bankNo
			        LEFT JOIN bank_Branch_master bbm ON ac.bankBranchCode = bbm.bankBranchNo AND ac.bankCode = bbm.bankCode
			        WHERE e.employeeNo = #{employeeNo}
			    """)
		  ManageAddUser selectEmployee(String employeeNo);
		  //修正の検索employeeNameS
		  @Select("""
			        SELECT 
			            e.employeeNo,e.employeeName,e.password AS firstPwd,e.authorityCode,e.updateTime,e.createTime,  
			            ed.genderCode,ed.age,ed.JoiningCompanyOfYear AS joinConpanyYear,ed.intoCompanyOfMonth AS joinConpanyMonth,ed.companyMail,
			            ed.personalMail,ed.phoneNo,ed.dependentsPerson AS dependent,ed.salary,
			            ai.postalCode,ai.firstHalfOfAddress AS prefectures,ai.secondHalfOfAddress AS address,ai.nearestStation AS station,
			            ac.bankCode AS bankNo,ac.bankBranchCode AS branchNo,ac.accountNo,ac.accountName,
			            bm.bankName,
			            bbm.bankBranchName AS branchName
			        FROM employee e
			        LEFT JOIN employee_detail ed ON e.employeeNo = ed.employeeNo
			        LEFT JOIN address_information ai ON e.employeeNo = ai.employeeNo
			        LEFT JOIN account_information ac ON e.employeeNo = ac.employeeNo
			        LEFT JOIN bank_master bm ON ac.bankCode = bm.bankNo
			        LEFT JOIN bank_Branch_master bbm ON ac.bankBranchCode = bbm.bankBranchNo AND ac.bankCode = bbm.bankCode
			        WHERE e.employeeName = #{employeeNameS}
			    """)
		  ManageAddUser selectEmployeeName(String employeeNameS);
		  //修正
		 
		  @Select("SELECT updateTime FROM employee WHERE employeeNo = #{employeeNo}")
		    LocalDateTime getUpdateTimeByEmployeeNo(@Param("employeeNo") String employeeNo);
		// employee
		  @Update("UPDATE employee SET employeeName = #{employeeName}, password = #{firstPwd}, authorityCode = #{authorityCode}, updateTime = NOW() " +
		          "WHERE employeeNo = #{employeeNo};")
		  int employeeUpdate(@Param("employeeNo") String employeeNo, @Param("employeeName") String employeeName, @Param("firstPwd") String firstPwd,
		                     @Param("authorityCode") int authorityCode);

		  // employeeDetail
		  @Update("UPDATE employee_detail SET genderCode = #{genderCode}, age = #{age}, JoiningCompanyOfYear = #{joinConpanyYear}, " +
		          "intoCompanyOfMonth = #{joinConpanyMonth}, companyMail = #{companyMail}, personalMail = #{personalMail}, " +
		          "phoneNo = #{phoneNo}, dependentsPerson = #{dependent}, salary = #{salary}, updateTime = NOW() " +
		          "WHERE employeeNo = #{employeeNo};")
		  int employeeDetailUpdate(@Param("employeeNo") String employeeNo, @Param("genderCode") int genderCode, @Param("age") int age,
		                           @Param("joinConpanyYear") String joinConpanyYear, @Param("joinConpanyMonth") String joinConpanyMonth,
		                           @Param("companyMail") String companyMail, @Param("personalMail") String personalMail, 
		                           @Param("phoneNo") String phoneNo, @Param("dependent") int dependent, 
		                           @Param("salary") BigDecimal salary);

		  // account
		  @Update("UPDATE account_information SET bankCode = #{bankNo}, bankBranchCode = #{branchNo}, accountNo = #{accountNo}, " +
		          "accountName = #{accountName}, updateTime = NOW() WHERE employeeNo = #{employeeNo};")
		  int accountUpdate(@Param("employeeNo") String employeeNo, @Param("bankNo") String bankNo, @Param("branchNo") String branchNo,
		                    @Param("accountNo") String accountNo, @Param("accountName") String accountName);

		  // address
		  @Update("UPDATE address_information SET postalCode = #{postalCode}, firstHalfOfAddress = #{prefectures}, " +
		          "secondHalfOfAddress = #{address}, nearestStation = #{station}, updateTime = NOW() WHERE employeeNo = #{employeeNo};")
		  int addressUpdate(@Param("employeeNo") String employeeNo, @Param("postalCode") String postalCode, 
		                    @Param("prefectures") String prefectures, @Param("address") String address, 
		                    @Param("station") String station);
		  //削除
			@Delete("""
				    DELETE e, ed, ai, ac
				    FROM employee e
				    LEFT JOIN employee_detail ed ON e.employeeNo = ed.employeeNo
				    LEFT JOIN address_information ai ON e.employeeNo = ai.employeeNo
				    LEFT JOIN account_information ac ON e.employeeNo = ac.employeeNo
				    WHERE e.employeeNo = #{employeeNo} OR e.employeeName = #{employeeNameS};
				    """)
				int deleteEmployee(@Param("employeeNo") String employeeNo, @Param("employeeNameS") String employeeNameS);
			
}
		 