package com.example.gitassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitassignment.adapter.GitListAdapter2
import com.example.gitassignment.databinding.FragmentGitPullsBinding
import com.example.gitassignment.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitPullsFragment : Fragment() {

    private lateinit var binding: FragmentGitPullsBinding
    private val githubViewModel: GithubViewModel by viewModels()

    private var gitListAdapter: GitListAdapter2? = null

    var username = ""
    var repoName = ""

    companion object {
        const val USER = "user"
        const val REPOSITORY = "repository"
    }

    override fun onResume() {
        super.onResume()
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setUpView()
    }

    private fun setRecyclerView() {
        binding.gitRV.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.gitRV.adapter = gitListAdapter
    }


    private fun setUpView() {
        gitListAdapter = GitListAdapter2()
        binding.gitRV.adapter = gitListAdapter
        collectUiState()

        gitListAdapter!!.addLoadStateListener {
            binding.progressbar.isVisible = it.refresh is LoadState.Loading
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGitPullsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            username = arguments?.getString(USER, "").toString()
            repoName = arguments?.getString(REPOSITORY, "").toString()
        }

        return binding.root
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            githubViewModel.fetchGitPulls(username, repoName).collectLatest { gitList ->
                gitListAdapter?.submitData(gitList)
                hideProgressBar()
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

}