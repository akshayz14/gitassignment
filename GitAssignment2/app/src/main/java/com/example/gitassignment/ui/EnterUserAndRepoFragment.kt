package com.example.gitassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gitassignment.R
import com.example.gitassignment.databinding.FragmentEnterUserAndRepoBinding
import com.example.gitassignment.utils.isConnectedToInternet


class EnterUserAndRepoFragment : Fragment() {


    private lateinit var binding: FragmentEnterUserAndRepoBinding

    private var userName: String = ""
    private var repoName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {

        binding.findPullsBtn.setOnClickListener {
            if (!isConnectedToInternet(requireContext())) {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.check_internet_msg),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!binding.userET.text.isNullOrEmpty() && !binding.repoET.text.isNullOrEmpty()) {
                openGitPullsFragment()
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getText(R.string.error_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        binding.userET.setText("")
        binding.repoET.setText("")
    }

    private fun openGitPullsFragment() {

        userName = binding.userET.text.trim().toString()
        repoName = binding.repoET.text.trim().toString()

        val bundle = Bundle()
        bundle.putString(GitPullsFragment.USER, userName)
        bundle.putString(GitPullsFragment.REPOSITORY, repoName)

        findNavController().navigate(
            R.id.action_enterUserAndRepoFragment_to_gitPullsFragment,
            bundle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEnterUserAndRepoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

}