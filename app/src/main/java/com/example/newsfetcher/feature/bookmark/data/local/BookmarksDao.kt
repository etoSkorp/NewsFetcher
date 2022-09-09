package com.example.newsfetcher.feature.bookmark.data.local

import androidx.room.*
import com.example.newsfetcher.BOOKMARKS_TABLE
import com.example.newsfetcher.feature.bookmark.data.local.model.BookmarkEntity

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM $BOOKMARKS_TABLE")
    suspend fun read(): List<BookmarkEntity>

    @Update
    suspend fun update(entity: BookmarkEntity)

    @Delete
    suspend fun delete(entity: BookmarkEntity)
}