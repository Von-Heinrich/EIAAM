// import React from "react";
// import "./App.css";

// import { Form, Input, Button, Card} from 'antd';

// const layout = {
//   labelCol: {
//     span: 8,
//   },
//   wrapperCol: {
//     span: 16,
//   },
// };

// const tailLayout = {
//   wrapperCol: {
//     offset: 8,
//     span: 16,
//   },
// };

// const App = () => {
//   // 提交表单且数据验证成功后回调事件
//   const onFinish = values => {
//     console.log('Success:', values);
//   };

//   // 提交表单且数据验证失败后回调事件
//   const onFinishFailed = errorInfo => {
//     console.log('Failed:', errorInfo);
//   };

//   return (
//   <Card title="登陆页面" className="login-form">
//     <Form
//       {...layout}
//       name="basic"
//       initialValues={{
//         remember: true,
//       }}
//       onFinish={onFinish}
//       onFinishFailed={onFinishFailed}
//     >
//       <Form.Item
//         label="用户名"
//         name="用户名"
//         rules={[
//           {
//             required: true,
//             message: '请输入用户名',
//           },
//         ]}
//       >
//         <Input />
//       </Form.Item>

//       <Form.Item
//         label="密码"
//         name="密码"
//         rules={[
//           {
//             required: true,
//             message: '请输入密码',
//           },
//         ]}
//       >
//         <Input.Password />
//       </Form.Item>

//       <Form.Item {...tailLayout}>
//         <Button type="primary" htmlType="submit">
//           登陆
//         </Button>
//       </Form.Item>
//     </Form>
//   </Card>
//   );
// };

// export default App

//import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './Login/EmployeeLogin';
import Master from './Login/EmployeeMaster';
import Member from './Login/EmployeeMember';
import PasswordReset from './Login/EmployeePasswordReset';
import AddUser from './Login/EmployeeAdd';
import Manage from './Login/EmployeeManage';
import ManageTest from './Login/ManageTest';
import Test from './Login/EmployeeTest';



function App() {
  return (
    <Router>
      <Routes>
        <Route path="/Login" element={<Login/>}></Route>
        <Route path="/Master" element={<Master/>}></Route>
        <Route path="/Member" element={<Member/>}></Route>
        <Route path="/PasswordReset" element={<PasswordReset/>}></Route>
        <Route path="/AddUser" element={<AddUser/>}></Route>
        <Route path="/Manage" element={<Manage/>}></Route>
        
      </Routes>
    </Router>
  );  
}

export default App;