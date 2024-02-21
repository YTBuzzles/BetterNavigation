package com.example.betternavigation

import android.accessibilityservice.AccessibilityService
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.Preference
import com.example.betternavigation.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
lateinit var toolbar: Toolbar
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navController = findNavController(binding)
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        findViewById<Toolbar>(R.id.action_settings).setupWithNavController(navController, appBarConfiguration)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment_main)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}


class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("AccessibilityService", "Received event: $event")

        if (event?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            val txt = event.text[0]

            if (txt == "screenshot") {
                performGlobalAction(9)
            } else if (txt == "back") {
                performGlobalAction(1)
            } else if (txt == "recents") {
                performGlobalAction(3)
            } else if (txt == "home") {
                performGlobalAction(2)
            } else if (txt == "notifications") {
                performGlobalAction(4)
            } else if (txt == "quicksettings") {
                performGlobalAction(5)
            } else if (txt == "power") {
                performGlobalAction(6)
            } else if (txt == "lockscreen") {
                performGlobalAction(8)
            }
        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
        // Handle interruption (if necessary)
    }


    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("Accessibility Service", "onServiceConnected:")
    }
}