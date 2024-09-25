package com.example.nearbylocator.utils

import com.example.nearbylocator.R
import com.example.nearbylocator.model.ChoosePlaceCategoryDataClass
import com.example.nearbylocator.model.EventDataClass
import com.example.nearbylocator.model.HoriServicesDataClass
import com.example.nearbylocator.model.MapviewDataClass
import com.example.nearbylocator.model.MapviewFavDataClass
import com.example.nearbylocator.model.VertiServicesDataClass

//Search hints
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
    "Car Repair",
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
    "Salon",
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

val choosePlaceCategories = listOf(
    ChoosePlaceCategoryDataClass("Restaurant", R.drawable.resturant_icon),
    ChoosePlaceCategoryDataClass("Bank", R.drawable.bank_icon),
    ChoosePlaceCategoryDataClass("ATM", R.drawable.atm_icon),
    ChoosePlaceCategoryDataClass("Hospital", R.drawable.hospital_icon),
    ChoosePlaceCategoryDataClass("Groceries", R.drawable.groceries_icon),
    ChoosePlaceCategoryDataClass("Parking", R.drawable.parking_icon)
)


val homeSlideImages = arrayListOf(
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

val dineoutSlideList = arrayListOf(
    R.drawable.dineout_slide_1,
    R.drawable.dineout_slide_2,
    R.drawable.dineout_slide_3,
    R.drawable.dineout_slide_4,
    R.drawable.dineout_slide_5,
)

val dineoutBestOffersList = listOf(
    EventDataClass(
        R.drawable.dineout_hori_1,
        "Palmshore",
        "4.6",
        "Medavakkam",
        "2.8 km",
        "Chinese, North Indian",
        "₹1000 for two"
    ),
    EventDataClass(
        R.drawable.dineout_hori_2,
        "Smoke Hub Barbeque",
        "4.2",
        "Tambaram",
        "5.2 km",
        "India, South Indian",
        "₹1200 for three"
    ),
    EventDataClass(
        R.drawable.dineout_hori_3,
        "The Cycler Gap",
        "3.8",
        "Pallikaranai",
        "3.5 km",
        "Italian, Lebanese",
        "₹500 for four"
    ),
    EventDataClass(
        R.drawable.dineout_hori_4,
        "Kobe Sizzlers",
        "3.5",
        "Medavakkam",
        "1.2 km",
        "North Indian, Pan India",
        "₹700 for two"
    ),
    EventDataClass(
        R.drawable.dineout_hori_5,
        "Oragne Work & BBQ",
        "4.8",
        "Keelkatalai",
        "4.2 km",
        "American, Mexixan",
        "₹1500 for one"
    )
)

val dineoutMoreList = listOf(
    EventDataClass(
        R.drawable.dineout_verti_1,
        "Palmshore",
        "4.6",
        "Medavakkam",
        "2.8 km",
        "Chinese, North Indian",
        "₹1000 for two"
    ),
    EventDataClass(
        R.drawable.dineout_verti_2,
        "Smoke Hub Barbeque",
        "4.2",
        "Tambaram",
        "5.2 km",
        "India, South Indian",
        "₹1200 for three"
    ),
    EventDataClass(
        R.drawable.dineout_verti_3,
        "The Cycler Gap",
        "3.8",
        "Pallikaranai",
        "3.5 km",
        "Italian, Lebanese",
        "₹500 for four"
    ),
    EventDataClass(
        R.drawable.dineout_verti_4,
        "Kobe Sizzlers",
        "3.5",
        "Medavakkam",
        "1.2 km",
        "North Indian, Pan India",
        "₹700 for two"
    ),
    EventDataClass(
        R.drawable.dineout_verti_5,
        "Oragne Work & BBQ",
        "4.8",
        "Keelkatalai",
        "4.2 km",
        "American, Mexixan",
        "₹1500 for one"
    )
)