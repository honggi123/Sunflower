package com.example.sunflower.data

class PlantRepository constructor(private val plantDao:PlantDao){

    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber:Int) =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)
}