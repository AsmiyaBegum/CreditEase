package com.example.creditease.utils

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import java.text.DecimalFormat


object MoneyFormatter {
    fun formatMoney(amount: Double): CharSequence {
        val decimalFormat = DecimalFormat("#,##0.00")
        val moneyText: String = decimalFormat.format(amount)
        val decimalIndex = moneyText.indexOf(".")
        val spannableBuilder = SpannableStringBuilder(moneyText)

        // Set the text after the decimal point to half the size
        spannableBuilder.setSpan(
            RelativeSizeSpan(0.8f),
            decimalIndex + 1,
            moneyText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make the whole text bold
        spannableBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            moneyText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableBuilder
    }
}