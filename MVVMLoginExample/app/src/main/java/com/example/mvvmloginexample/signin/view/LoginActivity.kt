package com.example.mvvmloginexample.signin.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.view.MainDrawerActivity
import com.example.mvvmloginexample.databinding.ActivitySigninBinding
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.signin.viewmodel.LoginViewModel
import com.example.mvvmloginexample.utils.LocaleHelper
import com.example.mvvmloginexample.utils.MySharedPref
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(){

   lateinit var loginViewModel: LoginViewModel;
   lateinit var activitySigninBinding:ActivitySigninBinding;

    @set:Inject
    var mysharedPref: MySharedPref? = null;


    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base!!))
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        activitySigninBinding = DataBindingUtil.setContentView(this, R.layout.activity_signin)

        activitySigninBinding.loginViewModel = loginViewModel;

        loginViewModel.getUser().observe(this,
            Observer { loginUser ->
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
             if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strUserName)) {
                    activitySigninBinding.txtEmailAddress.error = "Enter an E-Mail Address"
                    activitySigninBinding.txtEmailAddress.requestFocus()
                } else if (!loginUser.isUserNameValid()) {
                    activitySigninBinding.txtEmailAddress.error = "Enter a Valid E-mail Address"
                    activitySigninBinding.txtEmailAddress.requestFocus()
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strPassword)) {
                    activitySigninBinding.txtPassword.error = "Enter a Password"
                    activitySigninBinding.txtPassword.requestFocus()
                } else if (!loginUser.isPasswrodValid()) {
                    activitySigninBinding.txtPassword.error = "Enter at least 6 Digit password"
                    activitySigninBinding.txtPassword.requestFocus()
                } else {
                    if(loginUser.strServerMessage!="")
                    {
                        val snackbar: Snackbar = Snackbar
                            .make(activitySigninBinding.coordinatorLayout as View, loginUser.strServerMessage,
                                Snackbar.LENGTH_LONG)


                        snackbar.show()

                    }
                 else
                    {

                        activitySigninBinding.lblEmailAnswer.text = loginUser.strUserName
                        activitySigninBinding.lblPasswordAnswer.text = loginUser.strPassword
                        mysharedPref?.putData("login",1)

                        mysharedPref?.putStringData("name",loginUser.strUserName)

                        val intent = Intent(this, MainDrawerActivity::class.java);

                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent);
                    }
             }
            })


    }
}