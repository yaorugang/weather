package com.yaorugang.weather.ui.databinding

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaorugang.weather.R
import com.yaorugang.weather.databinding.CountryItemBinding
import com.yaorugang.weather.domain.models.Country
import com.yaorugang.weather.ui.fragments.CountrySelectionViewModel

@BindingAdapter("countryItems")
fun setCountries(recyclerView: RecyclerView, countries: List<CountrySelectionViewModel.CountryItem>?) {
    countries?.let {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = CountriesAdapter(it, ContextCompat.getDrawable(context, R.drawable.ic_green_tick))
        }
    }
}

private class CountriesAdapter(private val countryItems: List<CountrySelectionViewModel.CountryItem>, private val tickDrawable: Drawable?): RecyclerView.Adapter<CountriesAdapter.CountryItemViewHolder>() {

    class CountryItemViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.binding.countryItem = countryItems[position]
    }

    override fun getItemCount(): Int = countryItems.size
}