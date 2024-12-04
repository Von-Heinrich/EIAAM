package jp.co.EmployeeInfo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {


	public String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String employeeName;
	public String passWord;
	
	
}
