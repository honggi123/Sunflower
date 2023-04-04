package com.example.sunflower.data

import javax.inject.Inject

class GardenPlantingRepository @Inject constructor(
    private val gardenPlantingDao: GardenPlantingDao
){
    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    fun isPlanted(plantId:String) = gardenPlantingDao.isPlanted(plantId)

    suspend fun createGardenPlanting(plantId: String) {
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }


}