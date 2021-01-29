package com.nkr.rishilpi_ed.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "closeup_video")
data class CloseupVideoDTO(
    @PrimaryKey
    @ColumnInfo(name = "entry_id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "description") var desc: String?,
    @ColumnInfo(name = "video_url") var url: String?
)