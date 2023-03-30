package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunflower.adapters.PlantAdapter
import com.example.sunflower.databinding.FragmentPlantListBinding
import com.example.sunflower.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {

    private lateinit var viewmodel: PlantListViewModel

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