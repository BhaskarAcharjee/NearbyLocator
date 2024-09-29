package com.example.nearbylocator.utils

import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIconDataClass
import com.example.nearbylocator.model.PlaceCategoryGroupDataClass
import com.example.nearbylocator.model.HoriServicesDataClass
import com.example.nearbylocator.model.MapviewDataClass
import com.example.nearbylocator.model.MapviewFavDataClass
import com.example.nearbylocator.model.PlaceItem
import com.example.nearbylocator.model.VertiServicesDataClass

//--------------------Search hints------------------------
val places_hint_Strings = arrayOf(
    "Restaurant",
    "Cafe",
    "Bar",
    "Grocery Store",
    "Supermarket",
    "Bank",
    "ATM",
    "Hospital",
    "Clinic",
    "Pharmacy",
    "Gas Station",
    "Salon",
    "Gym",
    "Park",
    "Movie Theater",
    "Shopping Mall",
    "Library",
    "Museum",
    "Post Office",
    "Hotel",
    "Parking",
    "Laundry",
    "Bus Stop",
    "Train Station",
    "Airport",
    "Police Station",
    "School",
    "University",
    "Church",
    "Temple",
    "Mosque",
    "Zoo"
)

val services_hint_Strings = arrayOf(
    "Car Repair",
    "Car Wash",
    "Pedicure",
    "Hair Cut",
    "Dry Cleaner",
    "Laundry Service",
    "Home Cleaning",
    "Plumbing Service",
    "Electrician",
    "HVAC Repair",
    "Pest Control",
    "Gardening Service",
    "Massage Therapy",
    "Fitness Trainer",
    "Pet Grooming",
    "Yoga Classes",
    "Tutoring",
    "Childcare",
    "Photography Service",
    "Moving Company"
)


object PlaceCategoryItems {
    fun getPlaceCategories(): List<PlaceItem> {
        return listOf(
            // Food & Drinks
            PlaceItem.Header("Food & Drinks"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Restaurant",
                    R.drawable.place_category_icon_restaurant
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Cafe",
                    R.drawable.place_category_icon_cafe
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Bar",
                    R.drawable.place_category_icon_bar
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Grocery Store",
                    R.drawable.place_category_icon_groceries
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Supermarket",
                    R.drawable.place_category_icon_supermarket
                )
            ),

            // Financial Services
            PlaceItem.Header("Financial Services"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Bank",
                    R.drawable.place_category_icon_bank
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "ATM",
                    R.drawable.place_category_icon_atm
                )
            ),

            // Health & Wellness
            PlaceItem.Header("Health & Wellness"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Hospital",
                    R.drawable.place_category_icon_hospital
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Clinic",
                    R.drawable.place_category_icon_clinic
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Pharmacy",
                    R.drawable.place_category_icon_pharmacy
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Gym",
                    R.drawable.place_category_icon_gym
                )
            ),

            // Personal Care
            PlaceItem.Header("Personal Care"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Salon",
                    R.drawable.place_category_icon_saloon
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Laundry",
                    R.drawable.place_category_icon_laundry
                )
            ),

            // Transportation
            PlaceItem.Header("Transportation"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Bus Stop",
                    R.drawable.place_category_icon_busstop
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Train Station",
                    R.drawable.place_category_icon_train_station
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Airport",
                    R.drawable.place_category_icon_airport
                )
            ),

            // Public Services
            PlaceItem.Header("Public Services"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Police Station",
                    R.drawable.place_category_icon_policestation
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Post Office",
                    R.drawable.place_category_icon_postoffice
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Parking",
                    R.drawable.place_category_icon_parking
                )
            ),

            // Entertainment
            PlaceItem.Header("Entertainment"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Park",
                    R.drawable.place_category_icon_park
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Movie Theater",
                    R.drawable.place_category_icon_theater
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Shopping Mall",
                    R.drawable.place_category_icon_shoppingmall

                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Library",
                    R.drawable.place_category_icon_library
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Museum",
                    R.drawable.place_category_icon_museum
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Zoo",
                    R.drawable.place_category_icon_zoo
                )
            ),

            // Education
            PlaceItem.Header("Education"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "School",
                    R.drawable.place_category_icon_school
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "University",
                    R.drawable.place_category_icon_university
                )
            ),

            // Religious
            PlaceItem.Header("Religious"),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Church",
                    R.drawable.place_category_icon_church
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Temple",
                    R.drawable.place_category_icon_temple
                )
            ),
            PlaceItem.CategoryItem(
                PlaceTypeIconDataClass(
                    "Mosque",
                    R.drawable.place_category_icon_mosque
                )
            )
        )
    }
}

