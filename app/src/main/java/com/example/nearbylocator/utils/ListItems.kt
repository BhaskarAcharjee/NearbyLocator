package com.example.nearbylocator.utils

import com.example.nearbylocator.R
import com.example.nearbylocator.model.PlaceTypeIconDataClass
import com.example.nearbylocator.model.PlaceCategoryGroupDataClass
import com.example.nearbylocator.model.HoriServicesDataClass
import com.example.nearbylocator.model.MapviewDataClass
import com.example.nearbylocator.model.MapviewFavDataClass
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
    PlaceTypeIconDataClass("Restaurant", R.drawable.resturant_icon),
    PlaceTypeIconDataClass("Bank", R.drawable.bank_icon),
    PlaceTypeIconDataClass("ATM", R.drawable.atm_icon),
    PlaceTypeIconDataClass("Hospital", R.drawable.hospital_icon),
    PlaceTypeIconDataClass("Groceries", R.drawable.groceries_icon),
    PlaceTypeIconDataClass("Parking", R.drawable.parking_icon),
    PlaceTypeIconDataClass("Post Office", R.drawable.postoffice_icon),
    PlaceTypeIconDataClass("Police Station", R.drawable.policestation_icon),
    PlaceTypeIconDataClass("Bus Stop", R.drawable.busstop_icon),
    PlaceTypeIconDataClass("Pharmacy", R.drawable.pharmacy_icon),
)

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

//------------------------ Individual Place Category List --------------------------

val restaurantList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Spice Route",
        "4.5",
        "T Nagar",
        "3.0 km",
        "Thai, Chinese",
        "₹800 for two"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "Café Mocha",
        "4.1",
        "Adyar",
        "2.5 km",
        "Continental, Italian",
        "₹600 for one"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Tandoor Tales",
        "4.3",
        "Ombakkam",
        "4.0 km",
        "Indian, Mughlai",
        "₹1000 for two"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "The Food Factory",
        "3.8",
        "Kilpauk",
        "1.5 km",
        "Fast Food, Chinese",
        "₹500 for three"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Bistro Cafe",
        "4.6",
        "Mylapore",
        "2.2 km",
        "Coffee, Snacks",
        "₹400 for one"
    )
)

val bankList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "HDFC Bank",
        "4.2",
        "Anna Nagar",
        "1.0 km",
        "Banking Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "ICICI Bank",
        "3.9",
        "T Nagar",
        "1.5 km",
        "Banking Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "SBI Bank",
        "4.1",
        "Velachery",
        "2.0 km",
        "Banking Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Axis Bank",
        "3.8",
        "Adyar",
        "1.2 km",
        "Banking Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Bank of Baroda",
        "4.3",
        "Kotturpuram",
        "2.5 km",
        "Banking Services",
        "N/A"
    )
)

val atmList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "HDFC ATM",
        "4.5",
        "Guindy",
        "0.5 km",
        "ATM Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "ICICI ATM",
        "4.0",
        "Anna Nagar",
        "0.8 km",
        "ATM Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "SBI ATM",
        "4.2",
        "Kottivakkam",
        "1.2 km",
        "ATM Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Axis ATM",
        "3.9",
        "Mylapore",
        "0.7 km",
        "ATM Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Bank of Baroda ATM",
        "4.1",
        "Choolaimedu",
        "1.0 km",
        "ATM Services",
        "N/A"
    )
)

val hospitalList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Apollo Hospital",
        "4.8",
        "Vanagaram",
        "2.3 km",
        "General Hospital",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "Fortis Malar Hospital",
        "4.5",
        "Adyar",
        "1.5 km",
        "Cardiology, Neurology",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Sri Ramachandra Medical Centre",
        "4.2",
        "Porur",
        "3.1 km",
        "General Hospital",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "MIOT International",
        "4.4",
        "Manapakkam",
        "2.0 km",
        "Multi-Specialty",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Government General Hospital",
        "4.0",
        "Chennai Central",
        "4.5 km",
        "General Hospital",
        "N/A"
    )
)

