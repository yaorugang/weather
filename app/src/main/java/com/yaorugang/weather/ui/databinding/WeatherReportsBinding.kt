package com.yaorugang.weather.ui.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaorugang.weather.databinding.WeatherReportItemBinding
import com.yaorugang.weather.ui.fragments.WeatherReportsViewModel

@BindingAdapter("weatherItems")
fun setWeatherReports(recyclerView: RecyclerView, weatherItems: List<WeatherReportsViewModel.WeatherItem>?) {
    weatherItems?.let {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            if (itemDecorationCount < 1) {
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }
            adapter = WeatherReportsAdapter(it)
        }
    }
}

private class WeatherReportsAdapter(private val weatherItems: List<WeatherReportsViewModel.WeatherItem>) :
    RecyclerView.Adapter<WeatherReportsAdapter.WeatherReportItemViewHolder>() {

    class WeatherReportItemViewHolder(val binding: WeatherReportItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportItemViewHolder {
        return WeatherReportItemViewHolder(
            WeatherReportItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherReportItemViewHolder, position: Int) {
        holder.binding.weatherItem = weatherItems[position]
    }

    override fun getItemCount(): Int = weatherItems.size
}