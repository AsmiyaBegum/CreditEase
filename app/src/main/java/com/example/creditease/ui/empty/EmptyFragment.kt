package com.example.creditease.ui.empty

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creditease.MainActivity
import com.example.creditease.R
import com.example.creditease.databinding.FragmentEmptyBinding
import com.example.creditease.databinding.FragmentRewardsBinding
import com.example.creditease.ui.rewards.RewardsViewModel
import com.example.creditease.utils.AdapterUtils
import com.jakewharton.rxbinding.view.clicks
import rx.android.schedulers.AndroidSchedulers

class EmptyFragment : Fragment() {

    private var _binding: FragmentEmptyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.clicks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                findNavController().navigate(R.id.action_EmptyFragment_to_RewardFragment)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}