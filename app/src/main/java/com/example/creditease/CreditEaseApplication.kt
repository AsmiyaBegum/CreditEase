package com.example.creditease

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.creditease.utils.Utils
import com.google.android.gms.location.LocationServices
import java.util.Currency
import java.util.Locale
import kotlin.system.exitProcess

class CreditEaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        if(!Utils.isBuildVariantDebug()){
            Thread.setDefaultUncaughtExceptionHandler { _, e ->
//                handleUncaughtException(e)
//                restartApp()
            }
        }
    }


    private fun handleUncaughtException(e : Throwable){
        Log.e("app_crash",e.message.toString())
    }

    private fun restartApp() {
        val intent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        exitProcess(1)
    }



    companion object{
        private var appContext: Context? = null

        var currencySymbol : String = "₹"

        fun appContext(): Context? {
            return appContext
        }
    }
}