package com.yaorugang.weather.ui.databinding

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter("selectedPosition")
fun setTabLayoutSelected(tabLayout: TabLayout, selectedPosition: Int) {
    val tab = tabLayout.getTabAt(selectedPosition)
    tabLayout.selectTab(tab)
}