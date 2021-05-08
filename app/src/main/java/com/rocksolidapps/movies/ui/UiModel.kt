package com.rocksolidapps.movies.ui

import com.rocksolidapps.movies.tools.Consumable

data class UiModel<out T>(
    val data: T,
    val isLoading: Boolean = false,
    val error: Consumable<Unit> = Consumable(null)
)