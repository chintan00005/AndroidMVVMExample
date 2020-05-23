package com.example.mvvmloginexample.injector

import com.example.mvvmloginexample.utils.LocaleHelper
import android.app.Application
import com.example.mvvmloginexample.browsablePage.model.RetrofitBuilder
import com.example.mvvmloginexample.signin.model.LoginUser
import com.example.mvvmloginexample.utils.SharedPreferencesModule


class AndroidApplicationClass :Application(){



    companion object{
        lateinit var myInjectorViewModel:MyInjectorViewModel
        lateinit var instanceApp:AndroidApplicationClass
        fun getInstance(): AndroidApplicationClass { return instanceApp }
        fun getDaggerComponent():MyInjectorViewModel{
            return myInjectorViewModel;
        }
        fun changeLange(lang:String){
            LocaleHelper.setLocale(instanceApp,lang);
        }

    }


    override fun onCreate() {
        super.onCreate();
        instanceApp=this;
        myInjectorViewModel = DaggerMyInjectorViewModel.builder().loginUser(LoginUser()).
            retrofitBuilder( RetrofitBuilder()).
        sharedPreferencesModule(SharedPreferencesModule(this)).build()


    }


}