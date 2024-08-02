import React from 'react';
import { useLocation } from 'react-router-dom';

const Master = function(){
    const location = useLocation();
    const id = new URLSearchParams(location.search).get('id');


    return(
       <div>

        <div >
            <span className='Master_Top1'>LYC株式会社</span>
            <span className='Master_Top2'><a href=''>Logout</a></span>
        </div>
        <br/><br/>
        <h1 className="system-title">社員勤務管理システム</h1>
        <h5 className="system-title">メニュー一覧</h5>
        <br/>
        <div className='Master_Box'>管理者：<span>李四</span></div>
        <div className='First_Row'>
            <span><input  className='Btn1' type='button' value='実績入力'/></span>
            <span><input  className='Btn0' type='button' value='社員情報管理'/></span>
        </div>
        <div className='First_Row'>
        <span><input  className='Btn2' type='button' value='実績入力'/></span>
        </div>
        <div className='First_Row'>
            <span><input  className='Btn1' type='button' value='パスワードリセット'/></span>
            <span><input  className='Btn1' type='button' value='実績検索'/></span>
        </div>
        <div >
            <span className='Master_Bottom'><a href="http://localhost:3000/Login">ログイン画面に戻る</a></span>
        </div>
       

        
       </div>
    )


}

export default Master;
