package com.nkr.rishilpi_ed.data.local

import com.nkr.rishilpi_ed.data.ICloseupVideoLocalDatasource
import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO
import com.nkr.rishilpi_ed.data.dto.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CloseupVideoLocalRepo(
    private val closeUpVideoDao: CloseUpVideoDao,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
) : ICloseupVideoLocalDatasource {


    override suspend fun insertCloseUpVideo(vdo: CloseupVideoDTO) = withContext(ioDispatcher) {
        closeUpVideoDao.insertVideo(vdo)

    }

    override suspend fun getCloseUpVideos(): Result<List<CloseupVideoDTO>> = withContext(ioDispatcher){
     return@withContext  try {
           Result.Success (closeUpVideoDao.getAllVideo())

       }catch (e:Exception){
           Result.Error(e.toString())
       }


    }


}