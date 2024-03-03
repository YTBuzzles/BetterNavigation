package com.example.betternavigation

import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi

class OverlayService : AccessibilityService() {

    @RequiresApi(32)
    @SuppressLint("ClickableViewAccessibility")
    override fun onServiceConnected() {
        super.onServiceConnected()

        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // calculate screen width
        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = windowManager.currentWindowMetrics.bounds
            displayMetrics.widthPixels = display.width()
        } else {
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        val screenWidth = displayMetrics.widthPixels
        val barWidth = 90


        // Set the overlay layout parameters
        val layoutParams = LayoutParams()
        layoutParams.apply {
            y = 0
            x = (screenWidth/2) - (barWidth/2)
            width = barWidth
            height = LayoutParams.MATCH_PARENT
            type = LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            format = PixelFormat.TRANSPARENT
            flags = LayoutParams.FLAG_NOT_FOCUSABLE
        }

        // Set up the overlay layout
        val overlayLayout = LayoutInflater.from(this)
            .inflate(R.layout.activity_overlay, null)

        // Add the overlay layout to the window
        try {
            windowManager.addView(overlayLayout, layoutParams)
        } catch (ex: Exception) {
            Log.e("ACCSVC", "adding view failed", ex)
        }

        // Set the touch event listener for the overlay
//        overlayLayout.setOnTouchListener { _, event ->
//            // Handle touch events here
//            val x = event.x
//            val y = event.y
//
//            // Determine which region was clicked
//            if (isClickInClickableRegion(x, y)) {
//                // Handle clicks for the clickable region
//                // Perform actions or pass through to the underlying app
//                // ...
//            } else {
//                // Handle clicks for the non-clickable region
//                // ...
//            }
//
//            // Return true to consume the event
//            true
//        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("AccessibilityService", "Received event: $event")

        if (event?.eventType == AccessibilityEvent.TYPE_GESTURE_DETECTION_START)


            if (event?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                when (event.text[0]) {
                    "screenshot" -> {
                        performGlobalAction(9)
                    }
                }
            }
    }

    val swipe = GestureReceiver() {
        fun onReceive(context: Context?, intent: Intent?) {
            // Handle the broadcast, extract swipe direction
            val swipeDirection = intent?.getStringExtra(ACTION_DIRECTION)

            // Do something with the swipe direction
            if (swipeDirection == "TIDDIES_ACTION_DETECTED") {
                // Perform global action for a right swipe
                performGlobalAction(GLOBAL_ACTION_BACK)
            } else {
                // Perform global action for a left swipe
                performGlobalAction(GLOBAL_ACTION_HOME)
            }
        }
    }
    override fun onInterrupt() {
    }

    companion object {
        val ACTION_DETECTED: String = "TIDDIES_ACTION_DETECTED"
        val ACTION_DIRECTION: String = ""
    }

}