//----------------------- Image Slider ---------------------------------
val homeOfferImages = arrayListOf(
    R.drawable.homeslide1,
    R.drawable.homeslide2,
    R.drawable.homeslide3,
    R.drawable.homeslide4
)

val serviceOfferImages = arrayListOf(
    R.drawable.homeslide5,
    R.drawable.homeslide6,
    R.drawable.homeslide1,
    R.drawable.homeslide7,
    R.drawable.homeslide2
)

val mapviewFavDataClasses = listOf(
    MapviewFavDataClass(
        "Salem RR Briyani",
        4.5f,
        "20 mins",
        "Briyani, Pizza",
        "Medavakkam, 3.0 km",
        R.drawable.verti_img_1
    ),
    MapviewFavDataClass(
        "Burger King",
        4.0f,
        "15 mins",
        "Burgers, Fast Food",
        "Velachery, 2.5 km",
        R.drawable.verti_img_2
    ),
    MapviewFavDataClass(
        "Domino's Pizza",
        4.2f,
        "10 mins",
        "Pizza, Italian",
        "Tambaram, 4.0 km",
        R.drawable.verti_img_3
    ),
    MapviewFavDataClass(
        "Subway",
        3.8f,
        "25 mins",
        "Sandwiches, Salads",
        "Sholinganallur, 5.0 km",
        R.drawable.verti_img_4
    )
)

val topRatedFoodsList = listOf(
    HoriServicesDataClass(
        R.drawable.hori_img_1,
        "Salem RR Briyani",
        "3.8",
        "27 mins",
        "Briyani, Chettinad, Tandoori"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_2,
        "Fasta Pizza",
        "4.5",
        "29 mins",
        "Pizza, Burger, Chicken"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_3,
        "Grill Nights",
        "4.2",
        "25 mins",
        "Chinese, Mexican, Tandoori"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_4,
        "Domino's Pizza",
        "2.8",
        "15 mins",
        "Italian, Dutch, Cheese"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_5,
        "Oho Shawarma",
        "3.5",
        "44 mins",
        "Chicken, Cheese, Veggie"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_6,
        "Ya Rahman Briyani",
        "3.9",
        "32 mins",
        "Indian, Briyani"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_7,
        "Dindigul Thalapakatti",
        "4.6",
        "19 mins",
        "Arabian, Indian, BBQ, Chinese"
    ),
)

val getQuicklyFoodsList = listOf(
    HoriServicesDataClass(
        R.drawable.hori_img_8,
        "A2B Adayar Anandha Bavan",
        "4.9",
        "27 mins",
        "South Indian, Pan-India"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_9,
        "Muniyandi Vilaas",
        "4.3",
        "22 mins",
        "Tamilan, Pan-Indian, Meals"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_10,
        "Saravana Bavan",
        "3.8",
        "36 mins",
        "Tamilan, Meals, South Indian"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_11,
        "Apty Choice",
        "3.5",
        "48 mins",
        "Shawarma, Chicken, Noodles"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_12,
        "The Red Box",
        "3.7",
        "21 mins",
        "Mutton, Veggie, Chicken"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_1,
        "Ya Rahman Briyani",
        "3.9",
        "13 mins",
        "Indian, Briyani"
    ),
    HoriServicesDataClass(
        R.drawable.hori_img_2,
        "The Pizza Hut",
        "4.5",
        "34 mins",
        "Mexican, Lebanese, Italian"
    ),
)

val whatsOnMindFoodsList = listOf(
    R.drawable.whatsonmind1,
    R.drawable.whatsonmind2,
    R.drawable.whatsonmind3
)

