package com.example.mvvmloginexample.browsablePage.view.ui.startintent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.viewmodel.StartIntentViewModel
import com.example.mvvmloginexample.databinding.OpenintentLayoutBinding


class StartIntentFragment : Fragment() {

    private lateinit var startIntentViewModel: StartIntentViewModel
    private lateinit var openIntentBinding: OpenintentLayoutBinding;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startIntentViewModel =
            ViewModelProvider(this).get(StartIntentViewModel::class.java)
        openIntentBinding = DataBindingUtil.inflate(inflater,R.layout.openintent_layout, container, false)


        openIntentBinding.button.setOnClickListener {
            onLunchAnotherApp()
        }
        return openIntentBinding.root
    }

    fun onLunchAnotherApp() {
//        val appPackageName: String? =
//            activity?.applicationContext?.packageName
        val appPackageName: String? ="com.awsomeproject"

        val intent: Intent? = appPackageName?.let {
            activity?.packageManager?.getLaunchIntentForPackage(
                it
            )
        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("my_text", "This is my text to send.");
            startActivity(intent)
        } else {
            if (appPackageName != null) {
                onGoToAnotherInAppStore(intent, appPackageName)
            }
        }
    }

    private fun onGoToAnotherInAppStore(intent: Intent?, appPackageName: String) {
        var intent = intent
        try {
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("market://details?id=$appPackageName")
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")
            startActivity(intent)
        }
    }
}