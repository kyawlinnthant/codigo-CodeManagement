package com.kyawlinnthant.codigo.one.presentation.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kyawlinnthant.codigo.one.domain.usecase.GetPopular
import com.kyawlinnthant.codigo.one.domain.usecase.GetUpcoming
import com.kyawlinnthant.codigo.one.domain.usecase.SetFavourite
import com.kyawlinnthant.codigo.one.presentation.popular.PopularAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    getUpcoming: GetUpcoming,
    private val setFavourite: SetFavourite,
) : ViewModel() {
    val movies = getUpcoming().cachedIn(viewModelScope)

    fun updateFavourite(
        id: Int,
        enabled: Boolean
    ) {
        viewModelScope.launch {
            setFavourite(
                id = id,
                enabled = enabled
            )
        }
    }
}