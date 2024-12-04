package jp.co.EmployeeInfo.model;

import java.time.LocalDateTime;

public class BatchModel {
	private String employeeNo;
	private String yearAndMonth;
	private String day;
	private String week;
	private String morningTime;
	private String afernoonTime;
	private String workContent;
	private String remark;
	private Float sumTime;
	private Integer attendanceDays;//重命名
	private Integer monthCriteriaWorkTime;//重命名
	private Boolean holidayFlag;
	private Integer workNo;
	private Float workTime;
	private Boolean confirmFlag;
	private LocalDateTime updateTime;
	private LocalDateTime createTime;
	private int holidayResult; // 0: 非假日, 1: 周末, 2: 祝日
    public int getHolidayResult() {
		return holidayResult;
	}
	public void setHolidayResult(int holidayResult) {
		this.holidayResult = holidayResult;
	}
	public int getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	private int weekDay; 
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMorningTime() {
		return morningTime;
	}
	public void setMorningTime(String morningTime) {
		this.morningTime = morningTime;
	}
	public String getAfernoonTime() {
		return afernoonTime;
	}
	public void setAfernoonTime(String afernoonTime) {
		this.afernoonTime = afernoonTime;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Float getSumTime() {
		return sumTime;
	}
	public void setSumTime(Float sumTime) {
		this.sumTime = sumTime;
	}
	public Integer getAttendanceDays() {
		return attendanceDays;
	}
	public void setAttendanceDays(Integer attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	public Integer getMonthCriteriaWorkTime() {
		return monthCriteriaWorkTime;
	}
	public void setMonthCriteriaWorkTime(Integer monthCriteriaWorkTime) {
		this.monthCriteriaWorkTime = monthCriteriaWorkTime;
	}
	public Boolean getHolidayFlag() {
		return holidayFlag;
	}
	public void setHolidayFlag(Boolean holidayFlag) {
		this.holidayFlag = holidayFlag;
	}
	public Integer getWorkNo() {
		return workNo;
	}
	public void setWorkNo(Integer workNo) {
		this.workNo = workNo;
	}
	public Float getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Float workTime) {
		this.workTime = workTime;
	}
	public Boolean getConfirmFlag() {
		return confirmFlag;
	} 
	public void setConfirmFlag(Boolean confirmFlag) {
		this.confirmFlag = confirmFlag;
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
	
	

}