val exploreFoodsList = listOf(
    VertiServicesDataClass(
        R.drawable.verti_img_1,
        "Apty Choice",
        "3.9",
        "26 mins",
        "Briyani, Chinese, Continental Taste",
        "Nanmangalam (1.7 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_2,
        "Grill Nights",
        "3.4",
        "35 mins",
        "Briyani, Chinese, Continental Taste",
        "Shollinganalur (5.4 km)",
        R.drawable.img_freedelivery
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_3,
        "Oho Shwarma",
        "2.9",
        "40 mins",
        "Briyani, Chinese, Continental Taste",
        "Pallikaranai (3.2 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_5,
        "Salem RR Briyani",
        "4.5",
        "15 mins",
        "Briyani, Chinese, Continental Taste",
        "Tambaram (2.9 km)",
        R.drawable.img_freedelivery
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_4,
        "Fasta Pizza",
        "3.8",
        "10 mins",
        "Briyani, Chinese, Continental Taste",
        "Nanmangalam (1.8 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_6,
        "Domino's Pizza",
        "2.7",
        "22 mins",
        "Briyani, Chinese, Continental Taste",
        "Keelkatalai (3.6 km)",
        R.drawable.img_freedelivery
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_7,
        "Sangeetha Veg Restarurant",
        "3.9",
        "32 mins",
        "Briyani, Chinese, Continental Taste",
        "Medavakkam (4.2 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_8,
        "Nandhana Palace",
        "4.4",
        "12 mins",
        "Briyani, Chinese, Continental Taste",
        "Tambaram (6.7 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_7,
        "Mani's Dum Briyani",
        "4.7",
        "28 mins",
        "Briyani, Chinese, Continental Taste",
        "Pallikaranai (4.4 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_8,
        "Subway",
        "3.6",
        "25 mins",
        "Briyani, Chinese, Continental Taste",
        "Shollinganalur (3.9 km)",
        R.drawable.img_freedelivery
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_9,
        "The Red Box",
        "4.1",
        "37 mins",
        "Briyani, Chinese, Continental Taste",
        "Nanmangalam (2.7 km)",
        R.drawable.img_extra10
    ),
    VertiServicesDataClass(
        R.drawable.verti_img_10,
        "A2B - Adyar Anandha Bhavan",
        "3.7",
        "34 mins",
        "Briyani, Chinese, Continental Taste",
        "Medavakkam (1.2 km)",
        R.drawable.img_freedelivery
    ),
)

val instamartSlide1 = arrayListOf(
    R.drawable.img_slide_1,
    R.drawable.img_slide_2,
    R.drawable.img_slide_3,
    R.drawable.img_slide_4,
)

val instamartSlide2 = arrayListOf(
    R.drawable.img_slide_5,
    R.drawable.img_slide_6,
    R.drawable.img_slide_7,
    R.drawable.img_slide_8,
)

val hotDealsList = listOf(
    MapviewDataClass(
        R.drawable.instamart_hori_1,
        "",
        "Robusta Banana",
        "வாழைப்பழம்",
        "4 pieces",
        "35",
        "28"
    ),
    MapviewDataClass(R.drawable.instamart_hori_2, "", "Lays", "", "500g", "50", "45"),
    MapviewDataClass(R.drawable.instamart_hori_3, "", "Little Hearts", "", "600g", "55", "42"),
    MapviewDataClass(
        R.drawable.instamart_hori_4,
        "",
        "Arokya Pure Milk",
        "பால்",
        "500ml",
        "25",
        "23"
    )
)

val topPicksList = listOf(
    MapviewDataClass(R.drawable.instamart_hori_5, "", "Onion", "வெங்காயம்", "1kg", "100", "75"),
    MapviewDataClass(R.drawable.instamart_hori_6, "", "Brown Bread", "", "30 pieces", "60", "45"),
    MapviewDataClass(
        R.drawable.instamart_hori_7,
        "",
        "Hatsun Curd",
        "தயிர்",
        "250ml",
        "25",
        "15"
    ),
    MapviewDataClass(
        R.drawable.instamart_hori_8,
        "",
        "Potato",
        "உருளைக்கிழங்கு",
        "1kg",
        "78",
        "64"
    )
)

val genieSlideList = arrayListOf(
    R.drawable.genie_slide_1,
    R.drawable.genie_slide_2,
    R.drawable.genie_slide_3,
    R.drawable.genie_slide_4,
    R.drawable.genie_slide_5,
    R.drawable.genie_slide_6,
)

