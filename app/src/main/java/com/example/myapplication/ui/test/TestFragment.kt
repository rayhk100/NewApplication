package com.example.myapplication.ui.test

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.myapplication.R
import com.example.myapplication.databinding.TestFragmentBinding
import com.example.myapplication.ui.home.HomeFragmentDirections

class TestFragment : Fragment() {

    private lateinit var binding: TestFragmentBinding

    companion object {
        fun newInstance() = TestFragment()
    }

    private lateinit var viewModel: TestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@TestFragment
            vm = ViewModelProviders.of(this@TestFragment).get(TestViewModel::class.java)
        }
//        return inflater.inflate(R.layout.test_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        // TODO: Use the ViewModel

        (arguments?.get("test") as? String).let { str ->

        }

        findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavTest("test"))
    }

}
