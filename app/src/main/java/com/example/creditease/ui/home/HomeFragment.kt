package com.example.creditease.ui.home

import android.R.attr.textColor
import android.R.attr.textSize
import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creditease.R
import com.example.creditease.databinding.FragmentHomeBinding
import com.example.creditease.utils.AdapterUtils
import com.example.creditease.utils.appendCurrencySymbol
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView




class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        updateCardDueDetails()
        updateCurrency()
        startSlideDownAnimation()
    }
    private fun startSlideDownAnimation() {
        val slideDownAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down_anim)
        binding.creditCardsLayout.startAnimation(slideDownAnimation)
        Handler().postDelayed(Runnable {
            binding.payNowLayout.visibility = View.VISIBLE
            binding.payNowLayout.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.expand_anim))
            binding.topLayoutWithDueCard.background = ResourcesCompat.getDrawable(resources,R.drawable.home_fragment_gradient,null)
        }
        ,350)
    }



//    private fun animateTextLetterByLetter() {
//        val text = "Hello, World!"
//        val spannableString = SpannableStringBuilder()
//
//        for (i in text.indices) {
//            val letter = text[i]
//            val animatedLetter = SpannableString(letter.toString())
//            animatedLetter.setSpan(RelativeSizeSpan(0f), 0, 1, 0)
//            spannableString.append(animatedLetter)
//        }
//
//        binding.totalDue.text = spannableString
//
//        val handler = Handler()
//        val delay = 100L // Delay between letters (in milliseconds)
//        var index = 0
//
//        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down_anim)
//
//        val runnable = object : Runnable {
//            override fun run() {
//                val animatedLetter = spannableString.getSpans(index, index + 1, RelativeSizeSpan::class.java)
//                if (animatedLetter.isNotEmpty()) {
//                    animatedLetter[0].sizeChange
//                }
//
//                binding.totalDue.startAnimation(slideUpAnimation)
//
//                index++
//                if (index < text.length) {
//                    handler.postDelayed(this, delay)
//                }
//            }
//        }
//
//        handler.postDelayed(runnable, delay * 2)
//    }
    private fun updateCurrency(){
        val numberString = "60000"
        binding.totalDue.setCharacterLists(TickerUtils.provideNumberList())
        binding.totalDue.setAnimationDuration(1500)
        binding.totalDue.setAnimationInterpolator(DecelerateInterpolator())
        binding.totalDue.setGravity(Gravity.START)
        binding.totalDue.setPreferredScrollingDirection(TickerView.ScrollingDirection.UP)
        binding.totalDue.setText("0")
        binding.totalDue.setText(numberString.appendCurrencySymbol())
}

    private fun playDigitAnimation(digitAnimators: List<ObjectAnimator>, index: Int) {
        if (index < digitAnimators.size) {
            digitAnimators[index].addListener(object : Animator.AnimatorListener {

                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    playDigitAnimation(digitAnimators, index + 1)
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            digitAnimators[index].start()
        }
    }

    private fun updateCardDueDetails(){
        homeViewModel.cardDueDetails.observe(viewLifecycleOwner) { dueCardList ->
           binding.cardList.layoutManager = LinearLayoutManager(requireContext())
            binding.cardList.adapter = AdapterUtils.setDueCardListAdapter(dueCardList)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}