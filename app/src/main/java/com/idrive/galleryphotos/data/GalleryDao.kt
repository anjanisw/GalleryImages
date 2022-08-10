package com.idrive.galleryphotos.data


import androidx.room.*
import com.idrive.galleryphotos.model.MediaStoreImage
import kotlinx.coroutines.flow.Flow


@Dao
interface GalleryDao {

    //insert data to room database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: MediaStoreImage): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(order: List<MediaStoreImage>?)

    //get all users inserted to room database...normally this is supposed to be a list of users
    @Transaction
    @Query("SELECT * FROM photo_table ORDER BY id DESC")
    fun getAllImages(): Flow<List<MediaStoreImage>>

    //get single user inserted to room database
    @Transaction
    @Query("SELECT * FROM photo_table WHERE id = :id ORDER BY id DESC")
    fun getImageDetails(id: Long): Flow<MediaStoreImage>

}