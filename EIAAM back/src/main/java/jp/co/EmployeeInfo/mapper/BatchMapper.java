package jp.co.EmployeeInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.EmployeeInfo.model.BatchModel;

@Mapper
public interface BatchMapper {
	//勤務時間情報取得
	@Select("SELECT COUNT(employeeNo) " +
	        "FROM employee_work_time " +
	        "WHERE employeeNo = #{employeeNo} " +
	        "AND yearAndMonth = #{yearAndMonth}")
	int countEmployeeWorkTime(@Param("employeeNo") String employeeNo, 
	                          @Param("yearAndMonth") String yearAndMonth);
	//勤務日数情報を取得
		@Select("SELECT COUNT(employeeNo) " +
		        "FROM work_total_time " +
		        "WHERE employeeNo = #{employeeNo} " +
		        "AND yearAndMonth = #{yearAndMonth}")
		int countEmployeeWorkDays(@Param("employeeNo") String employeeNo, 
		                          @Param("yearAndMonth") String yearAndMonth);
		@Insert({
		    "<script>",
		    "INSERT INTO employee_work_time (",
		    "employeeNo, yearAndMonth, day, week, morningTime, afernoonTime, holidayFlag, ",
		    "workNo, workTime, confirmFlag, workContent, remark, createTime, updateTime",
		    ") VALUES ",
		    "<foreach collection='workTimeList' item='item' separator=','>",
		    "(",
		    "#{item.employeeNo}, #{item.yearAndMonth}, #{item.day}, #{item.week}, #{item.morningTime}, ",
		    "#{item.afernoonTime}, #{item.holidayFlag}, #{item.workNo}, #{item.workTime}, ",
		    "#{item.confirmFlag}, #{item.workContent}, #{item.remark}, NOW(), NOW()",
		    ")",
		    "</foreach>",
		    "</script>"
		})
		int insertWorkTime(@Param("workTimeList") List<BatchModel> workTimeList);
	//勤務日数の作成処理
	@Insert("INSERT INTO work_total_time (" +
	        "employeeNo, yearAndMonth, sumTime, attendanceDays, monthCriteriaWorkTime, createTime, updateTime" +
	        ") VALUES (" +
	        "#{employeeNo}, #{yearAndMonth}, " +
	        "(SELECT SUM(workTime) FROM employee_work_time WHERE employeeNo = #{employeeNo} AND yearAndMonth = #{yearAndMonth}), " + // sumTime: 合计 workTime
	        "(SELECT COUNT(*) FROM employee_work_time WHERE holidayFlag = 0 AND employeeNo = #{employeeNo} AND yearAndMonth = #{yearAndMonth}), " + // attendanceDays: holidayFlag = 0
	        "(SELECT COUNT(*) FROM employee_work_time WHERE holidayFlag = 0 AND employeeNo = #{employeeNo} AND yearAndMonth = #{yearAndMonth}) * 8, " + // monthCriteriaWorkTime: attendanceDays * 8
	        "NOW(), NOW()" +
	        ")")
	int insertWorkTotalTime(@Param("employeeNo") String employeeNo, 
	                        @Param("yearAndMonth") String yearAndMonth);

}
