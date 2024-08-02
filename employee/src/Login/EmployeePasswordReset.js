import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import { Link } from 'react-router-dom';
import Cookies from 'js-cookie';
import CryptoJS from 'crypto-js';
import { useDispatch } from 'react-redux';
import { setUser } from '../redux/actions';
import { useSelector } from 'react-redux';
import { DatePicker, Typography, Button, Col, Divider, Row, Space, message, ConfigProvider } from "antd";
function ResetPassword() {
    const user = useSelector(state => state.user.user);
    const userRole = user.userRole;
    const userID2 = user.userID;
    const pwd2 = user.password;
    const Old_pwd = user.pwd2;
    const emp_Name = user.username;
    const { Title } = Typography;

//     const location = useLocation();
//    //const userRole = location.state?.userRole;
//     const userID2 = location.state?.userID;
//     const pwd2 = location.state?.pwd;
//     const Old_pwd = location.state?.pwd2;
//     const emp_Name = location.state?.employeeName;

    const [encryptPwd, setEncryptPwd] = useState('');

    const [allowLogin, setAllowLogin ] = useState(false);
    const [backOrNot, setBackOrNot ] = useState('false');


    //Masterから、IDとPwdを取得したか  employeeNo
    console.log(userID2);
    console.log(pwd2);
    console.log("Old_pwd"+Old_pwd);

  
    //const [username, setUsername] = useState(userID2);
    const [employeeNo, setEmployeeNo] = useState(userID2);
    const [password, setPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [passwordError2, setPasswordError2] = useState('');
    const [passwordError3, setPasswordError3] = useState('');

    const navigate = useNavigate();

    const specialCharRegex = /[^a-zA-Z0-9]/;
    // //旧パスワードチェック
    // const handleOldPassword = (event) => {
    //     setPasswordError('');
    //     if (password !== pwd2) {
    //         setPasswordError('正しくパスワードを入力してください');
    //     } else if (password == null){
    //         setPasswordError('パスワード入力してください');
    //     } 
    // }

    // //新パスワードチェック
    // const handleNewPassword = (event) => {
    //     setPasswordError2('');
    //     if (newPassword === null) {
    //         setPasswordError2('パスワード入力してください');
    //     } else if (newPassword.length <= 5){
    //         setPasswordError2('正しくパスワードを入力してください');
    //     }else{
    //         const specialCharRegex = /[^a-zA-Z0-9]/;
    //         if( specialCharRegex.test(newPassword)){
    //             setPasswordError2('英字また数字以外が入力できない');
    //         }
    //     }
    // }

    // //新パスワード再入力チェック
    // const handleConfirmedPassword = (event) => {
    //     setPasswordError3('');
    //     if (confirmNewPassword === null) {
    //         setPasswordError3('パスワード入力してください');
    //     } else if (confirmNewPassword.length <= 5){
    //         setPasswordError3('正しくパスワードを入力してください');
    //     }else if(confirmNewPassword !== newPassword){
    //         setPasswordError3('新パスワードと新パスワード再入力不一致です。');
    //     }else{
    //         const specialCharRegex = /[^a-zA-Z0-9]/;
    //         if( specialCharRegex.test(confirmNewPassword)){
    //             setPasswordError2('英字また数字以外が入力できない');
    //         }
    //     }
    // }

  const handleSubmit = (event) => {
    setPasswordError('');
    setPasswordError2('');
    setPasswordError3('');
    event.preventDefault();
    
    //旧パスワードチェック
    if (password === '') {
        setPasswordError('パスワード入力してください');
    } else if(password !== Old_pwd){
        setPasswordError('正しくパスワードを入力してください');
    }
    //新パスワードチェック
    else if (newPassword === '') {
        setPasswordError2('パスワード入力してください');
    } else if (newPassword.length <= 5){
        setPasswordError2('正しくパスワードを入力してください');
    } else if(specialCharRegex.test(newPassword)){
        setPasswordError2('英字また数字以外が入力できない');
    } else if(specialCharRegex.test(newPassword)){
        setPasswordError2('英字また数字以外が入力できない');
    }

    //新パスワード再入力チェック
    else if(confirmNewPassword === '') {
        setPasswordError3('パスワード入力してください');
    } else if (confirmNewPassword.length <= 5){
        setPasswordError3('正しくパスワードを入力してください');
    }else if(specialCharRegex.test(confirmNewPassword)){
        setPasswordError3('英字また数字以外が入力できない');
    }else if(confirmNewPassword !== newPassword){
        setPasswordError3('新パスワードと新パスワード再入力不一致です。');
    } else{
        setAllowLogin(true);
    }
};

  useEffect(() => {
    const loginRequest = async () => {
      if (allowLogin) {
        try {
          const response = await axios.post('http://localhost:8080/employees/PasswordReset', {employeeNo,password,newPassword});
          console.log("-----------------" , response.data);
          if (response.status === 200) {
            // 登录成功
            // 使用 history.push() 进行页面跳转，并将用户角色作为 state 传递
            //navigate('/Master', { state: { userRole, userID, pwd} });
            //Cookies.set('user', { employeeNo, password, newPassword });
           // Cookies.set('employeeNo', employeeNo);
            //window.location.href = '/Login';
    
            navigate('/Login', { state: {employeeNo} });

          } else {
            // 登录失败
            //setErrorMessage('登録失敗');
            console.log("登録失敗");
            setAllowLogin(false);
          }
        } catch (error) {
            console.log("登録失敗");
          setAllowLogin(false);
        }
      }
    };
    loginRequest();
    }, [allowLogin, employeeNo, password,newPassword]);


  return (
    <div>
        <div>
            <h1 className='Master_Top1'>LYC株式会社</h1>
            <span className='Master_Top2'><a href='/login'>サインアウト</a></span>
        </div>
        <br/><br/>
        <Title level={1} style = {{textAlign:'center'}}>社員勤務管理システム</Title>
        <h5 className="system-title2">パスワードリセット</h5><br/>
        <div className='Second_Row'>
            <span className='Manage_Row'>{`${userRole == 'admin' ? '管理者' : '社員'}`}:</span>
            <span >{emp_Name}</span>
        </div><br/><br/><br/>

      <form onSubmit={handleSubmit}>
        <div className='Item_Div_Row'>
          <span className='ItemSty'><label >社員番号:</label></span>
          <span className='InputSty'><input type="text" value={employeeNo} disabled /></span>
        </div><br/>
        <div className='Item_Div_Row'>
          <span className='ItemSty'><label >既存パスワード:</label></span>
          <span className='InputSty'><input className={`${passwordError ? 'error-input2' : ''}`} maxLength="10" type="text" value={password} onChange={(e) => setPassword(e.target.value)} /></span>     
          {passwordError && <span className="PwdResetError-msg" >{passwordError}</span>}       
        </div> 
        <br/>
        <div className='Item_Div_Row'>
          <span className='ItemSty'><label >新しいパースワード:</label></span>
          <span className='InputSty'><input className={`${passwordError2 ? 'error-input2' : ''}`} maxLength="10" type="text" value={newPassword}  onChange={(e) => setNewPassword(e.target.value)} /></span>
          {passwordError2 && <span className="PwdResetError-msg" >{passwordError2}</span>}
        </div><br/>
        <div className='Item_Div_Row'>
          <span className='ItemSty'><label >新しいパースワード再入力:</label></span>
          <span className='InputSty'><input className={`${passwordError3 ? 'error-input2' : ''}`} maxLength="10" type="text" value={confirmNewPassword}  onChange={(e) => setConfirmNewPassword(e.target.value)} /></span>
          {passwordError3 && <span className="PwdResetError-msg" >{passwordError3}</span>}
        </div><br/><br/>
        <div className='Item_Div_Row'>
          <button className='PwdReset_Btn' type="submit">確認</button>
        </div><br/><br/><br/>
        <div className='Second_Row'>
            <span className='Manage_Row'><Link to={{ pathname: '/Master'}}>サブメニューに戻る</Link></span>
        </div>
      </form>
    </div>
  );
}

export default ResetPassword;
