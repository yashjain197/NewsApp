package com.yashjain.newsapp.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.*
import androidx.room.TypeConverters
import com.yashjain.newsapp.POJO.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        private const val DB_NAME = "article_db.db"

        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                DB_NAME
            ).build()

    }
}