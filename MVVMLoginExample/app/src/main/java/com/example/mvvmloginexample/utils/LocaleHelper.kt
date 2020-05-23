package com.example.mvvmloginexample.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.utils.MySharedPref
import java.util.*
import javax.inject.Inject


object LocaleHelper {

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }
    @set:Inject
    var mysharedPref: MySharedPref? = null;

    fun onAttach(context: Context): Context {
        return setLocale(context, mysharedPref?.getStringData("lang"))
    }

    fun onAttach(
        context: Context,
        defaultLanguage: String
    ): Context {
        return setLocale(context, defaultLanguage)
    }


    fun setLocale(
        context: Context,
        language: String?
    ): Context {
        Log.e("lang",language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }



    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(
        context: Context,
        language: String?
    ): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration =
            context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(
        context: Context,
        language: String?
    ): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}