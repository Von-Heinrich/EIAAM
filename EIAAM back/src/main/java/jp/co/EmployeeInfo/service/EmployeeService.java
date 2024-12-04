package jp.co.EmployeeInfo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.EmployeeInfo.common.MD5PasswordEncoder;
import jp.co.EmployeeInfo.controller.EmployeeController;
import jp.co.EmployeeInfo.mapper.EmployeeMapper;
import jp.co.EmployeeInfo.model.EmployeeModel;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.pojo.EmployeePojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

public interface EmployeeService {
	static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	ManageAddUser getEmployeeByNo(String employeeNo);

	int deleteEmloyee(ManageAddUser manageAddUser);

	ManageAddUser getEmployeeByName(String employeeName);
	int insertEmployee(ManageAddUser manageAddUser);

	String checkBranchNoByBankCode(String bankNo, String branchNo);

	String checkBranchNameByBankCode(String BankCode, String BranchName);

	int passwordReset(String employeeNo, String newPassword);

	String MaxEmployeeNo();

	boolean passwordReset(String employeeNo, String password, String newPassword);

	EmployeePojo login(String employeeName, String password);

	EmployeeModel login2(String employeeName);

	int updateEmployee(ManageAddUser manageAddUser);

	LocalDateTime getUpdateTimeByEmployeeNo(String employeeNo);
	

	/*LocalDateTime getUpdateTimeByEmployeeNo(String employeeNo);*/
	
	
}

