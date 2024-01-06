package com.ravi.vishwakarma.qrreader.mainActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ravi.vishwakarma.qrreader.scanned_history.ScannedHistoryFragment
import com.ravi.vishwakarma.qrreader.scanner.QrScannerFragment

class MainPagerAdapter(private var fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> QrScannerFragment.newInstance()
            1 -> ScannedHistoryFragment.newInstance()
            2 -> ScannedHistoryFragment.newInstance()
            else -> QrScannerFragment.newInstance()
        }
    }

}