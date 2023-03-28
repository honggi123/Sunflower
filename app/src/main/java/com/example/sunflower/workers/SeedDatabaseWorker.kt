package com.example.sunflower.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SeedDatabaseWorker(
    context: Context,
    wokrerParams: WorkerParameters
) : CoroutineWorker(context, wokrerParams) {

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }

}