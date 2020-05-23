package com.example.mvvmloginexample.browsablePage.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmloginexample.browsablePage.model.Api
import com.example.mvvmloginexample.browsablePage.model.Tool
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class ToolViewModel : ViewModel(){

    @set:Inject
     var api: Api? = null;

     var liveToolData: MutableLiveData<Tool>? = null;

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }

    fun getTool(): LiveData<Tool> {
        if (liveToolData == null) {
            liveToolData = MutableLiveData<Tool>()
            loadTool()
        }
        return liveToolData as MutableLiveData<Tool>
    }

    private fun loadTool():Unit{

        val call = api?.getTool

        call?.enqueue(object : Callback<Tool> {
            override fun onResponse(call: Call<Tool>, response: Response<Tool>) {
                liveToolData?.value = response.body()
            }

            override fun onFailure(call: Call<Tool>, t: Throwable) {
                throw Exception(t);

            }
        })
    }

}