package jp.co.EmployeeInfo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.EmployeeInfo.controller.EmployeeController;
import jp.co.EmployeeInfo.mapper.SearchEmployeeMapper;
import jp.co.EmployeeInfo.mapper.EmployeeMapper;
import jp.co.EmployeeInfo.model.AgeRange;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.model.SearchEmployee;
@Service
public class  EmployeeInfoService {

	@Autowired
	private SearchEmployeeMapper searchEmployeeMapper;

	//詳細情報検索の初期化
	public List<ManageAddUser> getAllEmployees() {
		return searchEmployeeMapper.selectAllEmployees();
	}
	//详细检索
	public List<SearchEmployee> searchEmployees(SearchEmployee searchEmployee) {
		if (searchEmployee.getlAge() == 0) {
            searchEmployee.setlAge(null);
        }
        if (searchEmployee.getuAge() == 0) {
            searchEmployee.setuAge(null);
        }
	    return searchEmployeeMapper.searchEmployees( searchEmployee.getEmployeeNo(),
	            searchEmployee.getEmployeeName(),
	            searchEmployee.getGenderCode(),
	            searchEmployee.getJoinCompanyYear(),
	            searchEmployee.getJoinCompanyMonth(),
	            searchEmployee.getSalary(),
	            searchEmployee.getlAge(),
	            searchEmployee.getuAge());
	    }
}