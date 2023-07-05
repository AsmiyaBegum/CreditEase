package com.example.creditease.utils

import android.view.View
import com.example.creditease.CreditEaseApplication
import com.jakewharton.rxbinding.BuildConfig

object Utils {


    fun View.showVisibility(condition: Boolean) {
        this.visibility = if (condition) View.VISIBLE else View.GONE
    }

    fun isBuildVariantDebug(): Boolean {
        return BuildConfig.BUILD_TYPE == Constants.BUILD_TYPE_DEBUG
    }

}

fun Double.removeTrailingZeros(): String {
    return this.toString().replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
}

fun Double.appendCurrencySymbol() : String {
    return CreditEaseApplication.currencySymbol + " " +this
}

fun String.appendCurrencySymbol() : String {
    return CreditEaseApplication.currencySymbol + " " +this
}