package com.example.creditease.ui.rewards

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creditease.R
import com.example.creditease.databinding.FragmentNotificationsBinding
import com.example.creditease.databinding.FragmentRewardsBinding
import com.example.creditease.utils.AdapterUtils
import com.jakewharton.rxbinding.view.clicks
import rx.android.schedulers.AndroidSchedulers

class RewardsFragment : Fragment(),AdapterUtils.Delegate {

    private var _binding: FragmentRewardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var rewardsViewModel : RewardsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rewardsViewModel = ViewModelProvider(this).get(RewardsViewModel::class.java)

        rewardsViewModel.featuredDeals.observe(viewLifecycleOwner) {featuredDeals ->
            binding.featuredDealList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.otherFeaturedDealsList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

            binding.featuredDealList.adapter = AdapterUtils.setFeaturedDealsListAdapter(featuredDeals,this)
            binding.otherFeaturedDealsList.adapter = AdapterUtils.setFeaturedDealsListAdapter(featuredDeals,this)

        }

        addAnimation()
    }

    private fun addAnimation(){
        binding.cashInstantWithdrawCard.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.cashInstantWithdrawCard)
            }

        binding.chipsBalanceCard.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.chipsBalanceCard)
            }

        binding.voucherCard.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.voucherCard)
            }

        binding.cashInstantWithdrawCard.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.cashInstantWithdrawCard)
            }

        binding.foodVoucher.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.foodIcon)
            }

        binding.travelVoucher.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.travelIcon)
            }

        binding.shoppingVoucher.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.shoppingIcon)
            }
        binding.moreVoucher.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                startAnimation(binding.moreIcon)
            }

    }

    private fun startAnimation(view: View){
       view.startAnimation(
            AnimationUtils.loadAnimation(
                context, com.example.creditease.R.anim.zoom_out_animation
            )
        )

        Handler().postDelayed({clearAnimAndRouteToNextScreen(view)},400)
    }

    private fun clearAnimAndRouteToNextScreen(view: View){
        view.clearAnimation()
        Handler().postDelayed({routeToNextScreen()},1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun routeToNextScreen() {
        findNavController().navigate(R.id.action_RewardFragment_to_EmptyFragment)
    }
}