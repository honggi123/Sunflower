package com.example.sunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sunflower.databinding.FragmentPlantDetailBinding
import com.example.sunflower.viewmodels.PlantDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : Fragment() {

    private val plantDetailViewModel: PlantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater,
            R.layout.fragment_plant_detail,
            container,
            false
        ).apply {
            viewModel = plantDetailViewModel
            /*
            viewLifecycleOwner 사용한 이유

            */
            lifecycleOwner = viewLifecycleOwner

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            setHasOptionsMenu(true)
        }

        return binding.root
    }

}