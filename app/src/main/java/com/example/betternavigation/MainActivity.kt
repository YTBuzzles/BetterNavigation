package com.example.betternavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val recents = findViewById<Button>(R.id.recents)
        val accessibilityNav = MyAccessibilityService()
        val access = findViewById<TextView>(R.id.access)
        val requestStatus = findViewById<Button>(R.id.requestStatus)


        requestStatus.setOnClickListener {
            var accList = accessibilityNav.systemActions
        }

        recents.setOnClickListener{
            accessibilityNav.performGlobalAction(3)
        }
    }
}

class MyAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {}
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
}




