package com.example.sunflower.data
/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

import com.example.sunflower.utilities.DATABASE_NAME
import com.example.sunflower.utilities.PLANT_DATA_FILENAME
import com.example.sunflower.workers.SeedDatabaseWorker
import com.example.sunflower.workers.SeedDatabaseWorker.Companion.KEY_FILENAME


@Database(entities = [GardenPlanting::class, Plant::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object {

        // For Singleton instantiation
        // double check 방식으로 하나의 인스턴스만 생성한다.
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        // 데이터 베이스가 생성 되자마자 worker를 통해 plant.json 파일의 데이터로 db를 초기화 해준다.
        private fun buildDatabase(context: Context): AppDatabase {
            val instance =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
                .build()
            WorkManager.getInstance(context).enqueue(request)

            return instance
        }
    }
}
