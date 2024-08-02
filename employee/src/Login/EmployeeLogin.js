import React, { useState, useEffect, createContext, useContext, eState } from 'react';
import axios from 'axios';
import { useNavigate, useLocation } from 'react-router-dom';
import Cookies from 'js-cookie';
import { useDispatch } from 'react-redux';
import { setUser } from '../redux/actions';
import { DatePicker, Input,Typography } from 'antd';
import moment from 'moment';
import '../styles/Employee.css';
//import { DatePicker, Typography, Button, Col, Divider, Row, Space, message, ConfigProvider } from "antd";


const EmployeeLogin = () => {

  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();
  const { Title } = Typography;
  
  const toEmployeeManage1 =  (event) => {
    navigate('/EmployeeManage1');
  };


  // const pwd3 = location.state?.Old_pwd;
  // console.log("--------------------",pwd3);
  //const EmployeeNo = Cookies.get('employeeNo');
  // console.log("--------------------",EmployeeNo);
 //  const [username, setUsername] = useState(`${EmployeeNo ? EmployeeNo : ''}`);

//  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [allowLogin, setAllowLogin ] = useState(false);
  const [username, setUsername] = useState('');
  const [employeeNo, setEmployeeNo] = useState('');
  
  /*useEffect(() => {
    if (location.state?.employeeNo) {
      setUsername(location.state.employeeNo);
    }
  }, [location]);*/

  const handleLogin = async (e) => {
    e.preventDefault();
    setErrorMessage('');
    setUsernameError('');
    setPasswordError('');


    // ① 社員番号がブランクの場合
    // ② 社員番号が5桁以下の場合、また最初の三桁がLYCではない場合
    // ⑤ 社員番号やパスワードが英字また数字以外の場合
    if (username.trim() === '') {
      setUsernameError('社員番号を入力してください');
    } else if (username.length <= 5 || !/^lyc/i.test(username)) {
      setUsernameError('正しい社員番号を入力してください');
    } else {
      const specialCharRegex = /[^a-zA-Z0-9]/;
      if (specialCharRegex.test(username)) {
        setUsernameError('社員番号は英字と数字のみ使用可能です');
      } else{
        // ③ パスワードがブランクの場合
        // ④ パスワードが5桁以下の場合
        // ⑤ 社員番号やパスワードが英字また数字以外の場合
        if (password.trim() === '') {
          setPasswordError('パスワードを入力してください');
        } else if (password.length <= 5) {
          setPasswordError('パスワードは6桁以上で入力してください');
        } else {
          const specialCharRegex = /[^a-zA-Z0-9]/;
          if (specialCharRegex.test(password)) {
            setPasswordError('社員番号とパスワードは英字と数字のみ使用可能です');
          } else {
            setAllowLogin(true);
          }
        }
      }
    }
  };
  useEffect(() => {
    const loginRequest = async () => {
      if (allowLogin) {
        setAllowLogin(false);
        try {
          const response = await axios.post('http://localhost:8080/employees/login', {username,password});
          console.log("-----------------" , response.data);
          if (response.status === 200) {
          // 登录失败 显示errorMsg
            if ( response.data === null || response.data === ''){
              setAllowLogin(false);
              console.log("-----------------" , response.data);
             console.log("------into null------");
             console.log("-------null----------" , response.data);
             setErrorMessage('入力した社員番号とパスワードを確認してください');
           
             // 登录成功 跳转到员工主页 
             }else  {
             
             console.log("------into not null------");
             console.log("-------not null----------" , response.data);
             // 后端返回的用户角色字段为 'authorityCode'            
            const authorityCode = response.data.authorityCode;
            const authorityProperties = response.data.authorityProperties;
            const userID = response.data.employeeNo;
            const userName = response.data.employeeName;
            const pwd = response.data.password;
            const pwd2 = password;
            const userRole = authorityCode == '1' ? 'admin' : 'user';
             dispatch(setUser({userID: userID, username:userName, password:pwd, userRole:userRole , pwd2:pwd2}));
            
             navigate('/Master');
             }          
            //  const authorityCode = response.data.authorityCode;
            //  const authorityProperties = response.data.authorityProperties;
            //  const userID = response.data.employeeNo;
            //  const userName = response.data.employeeName;
            //  const pwd = response.data.password;
            //  const pwd2 = password;
            //  const userRole = authorityCode == '1' ? '管理者' : '社員';
            // dispatch(setUser({userID: userID, username:userName, password:pwd, userRole:userRole , pwd2:pwd2}));
    
             // Cookies.set('myCookieName', 'myCookieValue');
             // window.location.href = '/Login';    
             // 使用 history.push() 进行页面跳转，并将用户角色作为 state 传递
             //navigate('/Master', { state: { userRole, userID, pwd, employeeName, pwd2} });
            //navigate('/Master');

          } else {
            // 登录失败
            setErrorMessage('登録失敗');
            setAllowLogin(false);
          }
        } catch (error) {
          setErrorMessage('入力した社員番号とパスワードを確認してください');
          setAllowLogin(false);
          setUsername('');
          setPassword('');
        }
      }
    };
   
    loginRequest();
    }, [allowLogin]);
    

  return (
    <div className="login-container">
      <div className="company-name">LYC株式会社</div>
      <Title level={1} style = {{textAlign:'center'}}>社員勤務管理システム</Title>
      <h5 className="system-title">社員ログイン</h5>
      <br/><br/>
      
      <form onSubmit={handleLogin} className="login-form">
        <div className="form-row">
          <label className="form-label">社員番号:</label>
          <input type="text" maxLength="6"  value={username} onChange={(e) => setUsername(e.target.value)} 
          className={`form-input ${usernameError ? 'error-input' : ''}`}/>
          <div className="error-placeholder" >
            {usernameError && <span className="error-message">{usernameError}</span>}
          </div>
        </div>
        <br/>

        <div className="form-row">
          <label className="form-label">パスワード:</label>
          <input type="password" maxLength="12" value={password} onChange={(e) => setPassword(e.target.value)}
            className={`form-input ${passwordError ? 'error-input' : ''}`}/>
          <div className="error-placeholder" >
            {passwordError && <span className="error-message">{passwordError}</span>}
            </div>
        </div>

        <div className="form-row">
          <button type="submit" className="login-button">ログイン</button>
        </div>
        <div className="error-placeholder" >
        {errorMessage && <span className="error-message">{errorMessage}</span>}
        </div>
      
      
      </form>
      <span className='Test1' onClick={toEmployeeManage1}>Test</span>

      
    </div>
  );

};

export default EmployeeLogin;