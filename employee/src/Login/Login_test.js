import React from 'react';
import { Input, Button, message } from "antd";
import { UserOutlined, LockOutlined, EyeInvisibleOutlined, EyeTwoTone } from '@ant-design/icons';
import './index.less'
class Login extends React.Component{
 constructor(props) {
 super(props)
 this.state = {
 username: '',
 password: ''
 }
 };
 submit=()=>{
 if (this.state.username !== '' && this.state.password !== '') {
 this.props.history.push('/Index')
 } else {
 message.error("用户名和密码不能为空")
 }
 };
 user_change=(e)=>{
 this.setState({
 username: e.target.value
 })
 }
 password_change=(e)=>{
 this.setState({
 password: e.target.value
 })
 }
 render () {
 const {username, password} = this.state
 return (
 <div className="login">
  <Input
  value={username}
  onChange={this.user_change}
  size="large"
  placeholder="用户名"
  prefix={<UserOutlined />} />
  <Input.Password
  value={password}
  onChange={this.password_change}
  size="large"
  className="login__input"
  placeholder="密码"
  prefix={<LockOutlined />}
  iconRender={visible => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
  />
  <Button
  className="login__btn"
  size="large"
  type="primary"
  onClick={this.submit}
  >
  登录
  </Button>
 </div>
 );
 }
}  
export default Login;