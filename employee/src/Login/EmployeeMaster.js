import React, { createContext, useContext, useState,  } from 'react';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { DatePicker, Typography, Button, Col, Divider, Row, Space, message, ConfigProvider } from "antd";

const Master = function(){
    const user = useSelector(state => state.user.user);
  //const location = useLocation();
    const userRole = user.userRole;
    const userID = user.userID;
    const employeeName = user.employeeName;
    const pwd = user.pwd;
    const pwd2 = user.pwd2;
    const navigate = useNavigate();
    const { Title } = Typography;

    // const location = useLocation();
    // const userRole = location.state?.userRole;
    // const userID = location.state?.userID;
    // const employeeName = location.state?.employeeName;
    // const pwd = location.state?.pwd;
    // const pwd2 = location.state?.pwd2;
    // const navigate = useNavigate();

    // const cookieValue = Cookies.get('myCookieName');
    // console.log("1111111111111111111",cookieValue);
    console.log("1111111111111111111",userID);
    console.log("22222222222222222",pwd2);

    const toPwdReset =  (event) => {
        navigate('/PasswordReset', { state: {userID, pwd, employeeName, pwd2} });
      };
    
    const toManage =  (event) => {
    navigate('/Manage', { state: {userID, pwd, employeeName, pwd2,userRole} });
    };

    // const AddNewEmployeePage =  (event) => {
    //     const getMaxNo = async () => {
    //     try {
    //         const response = await axios.post('http://localhost:8080/employees/PasswordReset');
    //         console.log("-----------------" , response.data);
    //         if (response.status === 200) {
    //           // 登录成功
    //           // 使用 history.push() 进行页面跳转，并将用户角色作为 state 传递
    //           //navigate('/Master', { state: { userRole, userID, pwd} });
    //           //Cookies.set('user', { employeeNo, password, newPassword });
    //          // Cookies.set('employeeNo', employeeNo);
    //           //window.location.href = '/Login';
  
    //         } else {
    //           // 登录失败
    //           //setErrorMessage('登録失敗');
    //           console.log("登録失敗");
    //           setAllowLogin(false);
    //         }
    //       } catch (error) {
    //           console.log("登録失敗");
    //         setAllowLogin(false);
    //       }
    //     }
    //     };
     //line 78:className={`${userRole == 'admin' ? 'Btn1' : 'Btn0'}`}
    return(
       <div>
        <div >
            <h1 className='Master_Top1'>LYC株式会社</h1>
            <span className='Master_Top2'><a href='/login'>サインアウト</a></span>
        </div>
        <br/><br/>
        <Title level={1} style = {{textAlign:'center'}}>社員勤務管理システム</Title>
        <h5 className="system-title2">メニュー一覧</h5>
        <br/>
        <div className='Manage_Row'>{`${userRole == 'admin' ? '管理者' : '社員'}`}: <span>{user.username}</span></div><br/><br/>
    <div className='five-btns'>
        <div className='First_Row'>
            <span><input  className='Btn1' type='button' value='実績入力'/></span>
            <span><input className={`${userRole == 'admin' ? 'Btn1' : 'Btn0'}`} type='button' onClick={toManage} value='社員管理情報'/></span>
           
        </div>
        <div className='First_Row'>
        <span><input  className='Btn2' type='button' value='休憩時&#13;&#10;間設定'/></span>
        </div>
        <div className='First_Row'>
            <span>
                <input onClick={toPwdReset} className='Btn1' type='button' value='パスワードリセット'/>
            </span>
            <span><input  className='Btn1' type='button' value='実績検索'/></span>
        </div>
    </div>
        <div >
            <span className='Master_Bottom'><a href="/Login">ログイン画面に戻る</a></span>
        </div>
        
       </div>
    )

}
export default Master;
