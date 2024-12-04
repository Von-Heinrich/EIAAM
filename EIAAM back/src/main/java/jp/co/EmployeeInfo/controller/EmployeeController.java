package jp.co.EmployeeInfo.controller;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.EmployeeInfo.common.MD5PasswordEncoder;
import jp.co.EmployeeInfo.model.EmployeeModel;
import jp.co.EmployeeInfo.model.LoginRequest;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.pojo.EmployeePojo;
import jp.co.EmployeeInfo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	//修正の検索No
	@PostMapping("/employeeNo")
	public ManageAddUser getEmployeeByNo(@RequestBody EmployeePojo employeeRequest) {
		/*logger.info("Entered getEmployeeByNo method with employeeNo: {}", employeeNo);

		ManageAddUser manageAddUser = employeeService.getEmployeeByNo(employeeNo);
		logger.info("Exiting getEmployeeByNo method with employee: {}", manageAddUser);*/
		 String employeeNo = employeeRequest.getEmployeeNo();
		    //String employeeName = employeeRequest.getEmployeeName();
		    
		    logger.info("Entered getEmployeeByNo method with employeeNo: {} and employeeName: {}", employeeNo);

		    ManageAddUser manageAddUser = employeeService.getEmployeeByNo(employeeNo);
		    logger.info("Exiting getEmployeeByNo method with employee: {}", manageAddUser);
		if(manageAddUser.getEmployeeNo() == null ) {
			return null;
		}else {
			return manageAddUser;
		}
		
	}
	//修正の検索Name
	@PostMapping("/employeeNameS")
	public ManageAddUser getEmployeeByName(@RequestBody EmployeePojo employeeRequest) {
		/*logger.info("Entered getEmployeeByNo method with employeeNo: {}", employeeNo);

		ManageAddUser manageAddUser = employeeService.getEmployeeByNo(employeeNo);
		logger.info("Exiting getEmployeeByNo method with employee: {}", manageAddUser);*/
		 String employeeName = employeeRequest.getEmployeeNameS();
		    //String employeeName = employeeRequest.getEmployeeName();
		    
		    logger.info("Entered getEmployeeByName method with employeeNo: {} and employeeName: {}", employeeName);

		    ManageAddUser manageAddUser = employeeService.getEmployeeByName(employeeName);
		    logger.info("Exiting getEmployeeByNo method with employee: {}", manageAddUser);
		if(manageAddUser.getEmployeeName() == null ) {
			return null;
		}else {
			return manageAddUser;
		}
		
	}
	  @PostMapping("/login") 
	  public EmployeePojo login(@RequestBody LoginRequest loginRequest) { 
		 System.out.println(loginRequest.getUsername()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		  String employeeName = loginRequest.getUsername();
		  String password = loginRequest.getPassword();
		  String  employeeNo = employeeName;
	  
	  logger.info("--1-- employeeNo: {}", employeeName);
	  logger.info("--2-- password: {}", password);
	  
	  //对密码进行MD5加密 
	  String encryptedPassword = MD5PasswordEncoder.encode(password);
	  logger.info("--加密Password-- Password: {}" + encryptedPassword);
	  password = encryptedPassword;
	  
	  EmployeePojo employeeList = employeeService.login(employeeNo, password);
	if(employeeList != null) {
		  return employeeList; 

	}
	/*
	 * if (employeeList != null) {
	 * 
	 * return employeeList;
	 * 
	 * }
	 */else { 
			  
		  return null; 
		 }	 
	  }
	 
		
	@PostMapping("/PasswordReset")
	public int PasswordReset(@RequestBody EmployeeModel employeeModel) {
		String employeeNo = employeeModel.getEmployeeNo();
		//String password = employee.getPassword();
		String newPassword = employeeModel.getNewPassword();
		
		
		logger.info("--PasswordReset-- username: {}", employeeNo);
		//logger.info("--PasswordReset-- password: {}", password);
		logger.info("--PasswordReset-- newPassword: {}", newPassword);
		
		//对新登录密码进行MD5加密
		String encryptedNewPassword = MD5PasswordEncoder.encode(newPassword);
		logger.info("--encryptedNewPassword444444-- password: {}" + encryptedNewPassword);
		newPassword = encryptedNewPassword;
		logger.info("--newPassword444444-- " + newPassword);
		
	  
		int employeeUpdate = employeeService.passwordReset(employeeNo,newPassword); 
		
		logger.info("--employeeUpdate---" , employeeUpdate); 
			
		if(employeeUpdate == 1) { 
			return 1; 
			}else {
				return 0; }
		
		/*
		 * //DBから旧パスワードを取得 Employee employee_list = employeeService.getPwd(employeeNo,
		 * password); //String DB_OldPwdMd2 = employee_list.getPassword(); String
		 * DB_OldPwdMd = employee_list.getPassword(); logger.info("--DB_OldPwdMd--" +
		 * DB_OldPwdMd); logger.info("--password--" + password);
		 * //logger.info(DB_OldPwdMd, "--DB_OldPwdMd" + encryptedPassword ==
		 * DB_OldPwdMd);
		 */
		/*
		 * if(password.equals(DB_OldPwdMd)) {
		 * 
		 * boolean employeeUpdate = employeeService.passwordReset(employeeNo, password,
		 * newPassword); //employeeService.passwordReset(employeeNo, password,
		 * newPassword); logger.info("--employeeUpdate---" , employeeUpdate); if
		 * (employeeUpdate) { return true; }else { return false; } } //else { return
		 * "Fail"; } return false;
		 */
	}
	
	
	@PostMapping("/getMaxNo") 
	public String getMaxNo() { 
	
	String MaxEmployeeNo = employeeService.MaxEmployeeNo();
	  
	  logger.info("------MaxEmployeeNo------", MaxEmployeeNo);		
		  if (MaxEmployeeNo != null) {
		  return MaxEmployeeNo; }
		  else { return null; } 
	  }
	
	
	
	//支店名/支店番号　handleBranchName
	@PostMapping("/handleBranchName") 
	public String getBranchNo(@RequestBody EmployeeModel employeeModel) { 
		
		String bankNo = employeeModel.getBankNo();
		String branchName = employeeModel.getBranchName();
		
		logger.info("bankNo: ", bankNo);
		logger.info("branchName: {}", branchName);
		  
	
		String gotBranchNo = employeeService.checkBranchNameByBankCode(bankNo , branchName);
	  	
		
		logger.info("------gotBranchNo------", gotBranchNo);		
	
		  if (gotBranchNo != null) {
		  return gotBranchNo; }
		  else { return null; } 
	  }
	
	
	//支店名/支店番号　handleBranchNo
	@PostMapping("/handleBranchNo") 
	public String getBranchName(@RequestBody EmployeeModel employeeModel) { 
		
		String bankNo = employeeModel.getBankNo();
		String branchNo = employeeModel.getBranchNo();
		
		logger.info("bankNo: ", bankNo);
		logger.info("branchNo: {}", branchNo);
		  
	
		String gotBranchName = employeeService.checkBranchNoByBankCode(bankNo , branchNo);
	  	
		
		logger.info("------gotBranchNo------", gotBranchName);		
	
		  if (gotBranchName != null) {
		  return gotBranchName; }
		  else { return null; } 
	  }
	
	
	
	@PostMapping("/insertEmployee") 
	public int getInsetResult(@RequestBody ManageAddUser manageAddUser) { 
		
		String employeeNo = manageAddUser.getEmployeeNo();
		String employeeName = manageAddUser.getEmployeeName();
		
		logger.info("employeeNo: ......", employeeNo);
		logger.info("employeeName: ......", employeeName);
		  
	
		int gotInsertFromBack = employeeService.insertEmployee(manageAddUser);
		
		logger.info("------gotInsertFromBack------", gotInsertFromBack);		
	
		 
			  
		  return gotInsertFromBack; }
		  //else { return 0; } 
	  
	//修正
	@PostMapping("/updateEmployee") 
	public int getUpdateResult(@RequestBody ManageAddUser manageAddUser) { 
		LocalDateTime clientUpdateTime = manageAddUser.getUpdateTime();
		String employeeNo = manageAddUser.getEmployeeNo();
		String employeeName = manageAddUser.getEmployeeName();
		LocalDateTime dbUpdateTime = employeeService.getUpdateTimeByEmployeeNo(employeeNo);
		
		 if (!dbUpdateTime.isEqual(clientUpdateTime)) {
			 System.out.println("排他");
			 return 3;
	        }
		 int gotUpdateFromBack = employeeService.updateEmployee(manageAddUser);
		
		logger.info("------gotUpdateFromBack------", gotUpdateFromBack);		
	
		
			  
		  return gotUpdateFromBack; }
		 
	  
	//削除
	@PostMapping("/deleteEmployee") 
	public int getDeleteResult(@RequestBody ManageAddUser manageAddUser) {
		String employeeNo = manageAddUser.getEmployeeNo();
		String employeeName = manageAddUser.getEmployeeName();
		int gotDeleteFromBack = employeeService.deleteEmloyee(manageAddUser);
		
		logger.info("------gotDeleteFromBack------", gotDeleteFromBack);		
	
		  if (gotDeleteFromBack == 1) {
			  
		  return gotDeleteFromBack; }
		  else { return 0; } 
	}
	
	
}
