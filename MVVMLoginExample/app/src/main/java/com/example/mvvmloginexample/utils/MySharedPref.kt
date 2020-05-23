package com.example.mvvmloginexample.utils

import android.content.SharedPreferences
import dagger.Provides

import javax.inject.Inject




class MySharedPref {

    private var mSharedPreferences: SharedPreferences? = null

    @Inject
    constructor(mSharedPreferences: SharedPreferences?) {
        this.mSharedPreferences = mSharedPreferences
    }


    fun putData(key: String?, data: Int) {
        mSharedPreferences!!.edit().putInt(key, data).apply()
    }

    fun putStringData(key: String?, data: String) {
        mSharedPreferences!!.edit().putString(key, data).apply()
    }

    fun getData(key: String?): Int {
        return mSharedPreferences!!.getInt(key, 0)
    }

    fun getStringData(key: String?): String? {
        return mSharedPreferences!!.getString(key, "")
    }

}