package com.carlosdiestro.jobapplicationmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.carlosdiestro.jobapplicationmanager.R
import com.carlosdiestro.jobapplicationmanager.databinding.FragmentJobApplicationsBinding
import com.carlosdiestro.jobapplicationmanager.ui.adapters.JobApplicationAdapter
import com.carlosdiestro.jobapplicationmanager.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobApplicationsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentJobApplicationsBinding
    private lateinit var recyclerAdapter: JobApplicationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobApplicationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListeners()
        setUpRecyclerView()
        observeJobApplications()
    }

    private fun setUpClickListeners() {
        binding.btnNewApplication.setOnClickListener { navigateToNewJobApplication() }
    }

    private fun navigateToNewJobApplication() {
        findNavController().navigate(R.id.jobApplicationsToNewJobApplication)
    }

    private fun setUpRecyclerView() = binding.rvJobApplications.apply {
        recyclerAdapter = JobApplicationAdapter(requireContext())
        adapter = recyclerAdapter
    }

    private fun observeJobApplications() {
        viewModel.jobApplications.observe(viewLifecycleOwner, { jobApplications ->
            recyclerAdapter.submitList(jobApplications)
        })
    }

}