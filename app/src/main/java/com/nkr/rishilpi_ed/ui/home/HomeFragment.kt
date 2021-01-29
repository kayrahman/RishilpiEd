package com.nkr.rishilpi_ed.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nkr.rishilpi_ed.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
      //  homeViewModel =
        //        ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for(i in 1..10){
            homeViewModel.saveVideoInfoIntoLocalDb()
        }

        homeViewModel.fetchVideoInfoList()

        // set recycler adapter
        homeViewModel.videoInfoList.observe(viewLifecycleOwner, Observer {
            Log.d("video_list",it.size.toString())
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}