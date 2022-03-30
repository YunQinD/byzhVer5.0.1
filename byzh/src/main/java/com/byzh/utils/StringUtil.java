package com.byzh.utils;
import java.lang.String;

public class StringUtil {

    //验证密码是否为合法字符（！—— z）
    public boolean isPwd(String pwd){
        for(int i = 0;i < pwd.length();i++){
            if('!' >= pwd.charAt(i) && pwd.charAt(i) >= 'z'){
                return false;
            }
        }
        return true;
    }
}
