package com.example.statetaxcalcapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.example.statetaxcalcapp.databinding.FragmentLocalBinding
import com.google.android.gms.location.LocationServices
import java.util.Locale


class LocalFragment : Fragment() {

    private lateinit var binding: FragmentLocalBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 100
    private val viewModel: LocalFragmentViewModel by viewModels()
    private  lateinit var savedZipCode: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accessUserLocation()

        binding.etLocalEnterAmount.setOnClickListener {
            val savedLocalTotal = binding.etLocalEnterAmount.text.toString()
        }

        binding.etInputLocalZipCode.setOnClickListener {
            val savedZipCode = binding.etInputLocalZipCode.text.toString()
        }

        viewModel.fetchData()
        viewModel.zipCodeLiveData.observe(viewLifecycleOwner) {
//            Unsure about what we should observe
        }
    }

    // Inside your fragment

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "tHIS SI THE ONREQUEST PERMISSION 2 ")
                // Location permission granted
                // Perform your location-related operations
            } else {
                Toast.makeText(
                    requireContext(),
                    "Location permission denied. Input zip code manually.",
                    Toast.LENGTH_LONG
                ).show()
                // Location permission denied
                // Handle accordingly (e.g., show a message to the user)
            }
        }
    }

    private fun accessUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "Was this even called")
            // Location permission is granted
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Handle the location result
                    if (location != null) {
                        // Access the latitude and longitude
                        val latitude = location.latitude
                        val longitude = location.longitude
                        Log.d(TAG, " this is the lat $latitude")

                        // Perform reverse geocoding to obtain the ZIP code
                        val geocoder = Geocoder(requireContext(), Locale.getDefault())
                        val addressList = geocoder.getFromLocation(latitude, longitude, 1)
                        Log.d(TAG, " This $addressList might  be empty")
                        if (addressList != null) {
                            if (addressList.isNotEmpty()) {
                                val address = addressList[0]
                                val zipCode = address.postalCode

                                // Update your UI text field with the ZIP code
                                // textField.text = "ZIP code: $zipCode"
                            } else {
                                // Handle the case where no address is found
                            }
                        }
                    } else {
                        // Handle the case where the location is null
                    }
                }
                .addOnFailureListener { exception: Exception ->
                    // Handle the location request failure
                }
        } else {
            // Location permission is not granted
            // Request location permission
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }
}