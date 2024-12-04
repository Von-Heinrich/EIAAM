package jp.co.EmployeeInfo.model;

import java.time.LocalDateTime;

public class ManageAddUser {
	
	private String employeeNo;
	private String maxEmployeeNo;
	private String employeeName;
	private String gender;
	private String joinConpanyYear;
	private String joinConpanyMonth;
	private int dependent;
	private String salary;
	private String firstPwd;
	private String companyMail;
	private String personalMail;
	
	private String authority;
	private int authorityCode;
	
	private String postalCode;
	private String prefectures;
	private String address;
	
	private String station;
	private String bankName;
	private String bankNo;
	private String branchName;
	private String branchNo;
	private String accountNo;
	private String accountName;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
	//corrected 
    private int genderCode;	
    private int age;
   
	
    //排他参数
    public int unique;
    public String getPhoneNo() {
		return phoneNo;
	}
	private String phoneNo;
    private int bankCode;
   

	
    public int getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(int genderCode) {
		this.genderCode = genderCode;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJoinConpanyYear() {
		return joinConpanyYear;
	}
	public void setJoinConpanyYear(String joinConpanyYear) {
		this.joinConpanyYear = joinConpanyYear;
	}
	public String getJoinConpanyMonth() {
		return joinConpanyMonth;
	}
	public void setJoinConpanyMonth(String joinConpanyMonth) {
		this.joinConpanyMonth = joinConpanyMonth;
	}
	public int getDependent() {
		return dependent;
	}
	public void setDependent(int dependent) {
		this.dependent = dependent;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getFirstPwd() {
		return firstPwd;
	}
	public void setFirstPwd(String firstPwd) {
		this.firstPwd = firstPwd;
	}
	public String getCompanyMail() {
		return companyMail;
	}
	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}
	public String getPersonalMail() {
		return personalMail;
	}
	public void setPersonalMail(String personalMail) {
		this.personalMail = personalMail;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPrefectures() {
		return prefectures;
	}
	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String account) {
		this.accountName = account;
	}
	
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
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
	public String getMaxEmployeeNo() {
		return maxEmployeeNo;
	}
	public void setMaxEmployeeNo(String maxEmployeeNo) {
		this.maxEmployeeNo = maxEmployeeNo;
	}
	//排他参数
	public int getUnique() {
		return unique;
	}
	public void setUnique(int unique) {
		this.unique = unique;
	}
	
}
