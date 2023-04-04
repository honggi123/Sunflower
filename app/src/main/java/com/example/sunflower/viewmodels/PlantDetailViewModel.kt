package com.example.sunflower.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sunflower.data.GardenPlantingRepository
import com.example.sunflower.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    // viewmodel을 생성할때 factory를 넘기지 않으면 defaultfactory를 생성해 viewmodel을 만든다.
    // defaultfactory는 내부적으로 savedStateHandle를 매개변수로 받는 SavedStateViewModelFactory를 사용한다.
    // SavedStateViewModelFactory는 뷰의 argument 번들 값을 전달받아 statehandle을 만든다.
    val plantId: String = savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!
    val isPlanted = gardenPlantingRepository.isPlanted(plantId).asLiveData()
    val plant = plantRepository.getPlant(plantId).asLiveData()

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

    companion object {
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }
}