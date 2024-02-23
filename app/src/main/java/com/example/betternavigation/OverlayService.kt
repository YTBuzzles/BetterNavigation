package com.example.betternavigation

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class OverlayService : Service() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate() {
        super.onCreate()

        // Set up the overlay layout
        val overlayLayout = LayoutInflater.from(applicationContext)
            .inflate(R.layout.activity_overlay, null) as OverlayFrameLayout

        // Set the overlay layout parameters
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            PixelFormat.TRANSLUCENT
        )


        // Add the overlay layout to the window
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.addView(overlayLayout, params)

        // Set the touch event listener for the overlay
        overlayLayout.setOnTouchListener { _, event ->
            // Handle touch events here
            val x = event.x
            val y = event.y

            // Determine which region was clicked
            if (isClickInClickableRegion(x, y)) {
                // Handle clicks for the clickable region
                // Perform actions or pass through to the underlying app
                // ...
            } else {
                // Handle clicks for the non-clickable region
                // ...
            }

            // Return true to consume the event
            false
        }
    }

    private fun isClickInClickableRegion(x: Float, y: Float): Boolean {
        // Implement logic to determine if the click is in the clickable region
        // You might want to define specific coordinates or areas
        // For example, check if x is in a certain range or if y is in a specific area
        // Return true if it's in the clickable region, false otherwise
        // ...
        return false
    }

    override fun onDestroy() {
        // Clean up resources and remove the overlay from the window
//        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
//        // Replace overlayLayout with the actual view added to the window
//        windowManager.removeView(overlayLayout)

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun viewHierarchyToString(view: View): String {
        val stringBuilder = StringBuilder()
        traverseViewHierarchy(view, stringBuilder, 0)
        return stringBuilder.toString()
    }

    private fun traverseViewHierarchy(view: View, stringBuilder: StringBuilder, depth: Int) {
        val padding = "  ".repeat(depth)
        stringBuilder.append("$padding${view.javaClass.simpleName}\n")

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                traverseViewHierarchy(view.getChildAt(i), stringBuilder, depth + 1)
            }
        }
    }

}


class OverlayFrameLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        // Intercept touch events to prevent them from reaching child views
//        return false
//    }

//    override fun performClick(): Boolean {
//        return super.performClick()
//    }
}
