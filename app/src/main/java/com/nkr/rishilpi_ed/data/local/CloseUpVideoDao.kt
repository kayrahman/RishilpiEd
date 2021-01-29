package com.nkr.rishilpi_ed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO


@Dao
interface CloseUpVideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(vdo_info : CloseupVideoDTO)

    @Query("SELECT * FROM closeup_video")
   suspend fun getAllVideo() : List<CloseupVideoDTO>

}