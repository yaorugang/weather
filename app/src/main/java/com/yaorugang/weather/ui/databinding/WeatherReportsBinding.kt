package com.yaorugang.weather.ui.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaorugang.weather.databinding.WeatherReportItemBinding
import com.yaorugang.weather.domain.models.WeatherReport

@BindingAdapter("weatherReports")
fun setWeatherReports(recyclerView: RecyclerView, weatherReports: List<WeatherReport>?) {
    weatherReports?.let {
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

private class WeatherReportsAdapter(private val weatherReports: List<WeatherReport>): RecyclerView.Adapter<WeatherReportsAdapter.WeatherReportItemViewHolder>() {

    class WeatherReportItemViewHolder(val binding: WeatherReportItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportItemViewHolder {
        return WeatherReportItemViewHolder(WeatherReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WeatherReportItemViewHolder, position: Int) {
        holder.binding.suburb = weatherReports[position].suburbName
        holder.binding.weatherCondition = weatherReports[position].weatherCondition
        holder.binding.temperature = weatherReports[position].weatherTemp
    }

    override fun getItemCount(): Int = weatherReports.size
}