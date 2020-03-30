package dev.macindoe.dictionary.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

private const val DATA_FILENAME = "words.json"

class InitialDataImportWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    val db: AppDatabase by inject()

    override suspend fun doWork(): Result {
        try {
            applicationContext.assets.open(DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Word>>() {}.type
                    val words: List<Word> = Gson().fromJson(jsonReader, type)

                    db.wordDao().insertAll(words)

                    Result.success()
                }
            }
        } catch (error: Exception) {
            // TODO: If this was a production app, we should report this error
            Result.failure()
        }

        return Result.success()
    }
}

