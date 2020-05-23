package com.example.mvvmloginexample.injector


import com.example.mvvmloginexample.utils.LocaleHelper
import com.example.mvvmloginexample.browsablePage.model.RetrofitBuilder
import com.example.mvvmloginexample.browsablePage.view.MainDrawerActivity
import com.example.mvvmloginexample.browsablePage.view.ui.changelang.ChangeLangFragment
import com.example.mvvmloginexample.browsablePage.viewmodel.ChangeLangViewModel
import com.example.mvvmloginexample.browsablePage.viewmodel.PersonViewModel
import com.example.mvvmloginexample.browsablePage.viewmodel.TermsViewModel
import com.example.mvvmloginexample.browsablePage.viewmodel.ToolViewModel
import com.example.mvvmloginexample.signin.model.LoginUser
import com.example.mvvmloginexample.signin.view.LoginActivity
import com.example.mvvmloginexample.signin.viewmodel.LoginViewModel
import com.example.mvvmloginexample.splash.view.SplashActivity
import com.example.mvvmloginexample.utils.SharedPreferencesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(LoginUser::class),(RetrofitBuilder::class),(SharedPreferencesModule::class)])
interface MyInjectorViewModel {

    fun inject(loginViewModel:LoginViewModel)
    fun inject(toolViewModel:ToolViewModel)
    fun inject(termViewModel:TermsViewModel)
    fun inject(personViewModel: PersonViewModel)
    fun inject(loginActivity: LoginActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(mainDrawerActivity: MainDrawerActivity)
    fun inject(changeLangViewModel: ChangeLangViewModel)
    fun inject(localeHelper: LocaleHelper)
    fun inject(changeLangFragment: ChangeLangFragment)

}
