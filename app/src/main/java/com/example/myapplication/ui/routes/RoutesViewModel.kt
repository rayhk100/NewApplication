package com.example.myapplication.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Route

class RoutesViewModel() : ViewModel() {
    constructor(route:Route) : this() {
        _title.value = route.title
        _bounds.value = route.bounds
    }

    private val _title = MutableLiveData<String>().apply {
        value = ""
    }
    val title: LiveData<String> = _title

    private val _bounds = MutableLiveData<List<Int>>().apply {
        value = listOf()
    }
    val bounds: LiveData<List<Int>> = _bounds
}