//------------------------ Group Place Category List --------------------------

// Dummy Data for Food & Drinks Category
val foodAndDrinksList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_1,
        "Palmshore",
        "4.6",
        "Medavakkam",
        "2.8 km",
        "Chinese, North Indian",
        "₹1000 for two"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_2,
        "Smoke Hub Barbeque",
        "4.2",
        "Tambaram",
        "5.2 km",
        "India, South Indian",
        "₹1200 for three"
    )
)

// Dummy Data for Shopping Category
val shoppingList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_3,
        "Fashion Street",
        "4.5",
        "Anna Nagar",
        "3.0 km",
        "Clothing, Accessories",
        "₹1500 for shopping"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_4,
        "Tech Store",
        "4.0",
        "T. Nagar",
        "2.0 km",
        "Electronics, Gadgets",
        "₹3000 for electronics"
    )
)

// Dummy Data for Transportation Category
val transportationList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_5,
        "City Cabs",
        "4.8",
        "Koramangala",
        "1.5 km",
        "Taxi, Ride Sharing",
        "₹300 for 10 km"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_1,
        "Metro Station",
        "4.6",
        "MG Road",
        "1.0 km",
        "Metro Services",
        "₹50 for one ride"
    )
)

// Dummy Data for Health Care Category
val healthCareList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_2,
        "City Hospital",
        "4.3",
        "Indiranagar",
        "3.5 km",
        "General Health",
        "₹1000 for consultation"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_3,
        "Dental Care Clinic",
        "4.7",
        "Jayanagar",
        "2.2 km",
        "Dental Services",
        "₹800 for check-up"
    )
)

// Dummy Data for Financial Services Category
val financialServicesList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_4,
        "ABC Bank",
        "4.5",
        "Bangalore",
        "3.8 km",
        "Banking, Loans",
        "Varies"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_5,
        "XYZ ATMs",
        "4.0",
        "Whitefield",
        "1.5 km",
        "ATM Services",
        "Varies"
    )
)

// Dummy Data for Public Services Category
val publicServicesList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_1,
        "City Hall",
        "4.2",
        "Cubbon Park",
        "2.0 km",
        "Public Administration",
        "Varies"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_2,
        "Passport Office",
        "4.1",
        "Brigade Road",
        "1.8 km",
        "Government Services",
        "Varies"
    )
)

// Dummy Data for Fitness & Wellness Category
val fitnessWellnessList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_3,
        "Fit Gym",
        "4.8",
        "HSR Layout",
        "1.5 km",
        "Gym, Fitness Classes",
        "₹1500 per month"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_4,
        "Wellness Spa",
        "4.9",
        "BTM Layout",
        "1.0 km",
        "Spa Services",
        "₹2000 for package"
    )
)

// Dummy Data for Personal Care Category
val personalCareList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_5,
        "Beauty Salon",
        "4.6",
        "Koramangala",
        "2.5 km",
        "Hair, Skin",
        "₹800 for services"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_1,
        "Nail Bar",
        "4.4",
        "Indiranagar",
        "1.8 km",
        "Nail Art",
        "₹500 for services"
    )
)

// Dummy Data for Entertainment Category
val entertainmentList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_2,
        "Cinema X",
        "4.5",
        "Jayanagar",
        "2.0 km",
        "Movies, Events",
        "₹300 for ticket"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_3,
        "Fun Park",
        "4.7",
        "Brigade Road",
        "3.5 km",
        "Amusement Park",
        "₹800 for entry"
    )
)

// Dummy Data for Education Category
val educationList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_4,
        "XYZ School",
        "4.3",
        "BTM Layout",
        "1.5 km",
        "Primary Education",
        "₹20000 per year"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_5,
        "ABC College",
        "4.6",
        "Koramangala",
        "2.2 km",
        "Higher Education",
        "₹50000 per year"
    )
)

// Dummy Data for Religious Category
val religiousList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_1,
        "City Church",
        "4.4",
        "Indiranagar",
        "2.0 km",
        "Christian Place of Worship",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_hori_2,
        "Grand Mosque",
        "4.5",
        "Malleswaram",
        "1.8 km",
        "Islamic Place of Worship",
        "N/A"
    )
)

