package com.kyawlinnthant.codigo.one.presentation.upcoming

sealed interface UpcomingAction {
    data class Navigate(val id: Int) : UpcomingAction
    data class Favourite(val id: Int, val enabled: Boolean) : UpcomingAction
}