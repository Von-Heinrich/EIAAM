package jp.co.EmployeeInfo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.EmployeeInfo.mapper.BatchMapper;
import jp.co.EmployeeInfo.model.HolidayResult;

@Service
public class HolidayService {

    private static final String HOLIDAY_API_URL = "https://date.nager.at/Api/v2/PublicHolidays/";

    public HolidayResult isHoliday(String yearAndMonth, String day) {
        // 拼接成完整日期，假设格式为 yyyy-MM-dd
        String dateString = yearAndMonth.substring(0, 4) + "-" + yearAndMonth.substring(4, 6) + "-" +String.format("%02d", Integer.parseInt(day));
        
        RestTemplate restTemplate = new RestTemplate();
        try {
            // 提取年份，用于调用 Nager.Date API
            String year = yearAndMonth.substring(0, 4);
            String url = HOLIDAY_API_URL + year + "/JP";
            
            LocalDate date = LocalDate.parse(dateString); 

            // 发送 GET 请求
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // 解析 API 返回的假日数据
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode holidays = objectMapper.readTree(response.getBody());

                // 遍历假日列表，检查是否包含指定日期
                for (JsonNode holiday : holidays) {
                    String holidayDate = holiday.get("date").asText();
                
                    if (dateString.equals(holidayDate)) {
                    	
                        return new HolidayResult(2, date.getDayOfWeek().getValue());  // 节假日
                    }
                }
            }

            // 如果不是假日，检查是否是周末
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            	
                return new HolidayResult(1, dayOfWeek.getValue());  // 周末
            }

        } catch (Exception e) {
            e.printStackTrace(); // 打印异常日志
        }
     
        return new HolidayResult(0, LocalDate.parse(dateString).getDayOfWeek().getValue()); // 既不是假日也不是周末
    }
}
