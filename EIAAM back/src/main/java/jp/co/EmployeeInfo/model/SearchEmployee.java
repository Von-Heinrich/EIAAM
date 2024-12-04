package jp.co.EmployeeInfo.model;


public class SearchEmployee {
	
	private String employeeNo;
	private String employeeName;
	private int genderCode;
	private String joinCompanyYear;
	private String joinCompanyMonth;
	private String salary;
	private int age;
	private Integer uAge;
	private Integer lAge;
	private int dependent;
	private String companyMail;
	private String phoneNo;
	private String address;
	private String station;
	private String accountNo;
	private String accountName;

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
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
	public int getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(int genderCode) {
		this.genderCode = genderCode;
	}
	public String getJoinCompanyYear() {
		return joinCompanyYear;
	}
	public void setJoinCompanyYear(String joinCompanyYear) {
		this.joinCompanyYear = joinCompanyYear;
	}
	public String getJoinCompanyMonth() {
		return joinCompanyMonth;
	}
	public void setJoinCompanyMonth(String joinCompanyMonth) {
		this.joinCompanyMonth = joinCompanyMonth;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "SearchEmployee [employeeNo=" + employeeNo + ", employeeName=" + employeeName + ", genderCode="
				+ genderCode + ", joinCompanyYear=" + joinCompanyYear + ", joinCompanyMonth=" + joinCompanyMonth
				+ ", salary=" + salary + ", age=" + age + "]";
	}
	
	public int getDependent() {
		return dependent;
	}
	public void setDependent(int dependent) {
		this.dependent = dependent;
	}
	public String getCompanyMail() {
		return companyMail;
	}
	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getuAge() {
		return uAge;
	}
	public void setuAge(Integer uAge) {
		this.uAge = uAge;
	}
	public Integer getlAge() {
		return lAge;
	}
	public void setlAge(Integer lAge) {
		this.lAge = lAge;
	}
	

}
