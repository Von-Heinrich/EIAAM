package jp.co.EmployeeInfo.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.EmployeeInfo.mapper.BatchMapper;
import jp.co.EmployeeInfo.model.BatchModel;
import jp.co.EmployeeInfo.model.HolidayResult;

@Service
public class BatchService {
	@Autowired
	private BatchMapper batchMapper;
	@Autowired
    private HolidayService holidayService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	//勤務時間情報取得
	public int countEmployeeWorkTime(String employeeNo, String yearAndMonth) {
        // 调用 Mapper 方法执行 SQL 查询
		int countTimeResult = batchMapper.countEmployeeWorkTime(employeeNo, yearAndMonth);
		if (countTimeResult != 0) {
            logger.info("勤務時間は {} 件を存在しました", countTimeResult);
        }
        return batchMapper.countEmployeeWorkTime(employeeNo, yearAndMonth);
    }
	//勤務日数情報を取得
	public int countEmployeeWorkDays(String employeeNo,String yearAndMonth) {
		int countDaysResult = batchMapper.countEmployeeWorkDays(employeeNo, yearAndMonth);
		if (countDaysResult != 0) {
            logger.info("勤務日数は {} 件を存在しました", countDaysResult);
        }
		/*int days = getDaysInMonth(yearAndMonth);
		int count1 = countEmployeeWorkTime(employeeNo, yearAndMonth);
		if(countDaysResult>0&&countDaysResult<days) {
			logger.warn("1-2抽出した件数は{}件です",count1);
			logger.warn("1-3抽出した件数は{}件です",countDaysResult);
			logger.warn("勤務日数のデータがないので、データの整合性を確認ください。");
		}*/
		return batchMapper.countEmployeeWorkDays(employeeNo, yearAndMonth);
	}
	//日期判断
	public HolidayResult isHoliday(String yearAndMonth, String day) {
        // 调用 HolidayService 来判断是否为节假日
        return holidayService.isHoliday(yearAndMonth, day);
    }
	 //勤務時間の作成処理
	@Autowired
	private TransactionTemplate transactionTemplate;
	 public int insertWorkTime(List<BatchModel> batchModelList) {
	 try {
		  return transactionTemplate.execute(status -> {
		  try {
			  int insertWorkTimeResult =  batchMapper.insertWorkTime(batchModelList);
			  return insertWorkTimeResult;
		      } catch (Exception e) {
		          e.printStackTrace();
		          status.setRollbackOnly();
		          logger.info("ECOM0002:取得した異常情報");
		          return 0; // 失败返回0
		          }
		        });
		    } catch (Exception e) {
		        e.printStackTrace();
		        logger.info("ECOM0002:取得した異常情報");
		        return 0; // 事务失败返回0
		    }
	}
	//勤務日数の作成処理
	 @Autowired
	private TransactionTemplate transactionTemplate2;
	 public int insertWorkTotalTime(BatchModel batchModel){
		 try {
			  return transactionTemplate2.execute(status -> {
			  try {
					 batchMapper.insertWorkTotalTime(batchModel.getEmployeeNo(), batchModel.getYearAndMonth());
					 return 1;
			  } catch (Exception e) {
		          e.printStackTrace();
		          status.setRollbackOnly();
		          logger.info("ECOM0003:取得した異常情報");
		          return 0; // 失败返回0
		          }
		        });
		    } catch (Exception e) {
		        e.printStackTrace();
		        logger.info("ECOM0003:取得した異常情報");
		        return 0; // 事务失败返回0
		    }
	 }
	 
}
