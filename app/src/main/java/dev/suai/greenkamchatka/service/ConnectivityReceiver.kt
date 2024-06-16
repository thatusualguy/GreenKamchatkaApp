package dev.suai.greenkamchatka.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

//class ConnectivityReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = connectivityManager.activeNetworkInfo
//        val isConnected = activeNetwork?.isConnectedOrConnecting == true
//
//        if (isConnected) {
//            // Trigger the background service to send queued reports
//            val serviceIntent = Intent(context, ReportService::class.java)
//            context.startService(serviceIntent)
//        }
//    }
//}