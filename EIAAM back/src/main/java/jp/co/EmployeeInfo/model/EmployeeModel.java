package jp.co.EmployeeInfo.model;

import java.time.LocalDateTime;

public class EmployeeModel {
    private String employeeNo;
    private String employeeName;
    private String password;
    private String newPassword;
    //private String authorityCo;
    private int authorityCode;
    private int authorityNo;
    private String authorityProperties;
    
    private String bankName;
    private String bankNo;

    private String branchNo;
    private String branchName;
    private String bankCode;
    
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
 public String getEmployeeNo() {
  return employeeNo;
 }
 public void setEmployeeNo(String employeeNo) {
  this.employeeNo = employeeNo;
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

	/*
	 * public String getAuthorityCo() { return authorityCo; } public void
	 * setAuthorityCo(String authorityCo) { this.authorityCo = authorityCo; }
	 */
 public LocalDateTime getUpdateTime() {
  return updateTime;
 }
 public void setUpdateTime(LocalDateTime updateTime) {
  this.updateTime = updateTime;
 }
 public LocalDateTime getCreateTime() {
  return createTime;
 }
 public void setCreateTime(LocalDateTime createTime) {
  this.createTime = createTime;
 }
public int getAuthorityCode() {
	return authorityCode;
}
public void setAuthorityCode(int authorityCode) {
	this.authorityCode = authorityCode;
}
public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}
public int getAuthorityNo() {
	return authorityNo;
}
public void setAuthorityNo(int authorityNo) {
	this.authorityNo = authorityNo;
}
public String getAuthorityProperties() {
	return authorityProperties;
}
public void setAuthorityProperties(String authorityProperties) {
	this.authorityProperties = authorityProperties;
}
public String getBankNo() {
	return bankNo;
}
public void setBankNo(String bankNo) {
	this.bankNo = bankNo;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getBankCode() {
	return bankCode;
}
public void setBankCode(String bankCode) {
	this.bankCode = bankCode;
}
public String getBranchNo() {
	return branchNo;
}
public void setBranchNo(String branchNo) {
	this.branchNo = branchNo;
}
public String getBranchName() {
	return branchName;
}
public void setBranchName(String branchName) {
	this.branchName = branchName;
}

    
 
}