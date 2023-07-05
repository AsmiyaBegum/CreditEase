package com.example.creditease.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.creditease.R
import com.example.creditease.model.CreditCardDetail
import com.example.creditease.utils.Constants
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class HomeViewModel : ViewModel() {

    private val _cardDueDetails = MutableLiveData<List<CreditCardDetail>>().apply {
        value = listOf(
            CreditCardDetail("Axis Platinum",Constants.CARD_TYPE_CREDIT,30000.06,false,"Overdue by 1 Day", R.drawable.ic_axis_logo),
            CreditCardDetail("SBI Simply Click",Constants.CARD_TYPE_CREDIT,30000.06,false,"Due in 3 Days", R.drawable.ic_sbi_logo),
            CreditCardDetail("BOB Credit Platinum",Constants.CARD_TYPE_CREDIT,0.0,true,"", R.drawable.ic_bob)
        )
    }
    val cardDueDetails: LiveData<List<CreditCardDetail>> = _cardDueDetails

}