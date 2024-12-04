/*
 * package jp.co.EmployeeInfo.controller;
 * 
 * import java.util.HashMap; import java.util.Map; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.thymeleaf.util.StringUtils; import
 * jp.co.EmployeeInfo.common.AppHttpCodeEnum; import
 * jp.co.EmployeeInfo.common.ResponseResult; import
 * jp.co.EmployeeInfo.model.LoginModel; import
 * jp.co.EmployeeInfo.pojo.EmployeePojo; import
 * jp.co.EmployeeInfo.service.LoginService;
 * 
 * @Controller public class LoginController {
 * 
 * @Autowired LoginService loginService;
 * 
 * @GetMapping("/login")
 * 
 * @ResponseBody
 * 
 * public Map<String, String> login( ){ Map<String, String> map = new
 * HashMap<>(); return map; } }
 * 
 * public Map<String, String> login( EmployeePojo employeePojo ,LoginModel
 * loginModel ){
 * 
 * Map<String, String> sendMap = new HashMap<String, String>(); Map<String,
 * String> resultMap = new HashMap<String, String>(); Map<String, String>
 * errorMap = new HashMap<String, String>();
 * 
 * //前端 sendMap.put("employeeNo",loginModel.employeeName);
 * sendMap.put("password", loginModel.passWord);
 * 
 * //sendMap.put("employeeNo", "1"); //sendMap.put("password", "1");
 * 
 * //后端
 * 
 * employeePojo= loginService.getLogin(sendMap);
 * 
 * System.out.println(
 * "!!!!!!!!!!!!!!!!!!!!loginPojo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
 * 
 * 
 * resultMap.put("employeeNo", employeePojo.getEmployeeNo());
 * resultMap.put("password", employeePojo.getPassword());
 * 
 * 
 * //？？？？？？ ResponseResult<Map<String, String>> responseResult = new
 * ResponseResult<>();
 * 
 * AppHttpCodeEnum errorEnum = AppHttpCodeEnum.LOGIN_PASSWORD_ERROR;
 * errorMap.put(responseResult.getErrorMessage(),errorEnum.getErrorMessage());
 * if (employeePojo == null || !StringUtils.equals(sendMap, resultMap)) {
 * 
 * System.out.println(errorMap+
 * "gggggggggggggggggggggggggggggggggggggggggggggggggg"); return errorMap; }
 * 
 * AppHttpCodeEnum successEnum = AppHttpCodeEnum.SUCCESS; //？？？？？
 * errorMap.put(responseResult.getErrorMessage(),successEnum.getErrorMessage())
 * ; System.out.println(errorMap+"jjjjjjjjjjjjjjjjjjjjjjjjjjj");
 * 
 * return errorMap; }
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 */