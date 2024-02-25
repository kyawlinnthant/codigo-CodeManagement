package com.kyawlinnthant.codigo.one.presentation.popular

sealed interface PopularAction {
    data class Navigate(val id: Int) : PopularAction
    data class Favourite(val id: Int, val enabled: Boolean) : PopularAction
}