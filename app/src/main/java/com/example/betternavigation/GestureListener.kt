package com.example.betternavigation

import android.util.Log
import android.view.MotionEvent


class GestureListener : AdvancedGestureDetector.GestureListener {
        override fun onSingleTapUp(event: MotionEvent?): Boolean {
                // Perform an action on single tap, e.g., show a menu
                //Toast.makeText(context, "Single tap detected!", Toast.LENGTH_SHORT).show()
                Log.d("Gesture", "ListenForTap!")
                return true // Consume the event
        }

        override fun onFling(
                event1: MotionEvent?,
                event2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
        ): Boolean {
                Log.d("Gesture", "ListenForFling!")
                // Implement fling actions based on direction and velocity, e.g., page navigation
                if (velocityX > 1000) {
                        // Fling right, go to next page
//                        Log.d("Gesture", "Fling right detected!")
                        // Your navigation logic here
                } else if (velocityX < -1000) {
                        // Fling left, go to previous page
//                        Log.d("Gesture", "Fling left detected!")
                        // Your navigation logic here
                }
                return true // Consume the event
        }

        // Implement other desired gesture methods similarly
}
