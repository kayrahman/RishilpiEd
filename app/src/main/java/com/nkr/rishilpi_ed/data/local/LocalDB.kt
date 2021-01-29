package com.nkr.rishilpi_ed.data.local

import android.content.Context
import androidx.room.Room
import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO


object LocalDB {

    fun createCloseUpVideoDao(context: Context) : CloseUpVideoDao{
        return Room.databaseBuilder(
            context.applicationContext,
            CloseupVideoDB::class.java,"rishilpi_ed.db"
        ).build().closeupVdoDao()
    }
}