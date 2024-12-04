package jp.co.EmployeeInfo.controller;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import jp.co.EmployeeInfo.model.BatchModel;
import jp.co.EmployeeInfo.model.HolidayResult;
import jp.co.EmployeeInfo.service.BatchService;
import jp.co.EmployeeInfo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class BatchController {
	@Autowired
	private BatchService batchService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@PostMapping("/workTimeSelect")
	public int countEmployeeWorkTime(@RequestBody BatchModel batchModel) {	
		String employeeNo = batchModel.getEmployeeNo();
        String yearAndMonth = batchModel.getYearAndMonth();
        int countResult1 = batchService.countEmployeeWorkTime(employeeNo, yearAndMonth);
        // 将 "YYYYMM" 格式的字符串转换为 "YYYY-MM" 格式
        String formattedYearAndMonth = yearAndMonth.substring(0, 4) + "-" + yearAndMonth.substring(4, 6);
        
        // 使用自定义格式解析字符串
        YearMonth yearMonth = YearMonth.parse(formattedYearAndMonth, DateTimeFormatter.ofPattern("yyyy-MM"));
        
        // 获取该月的天数
        int days = yearMonth.lengthOfMonth();
        System.out.println("Days in month: " + days);
		if(countResult1>0&&countResult1<days) {
			logger.warn("1-2抽出した件数は{}件です",countResult1);
			logger.warn("勤務日数のデータがないので、データの整合性を確認ください。");
		}
		return countResult1;
}
	@PostMapping("/workDaysSelect")
	public int countEmployeeWorkDays(@RequestBody BatchModel batchModel) {
		String employeeNo = batchModel.getEmployeeNo();
        String yearAndMonth = batchModel.getYearAndMonth();
        int countResult2 = batchService.countEmployeeWorkDays(employeeNo, yearAndMonth);
        if(countResult2==0) {
        	logger.warn("1-3抽出した件数は{}件です",countResult2);
        	logger.warn("勤務日数のデータがないので、データの整合性を確認ください。");
        }
        return countResult2;
	}
	@PostMapping("/checkHoliday")
	 public HolidayResult checkHoliday(@RequestBody BatchModel batchModel) {
        // 从 BatchModel 中获取 yearAndMonth 和 day
        String yearAndMonth = batchModel.getYearAndMonth();
        String day = batchModel.getDay();

        // 调用 BatchService 层判断是否为假日，并返回 HolidayResult
        return batchService.isHoliday(yearAndMonth, day);
    }
	@PostMapping("/insertWorkTime")
	public int insertWorkTimeBatch(@RequestBody List<BatchModel> batchModelList) {
		int insertResult1 = batchService.insertWorkTime(batchModelList);
		
	    return insertResult1;
	}
	@PostMapping("/insertTotalWorkTime")
	public int insertTotalWorkTime(@RequestBody BatchModel batchModel) {
		int gotInsertFromBack3 = batchService.insertWorkTotalTime(batchModel);
		
		return gotInsertFromBack3;
	}
	
}
