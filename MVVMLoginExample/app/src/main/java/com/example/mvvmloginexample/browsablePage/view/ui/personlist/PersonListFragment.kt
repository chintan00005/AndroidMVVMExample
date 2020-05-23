package com.example.mvvmloginexample.browsablePage.view.ui.personlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.model.Person
import com.example.mvvmloginexample.browsablePage.viewmodel.PersonViewModel
import com.example.mvvmloginexample.browsablePage.viewmodel.TermsViewModel
import com.example.mvvmloginexample.databinding.FragmentTermsBinding
import com.example.mvvmloginexample.databinding.PersonListBinding


class PersonListFragment : Fragment() {


    lateinit var personViewModel: PersonViewModel;
    lateinit var personListBinding : PersonListBinding;
    lateinit var personAdapter: PersonAdapter;
    lateinit var  personList : ArrayList<Person.Data>;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        personListBinding = DataBindingUtil.inflate(inflater,
            R.layout.person_list, container, false)

        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        personListBinding.recyclerView.layoutManager = LinearLayoutManager(activity);
        personList = ArrayList<Person.Data>();
        personAdapter = PersonAdapter(this!!.context!!, personList)
        personListBinding.recyclerView.adapter = personAdapter;
            personViewModel.getPerson().observe(viewLifecycleOwner, Observer {
                personList.addAll(it.data!!)
                personAdapter.notifyDataSetChanged()
        })

        return personListBinding.root
    }
}