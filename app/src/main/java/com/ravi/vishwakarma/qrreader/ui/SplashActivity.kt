package com.ravi.vishwakarma.qrreader.ui

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.ravi.vishwakarma.qrreader.MainActivity
import com.ravi.vishwakarma.qrreader.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkForPermission()
    }

    private fun checkForPermission() {
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
            goToMainActivity()
        }
        else{
            requestThePermission()
        }
    }


    private fun requestThePermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                goToMainActivity()
            } else if(isUserPermanentlyDenied()){
                showGoToAppSettingDialog()
            }
        }
    }

    private fun showGoToAppSettingDialog() {
        AlertDialog.Builder(this)
            .setTitle("Grant Permission!!")
            .setMessage("we need permissions")
            .setPositiveButton("Grant"){dialog, which->
                goToAppSettings()
            }
            .setNegativeButton("Cancel"){ dialog, which ->
                Toast.makeText(this, "we need permission for this", Toast.LENGTH_SHORT).show()
                finish()
            }.show()
    }

    private fun goToAppSettings() {
        var intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package",packageName,null))
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun isUserPermanentlyDenied(): Boolean {
        return shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA).not()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        checkForPermission()
    }
}