package com.example.mvvmloginexample.browsablePage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmloginexample.browsablePage.model.Api
import com.example.mvvmloginexample.browsablePage.model.Person
import com.example.mvvmloginexample.browsablePage.model.Tool
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class PersonViewModel: ViewModel(){

    @set:Inject
    var api: Api? = null;

    var liveToolData: MutableLiveData<Person>? = null;

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }

    fun getPerson(): LiveData<Person> {
        if (liveToolData == null) {
            liveToolData = MutableLiveData<Person>()
            loadPerson("1")
        }
        return liveToolData as MutableLiveData<Person>
    }

    fun loadMoreData(page:String):Unit{
        loadPerson(page);
    }

    private fun loadPerson(page:String):Unit{

        val call = api?.getPerson(page)

        call?.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                liveToolData?.value = response.body()
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                throw Exception(t);

            }
        })
    }

}