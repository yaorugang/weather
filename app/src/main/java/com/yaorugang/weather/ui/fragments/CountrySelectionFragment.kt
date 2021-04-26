package com.yaorugang.weather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yaorugang.weather.databinding.CountrySelectionFragmentBinding
import com.yaorugang.weather.ui.utils.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CountrySelectionFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CountrySelectionViewModel by viewModels { viewModelFactory }

    private lateinit var binding: CountrySelectionFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CountrySelectionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateBack.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigateUp()
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
}