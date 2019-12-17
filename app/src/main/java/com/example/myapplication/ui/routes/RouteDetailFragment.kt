package com.example.myapplication.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.RouteInfo
import com.example.myapplication.databinding.FragmentRoutesDetailBinding
import com.example.myapplication.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RouteDetailFragment : Fragment() {

    lateinit var binding: FragmentRoutesDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoutesDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RouteDetailFragment
        }

        arguments?.getParcelable<RouteInfo>("routeInfo")?.let {
            binding.vm = RouteDetailViewModel(it)
            MainScope().launch { fetchStops(it) }
        }
        return binding.root
    }

    suspend fun fetchStops(routeInfo: RouteInfo) = withContext(Dispatchers.IO) {
        val stops = AppDatabase.get().routeStopDao().findStopsByTitleBoundServiceType(routeInfo.id.title, routeInfo.id.bound, routeInfo.id.serviceType)
        MainScope().launch {
            (binding.vm as RouteDetailViewModel).stops.addAll(stops)
        }
    }}