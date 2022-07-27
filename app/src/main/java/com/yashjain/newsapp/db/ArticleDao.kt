package com.yashjain.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yashjain.newsapp.POJO.Article
import retrofit2.http.DELETE

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllArticle():LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}