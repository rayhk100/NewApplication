package com.example.myapplication.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Route


class RoutesViewModel() : ViewModel() {
    constructor(route: Route) : this() {
        _title.value = route.title
    }
    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title
}