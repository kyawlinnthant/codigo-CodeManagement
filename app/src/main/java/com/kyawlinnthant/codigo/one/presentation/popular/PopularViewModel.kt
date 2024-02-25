package com.kyawlinnthant.codigo.one.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kyawlinnthant.codigo.one.data.paging.MovieType
import com.kyawlinnthant.codigo.one.domain.usecase.GetPopular
import com.kyawlinnthant.codigo.one.domain.usecase.SetFavourite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    getPopular: GetPopular,
    private val setFavourite: SetFavourite,
) : ViewModel() {
    val movies = getPopular().cachedIn(viewModelScope)

    fun updateFavourite(
        id: Int,
        enabled: Boolean
    ) {
        viewModelScope.launch {
            setFavourite(
                id = id,
                enabled = enabled,
                type = MovieType.POPULAR
            )
        }
    }
}