package com.example.nearbylocator.model

data class PlaceTypeIcon(
    val title: String,
    val icon: Int
)

sealed class PlaceItem {
    data class Header(val title: String, val headerIcon: Int) : PlaceItem()
    data class CategoryItem(val place: PlaceTypeIcon, val header: Header) : PlaceItem()
}
