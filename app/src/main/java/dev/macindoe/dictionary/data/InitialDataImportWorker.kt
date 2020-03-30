package dev.macindoe.dictionary.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.KoinComponent
import org.koin.core.inject

class InitialDataImportWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    val db: AppDatabase by inject()

    override suspend fun doWork(): Result {
        db.wordDao()
            .insertAll(
                listOf(
                    Word("1", "zh1 new", "pinyin1", "en1", false),
                    Word("2", "zh2", "pinyin2", "en2", true)
                )
            )
        return Result.success()
    }
}

