package com.example.nearbylocator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nearbylocator.adapters.PlaceCategoryIndividualAdapter
import com.example.nearbylocator.constants.airportList
import com.example.nearbylocator.constants.atmList
import com.example.nearbylocator.constants.bankList
import com.example.nearbylocator.constants.barList
import com.example.nearbylocator.constants.busStopList
import com.example.nearbylocator.constants.cafeList
import com.example.nearbylocator.constants.churchList
import com.example.nearbylocator.constants.clinicList
import com.example.nearbylocator.constants.collegeList
import com.example.nearbylocator.constants.groceryStoreList
import com.example.nearbylocator.constants.gymList
import com.example.nearbylocator.constants.hospitalList
import com.example.nearbylocator.constants.laundryList
import com.example.nearbylocator.constants.libraryList
import com.example.nearbylocator.constants.mosqueList
import com.example.nearbylocator.constants.museumList
import com.example.nearbylocator.constants.parkList
import com.example.nearbylocator.constants.parkingList
import com.example.nearbylocator.constants.pharmacyList
import com.example.nearbylocator.constants.policeStationList
import com.example.nearbylocator.constants.postOfficeList
import com.example.nearbylocator.constants.restaurantList
import com.example.nearbylocator.constants.salonList
import com.example.nearbylocator.constants.schoolList
import com.example.nearbylocator.constants.shoppingMallList
import com.example.nearbylocator.constants.supermarketList
import com.example.nearbylocator.constants.templeList
import com.example.nearbylocator.constants.theaterList
import com.example.nearbylocator.constants.trainStationList
import com.example.nearbylocator.constants.zooList
import com.example.nearbylocator.databinding.FragmentCategoryIndividualBinding
import com.example.nearbylocator.utils.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

class CategoryIndividualFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentCategoryIndividualBinding
    private lateinit var placeCategoryIndividualAdapter: PlaceCategoryIndividualAdapter
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var categoryType: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryIndividualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the category type from arguments
        arguments?.let {
            categoryType = it.getString("categoryType") ?: ""
        }

        // Initialize components
        setupMapView(savedInstanceState)
        setupSearchBar()
        setupRecyclerViews()
        setupScrollBehavior()
    }

    private fun setupMapView(savedInstanceState: Bundle?) {
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)  // Asynchronously initialize the map
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Set map features (e.g., markers, UI settings, etc.)
    }

    private fun setupRecyclerViews() {
        binding.apply {
            // Setup vertical RecyclerView for more around you
            rvMorearoundyou.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            // Set the appropriate adapter based on the category type
            placeCategoryIndividualAdapter = when (categoryType) {
                // Food & Drinks Categories
                "Restaurant" -> {
                    tvMoreAroundYou.text = "Check Out Restaurants Around You"
                    PlaceCategoryIndividualAdapter(restaurantList)
                }

                "Cafe" -> {
                    tvMoreAroundYou.text = "Check Out Cafes Around You"
                    PlaceCategoryIndividualAdapter(cafeList)
                }

                "Bar" -> {
                    tvMoreAroundYou.text = "Check Out Bars Around You"
                    PlaceCategoryIndividualAdapter(barList)
                }

                "Grocery Store" -> {
                    tvMoreAroundYou.text = "Check Out Grocery Stores Around You"
                    PlaceCategoryIndividualAdapter(groceryStoreList)
                }

                "Supermarket" -> {
                    tvMoreAroundYou.text = "Check Out Supermarkets Around You"
                    PlaceCategoryIndividualAdapter(supermarketList)
                }

                // Financial Services Categories
                "Bank" -> {
                    tvMoreAroundYou.text = "Check Out Banks Around You"
                    PlaceCategoryIndividualAdapter(bankList)
                }

                "ATM" -> {
                    tvMoreAroundYou.text = "Check Out ATMs Around You"
                    PlaceCategoryIndividualAdapter(atmList)
                }

                // Health & Wellness Categories
                "Hospital" -> {
                    tvMoreAroundYou.text = "Check Out Hospitals Around You"
                    PlaceCategoryIndividualAdapter(hospitalList)
                }

                "Clinic" -> {
                    tvMoreAroundYou.text = "Check Out Clinics Around You"
                    PlaceCategoryIndividualAdapter(clinicList)
                }

                "Pharmacy" -> {
                    tvMoreAroundYou.text = "Check Out Pharmacies Around You"
                    PlaceCategoryIndividualAdapter(pharmacyList)
                }

                "Gym" -> {
                    tvMoreAroundYou.text = "Check Out Gyms Around You"
                    PlaceCategoryIndividualAdapter(gymList)
                }

                // Personal Care Categories
                "Salon" -> {
                    tvMoreAroundYou.text = "Check Out Salons Around You"
                    PlaceCategoryIndividualAdapter(salonList)
                }

                "Laundry" -> {
                    tvMoreAroundYou.text = "Check Out Laundry Services Around You"
                    PlaceCategoryIndividualAdapter(laundryList)
                }

                // Transportation Categories
                "Bus Stop" -> {
                    tvMoreAroundYou.text = "Check Out Bus Stops Around You"
                    PlaceCategoryIndividualAdapter(busStopList)
                }

                "Train Station" -> {
                    tvMoreAroundYou.text = "Check Out Train Stations Around You"
                    PlaceCategoryIndividualAdapter(trainStationList)
                }

                "Airport" -> {
                    tvMoreAroundYou.text = "Check Out Airports Around You"
                    PlaceCategoryIndividualAdapter(airportList)
                }

                // Public Services Categories
                "Police Station" -> {
                    tvMoreAroundYou.text = "Check Out Police Stations Around You"
                    PlaceCategoryIndividualAdapter(policeStationList)
                }

                "Post Office" -> {
                    tvMoreAroundYou.text = "Check Out Post Offices Around You"
                    PlaceCategoryIndividualAdapter(postOfficeList)
                }

                "Parking" -> {
                    tvMoreAroundYou.text = "Check Out Parking Spots Around You"
                    PlaceCategoryIndividualAdapter(parkingList)
                }

                // Entertainment Categories
                "Park" -> {
                    tvMoreAroundYou.text = "Check Out Parks Around You"
                    PlaceCategoryIndividualAdapter(parkList)
                }

                "Movie Theater" -> {
                    tvMoreAroundYou.text = "Check Out Movie Theaters Around You"
                    PlaceCategoryIndividualAdapter(theaterList)
                }

                "Shopping Mall" -> {
                    tvMoreAroundYou.text = "Check Out Shopping Malls Around You"
                    PlaceCategoryIndividualAdapter(shoppingMallList)
                }

                "Library" -> {
                    tvMoreAroundYou.text = "Check Out Libraries Around You"
                    PlaceCategoryIndividualAdapter(libraryList)
                }

                "Museum" -> {
                    tvMoreAroundYou.text = "Check Out Museums Around You"
                    PlaceCategoryIndividualAdapter(museumList)
                }

                "Zoo" -> {
                    tvMoreAroundYou.text = "Check Out Zoos Around You"
                    PlaceCategoryIndividualAdapter(zooList)
                }

                // Education Categories
                "School" -> {
                    tvMoreAroundYou.text = "Check Out Schools Around You"
                    PlaceCategoryIndividualAdapter(schoolList)
                }

                "University" -> {
                    tvMoreAroundYou.text = "Check Out Universities Around You"
                    PlaceCategoryIndividualAdapter(collegeList)
                }

                // Religious Categories
                "Church" -> {
                    tvMoreAroundYou.text = "Check Out Churches Around You"
                    PlaceCategoryIndividualAdapter(churchList)
                }

                "Temple" -> {
                    tvMoreAroundYou.text = "Check Out Temples Around You"
                    PlaceCategoryIndividualAdapter(templeList)
                }

                "Mosque" -> {
                    tvMoreAroundYou.text = "Check Out Mosques Around You"
                    PlaceCategoryIndividualAdapter(mosqueList)
                }

                // Default case
                else -> {
                    tvMoreAroundYou.text = "Check Out Places Around You"
                    PlaceCategoryIndividualAdapter(emptyList())
                }
            }

            rvMorearoundyou.adapter = placeCategoryIndividualAdapter
        }
    }

    private fun setupScrollBehavior() {
        binding.rvMorearoundyou.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Get the height of rl_header to use as a threshold
                val linearLayoutHeight = binding.rlHeader.height
                val scrollY = recyclerView.computeVerticalScrollOffset()

                // Toggle visibility based on scroll position
                if (scrollY >= linearLayoutHeight) {
                    binding.llHeader.visibility = View.VISIBLE
                    binding.rlHeader.visibility = View.GONE
                } else {
                    binding.llHeader.visibility = View.GONE
                    binding.rlHeader.visibility = View.VISIBLE
                }
            }
        })
    }


    private fun setupSearchBar() {
        val searchBarView = binding.searchBarView
        searchBarView.setHints(services_hint_Strings)   // Sets up the search bar hints dynamically
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
