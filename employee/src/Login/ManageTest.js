import React, { createContext, useContext, useState, useRef, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import axios from 'axios';
import { useSelector } from 'react-redux';


const ManageEmployee = function(){

    return(
       <div>
        <div>
            <span className='Master_Top1'>LYC株式会社</span>
            <span className='Master_Top2'><a href=''>Logout</a></span>
        </div><br/><br/>

        <h1 className="system-title">社員勤務管理システム</h1>
        <h4 className="system-title">社員情報管理画面</h4>
        {/* <div className='Manage_Row'>{`${userRole == 'admin' ? '管理者' : '社員'}`}: <span>{user.username}</span></div><br/><br/>
        <span className='Master_Bottom'><a href="/Master">前の画面に戻る</a></span> */}
       
       <form onSubmit={ManageEmployee}>
       
        <div className='Item_Div_Row'>
            <button className='Manage_Item'>追加</button>
            <button className='Manage_Item'>修正</button>
            <button className='Manage_Item'>削除</button>
            <button className='Manage_Item'>詳細検索画面へ</button>
        </div>
        <br/>
        
        <div className='MainDiv'>
        <div >
            <label className='Block_Item'>社員番号：</label>
            <input className='Input_Item'/>
            <span className='Block_OR_Item'>OR</span>
            <label className='Block_Item2'>社員名：</label>
            <input className='Input_Item2'/>
        </div><br/>        
        <div className="line"></div><br/> 
        <div>
            <label className='Second_Item'>社員番号：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>社員名：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>性別：</label>
            <input className='Input_Item'/>
            <label className='Block_Item2'>年齢：</label>
            <input className='Input_Item2'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>入社年月：</label>
            <input className='Input_Item'/>
            <label className='Year_Item'>年</label>
            <input className='Input_Item'/>
            <label className='Year_Item'>月</label>
        </div><br/> 
        <div>
            <label className='Second_Item'>扶養人数：</label>
            <input className='Input_Item'/>
            <label className='Block_Item2'>給料：</label>
            <input className='Input_Item'/>
            <label className=''> 円</label>
        </div><br/> 
        <div>
            <label className='Block_Item'>初期パスワード：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div>
            <label className='Block_Item'>社用メールアドレス：</label>
            <input className='Input_Item'/>
            <label className=''>@lyc.co.jp</label>
        </div><br/> 
        <div>
            <label className='Block_Item'>私用メールアドレス：</label>
            <input className='Mail_Item'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>電話番号：</label>
            <input className='tel_Item'/><span className='tel_Item2'>-</span> 
            <input className='tel_Item3'/><span className='tel_Item2'>-</span> 
            <input className='tel_Item3'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>権限：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div className='Text_Item'><strong>住所情報</strong></div><br/> <br/>
        <div>
            <label className='address_Item'>郵便番号：〒</label>
            <input className='tel_Item'/><span className='tel_Item2'>-</span> 
            <input className='tel_Item3'/>
            <button className='address_Btn'>検索</button>

        </div><br/> 
        <div>
            <label className='Block_Item'>都道府県+市区町村：</label>
            <input className='address_input'/>
        </div><br/> 
        <div>
            <label className='Block_Item'>以降の住所：</label>
            <input className='address_input2'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>寄り駅：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div className='Text_Item'><strong>口座情報</strong></div><br/> <br/>
        <div>
            <label className='Second_Item'>銀行名：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>支店名：</label>
            <input className='Input_Item'/>
            <label className='Block_Item2'>支店番号：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div>
            <label className='Second_Item'>口座番号：</label>
            <input className='Input_Item'/>
            <label className='Block_Item2'>口座名義人：</label>
            <input className='Input_Item'/>
        </div><br/> 
        <div className="Item_Div_Row">
          <button type="submit" >ログイン</button>
        </div><br/>
        
        </div>        
        </form>
    </div> 
)}
export default ManageEmployee;
