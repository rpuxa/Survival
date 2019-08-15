package ru.rpuxa.survival.model.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class EventsWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

    }


    
}