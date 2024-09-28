package com.example.nearbylocator.model

data class PlaceCategoryGroupDataClass(
    var placeImage: Int,
    var placeName: String = "",
    var placeRating: String = "",
    var placeLocation: String = "",
    var placeDistance: String = "",
    var placeType: String = "",
    var placePrice: String = ""
)