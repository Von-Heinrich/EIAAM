package jp.co.EmployeeInfo.pojo;

import lombok.Getter;
import lombok.Setter;


public class EmployeePojo {
 
	public String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String employeeName;
	public String password;
	public int authorityCode;
	public String updateTime;
	public String createTime;
	public String employeeNameS;
	
	

	public String getEmployeeNameS() {
		return employeeNameS;
	}
	public void setEmployeeNameS(String employeeNameS) {
		this.employeeNameS = employeeNameS;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setAuthorityProperties(String getAuthorityProperties) {
		// TODO Auto-generated method stub
		
	}
		
		
	
}
