package com.nkr.rishilpi_ed.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkr.rishilpi_ed.data.ICloseupVideoLocalDatasource
import com.nkr.rishilpi_ed.data.dto.CloseupVideoDTO
import com.nkr.rishilpi_ed.data.dto.Result
import com.nkr.rishilpi_ed.model.AppRishilpiEd
import com.nkr.rishilpi_ed.model.CloseupVideo
import kotlinx.coroutines.launch

class HomeViewModel(
    val local_datasource : ICloseupVideoLocalDatasource
) : ViewModel() {

    private val _videoInfoList = MutableLiveData<List<CloseupVideo>>()
    val videoInfoList : LiveData<List<CloseupVideo>>
        get() = _videoInfoList

     fun saveVideoInfoIntoLocalDb() {
        viewModelScope.launch {
            val vdo_info = CloseupVideoDTO(
                title = "Demo Title",
                desc = "Demo Description",
                url = "Demo Url"
            )
            local_datasource.insertCloseUpVideo(vdo_info)
        }
    }

     fun fetchVideoInfoList() = viewModelScope.launch {
        val response = local_datasource.getCloseUpVideos()
        when(response){
            is Result.Success -> {
                _videoInfoList.value = response.data.map {
                    CloseupVideo(
                        id = it.id,
                        title = it.title,
                        desc = it.desc,
                        url = it.url
                    )
                }
            }
        }
    }

}