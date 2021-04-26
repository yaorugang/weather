package com.yaorugang.weather.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.yaorugang.weather.databinding.WeatherReportsFragmentBinding
import com.yaorugang.weather.ui.utils.EventObserver
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class WeatherReportsFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: WeatherReportsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: WeatherReportsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = WeatherReportsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.sortingTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> viewModel.onSortByAlphabetic()
                    1 -> viewModel.onSortByTemperature()
                    2 -> viewModel.onSortByLastUpdate()
                    else -> { }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewModel.navigateToCountrySelection.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(WeatherReportsFragmentDirections.actionToCountrySelectionDialog())
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
}