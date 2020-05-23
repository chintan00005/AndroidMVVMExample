package com.example.mvvmloginexample.browsablePage.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmloginexample.injector.AndroidApplicationClass

class ChangeLangViewModel : ViewModel(){
    var changeLang = MutableLiveData<String>();
    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }
    fun onClickEnglish(view: View):Unit{
        AndroidApplicationClass.changeLange("en")
        changeLang.value="en"
    }
    fun onClickSpanish(view: View):Unit{
        AndroidApplicationClass.changeLange("es")
        changeLang.value="es"
    }
}