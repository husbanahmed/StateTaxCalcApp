package com.example.statetaxcalcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.Manifest
import android.content.pm.PackageManager
import com.example.statetaxcalcapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stateFragment = StateFragment()
        val localFragment = LocalFragment()
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setCurrentFragment(localFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.state_tax -> setCurrentFragment(stateFragment)
                R.id.local_tax -> setCurrentFragment(localFragment)
            }
            true
        }

    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,fragment)
            commit()
        }

    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationFinePermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        var permissionToRequest= mutableListOf<String>()
        if(!hasLocationForegroundPermission()){
            permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!hasLocationFinePermission()){
            permissionToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }



}