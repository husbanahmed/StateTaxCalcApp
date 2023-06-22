package com.example.statetaxcalcapp
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.statetaxcalcapp.databinding.FragmentLocalBinding


class LocalFragment : Fragment() {

    private lateinit var binding: FragmentLocalBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLocalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etLocalEnterAmount.setOnClickListener{
            val savedLocalTotal = binding.etLocalEnterAmount.text.toString()
        }

        binding.etInputLocalZipCode.setOnClickListener {
            val savedZipCode = binding.etInputLocalZipCode.text.toString()
        }
    }

    // Inside your fragment

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission already granted
            // Perform your location-related operations
        } else {
            // Permission not granted, request it
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                // Perform your location-related operations
            } else {
                // Location permission denied
                // Handle accordingly (e.g., show a message to the user)
            }
        }
    }
}