package com.example.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

// GardenPlantingAdapter 클래스에서 Viewmodel을 하나씩 생성해서 view와 바인딩 시켜준다.
// PlantAndGardenPlantings 형식의 객체의 데이터들을 알맞은 형식으로 변환해서 가지고 있음.
class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings){

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]

    val waterDateString: String = dateFormat.format(gardenPlanting.lastWateringDate.time)
    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)
    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}