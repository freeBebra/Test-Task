package com.example.testtask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testtask.databinding.FragmentUserDetailsBinding
import com.example.testtask.presentation.viewmodels.UserDetailsViewModel
import com.example.testtask.presentation.viewmodels.providers.UserDetailsVMFactory


class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs: UserDetailsFragmentArgs by navArgs()
    private val viewModel: UserDetailsViewModel by viewModels { UserDetailsVMFactory(navArgs.userId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@UserDetailsFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}