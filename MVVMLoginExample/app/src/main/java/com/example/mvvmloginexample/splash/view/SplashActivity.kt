package com.example.mvvmloginexample.splash.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.view.MainDrawerActivity
import com.example.mvvmloginexample.databinding.SplashActivityBinding
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.signin.view.LoginActivity
import com.example.mvvmloginexample.utils.LocaleHelper
import com.example.mvvmloginexample.utils.MySharedPref
import javax.inject.Inject


class SplashActivity : AppCompatActivity(){

    private lateinit var splashActivityBinding:SplashActivityBinding;

    @set:Inject
    var mysharedPref: MySharedPref? = null;

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashActivityBinding = DataBindingUtil.setContentView(this,R.layout.splash_activity)

        Handler().postDelayed(Runnable {

            if(mysharedPref?.getData("login")==1)
            {
                val i = Intent(this@SplashActivity, MainDrawerActivity::class.java)
                startActivity(i)
                finish()
            }
            else
            {
                val i = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }

        }, 3000)
        val myanim: Animation = AnimationUtils.loadAnimation(this, R.anim.splshanimation)
        splashActivityBinding.imageViewSplash?.startAnimation(myanim)
    }
}