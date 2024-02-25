package com.kyawlinnthant.codigo.one.domain.util

object Constant {
    const val PAGE_SIZE = 50
    const val INITIAL_LOAD_SIZE = PAGE_SIZE * 3
    const val START_PAGE = 1
    const val PREFETCH_DISTANCE = 1
    const val MAX_LOAD_SIZE = INITIAL_LOAD_SIZE + (PAGE_SIZE * PREFETCH_DISTANCE)
}