@Service
class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	//修正の検索No
	@Override
	public ManageAddUser getEmployeeByNo(String employeeNo) {
		return employeeMapper.selectEmployee(employeeNo);
	}
	//修正の検索Name
	@Override
	public ManageAddUser getEmployeeByName(String employeeName) {
		return employeeMapper.selectEmployeeName(employeeName);
	}
	// 権限


	@Override
	public EmployeePojo login(String employeeNo, String password) {
		logger.info("------service start-----");
		EmployeePojo employeePojo = employeeMapper.getUser(employeeNo,password);
		
		if(employeePojo !=null) {
			int authority =  employeePojo.getAuthorityCode();
			String getAuthorityPropertie = employeeMapper.getauthorityProperties(authority);
			
			
			employeePojo.setAuthorityProperties(getAuthorityPropertie);
			return employeePojo;
		}else {
			return null;
		}
			
			
		//	String getAuthorityProperties = employeeMapper.getauthorityProperties(authority);
			
			//logger.info("service getAuthorityProperties-----", getAuthorityProperties);
			
			
			
	}

	
	@Override
	public int passwordReset(String employeeNo, String newPassword) {
		
	  int employeeResult = employeeMapper.passwordReset(employeeNo, newPassword);
	  if(employeeResult == 1) {   logger.info("service true employeeResult-----", employeeResult);
		  
	  return employeeResult; 
	  }else {  logger.info("service false employeeResult-----", employeeResult);
		  
	  return 0;
		 }
	}

	@Override
	public EmployeeModel login2(String employeeNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String MaxEmployeeNo () {
		
		String EmployeeMaxNo = employeeMapper.getMaxEmployeeNO();
		
		if(EmployeeMaxNo != null) {
			
			return EmployeeMaxNo;
		}else {
		return null;
		}
		
	}

	@Override
	public boolean passwordReset(String employeeNo, String password, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//銀行Codeで、入力した支店名の存在を確認する、ない場合、error；ある場合：支店番号を検索、フロントに反映
	@Override
	public String checkBranchNameByBankCode(String bankNo, String branchName) {
		
		//DBからの支店名をチェック
		String getBranchName = employeeMapper.getBranchNameByBankCode(bankNo);
		logger.info("------service getBranchName------", getBranchName);		
		
		if( getBranchName.equals(branchName)) {
			
			String branchNo = employeeMapper.getBranchNo(branchName);
			logger.info("------service branchNo------", branchNo);		
			
			if( branchNo != "") {
				return branchNo;
				
			}else {
				return "";
			}		
		}
		return null ;
		
	}
	
	//銀行Codeで、入力した支店番号の存在を確認する、ない場合、error；ある場合：支店名を検索、フロントに反映
	@Override
	public String checkBranchNoByBankCode(String bankNo, String branchNo) {
		
		//DBからの支店番号をチェック
		String checkedBranchNo = employeeMapper.checkBranchNoByBankCode(bankNo);
		logger.info("------service checkedBranchNo------", checkedBranchNo);		
		
		if( branchNo.equals(checkedBranchNo)) {
			
			String branchName = employeeMapper.getBranchName(branchNo);
			logger.info("------service branchName------", branchName);		
			
			if( branchName != "") {
				return branchName;
				
			}else {
				return "";
			}		
		}
		return null ;
	}
	
	
	//employee表にuserを追加
		

		//updateTime
		public LocalDateTime getUpdateTimeByEmployeeNo(String employeeNo) {
		    return employeeMapper.getUpdateTimeByEmployeeNo(employeeNo);
		}
		@Autowired
		private TransactionTemplate transactionTemplate;

		public int insertEmployee(ManageAddUser manageAddUser) {
		    // 加密密码
		    String encryptedPassword = MD5PasswordEncoder.encode(manageAddUser.getFirstPwd());
		    String password = encryptedPassword;

		    try {
		        return transactionTemplate.execute(status -> {
		            try {
		                // 插入 employee 表
		                employeeMapper.employeeInsert(manageAddUser.getEmployeeNo(), manageAddUser.getEmployeeName(),
		                        password, manageAddUser.getAuthorityCode());

		                // 处理薪水
		                String salaryStr = manageAddUser.getSalary();
		                BigDecimal salary = (salaryStr == null || salaryStr.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(salaryStr);

		                // 处理公司邮件
		                String companyMail = manageAddUser.getCompanyMail();
		                String tail = "@lyc.co.jp";
		                String fullMail = companyMail + tail;

		                // 插入 employee_detail 表
		                employeeMapper.employeeDetailInsert(manageAddUser.getEmployeeNo(), manageAddUser.getGenderCode(),
		                        manageAddUser.getAge(), manageAddUser.getJoinConpanyYear(), manageAddUser.getJoinConpanyMonth(),
		                        fullMail, manageAddUser.getPersonalMail(), manageAddUser.getPhoneNo(), manageAddUser.getDependent(), salary);

		                // 插入 account 表
		                employeeMapper.accountInsert(manageAddUser.getEmployeeNo(), manageAddUser.getBankNo(),
		                        manageAddUser.getBranchNo(), manageAddUser.getAccountNo(), manageAddUser.getAccountName());

		                // 插入 address 表
		                employeeMapper.addressInsert(manageAddUser.getEmployeeNo(), manageAddUser.getPostalCode(),
		                        manageAddUser.getPrefectures(), manageAddUser.getAddress(), manageAddUser.getStation());

		                return 1; // 成功返回1
		            } catch (Exception e) {
		            	
		                e.printStackTrace();
		                return 0; // 失败返回0
		            }
		        });
		    } catch (Exception e) {
		        e.printStackTrace();
		        return 0; // 事务失败返回0
		    }
		}
		//修正
		@Transactional
		public int updateEmployee(ManageAddUser manageAddUser) {
		try {
			//employee
			String encryptedPassword =MD5PasswordEncoder.encode(manageAddUser.getFirstPwd());//modify frontline password
			String password =encryptedPassword; 
				
			 employeeMapper.employeeUpdate(manageAddUser.getEmployeeNo(), manageAddUser.getEmployeeName(), password, manageAddUser.getAuthorityCode());
			//employeeDetail
			 String salaryStr = manageAddUser.getSalary();
				BigDecimal salary;
				if (salaryStr == null || salaryStr.isEmpty()) {
				    salary = BigDecimal.ZERO;  // 如果 salary 是空字符串或 null，设为 0
				} else {
				    salary = new BigDecimal(salaryStr);  // 将字符串转换为 BigDecimal
				}
			
			
			String companyMail =manageAddUser.getCompanyMail();
			String tail = "@lyc.co.jp";
			String fullMail = companyMail+tail;
			/*int dependent;
			if(manageAddUser.getDependent() == "") {
				dependent = 0;
			}*/
			 employeeMapper.employeeDetailUpdate(manageAddUser.getEmployeeNo(),manageAddUser.getGenderCode(),manageAddUser.getAge(),manageAddUser.getJoinConpanyYear(),manageAddUser.getJoinConpanyMonth(),fullMail,manageAddUser.getPersonalMail(),
					manageAddUser.getPhoneNo(),manageAddUser.getDependent(),salary);
			 //account
			employeeMapper.accountUpdate(manageAddUser.getEmployeeNo(), manageAddUser.getBankNo(), manageAddUser.getBranchNo(), manageAddUser.getAccountNo(), manageAddUser.getAccountName());
			//address
				employeeMapper.addressUpdate(manageAddUser.getEmployeeNo(), manageAddUser.getPostalCode() ,manageAddUser.getPrefectures(), manageAddUser.getAddress(), manageAddUser.getStation());
		}catch(Exception e) {
			 System.out.println("Sql exception");
			
			    return 0;
			}
				return 1;
			
			
				
			}
		//削除
		@Transactional
		@Override
		public int deleteEmloyee(ManageAddUser manageAddUser) {
			employeeMapper.deleteEmployee(manageAddUser.getEmployeeNo(),manageAddUser.getEmployeeName());
			
			return 1;
		}
		
		
		
		
		
		
		
	

}
