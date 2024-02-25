package com.kyawlinnthant.codigo.one.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.codigo.one.domain.model.detail.MovieDetail
import com.kyawlinnthant.codigo.one.domain.usecase.FetchDetail
import com.kyawlinnthant.codigo.one.domain.usecase.GetDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetail: GetDetail,
    private val fetchDetail: FetchDetail,
) : ViewModel() {

    private val vmState = MutableStateFlow(MovieDetail())
    val movie get() = vmState.asStateFlow()

    fun fetchMovies(id: Int) {
        viewModelScope.launch {
            fetchDetail(id)
        }
    }

    fun getMovie(id: Int) {
        viewModelScope.launch {
            getDetail(id).collectLatest {
                vmState.emit(it)
            }
        }
    }
}