package com.nkr.rishilpi_ed.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class CloseupVideo(
    val id: String?,
    var title: String?,
    var desc: String?,
   var url: String?
)