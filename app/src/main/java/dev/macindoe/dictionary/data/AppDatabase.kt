package dev.macindoe.dictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.macindoe.dictionary.utils.ioThread

/**
 * The Room database for this app
 */
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private val DATABASE_NAME = "dict-db"

        // Singleton instance. TODO: move to Dagger or Koin.
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).wordDao()
                                .insertAll(
                                    listOf(
                                        Word("1", "zh1", "pinyin1", "en1", false),
                                        Word("2", "zh2", "pinyin2", "en2", true)
                                    )
                                )
                        }
                    }
                })
                .build()
        }
    }
}
