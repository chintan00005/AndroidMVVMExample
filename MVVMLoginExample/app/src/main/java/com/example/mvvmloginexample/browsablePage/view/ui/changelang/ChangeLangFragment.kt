package com.example.mvvmloginexample.browsablePage.view.ui.changelang

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.viewmodel.ChangeLangViewModel
import com.example.mvvmloginexample.browsablePage.viewmodel.StartIntentViewModel
import com.example.mvvmloginexample.databinding.ChangeLangBinding
import com.example.mvvmloginexample.databinding.OpenintentLayoutBinding
import com.example.mvvmloginexample.injector.AndroidApplicationClass
import com.example.mvvmloginexample.utils.LocaleHelper
import com.example.mvvmloginexample.utils.MySharedPref
import javax.inject.Inject

class ChangeLangFragment : Fragment() {

    private lateinit var changeLangViewModel: ChangeLangViewModel
    private lateinit var changeLangBinding: ChangeLangBinding;


    @set:Inject
    var mysharedPref: MySharedPref? = null;

    init {
        AndroidApplicationClass.getDaggerComponent().inject(this);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changeLangViewModel =
            ViewModelProvider(this).get(ChangeLangViewModel::class.java)
        changeLangBinding = DataBindingUtil.inflate(inflater,
            R.layout.change_lang, container, false)

        changeLangBinding.changeLangViewModel =  changeLangViewModel


        changeLangViewModel.changeLang.observe(viewLifecycleOwner, Observer {
            if(it != mysharedPref?.getStringData("lang") && it!="")
            {
                mysharedPref?.putStringData("lang",it!!)
               val intent = activity?.intent
                activity?.finish()
                startActivity(intent)
            }
        })

        return changeLangBinding.root
    }
}