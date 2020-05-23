package com.example.mvvmloginexample.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.Module

import dagger.Provides


@Module
class SharedPreferencesModule(context: Context) {
    private val context: Context = context

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("mypref", Context.MODE_PRIVATE)
    }

}