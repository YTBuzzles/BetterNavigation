package com.example.betternavigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class OverlayActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlay)

        val overlayLayout = findViewById<OverlayFrameLayout>(R.id.overlayLayout)
        overlayLayout.setOnTouchListener { _, event ->
            // Handle touch events here
            val x = event.x
            val y = event.y

            // Determine which region was clicked (you can customize this logic)
            if (isClickInClickableRegion(x, y)) {
                // Handle clicks for the clickable region
                // Perform actions or pass through to the underlying app
                // ...
            } else {
                // Handle clicks for the non-clickable region
                // ...
            }

            // Return true to consume the event
            true
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

}

class OverlayFrameLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // Intercept touch events to prevent them from reaching child views
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}
