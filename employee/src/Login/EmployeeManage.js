import React, { createContext, useContext, useState, useRef, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { DatePicker, Typography, Button, Col, Divider, Row, Space, message, ConfigProvider } from "antd";
import moment from 'moment';

import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import { defaultTheme } from 'antd/es/theme/context';




const Manage = () => {

    const { Title } = Typography;

    //非活性化/活性化の制御
    const [maxEmployeeNo, setMaxEmployeeNo] = useState('');
    const [branchNameIsDisabled, setBranchNameIsDisabled] = useState(true);
    const [branchNoIsDisabled, setBranchNoIsDisabled] = useState(true);
    const [accountNoIsDisabled, setAccountNoIsDisabled] = useState(true);
    const [accountIsDisabled, setAccountIsDisabled] = useState(true);
    const [prefecturesIsDisabled, setPrefecturesIsDisabled] = useState(true);
    const [addressIsDisabled, setAddressIsDisabled] = useState(true);

    //カレンダー
    const [showCalendar, setShowCalendar] = useState(false);
    const [selectedDate, setSelectedDate] = useState(new Date());


    const [val, setVal] = useState('');

    //各項目
    const [employeeNo, setEmployeeNo] = useState('');
    const [employeeName, setEmployeeName] = useState('');
    const [gender, setGender] = useState('男');
    const [joinConpanyYear, setJoinConpanyYear] = useState('');
    const [joinConpanyMonth, setJoinConpanyMonth] = useState('');
    const [dependent, setDependent] = useState('');
    const [salary, setSalary] = useState('');
    //salaryClear
    const [salaryClear, setSalaryClear] = useState('');
    const [firstPwd, setFirstPwd] = useState('');
    const [companyMail, setCompanyMail] = useState('');
    const [personalMail, setPersonalMail] = useState('');
    const [tel1, setTel1] = useState('');
    const [tel2, setTel2] = useState('');
    const [tel3, setTel3] = useState('');
    const [authority, setAuthority] = useState('社員');

    const [postNo1, setPsotNo1] = useState('');
    const [postNo2, setPsotNo2] = useState('');
    const [prefectures, setPrefectures] = useState('');
    const [address, setAddress] = useState('');
    const [station, setStation] = useState('');
    const [bankName, setBankName] = useState('');
    const [bankNo, setBankNo] = useState('');
    const [branchName, setBranchName] = useState('');
    const [branchNo, setBranchNo] = useState('');
    const [accountNo, setAccountNo] = useState('');
    const [accountName, setAccountName] = useState('');
    const [branchNameNoError, setBranchNameNoError] = useState('');
    const [phoneNo, setPhoneNo] = useState('');

    const [calcuAge, setCalcuAge] = useState('');


    let authorityCode;
    let postalCode;

    //追加処理
    const [addUserOrNot, setAddUserOrNot] = useState(false);

    //必須項目のエラーメッセージ
    const [employeeNameError, setEmployeeNameError] = useState('');
    const [firstPwdError, setFirstPwdError] = useState('');
    const [authorityError, setAuthorityError] = useState('');

    //給料のエラーメッセージ
    const [salaryError, setSalaryError] = useState('');
    const numberRegex = /^[1-9][0-9]*$/;

    //mail验证
    const regMail_half = /^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*$/;
    const regMail_full = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;

    //mailエラー提示　
    const [companyMailError, setCompanyMailError] = useState('');
    const [personalMailError, setPersonalMailError] = useState('');

    //入社年月  joinConpanyError
    const [joinConpanyError, setJoinConpanyError] = useState('');

    //電話番号
    const reg_Tel = /^[0-9]+$/;
    const [telError, setTelError] = useState('');

    //初期パスワード
    const reg_Pwd = /^[a-zA-Z0-9]+$/;

    //郵便番号
    const [postNoError, setPostNoError] = useState('');

    //口座番号
    const [accountError, setAccountError] = useState('');

    //submitを許可
    const [allowLogin, setAllowLogin ] = useState(false);

    

    //追加処理
    const [isClicked, setIsClicked] = useState(false);
   //?
    //銀行と連携項目のチェック
    const handleBankInfo =  ()=> {

        setAccountError('');
       
        console.log("bankNo--",bankNo);

        if (bankNo !== '') {

            //銀行に関連する項目を活性化する
            setBranchNameIsDisabled(false);
            setBranchNoIsDisabled(false);
            setAccountNoIsDisabled(false);
            setAccountIsDisabled(false);
        } else {
            setBranchNameIsDisabled(true);
            setBranchNoIsDisabled(true);
            setAccountNoIsDisabled(true);
            setAccountIsDisabled(true);
            setBranchName('');
            setBranchNo('');
            setAccountNo('');
            setAccountName('');
            setBranchNameNoError('');
        }

    }


    const [oldBranchName, setOldBranchName] = useState('');//?

    useEffect(() => {
        console.log("oldBranchName",oldBranchName)
        console.log("branchName", branchName)

        if(branchName !== oldBranchName) {
            setBranchNo("")
        }
    }, [branchName])


    //支店名チェック
    const handleBranchName = async () => {
        setBranchNameNoError('');
        setAccountError('');

        setOldBranchName(branchName);
        if (branchName !== '' && branchNo === '') {
            try {
                const response = await axios.post('http://localhost:8080/employees/handleBranchName', {bankNo, branchName});
                console.log("-----------------", response.data);
                if (response.status === 200) {
              
                    if (response.data !== "") {
                        setBranchNo(response.data);
                        console.log("success");
                    } else {
                        setBranchNo('');
                        setBranchNameNoError("支店名また支店番号は支店マスターで存在してない、ご確認ください");
                        console.log("branchNameNoError",branchNameNoError);
                        console.log("fail");
                    }  
                } 
            } catch (error) {

            }
    
        }else if(branchName === ''){
            setBranchNo('');
        }

        
    }

useEffect(() => {
    console.log("ssssssssssssssss")
    if(branchNo.length !== 3) {
        setBranchName("")
    }
}, [branchNo])

        //支店番号チェック branchNo
        const handleBranchNo = async () => {
            setBranchNameNoError('');
            
            if( branchNo !== '' && !reg_Tel.test(branchNo)){
                setBranchName('');
                setBranchNameNoError("半角数字で入力してください");
                setAccountError('');
                return;
            }

        try {
            if( branchNo !== '' && branchName === ''){
                const response = await axios.post('http://localhost:8080/employees/handleBranchNo', {bankNo, branchNo});
                console.log("-----------------", response.data);
                if (response.status === 200) {
            
                    if (response.data !== "") {
                        setBranchName(response.data);
                        console.log("success");
                    } else {
                        setBranchName('');
                        setBranchNameNoError("支店名また支店番号は支店マスターで存在してない、ご確認ください");
                        console.log("branchNameNoError",branchNameNoError);
                        console.log("fail");
                    }  
                } 
        } else if(branchNo === ''){
            setBranchName('');
    }
        }

                catch (error) {

                } 
    
        }


        const [birthday, setBirthday] = useState(null);
        const handleBirthdayChange = (date) => {
            setBirthday(date);
            setDatePickerVisible(false); 
        };

        //

        const calculateAge = (birthday) => {
            if (!birthday) return '';
            
            const today = new Date();
            const birthDate = new Date(birthday);
            const plustoday = new Date() ;

            plustoday.setDate(today.getDate() + 5);
            plustoday.setFullYear(today.getFullYear() + 5);

            let age = today.getFullYear() - birthDate.getFullYear();
            const monthDiff = today.getMonth() - birthDate.getMonth();

            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
            age--;
            }
            
            return age;
        };


        //
        const [postCheck, setPostCheck] = useState(false);
        const handlePost = async (event) => {

            event.preventDefault();
            console.log('postNo1',postNo1)
            console.log('postNo2',postNo2)

           
            setPostNoError('');
          

            setPrefectures('');
            setAddress('');
            setStation('');

            if( postNo1 === '' || postNo2 === '' || postNo1.length !== 3 || postNo2.length !== 4){
                setPostNoError('正しく郵便番号を入力してください');      
            
            }else{  
            
                const fullPost = postNo1 + postNo2;
                const fullURL = 'https://zipcloud.ibsnet.co.jp/api/search?zipcode='+fullPost;
                console.log('fullURL----', fullURL)
                
                const PostResponse =await axios.get(fullURL);
                
                if (PostResponse.data.status === 200) {
                    console.log('PostResponse.data----', PostResponse.data)
                    console.log('PostResponse.data.message----', PostResponse.data.message)
                    console.log('PostResponse.data.results----', PostResponse.data.results)
                    console.log('PostResponse.data.status----', PostResponse.data.status)
                    
                    if (PostResponse.data.results === null) {
                        setPostNoError('正しく郵便番号を入力してください');
                    } else {
                        const address = PostResponse.data.results[0].address1 + PostResponse.data.results[0].address2 + PostResponse.data.results[0].address3
                        setPrefectures(address);
                        setAddressIsDisabled(false);

                        //???????
                        setPostCheck(true);
                    }                  

                } else if(PostResponse.status === 400){
                    setPostNoError('必須パラメータが指定されていません。');
                }
            }           
        }  

        function addCommasToNumber(number) {
           // 将数字转换为字符串
                let numStr = number.toString();
                // 从右向左每隔三位添加逗号
                let result = "";
                let count = 0;
                for (let i = numStr.length - 1; i >= 0; i--) {
                    result = numStr[i] + result;
                    count++;
                    if (count % 3 === 0 && i !== 0) {
                        result = "," + result;
                    }
            }
                 return result;
        }
        
        

        const handleClearSalary = () => {
            const newSalaryClear = salary.replace(/,/g, "");
            const clearZeroSalary = newSalaryClear.replace(/^0+/, '');  
            setSalary(clearZeroSalary);   
            setSalaryClear(clearZeroSalary);  

           // console.log("newSalaryClear---",newSalaryClear)

    }

        const handleSalary = () => {
            const newSalary = salary.replace(/,/g, "");
            const clearZeroSalary = newSalary.replace(/^0+/, '');              
            const formattedNumber = addCommasToNumber(clearZeroSalary);
            setSalary(formattedNumber);
            setSalaryClear(salary);    
        }


        // 计算 20 岁和 50 岁的日期范围
            const twentyYearsAgo = moment().subtract(20, 'years');
            const fiftyYearsAgo = moment().subtract(50, 'years');

            // 定义一个禁用日期的函数
            const disabledDate = (current) => {
            if (current) {
                return current.isAfter(twentyYearsAgo) || current.isBefore(fiftyYearsAgo);
            }
            return false;
            };
                const [isDatePickerVisible, setDatePickerVisible] = useState(false);

                const handleButtonClick = () => {
                    setDatePickerVisible(!isDatePickerVisible); // 切换日历的显示与隐藏
                };
                const handleDatePickerChange = (date, dateString) => {
                    console.log(date, dateString);
                    setDatePickerVisible(false); // 选择日期后隐藏日历
                };

                const handleDatePickerCancel = () => {
                    setDatePickerVisible(false);
                };

    //form提出チェック
    const ManageEmployee = function (event) {
        event.preventDefault();


        if(postNo1 ==='' || postNo2 === ''){
            setPrefectures('');
            setAddress('');
            setStation('');
        }

        const newSalary = salary.replace(/,/g, "");
        console.log("bankNo--------------",bankNo);

        //エラー提示を消す
        setEmployeeNameError('');
        setFirstPwdError('');
        setAuthorityError('');
        setSalaryError('');
        setCompanyMailError('');
        setPersonalMailError('');
        setJoinConpanyError('');
        setTelError('');
        setPostNoError('');
        setAccountError('');
        
        console.log('bankNo--------------',bankNo);

 
        //必須項目
        if (employeeName === '') {
            setEmployeeNameError('必須項目を入力してください');

        } else if(firstPwd === ''){
            setFirstPwdError('必須項目を入力してください');

        }else if( !reg_Pwd.test(firstPwd) ){
            setFirstPwdError('半角数字またはアルファベットで入力してください');
        }
        else if(authority === ''){
            setAuthorityError('必須項目を入力してください');
        
        //給料を5桁以下の場合
        }else if( newSalary !== '' && newSalary.length < 5){
            // const salaryClear = salary.replace(/,/g, "");
            setSalaryError('入力桁数が正しくありません、再入力してください');
        }
        //社用メールチェック
        else if( companyMail !== '' &&  !regMail_half.test(companyMail)){
            console.log('companyMail----',companyMail)
            const aa = regMail_half.test(companyMail)
            console.log('----aa',aa)
            setCompanyMailError('正しくメールアドレス入力してください');
        }
        //私用メールチェック
        else if( personalMail !== '' &&  !regMail_full.test(personalMail)){
            setPersonalMailError('正しくメールアドレス入力してください');
        }
        //入社年月
        else if( joinConpanyYear === '' && joinConpanyMonth !== '' ){
            setJoinConpanyError('入社年月を正しく入力してください');
        }else if( joinConpanyYear !== '' && joinConpanyMonth === '' ){
            setJoinConpanyError('入社年月を正しく入力してください');
        }
        //電話番号
        else if( tel1 !== '' && !reg_Tel.test(tel1)   ){
            setTelError('電話番号を正しく入力してください(数字のみ)');
        }else if( tel2 !== '' && !reg_Tel.test(tel2) ){
            setTelError('電話番号を正しく入力してください(数字のみ)');
        }else if( tel3 !== '' && !reg_Tel.test(tel3) ){
            setTelError('電話番号を正しく入力してください(数字のみ)');
        }else if(tel1!== '' || tel2 !== '' || tel3 !== ''){
            if(tel1.length + tel2.length + tel3.length < 11){
                setTelError('桁数入力が間違っています');
            }

        }else if (postNo1 !== '' || postNo2 !== '' ){
        console.log("postNo1!!!!!!!!!!!!!!!!!!!!",postNo1)
        if (postNo1.length !== 3  ) {
            setPostNoError('正しく郵便番号を入力してください');    
        }else if(postNo2.length !== 4){
            setPostNoError('正しく郵便番号を入力してください');    
        } 
        }

        //銀行に関連する項目
        else if( bankNo !== '' && branchName === ''){
            setAccountError('銀行情報関連項目を入力してくださいaa');
        }else if( bankNo !== '' && branchNo === ''){
            setAccountError('銀行情報関連項目を入力してくださいbb');
        }else if( bankNo !== '' && accountNo === ''){
            setAccountError('銀行情報関連項目を入力してくださいcc');
        }else if( bankNo !== '' && accountName === ''){
            setAccountError('銀行情報関連項目を入力してくださいdd');
        }else if( accountNo !== '' && !reg_Tel.test(accountNo) ){
            setAccountError('半角数字で入力してください');
        }else{
            console.log('bankNo----11111----------',bankNo);
            //すべての項目のチェックが完了する場合、提出を許可
            setAllowLogin(true);

            console.log('bankNo----11111----------',bankNo);
            
            console.log("employeeNo",employeeNo);
            console.log("employeeName",employeeName);
            console.log("gender",gender);
            console.log("joinConpanyYear",joinConpanyYear);
            console.log("joinConpanyMonth",joinConpanyMonth);
            console.log("dependent",dependent);
            console.log("salary",salary);
            console.log("firstPwd",firstPwd);
            console.log("companyMail",companyMail);
            console.log("personalMail",personalMail);
            console.log("tel1",tel1);
            console.log("tel2",tel2);
            console.log("tel3",tel3);
            console.log("authority",authority);

            
            if (authority === '社員') {
                 authorityCode = 0;
            } else {
                authorityCode = 1;
            }

            console.log("authorityCode",authorityCode);
            console.log("postNo1",postNo1);
            console.log("postNo2",postNo2);
            console.log("prefectures",prefectures);
            console.log("address",address);
            console.log("station",station);

            console.log("bankName",bankName);
            console.log("bankNo",bankNo);
            console.log("branchName",branchName);
            console.log("branchNo",branchNo);
            console.log("accountNo",accountNo);
            console.log("accountName",accountName);
    
            const phoneNo = tel1+tel2+tel3
            console.log("phoneNo",phoneNo)
    
            postalCode = postNo1+postNo2
            console.log("postalCode",postalCode)
    
    
            let StrAge = calculateAge(birthday);
            console.log("StrAge",StrAge)
            console.log('calculateAge(birthday)',calculateAge(birthday))
    
            
        }
        
    }


    




    return (
        <div>     
            <div>
                <span className='Manage_Top1'>LYC株式会社</span>
                <span className='Manage_Top2'><a href=''>ログアウト</a></span>
            </div><br /><br />

            <Title level={2} style={{textAlign:'center'}}>社員勤務管理システム</Title>
            <Title level={4} style={{textAlign:'center'}}>社員情報管理画面</Title>
            <div className="Manage_Row">管理者：</div>
            

            <form onSubmit={ManageEmployee}>


                
            <div className='div1'>
           
           <button className='EMbtn1' style={{ backgroundColor: isClicked? 'green' : '' }}>追加</button>
           <button className='EMbtn1'>修正</button>
           <button className='EMbtn1'>削除</button>
           <button className='EMbtn1'>詳細検索画面へ</button>
       
       </div><br/>
                <div >
                    <label className='Block_Item'>社員番号：</label>
                    <input className='Input_Item' disabled='true'/>
                    <span className='Block_OR_Item'>OR</span>
                    <label className='Block_Item2' >社員名：</label>
                    <input className='Input_Item2' />

                </div><br />
                <div className="line"></div><br />
                <div>
                    <label className='Second_Item'>社員番号：</label>
                    <input className='EmployeeNo_Input_Item' value={maxEmployeeNo} onChange={(e) => setMaxEmployeeNo(e.target.value)} readOnly disabled />{/*? */}
                </div><br />
                <div>
                    <label className='Second_Item'>社員名：</label>
                    <input className='EmployeeName_Input_Item' value={employeeName} onChange={(e) => setEmployeeName(e.target.value)} />
                    <span style={{ color: 'red', marginLeft: '5px' }}>&#9733;</span>
                    {employeeNameError && <span className="manage_branchNameNoError-msg" >{employeeNameError}</span>}
                </div><br />
                <div >
                    <label className='Second_Item' >性別：</label>
                    <select className='Input_Item' value={gender} onChange={(e) => setGender(e.target.value)}>
                        <option>男</option>
                        <option>女</option>
                    </select>
                    <label className='Block_Item2'>年齢：</label>
                    <input className='Input_Item2' value={calculateAge(birthday)} disabled addonAfter="岁"/>
                   
                   
                    <Button onClick={handleButtonClick} style={{marginLeft:'5px', display:'inline-block'}}>カレンダー</Button>
                    {/*？ */}{isDatePickerVisible && (<DatePicker disabledDate={disabledDate} onChange={handleBirthdayChange} onCancel={handleDatePickerCancel} style={{marginLeft:'10px',display:'inline-block', height:'22px'}}/>)}

                </div><br />

              
                <div>
                    <label className='Second_Item'>入社年月：</label>
                        <select className='Input_Item' value={joinConpanyYear} onChange={(e) => setJoinConpanyYear(e.target.value)}>
                            <option></option>
                            <option>2015</option>
                            <option>2016</option>
                            <option>2017</option>
                            <option>2018</option>
                            <option>2019</option>
                            <option>2020</option>
                            <option>2021</option>
                            <option>2022</option>
                            <option>2023</option>
                            <option>2024</option>
                        </select>
                    <label className='Year_Item'>年</label>
                        <select className='Input_Item' value={joinConpanyMonth} onChange={(e) => setJoinConpanyMonth(e.target.value)}>
                            <option></option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                            <option>8</option>
                            <option>9</option>
                            <option>10</option>
                            <option>11</option>
                            <option>12</option>
                        </select>
                    <label className='Year_Item'>月</label>
                    {joinConpanyError && <span className="manage_branchNameNoError-msg" >{joinConpanyError}</span>}
                </div><br />
                <div>
                    <label className='Second_Item'>扶養人数：</label>
                    <select className='Input_Item' value={dependent} onChange={(e) => setDependent(e.target.value)}>
                        <option>0</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                    </select>
                    <label className='Block_Item2'>給料：</label>
                    <input className='Input_Item' value={salary} maxLength={6}  onClick={handleClearSalary} onBlur={handleSalary} onChange={(e) => setSalary(e.target.value)} />
                    <label className=''> 円</label>
                    {salaryError && <span className="manage_branchNameNoError-msg" >{salaryError}</span>}
                </div><br />
                <div>
                    <label className='Block_Item'>初期パスワード：</label>
                    <input className='Pwd_Input_Item' maxLength={30} value={firstPwd} onChange={(e) => setFirstPwd(e.target.value)} />
                    <span style={{ color: 'red', marginLeft: '5px' }}>&#9733;</span>
                    {firstPwdError && <span className="manage_branchNameNoError-msg" >{firstPwdError}</span>}
                </div><br />
                <div>
                    <label className='Block_Item'>社用メールアドレス：</label>
                    <input className='Personal_Mail_Input' maxLength={20} value={companyMail} onChange={(e) => setCompanyMail(e.target.value)} />
                    <label className=''>@lyc.co.jp</label>
                    {companyMailError && <span className="manage_branchNameNoError-msg" >{companyMailError}</span>}
                </div><br />
                <div>
                    <label className='Block_Item'>私用メールアドレス：</label>
                    <input className='Mail_Item' maxLength={30} value={personalMail} onChange={(e) => setPersonalMail(e.target.value)} />
                    {personalMailError && <span className="manage_branchNameNoError-msg" >{personalMailError}</span>}
                </div><br />
                <div>
                    <label className='Second_Item'>電話番号：</label>
                    <input className='tel_Item' value={tel1} maxLength={3} onChange={(e) => setTel1(e.target.value)} /><span className='tel_Item2'>-</span>
                    <input className='tel_Item3' value={tel2} maxLength={4} onChange={(e) => setTel2(e.target.value)} /><span className='tel_Item2'>-</span>
                    <input className='tel_Item3' value={tel3} maxLength={4} onChange={(e) => setTel3(e.target.value)} />
                    {telError && <span className="manage_branchNameNoError-msg" >{telError}</span>}
                </div><br />
                <div>
                    <label className='Second_Item'>権限：</label>
                    <select className='Input_Item' value={authority} onChange={(e) => setAuthority(e.target.value)}>
                        <option>社員</option>
                        <option>管理者</option>
                    </select>
                    <span style={{ color: 'red', marginLeft: '5px' }}>&#9733;</span>
                    {authorityError && <span className="manage_branchNameNoError-msg" >{authorityError}</span>}
                </div><br />
                <div className='Text_Item'><strong>住所情報</strong></div><br /> <br />
                <div>
                    <label className='address_Item'>郵便番号：〒</label>
                    <input className='tel_Item' value={postNo1} maxLength={3} onChange={(e) => setPsotNo1(e.target.value)} /><span className='tel_Item2'>-</span>
                    <input className='tel_Item3' value={postNo2}  maxLength={4} onChange={(e) => setPsotNo2(e.target.value)} />
                    <button className='address_Btn' onClick={handlePost} type='onClick'>検索</button>
                    {postNoError && <span className="manage_branchNameNoError-msg" >{postNoError}</span>}
                </div><br />
                <div>
                    <label className='Block_Item'>都道府県+市区町村：</label>
                    <input className='address_input' disabled={prefecturesIsDisabled} value={prefectures} onChange={(e) => setPrefectures(e.target.value)} />           
                </div><br />
                <div>
                    <label className='Block_Item'>以降の住所：</label>
                    <input className='address_input2' disabled={addressIsDisabled} value={address} onChange={(e) => setAddress(e.target.value)} />
                </div><br />
                <div>
                    <label className='Second_Item'>寄り駅：</label>
                    <input className='Input_Item' value={station} onChange={(e) => setStation(e.target.value)} />
                </div><br />
                <div className='Text_Item'><strong>口座情報</strong></div><br /> <br />
                <div>
                    <label className='Second_Item'>銀行名：</label>
                <select className='Bank_Input_Item' value={bankNo} onBlur={handleBankInfo} onChange={(e) => setBankNo(e.target.value)} >
                    <option></option>
                    <option value={1}>東京三菱UFJ銀行</option>
                    <option value={2}>みずほ銀行</option>
                    <option value={3}>三井銀行</option>
                    <option value={4}>郵政銀行</option>
                </select>
                  
                </div><br />
                <div>
                    <label className='Second_Item'>支店名：</label>
                    <input className='Input_Item' disabled={branchNameIsDisabled} onBlur={handleBranchName} value={branchName} onChange={(e) => setBranchName(e.target.value)} />
                    <label className='Branch_No_Item'>支店番号：</label>
                    <input className='Branch_Item' disabled={branchNoIsDisabled}  onBlur={handleBranchNo} value={branchNo} onChange={(e) => setBranchNo(e.target.value)} />
                    {branchNameNoError && <span className="manage_branchNameNoError-msg" >{branchNameNoError}</span>}
                </div><br />
                <div>
                    <label className='Second_Item'>口座番号：</label>
                    <input className='Input_Item' maxLength={5} disabled={accountNoIsDisabled} value={accountNo} onChange={(e) => setAccountNo(e.target.value)} />
                    <label className='Bank_Item2'>口座名義人：</label>
                    <input className='BankInput_Item' maxLength={5} disabled={accountIsDisabled} value={accountName} onChange={(e) => setAccountName(e.target.value)} />
                    {accountError && <span className="manage_branchNameNoError-msg" >{accountError}</span>}
                </div><br />

                <div className="Manage_Btn">
                <button type="submit" className='submitM1'>ログイン</button>
                </div>
                <div>
                </div>
            </form>
        </div>
    )
}
export default Manage;
