package com.example.mvvmloginexample.browsablePage.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.databinding.ActivityMainDrawerBinding
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.signin.view.LoginActivity
import com.example.mvvmloginexample.utils.LocaleHelper
import com.example.mvvmloginexample.utils.MySharedPref
import javax.inject.Inject


class MainDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var activityMainDrawerBinding: ActivityMainDrawerBinding;

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
        activityMainDrawerBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val navController = findNavController(R.id.nav_host_fragment)

        val header: View = activityMainDrawerBinding.navView.getHeaderView(0)
        val tvName:TextView = header.findViewById(R.id.textViewName)

        tvName.text =  mysharedPref?.getStringData("name");

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_aboutus, R.id.nav_terms,  R.id.nav_people, R.id.nav_open_intent, R.id.nav_change_lang
            ), activityMainDrawerBinding.drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        activityMainDrawerBinding.navView.setupWithNavController(navController)
        activityMainDrawerBinding.navView.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.nav_logout -> {
                    logout();
                    true;
                }
                R.id.nav_aboutus -> {
                    activityMainDrawerBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.nav_aboutus)
                    true;
                }
                R.id.nav_terms -> {
                    activityMainDrawerBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.nav_terms)
                    true;
                }
                R.id.nav_people -> {
                    activityMainDrawerBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.nav_people)
                    true;
                }
                R.id.nav_open_intent -> {
                    activityMainDrawerBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.nav_open_intent)
                    true;
                }
                R.id.nav_change_lang -> {
                    activityMainDrawerBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.nav_change_lang)
                    true;
                }
                else -> false
            }
        }
    }

    private fun logout(){
        mysharedPref?.putData("login",0)
        val intent = Intent(this, LoginActivity::class.java);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
