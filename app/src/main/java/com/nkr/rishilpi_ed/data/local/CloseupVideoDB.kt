package com.nkr.rishilpi_ed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO


@Database(entities = [CloseupVideoDTO::class],version = 1,exportSchema = false)
abstract class CloseupVideoDB : RoomDatabase() {
    abstract fun closeupVdoDao() : CloseUpVideoDao
}