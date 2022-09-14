package com.example.newsfetcher

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsfetcher.feature.bookmark.data.local.BookmarksDao
import com.example.newsfetcher.feature.bookmark.data.local.model.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao
}