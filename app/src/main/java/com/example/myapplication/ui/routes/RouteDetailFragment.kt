package com.example.myapplication.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentRoutesDetailBinding

class RouteDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRoutesDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RouteDetailFragment }

        binding.route = arguments?.getString("route")

        return binding.root
    }
}