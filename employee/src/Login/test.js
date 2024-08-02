// function Login() {
//     return (
//       <div className="App">
//             <h1>社員勤務管理システム</h1>
//             社員番号：<input type="text" name="ID" ></input><br/><br/>
//             パスワード：<input type="password" name="password"></input><br/><br/>
//             <input type="submit" value="ログイン"></input>
//       </div>
//     );
//   }
//   export default Login;

import React, { useState } from 'react';
import axios from 'axios';

const EmployeeLogin = () => {
  const [employeeNo, setEmployeeNo] = useState('');
  const [password, setPassword] = useState('');
  const [rememberPassword, setRememberPassword] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async (e) => {

    e.preventDefault();
    try {
      //const response = await axios.get(`http://localhost:4001/employees?employeeNo=${employeeNo}&password=${password}`);
      //const response = await axios.get(`http://localhost:8080/employees/`);
      const http = require('http');

      // const hostname = '127.0.0.1';
      const hostname = 'localhost';
      const port = 8080;
      
      const server = http.createServer((req, res) => {
        res.statusCode = 200; 
        res.setHeader('Access-Control-Allow-Origin', '*') // 允许所有npm config的地址跨域访问        
        res.end('Hello World');
      });
      
      server.listen(port, hostname, () => {
        
         console.log('http://localhost:8080');
         console.log('端口被调用1')
      });

      
    const response = await axios.get(`http://localhost:8080/Login?employeeNo=${employeeNo}&password=${password}`);
      if (response.data.length > 0) {
        // 登录成功
        console.log('登录成功:', response.data[0]);
        // 保存登录信息到 Cookie
    
        if (rememberPassword) {
          const expirationDate = new Date();
          expirationDate.setDate(expirationDate.getDate() + 7);
          document.cookie = `employeeInfo=${employeeNo}&${password};expires=${expirationDate.toUTCString()}`;
        }
        // 跳转到员工主页
        window.location.href = '/employee/home';
    
    } else {
        // 登录失败
        setErrorMessage('登录失败,请检查员工号和密码');  
    }
    
} catch (error) {
      console.error('登录失败:', error);
      setErrorMessage('登录失败,请稍后重试');
    
    }  
};

  return (
    <div className="login-page">
      <h2>员工登录</h2>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <form onSubmit={handleLogin}>
        <div>
          <label>员工号:</label>
          <input type="text" value={employeeNo} onChange={(e) => setEmployeeNo(e.target.value)} />
        </div>
        <div>
          <label>密码:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div>
          <label>
            <input type="checkbox" checked={rememberPassword} onChange={(e) => setRememberPassword(e.target.checked)} />
            记住密码
          </label>
        </div>
        <button type="submit">ログイン</button>
      </form>
    </div>
  );
};

export default EmployeeLogin;