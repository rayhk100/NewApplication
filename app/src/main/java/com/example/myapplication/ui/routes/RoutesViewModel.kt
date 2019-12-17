package com.example.myapplication.ui.routes

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.RouteInfo
import com.example.myapplication.model.Route
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RoutesViewModel() : ViewModel() {
    constructor(route:Route) : this() {
        _title.value = route.title
        route.routeInfo?.let { items.addAll(it.cellSet().map { it.value }) }
    }

    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title

    val items: ObservableList<RouteInfo> = ObservableArrayList()
    val itemBinding = ItemBinding.of<RouteInfo>(BR.item, R.layout.fragment_routes_item_subitem)
}