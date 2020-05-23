package com.example.mvvmloginexample.signin.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmloginexample.browsablePage.model.Api
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.signin.model.Login
import com.example.mvvmloginexample.signin.model.LoginUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoginViewModel : ViewModel(){

    var emailAddress = MutableLiveData<String>();
    var password = MutableLiveData<String>();

    var userModel : MutableLiveData<LoginUser>? = null;

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }
    @set:Inject
    var loginUser: LoginUser? = null;

    @set:Inject
    var api:Api?=null


    fun login(loginUser:LoginUser):Unit{

        val call = api?.savePost(loginUser.strUserName,loginUser.strPassword)

        call?.enqueue(object : Callback<Login>{
            override fun onFailure(call: Call<Login>, t: Throwable) {
                throw  Exception(t);
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
               if(response.code()==200)
               {
                   loginUser.strServerMessage=""
                   userModel?.value = loginUser;
               }
                else
               {
                   loginUser.strServerMessage = if(response.message()!="")response.message() else "Something went wrong"
                   userModel?.value = loginUser;
               }

            }

        })
    }

   fun getUser():MutableLiveData<LoginUser>{
       if(userModel==null)
       {
            userModel = MutableLiveData<LoginUser>()
       }
       return userModel as MutableLiveData<LoginUser>;
   }

    fun onClick(view: View):Unit{
        var emailValue="";
        var passwordValue="";

        if(emailAddress.value!=null)
        {
            emailValue = emailAddress.value!!;
        }

        if(password.value!=null)
        {
            passwordValue = password.value!!;
        }

        loginUser!!.strPassword = passwordValue.trim();
        loginUser!!.strUserName = emailValue.trim();


        login(loginUser!!);
    }
}