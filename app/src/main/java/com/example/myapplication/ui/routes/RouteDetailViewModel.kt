package com.example.myapplication.ui.routes

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.RouteInfo
import com.example.myapplication.model.Stop
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RouteDetailViewModel() : ViewModel() {
    constructor(routeInfo: RouteInfo) : this() {
        _title.value = routeInfo.id.title
        _source.value = routeInfo.source
        _destination.value = routeInfo.destination
    }

    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title

    private val _source = MutableLiveData<String>().apply {
        value = ""
    }
    val source: LiveData<String> = _source

    private val _destination = MutableLiveData<String>().apply {
        value = ""
    }
    val destination: LiveData<String> = _destination

    val stops: ObservableList<Stop> = ObservableArrayList()
    val itemBinding = ItemBinding.of<Stop>(BR.stop, R.layout.fragment_route_detail_item)
}