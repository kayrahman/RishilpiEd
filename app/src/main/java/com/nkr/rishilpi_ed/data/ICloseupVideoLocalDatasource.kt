package com.nkr.rishilpi_ed.data

import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO
import com.nkr.rishilpi_ed.data.dto.Result

interface ICloseupVideoLocalDatasource {

    suspend fun insertCloseUpVideo(vdo : CloseupVideoDTO)
    suspend fun getCloseUpVideos() : Result<List<CloseupVideoDTO>>
}