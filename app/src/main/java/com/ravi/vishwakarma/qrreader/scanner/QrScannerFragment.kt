package com.ravi.vishwakarma.qrreader.scanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravi.vishwakarma.qrreader.R

class QrScannerFragment : Fragment() {

    companion object{
        fun newInstance() : QrScannerFragment{
            return QrScannerFragment()
        }
    }
    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        initializeOrScanner()
        return mView.rootView
    }

    private fun initializeOrScanner() {

    }


}