package com.example.creditease.model

import java.util.Date

data class CreditCardDetail(
    val cardName : String = "",
    val cardType : String = "",
    val dueAmount : Double = 0.0,
    val noBillFound : Boolean = false,
    val dueInfo : String = "",
    val cardLogo : Int = 0
)

data class FeaturedDeals(
    val shopLogo : Int = 0,
    val amount : Double = 0.0,
    val oneChipValue : Double = 0.0,
    val itemLogo : Int = 0,
    val backgroundColor : Int = 0
)