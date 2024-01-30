package com.example.testtask.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testtask.R
import com.example.testtask.databinding.FragmentUserListBinding
import com.example.testtask.domain.models.Resource
import com.example.testtask.presentation.viewmodels.UserListViewModel
import com.example.testtask.presentation.viewmodels.providers.UserListVMFactory
import com.example.testtask.utils.toBrief
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.training.companion.presentation.recyclerview.decorations.MarginItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        val userListItemDecoration = MarginItemDecoration(
            verticalSpace = resources.getDimension(R.dimen.user_list_vertical_margin).toInt()
        )
        binding.userList.addItemDecoration(userListItemDecoration)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newUsers.collectLatest { resource ->
                    when (resource) {
                        null -> {
                            return@collectLatest
                        }

                        is Resource.Error -> {
                            showErrorDialog(resource.message)
                        }

                        is Resource.Data -> {
                            val briefUsers = resource.users.map { it.toBrief() }
                            viewModel.listAdapter.submitList(briefUsers)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showErrorDialog(errorMessage: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_error_title)
            .setMessage(errorMessage)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }
}