package com.example.sunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sunflower.adapters.PlantAdapter
import com.example.sunflower.databinding.FragmentPlantListBinding
import com.example.sunflower.viewmodels.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment : Fragment() {

    private val viewmodel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter) {
      viewmodel.plants.observe(viewLifecycleOwner) { plants ->
          adapter.submitList(plants)
      }

    }
}