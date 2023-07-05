package com.example.creditease.ui.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creditease.R
import com.example.creditease.model.FeaturedDeals

class RewardsViewModel : ViewModel() {

    private val _featuredDeals = MutableLiveData<List<FeaturedDeals>>().apply {
        value = listOf(
            FeaturedDeals(R.drawable.ic_swiggy_logo,1000.0,0.50,R.drawable.ic_swiggy,R.color.swiggy_bg_color),
            FeaturedDeals(R.drawable.ic_subway_logo,1000.0,0.40,R.drawable.ic_subway,R.color.green),
            FeaturedDeals(R.drawable.ic_pizzahut_logo,1000.0,0.50,R.drawable.ic_pizzahut,R.color.pizzahut_bg_color)
           )
    }
    val featuredDeals: LiveData<List<FeaturedDeals>> = _featuredDeals

}