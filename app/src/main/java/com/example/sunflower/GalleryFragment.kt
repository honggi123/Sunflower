package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sunflower.adapters.GalleryAdapter
import com.example.sunflower.databinding.FragmentGalleryBinding
import com.example.sunflower.viewmodels.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val adapter = GalleryAdapter()
    private var searchJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels()
    private val args: GalleryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        search(args.plantName)
        binding.photoList.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun search(query: String) {
        //lifecycleScope는 lifecycleowner가 destroy되면 CoroutineContext.cancel()을 호출한다.
        searchJob = lifecycleScope.launch {
            // collectLatest는 새로운 데이터가 발행되면 이전 데이터의 처리를 취소하고 새로운 데이터의 처리를 시작한다.
            viewModel.searchPictures(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}