val groceriesList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Reliance Fresh",
        "4.1",
        "Thiruvanmiyur",
        "1.0 km",
        "Grocery Store",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "Big Bazaar",
        "4.3",
        "Velachery",
        "1.5 km",
        "Grocery Store",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "D-Mart",
        "4.5",
        "Madipakkam",
        "2.2 km",
        "Grocery Store",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "More Supermarket",
        "4.2",
        "Sholinganallur",
        "1.8 km",
        "Grocery Store",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "SPAR Hypermarket",
        "4.0",
        "Adyar",
        "3.0 km",
        "Grocery Store",
        "N/A"
    )
)

val parkingList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "T Nagar Parking",
        "4.5",
        "T Nagar",
        "1.0 km",
        "Parking Area",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "Anna Nagar Parking",
        "4.2",
        "Anna Nagar",
        "1.5 km",
        "Parking Area",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Pallikaranai Parking",
        "4.1",
        "Pallikaranai",
        "2.0 km",
        "Parking Area",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Adyar Parking",
        "3.9",
        "Adyar",
        "0.8 km",
        "Parking Area",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Velachery Parking",
        "4.3",
        "Velachery",
        "2.5 km",
        "Parking Area",
        "N/A"
    )
)

val postOfficeList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Anna Nagar Post Office",
        "4.4",
        "Anna Nagar",
        "1.0 km",
        "Postal Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "T Nagar Post Office",
        "4.1",
        "T Nagar",
        "1.2 km",
        "Postal Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Adyar Post Office",
        "4.3",
        "Adyar",
        "1.5 km",
        "Postal Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Kilpauk Post Office",
        "3.8",
        "Kilpauk",
        "0.9 km",
        "Postal Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Velachery Post Office",
        "4.0",
        "Velachery",
        "2.0 km",
        "Postal Services",
        "N/A"
    )
)

val policeStationList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Anna Nagar Police Station",
        "4.2",
        "Anna Nagar",
        "1.5 km",
        "Police Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "T Nagar Police Station",
        "4.0",
        "T Nagar",
        "1.0 km",
        "Police Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Adyar Police Station",
        "4.3",
        "Adyar",
        "2.0 km",
        "Police Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Kilpauk Police Station",
        "4.1",
        "Kilpauk",
        "1.2 km",
        "Police Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Velachery Police Station",
        "3.9",
        "Velachery",
        "1.8 km",
        "Police Services",
        "N/A"
    )
)


val busStopList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Anna Nagar Bus Stop",
        "4.5",
        "Anna Nagar",
        "1.0 km",
        "Bus Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "T Nagar Bus Stop",
        "4.2",
        "T Nagar",
        "0.8 km",
        "Bus Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "Adyar Bus Stop",
        "4.3",
        "Adyar",
        "1.5 km",
        "Bus Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Kilpauk Bus Stop",
        "4.1",
        "Kilpauk",
        "1.2 km",
        "Bus Services",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Velachery Bus Stop",
        "4.0",
        "Velachery",
        "1.8 km",
        "Bus Services",
        "N/A"
    )
)

val pharmacyList = listOf(
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_1,
        "Apollo Pharmacy",
        "4.4",
        "Adyar",
        "1.0 km",
        "Pharmacy",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_2,
        "Health Plus Pharmacy",
        "4.2",
        "T Nagar",
        "1.5 km",
        "Pharmacy",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_3,
        "MedPlus Pharmacy",
        "4.3",
        "Velachery",
        "2.0 km",
        "Pharmacy",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_4,
        "Farmacia Pharmacy",
        "4.1",
        "Kilpauk",
        "1.2 km",
        "Pharmacy",
        "N/A"
    ),
    PlaceCategoryGroupDataClass(
        R.drawable.dineout_verti_5,
        "Sundaram Medicals",
        "4.0",
        "Pallikaranai",
        "2.5 km",
        "Pharmacy",
        "N/A"
    )
)

