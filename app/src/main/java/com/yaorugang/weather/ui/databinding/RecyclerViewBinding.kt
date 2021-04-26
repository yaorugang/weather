package com.yaorugang.weather.ui.databinding

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaorugang.weather.R
import com.yaorugang.weather.databinding.CountryItemBinding
import com.yaorugang.weather.databinding.CountrySelectionFragmentBinding
import com.yaorugang.weather.databinding.WeatherReportItemBinding
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.domain.models.WeatherReport

@BindingAdapter("weatherReports")
fun setWeatherReports(recyclerView: RecyclerView, weatherReports: List<WeatherReport>?) {
    weatherReports?.let {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
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

@BindingAdapter("countries", "selectedPosition")
fun setCountries(recyclerView: RecyclerView, countries: List<Country>?, selectedPosition: Int) {
    countries?.let {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = CountriesAdapter(it, selectedPosition, ContextCompat.getDrawable(context, R.drawable.ic_green_tick))
        }
    }
}

private class CountriesAdapter(private val countries: List<Country>, private val selectedPosition: Int, private val tickDrawable: Drawable?): RecyclerView.Adapter<CountriesAdapter.CountryItemViewHolder>() {

    class CountryItemViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.binding.countryTextview.text = countries[position].name
        holder.binding.countryTextview.setCompoundDrawables(null, null, null, if (position == selectedPosition) tickDrawable else null)
    }

    override fun getItemCount(): Int = countries.size
}