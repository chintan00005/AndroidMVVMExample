package com.example.mvvmloginexample.browsablePage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmloginexample.browsablePage.model.Api
import com.example.mvvmloginexample.browsablePage.model.Term
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class TermsViewModel : ViewModel(){

@set:Inject
var api: Api? =null;

var liveTermData: MutableLiveData<Term>? = null

init {
    AndroidApplicationClass.getDaggerComponent().inject(this);
}

    fun getTerm():MutableLiveData<Term>{
        if(liveTermData==null)
        {
            liveTermData = MutableLiveData<Term>()
            loadTerm();
        }

        return liveTermData as MutableLiveData<Term>;
    }

    private fun loadTerm():Unit{
        val call = api?.getTerm

        call?.enqueue(object : Callback<Term> {
            override fun onFailure(call: Call<Term>, t: Throwable) {
                throw Exception(t)
            }

            override fun onResponse(call: Call<Term>, response: Response<Term>) {
                liveTermData?.value = response.body();
            }

        })

    }


}