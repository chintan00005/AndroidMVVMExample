package com.example.mvvmloginexample.browsablePage.view.ui.personlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmloginexample.R
import com.example.mvvmloginexample.browsablePage.model.Person
import com.example.mvvmloginexample.databinding.PersonItemBinding

class PersonAdapter(var context: Context, var list: ArrayList<Person.Data>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val binding: PersonItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.person_item, parent, false)
        return PersonAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(val binding: PersonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Person.Data) {
            binding.personDataViewModel = data;
        }
    }

}