package com.nicklasslagbrand.feature.repo.list

import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.nicklasslagbrand.core.extension.showKeyboard
import com.nicklasslagbrand.baseline.databinding.FragmentRepoListBinding
import com.nicklasslagbrand.feature.repo.RepoViewModel
import com.nicklasslagbrand.feature.search.MySuggestionProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@ExperimentalCoroutinesApi
class ReposListFragment : Fragment() {
    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!

    private val reposAdapter: ReposAdapter = ReposAdapter()
    private val viewModel: RepoViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLivedata()
        initializeList()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchRepositories(query)
                return true
            }

            override fun onQueryTextChange(newText: String) = false
        })
    }

    private fun subscribeToLivedata() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.githubRepos.collectLatest{
                reposAdapter.submitData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            reposAdapter.loadStateFlow.collectLatest {
                binding.pb.isVisible = it.refresh is LoadState.Loading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeList() {
        binding.rvRepos.apply {
            adapter = reposAdapter.apply {
                clickListener = {
                    val action = ReposListFragmentDirections.actionListToDetails(it)
                    findNavController().navigate(action)
                }
            }
        }
        binding.rvRepos.adapter = reposAdapter.apply {
            clickListener = {
                val action = ReposListFragmentDirections.actionListToDetails(it)
                findNavController().navigate(action)
            }
        }
    }
}
