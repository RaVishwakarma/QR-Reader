package com.ravi.vishwakarma.qrreader.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.ravi.vishwakarma.qrreader.R
import com.ravi.vishwakarma.qrreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewPagerAdapter()
        setBottomNavigation()
        setViewPagerListener()
    }

    private fun setViewPagerListener() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.selectedItemId = when(position){
                    0 ->  R.id.scanMenuId
                    1 ->  R.id.recentScannedMenuId
                    2 ->  R.id.favouritesMenuId
                    else -> R.id.scanMenuId
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.scanMenuId -> binding.viewPager.currentItem = 0
                R.id.recentScannedMenuId -> binding.viewPager.currentItem = 1
                R.id.favouritesMenuId -> binding.viewPager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        binding.viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }
}