package com.idrive.galleryphotos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.idrive.galleryphotos.Converters
import com.idrive.galleryphotos.data.GalleryDao
import com.idrive.galleryphotos.model.MediaStoreImage

@Database(entities = [MediaStoreImage::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class MediaRoomDatabase : RoomDatabase() {

    abstract fun photoDoa(): GalleryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: MediaRoomDatabase? = null

        fun getDatabase(context: Context): MediaRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MediaRoomDatabase::class.java,
                    "gallery_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}