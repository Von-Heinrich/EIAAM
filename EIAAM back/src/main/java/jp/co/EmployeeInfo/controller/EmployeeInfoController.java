package jp.co.EmployeeInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.EmployeeInfo.model.AgeRange;
import jp.co.EmployeeInfo.model.ManageAddUser;
import jp.co.EmployeeInfo.model.SearchEmployee;
import jp.co.EmployeeInfo.service.EmployeeInfoService;
import jp.co.EmployeeInfo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeInfoController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeInfoService employeeInfoService;
	
	@PostMapping("/selectAll")
	public List<ManageAddUser> getAllEmployees() {
	    logger.info("Entered getAllEmployees method");

	    List<ManageAddUser> employees = employeeInfoService.getAllEmployees();

	    if (employees.isEmpty()) {
	        logger.info("No employees found");
	        return null;
	    } else {
	        logger.info("Exiting getAllEmployees method with employees: {}", employees);
	        return employees;
	    }
	}
	//
	@PostMapping("/searchInfo")
	    public List<SearchEmployee> searchEmployees(@RequestBody SearchEmployee searchEmployee) {
		System.out.println("Controller------------------------");
		List<SearchEmployee> list = employeeInfoService.searchEmployees(searchEmployee);
		list.forEach(System.out::println);
		System.out.println("Controller------------------------");
        // 调用Service层方法
        return list;
        		
        		
    }
	

}
