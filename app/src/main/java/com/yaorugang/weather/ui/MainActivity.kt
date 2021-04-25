package com.yaorugang.weather.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yaorugang.weather.R
import com.yaorugang.weather.databinding.MainActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        viewModel.errorMessage.observe(this) {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.refresh_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            viewModel.fetchWeatherReports()
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
}