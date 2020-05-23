package com.example.mvvmloginexample.signin.model

import android.util.Patterns
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginUser() {
//    constructor(strUserName: String, strPassword: String) {
//        this.strUserName = strUserName
//        this.strPassword = strPassword
//    }


    @Provides
    @Singleton
    fun getModel():LoginUser{
        return LoginUser();
    }

    var strUserName:String="";
    var strPassword:String="";
    var strServerMessage:String="";


    @Provides
    @Singleton
    fun isUserNameValid():Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(strUserName).matches();
    }


    @Provides
    @Singleton
    fun isPasswrodValid():Boolean{
        return strPassword.length>5;
    }

}