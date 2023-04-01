package com.example.sunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sunflower.data.Plant
import com.example.sunflower.databinding.FragmentPlantDetailBinding
import com.example.sunflower.viewmodels.PlantDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
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

            // viewLifecycleOwner 사용한 이유
            lifecycleOwner = viewLifecycleOwner
            callback = Callback {
                hideAppBarFab(fab)
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                    .show()
            }

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            setHasOptionsMenu(true)
        }

        return binding.root
    }

    // FloatingActionButtons anchored to AppBarLayouts have their visibility controlled by the scroll position.
    // We want to turn this behavior off to hide the FAB when it is clicked.
    //
    // This is adapted from Chris Banes' Stack Overflow answer: https://stackoverflow.com/a/41442923
    // floatingbutton이 자동으로 스크롤에 의해서 숨겨지는 기능 사용하지 않고, 숨기기
    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    fun interface Callback {
        fun add(plant: Plant?)
    }
}