package com.example.statetaxcalcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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

}