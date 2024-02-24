package com.example.betternavigation

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.betternavigation.databinding.ActivityMainBinding
import android.graphics.Path

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
        val accessibilitySettings = findViewById<Button>(R.id.accessibility)
        accessibilitySettings.setOnClickListener() {
            val intent: Intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName"))
            startActivity(intent)
        } else {
            // Permission already granted, proceed with your overlay logic
            return
        }
    }




//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment_main)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}


class MyAccessibilityService : AccessibilityService() {
    private var overlayElements: List<Rect> = listOf() // Replace with logic to determine your overlay element bounds

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("AccessibilityService", "Received event: $event")

        if (event?.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            val clickedView = event.source

            if (clickedView != null) { // Check if the view is available
                val bounds = Rect()
                clickedView.getBoundsInScreen(bounds)

                val clickX = bounds.centerX()
                val clickY = bounds.centerY()

                if (!isTouchOnOverlayElements(clickedView, clickX, clickY)) {
                    if (bounds.contains(clickX, clickY)) {
                        // Dispatch the touch event to the underlying app
                        val clickPath = Path()
                        clickPath.moveTo(clickX.toFloat(), clickY.toFloat())

                        val gestureBuilder = GestureDescription.Builder()
                        gestureBuilder.addStroke(GestureDescription.StrokeDescription(clickPath, 0, 50))

                        dispatchGesture(gestureBuilder.build(), object : GestureResultCallback() {
                            override fun onCompleted(gestureDescription: GestureDescription?) {
                                super.onCompleted(gestureDescription)
                                // Gesture dispatched successfully
                            }

                            override fun onCancelled(gestureDescription: GestureDescription?) {
                                super.onCancelled(gestureDescription)
                                // Gesture dispatch cancelled
                            }
                        }, null)
                    }
                }

                // Do not recycle the sourceNode in this case, as it might be needed later
                // clickedView.recycle()
            }
        }

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

//        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
//            // Handle overlay creation here
//            // Example: Show a transparent overlay
//            val overlayIntent = Intent(applicationContext, OverlayActivity::class.java)
//            overlayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(overlayIntent)
//        }
    }

    private fun isTouchOnOverlayElements(node: AccessibilityNodeInfo, x: Int, y: Int): Boolean {
        // Implement logic to check if the touch coordinates are within the bounds of your overlay elements
        // You can use node.getBoundsInScreen(rect) and compare with x and y
        // Use AccessibilityNodeInfo properties like className, resourceId, etc. for accurate identification
        return false // Replace with your implementation
    }

    override fun onInterrupt() {
        // Handle service interruption (e.g., service disabled)
    }


    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d("Accessibility Service", "onServiceConnected:")

        val overlayIntent = Intent(applicationContext, OverlayService::class.java)
//        overlayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ContextCompat.startForegroundService(applicationContext, overlayIntent)
//        startService(overlayIntent)
    }
}