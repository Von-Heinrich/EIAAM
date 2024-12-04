package jp.co.EmployeeInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import jp.co.EmployeeInfo.model.AgeRange;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.model.SearchEmployee;
@Mapper
public interface SearchEmployeeMapper {
	//詳細情報検索の初期化
	@Select("""
		    SELECT 
		        e.employeeNo, e.employeeName, e.password AS firstPwd, e.authorityCode, e.updateTime, e.createTime,  
		        ed.genderCode, ed.age, ed.JoiningCompanyOfYear AS joinConpanyYear, ed.intoCompanyOfMonth AS joinConpanyMonth, ed.companyMail,
		        ed.personalMail, ed.phoneNo, ed.dependentsPerson AS dependent, ed.salary,
		        ai.postalCode, ai.firstHalfOfAddress AS prefectures, ai.secondHalfOfAddress AS address, ai.nearestStation AS station,
		        ac.bankCode AS bankNo, ac.bankBranchCode AS branchNo, ac.accountNo, ac.accountName,
		        bm.bankName,
		        bbm.bankBranchName AS branchName
		    FROM employee e
		    LEFT JOIN employee_detail ed ON e.employeeNo = ed.employeeNo
		    LEFT JOIN address_information ai ON e.employeeNo = ai.employeeNo
		    LEFT JOIN account_information ac ON e.employeeNo = ac.employeeNo
		    LEFT JOIN bank_master bm ON ac.bankCode = bm.bankNo
		    LEFT JOIN bank_Branch_master bbm ON ac.bankBranchCode = bbm.bankBranchNo AND ac.bankCode = bbm.bankCode
		""")
	List<ManageAddUser> selectAllEmployees();
	
	
	//詳細情報検索
	@Select("<script>" +
		    "SELECT " +
		    "e.employeeNo, e.employeeName, e.password AS firstPwd, e.authorityCode, e.updateTime, e.createTime, " +
		    "ed.genderCode, ed.age, ed.JoiningCompanyOfYear AS joinCompanyYear, ed.intoCompanyOfMonth AS joinCompanyMonth, ed.companyMail, " +
		    "ed.personalMail, ed.phoneNo, ed.dependentsPerson AS dependent, ed.salary, " +
		    "ai.postalCode, ai.firstHalfOfAddress AS prefectures, ai.secondHalfOfAddress AS address, ai.nearestStation AS station, " +
		    "ac.bankCode AS bankNo, ac.bankBranchCode AS branchNo, ac.accountNo, ac.accountName, " +
		    "bm.bankName, " +
		    "bbm.bankBranchName AS branchName " +
		    "FROM employee e " +
		    "LEFT JOIN employee_detail ed ON e.employeeNo = ed.employeeNo " +
		    "LEFT JOIN address_information ai ON e.employeeNo = ai.employeeNo " +
		    "LEFT JOIN account_information ac ON e.employeeNo = ac.employeeNo " +
		    "LEFT JOIN bank_master bm ON ac.bankCode = bm.bankNo " +
		    "LEFT JOIN bank_Branch_master bbm ON ac.bankBranchCode = bbm.bankBranchNo AND ac.bankCode = bbm.bankCode " +
			"WHERE 1=1 " + // 动态条件拼接基础 
			"<if test='employeeNo != null and employeeNo != \"\"'> " +
			"AND e.employeeNo LIKE CONCAT('%', #{employeeNo}, '%') " + 
			"</if> " + 
			"<if test='employeeName != null and employeeName != \"\"'> " +
			"AND e.employeeName LIKE CONCAT('%', #{employeeName}, '%') " + 
			"</if> " +
			"<if test='genderCode != -1'> " +
			"AND ed.genderCode = #{genderCode} " + 
			"</if> " +
			"<if test='joinCompanyYear != null and joinCompanyYear != \"\" and joinCompanyMonth != null and joinCompanyMonth != \"\"'>" +
	        "AND (ed.JoiningCompanyOfYear &gt; #{joinCompanyYear} " +
	        "OR (ed.JoiningCompanyOfYear = #{joinCompanyYear} " +
	        "AND ed.intoCompanyOfMonth &gt;= #{joinCompanyMonth})) " +
	        "</if>" +
	        "<if test='joinCompanyYear != null and joinCompanyYear != \"\" and (joinCompanyMonth == null or joinCompanyMonth == \"\")'>" +
	        "AND ed.JoiningCompanyOfYear &gt;= #{joinCompanyYear} " +
	        "</if>" +
	        "<if test='joinCompanyMonth != null and joinCompanyMonth != \"\" and (joinCompanyYear == null or joinCompanyYear == \"\")'>" +
	        "AND ed.intoCompanyOfMonth &gt;= #{joinCompanyMonth} " +
	        "</if>" +
			"<if test='salary != null'> " + 
			"AND ed.salary > #{salary} " +
			"</if> " +
			"<if test='uAge != null'>"+ 
			"AND ed.age &gt;= #{uAge} "+ 
			"</if>"+ 
			"<if test='lAge != null'>"+ 
			"AND ed.age &lt;= #{lAge} "+ 
			"</if>"+ 
			"</script>")
	List<SearchEmployee> searchEmployees(@Param("employeeNo") String employeeNo,
	                                      @Param("employeeName") String employeeName,
	                                      @Param("genderCode") int genderCode,
	                                      @Param("joinCompanyYear") String joinCompanyYear,
	                                      @Param("joinCompanyMonth") String joinCompanyMonth,
	                                      @Param("salary") String salary,
	                                      @Param("uAge") Integer uAge,
	                                      @Param("lAge") Integer lAge);

	
}
	
	