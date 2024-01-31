package com.example.testtask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtask.R
import com.example.testtask.databinding.FragmentUserListBinding
import com.example.testtask.presentation.viewmodels.UserListViewModel
import com.example.testtask.presentation.viewmodels.providers.UserListVMFactory
import com.training.companion.presentation.recyclerview.decorations.MarginItemDecoration

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserListViewModel by viewModels { UserListVMFactory() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this@UserListFragment
        val userListItemDecoration = MarginItemDecoration(
            verticalSpace = resources.getDimension(R.dimen.user_list_vertical_margin).toInt()
        )
        binding.userList.addItemDecoration(userListItemDecoration)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}