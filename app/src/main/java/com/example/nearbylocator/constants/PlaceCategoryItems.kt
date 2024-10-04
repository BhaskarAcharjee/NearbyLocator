package com.example.nearbylocator.constants

import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.PlaceTypeIcon

// Used in Choose Place Fragment
object PlaceCategoryItems {
    fun getPlaceCategories(): List<PlaceItem> {
        return listOf(
            // Food & Drinks
            PlaceItem.Header("Food & Drinks", R.drawable.place_category_group_icon_food_and_drinks),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Restaurant",
                    R.drawable.place_category_icon_restaurant
                ),
                PlaceItem.Header(
                    "Food & Drinks",
                    R.drawable.place_category_group_icon_food_and_drinks
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Cafe",
                    R.drawable.place_category_icon_cafe
                ),
                PlaceItem.Header(
                    "Food & Drinks",
                    R.drawable.place_category_group_icon_food_and_drinks
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Bar",
                    R.drawable.place_category_icon_bar
                ),
                PlaceItem.Header(
                    "Food & Drinks",
                    R.drawable.place_category_group_icon_food_and_drinks
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Grocery Store",
                    R.drawable.place_category_icon_groceries
                ),
                PlaceItem.Header(
                    "Food & Drinks",
                    R.drawable.place_category_group_icon_food_and_drinks
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Supermarket",
                    R.drawable.place_category_icon_supermarket
                ),
                PlaceItem.Header(
                    "Food & Drinks",
                    R.drawable.place_category_group_icon_food_and_drinks
                )
            ),

            // Financial Services
            PlaceItem.Header(
                "Financial Services",
                R.drawable.place_category_group_icon_financial_services
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Bank",
                    R.drawable.place_category_icon_bank
                ),
                PlaceItem.Header(
                    "Financial Services",
                    R.drawable.place_category_group_icon_financial_services
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "ATM",
                    R.drawable.place_category_icon_atm
                ),
                PlaceItem.Header(
                    "Financial Services",
                    R.drawable.place_category_group_icon_financial_services
                )
            ),

            // Health & Wellness
            PlaceItem.Header(
                "Health & Wellness",
                R.drawable.place_category_group_icon_health_and_wellness
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Hospital",
                    R.drawable.place_category_icon_hospital
                ),
                PlaceItem.Header(
                    "Health & Wellness",
                    R.drawable.place_category_group_icon_health_and_wellness
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Clinic",
                    R.drawable.place_category_icon_clinic
                ),
                PlaceItem.Header(
                    "Health & Wellness",
                    R.drawable.place_category_group_icon_health_and_wellness
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Pharmacy",
                    R.drawable.place_category_icon_pharmacy
                ),
                PlaceItem.Header(
                    "Health & Wellness",
                    R.drawable.place_category_group_icon_health_and_wellness
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Gym",
                    R.drawable.place_category_icon_gym
                ),
                PlaceItem.Header(
                    "Health & Wellness",
                    R.drawable.place_category_group_icon_health_and_wellness
                )
            ),

            // Personal Care
            PlaceItem.Header("Personal Care", R.drawable.place_category_group_icon_personal_care),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Salon",
                    R.drawable.place_category_icon_saloon
                ),
                PlaceItem.Header(
                    "Personal Care",
                    R.drawable.place_category_group_icon_personal_care
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Laundry",
                    R.drawable.place_category_icon_laundry
                ),
                PlaceItem.Header(
                    "Personal Care",
                    R.drawable.place_category_group_icon_personal_care
                )
            ),

            // Transportation
            PlaceItem.Header("Transportation", R.drawable.place_category_group_icon_transportation),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Bus Stop",
                    R.drawable.place_category_icon_busstop
                ),
                PlaceItem.Header(
                    "Transportation",
                    R.drawable.place_category_group_icon_transportation
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Train Station",
                    R.drawable.place_category_icon_train_station
                ),
                PlaceItem.Header(
                    "Transportation",
                    R.drawable.place_category_group_icon_transportation
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Airport",
                    R.drawable.place_category_icon_airport
                ),
                PlaceItem.Header(
                    "Transportation",
                    R.drawable.place_category_group_icon_transportation
                )
            ),

            // Public Services
            PlaceItem.Header(
                "Public Services",
                R.drawable.place_category_group_icon_public_services
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Police Station",
                    R.drawable.place_category_icon_policestation
                ),
                PlaceItem.Header(
                    "Public Services",
                    R.drawable.place_category_group_icon_public_services
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Post Office",
                    R.drawable.place_category_icon_postoffice
                ),
                PlaceItem.Header(
                    "Public Services",
                    R.drawable.place_category_group_icon_public_services
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Parking",
                    R.drawable.place_category_icon_parking
                ),
                PlaceItem.Header(
                    "Public Services",
                    R.drawable.place_category_group_icon_public_services
                )
            ),

            // Entertainment
            PlaceItem.Header("Entertainment", R.drawable.place_category_group_icon_entertainment),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Park",
                    R.drawable.place_category_icon_park
                ),
                PlaceItem.Header(
                    "Entertainment",
                    R.drawable.place_category_group_icon_entertainment
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Movie Theater",
                    R.drawable.place_category_icon_theater
                ),
                PlaceItem.Header(
                    "Entertainment",
                    R.drawable.place_category_group_icon_entertainment
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Shopping Mall",
                    R.drawable.place_category_icon_shoppingmall

                ),
                PlaceItem.Header(
                    "Entertainment",
                    R.drawable.place_category_group_icon_entertainment
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Museum",
                    R.drawable.place_category_icon_museum
                ),
                PlaceItem.Header(
                    "Entertainment",
                    R.drawable.place_category_group_icon_entertainment
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Zoo",
                    R.drawable.place_category_icon_zoo
                ),
                PlaceItem.Header(
                    "Entertainment",
                    R.drawable.place_category_group_icon_entertainment
                )
            ),

            // Education
            PlaceItem.Header("Education", R.drawable.place_category_group_icon_education),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "School",
                    R.drawable.place_category_icon_school
                ), PlaceItem.Header("Education", R.drawable.place_category_group_icon_education)
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "University",
                    R.drawable.place_category_icon_university
                ), PlaceItem.Header("Education", R.drawable.place_category_group_icon_education)
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Library",
                    R.drawable.place_category_icon_library
                ), PlaceItem.Header("Education", R.drawable.place_category_group_icon_education)
            ),

            // Religious
            PlaceItem.Header("Religious", R.drawable.place_category_group_icon_religious),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Church",
                    R.drawable.place_category_icon_church
                ), PlaceItem.Header("Religious", R.drawable.place_category_group_icon_religious)
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Temple",
                    R.drawable.place_category_icon_temple
                ), PlaceItem.Header("Religious", R.drawable.place_category_group_icon_religious)
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIcon(
                    "Mosque",
                    R.drawable.place_category_icon_mosque
                ), PlaceItem.Header("Religious", R.drawable.place_category_group_icon_religious)
            )
        )
    }
}