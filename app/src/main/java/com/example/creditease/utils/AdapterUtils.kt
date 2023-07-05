package com.example.creditease.utils

import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import com.example.creditease.CreditEaseApplication
import com.example.creditease.R
import com.example.creditease.databinding.CreditCardListRowBinding
import com.example.creditease.databinding.DealsListLayoutBinding
import com.example.creditease.model.CreditCardDetail
import com.example.creditease.model.FeaturedDeals
import com.example.creditease.utils.Utils.showVisibility


object AdapterUtils {
    fun setDueCardListAdapter(dueCardList: List<CreditCardDetail>): GenericAdapter<CreditCardDetail, CreditCardListRowBinding, List<Unit>> {

        val adapter = GenericAdapter(R.layout.credit_card_list_row,
            object :
                GenericAdapterInteraction<CreditCardDetail, CreditCardListRowBinding, List<Unit>>() {

                override fun bindingViewHolder(
                    binding: CreditCardListRowBinding,
                    data: CreditCardDetail,
                    holder: GenericAdapter.GenericViewHolder<CreditCardDetail, CreditCardListRowBinding, List<Unit>>,
                    additionalData: List<Unit>?
                ) {
                    binding.creditCardName.text = data.cardName
                    binding.creditCard.text = data.cardType
                    binding.creditCardImg.background = ResourcesCompat.getDrawable(binding.root.resources, data.cardLogo, null)
                    showNoBillFound(data.noBillFound, binding)
                    bindDueAmountAndDueByDay(data, binding)
                }

                private fun bindDueAmountAndDueByDay(
                    data: CreditCardDetail,
                    binding: CreditCardListRowBinding
                ) {
                    if (!data.noBillFound) {
                        binding.dueAmount.text = MoneyFormatter.formatMoney(data.dueAmount).toString().appendCurrencySymbol()
                        binding.overDueByDay.text = data.dueInfo
                    }

                }

                private fun showNoBillFound(show: Boolean, binding: CreditCardListRowBinding) {
                    binding.noBillFound.showVisibility(show)
                    binding.dueAmount.showVisibility(!show)
                    binding.overDueByDay.showVisibility(!show)
                }

                override fun onClicked(data: CreditCardDetail, binding: CreditCardListRowBinding) {
                    // override function not implemented
                }
            })
        adapter.addItems(dueCardList)

        return adapter
    }


    fun setFeaturedDealsListAdapter(
        featureDeals: List<FeaturedDeals>,
        delegate: Delegate
    ): GenericAdapter<FeaturedDeals, DealsListLayoutBinding, List<Unit>> {

        val adapter = GenericAdapter(R.layout.deals_list_layout,
            object :
                GenericAdapterInteraction<FeaturedDeals, DealsListLayoutBinding, List<Unit>>() {
                override fun bindingViewHolder(
                    binding: DealsListLayoutBinding,
                    data: FeaturedDeals,
                    holder: GenericAdapter.GenericViewHolder<FeaturedDeals, DealsListLayoutBinding, List<Unit>>,
                    additionalData: List<Unit>?
                ) {
                    val resources = binding.root.resources

                    binding.cardLayout.setBackgroundColor(
                        ResourcesCompat.getColor(
                            resources,
                            data.backgroundColor,
                            null
                        )
                    )
                    binding.shopLogo.background =
                        ResourcesCompat.getDrawable(resources, data.shopLogo, null)
                    binding.shopItemLogo.background =
                        ResourcesCompat.getDrawable(resources, data.itemLogo, null)

                    binding.amount.text = data.amount.removeTrailingZeros()
                    binding.chipValue.text = String.format(
                        resources.getString(R.string.one_chip_value),
                        data.oneChipValue.appendCurrencySymbol())
                }

                override fun onClicked(data: FeaturedDeals, binding: DealsListLayoutBinding) {
                    binding.root.startAnimation(
                        AnimationUtils.loadAnimation(
                            binding.root.context, R.anim.zoom_out_animation
                        )
                    )

                    Handler().postDelayed({ binding.root.clearAnimation() }, 400)
                    delegate.routeToNextScreen()

                }

            })
        adapter.addItems(featureDeals)
        return adapter

    }

    interface Delegate {
        fun routeToNextScreen()
    }
}