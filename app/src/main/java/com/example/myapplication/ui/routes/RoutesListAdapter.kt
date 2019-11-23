package com.example.myapplication.ui.routes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.DataBoundListAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRoutesItemBinding
import com.example.myapplication.model.Route

class RoutesListAdapter() : DataBoundListAdapter<Route>(
    diffCallback = object: DiffUtil.ItemCallback<Route>() {
        override fun areItemsTheSame(oldItem: Route, newItem: Route) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Route, newItem: Route) = oldItem == newItem
    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int) = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.fragment_routes_item,
        parent,
        false
    ) as ViewDataBinding

    override fun bind(binding: ViewDataBinding, item: Route): Unit = when (binding) {
        is FragmentRoutesItemBinding -> {
            binding.vm = RoutesViewModel(item)
        }
        else -> {}
    }
}