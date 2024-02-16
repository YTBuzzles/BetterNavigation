package com.example.betternavigation


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.GestureDetectorCompat


class AdvancedGestureDetector(context: Context, view: View, listener: GestureListener) {

    private val gestureDetectorCompat: GestureDetectorCompat
    private val view: View
    private lateinit var action: String
    val listener: GestureListener


    init {
        gestureDetectorCompat =
            GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onDown(e: MotionEvent): Boolean {
                    val intent = Intent(action)
                    context.startActivity(intent)

                    Log.d("Gesture", "Down!")
                    return listener.onDown(e) // Allow handling down event within listener
                }

//                override fun onUp(e: MotionEvent): Boolean {
//                    Log.d("Gesture", "Down!")
//                    return listener.onDown(e) // Allow handling down event within listener
//                }

                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    Log.d("Gesture", "Tap!")
                    return listener.onSingleTapUp(e)
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    Log.d("Gesture", "DoubleTap!")
                    return listener.onDoubleTap(e)
                }

                override fun onLongPress(e: MotionEvent) {
                    Log.d("Gesture", "LongPress!")
                    listener.onLongPress(e)
                }

                override fun onFling(
                    e1: MotionEvent,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    return listener.onFling(e1, e2, velocityX, velocityY)
                    Log.d("Gesture", "Fling!")
                }

                override fun onScroll(
                    e1: MotionEvent,
                    e2: MotionEvent,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    return listener.onScroll(e1, e2, distanceX, distanceY)
                    Log.d("Gesture", "Scroll!")
                }

                // Add other gesture methods as needed (e.g., onShowPress, onSwipe)
            })
        this.view = view
        this.listener = listener

        view.setOnTouchListener { _, event ->
            gestureDetectorCompat.onTouchEvent(event)
        }
    }

    fun setAction(intent: String) {
        action = intent
        Log.d("Action", "action = $intent")
    }

    fun setAction(intent: Intent) {
        action = intent.toString()
        Log.d("Action", "action = $intent")
    }


    interface GestureListener {
        fun onDown(event: MotionEvent?): Boolean {
            return false
        }

        fun onUp(event: MotionEvent?): Boolean {
            return false
        }

        fun onSingleTapUp(event: MotionEvent?): Boolean {
            return false
        }

        fun onDoubleTap(event: MotionEvent?): Boolean {
            return false
        }

        fun onLongPress(event: MotionEvent?): Boolean {
            return false
        }

        fun onFling(
            event1: MotionEvent?,
            event2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return false
        }

        fun onScroll(
            event1: MotionEvent?,
            event2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return false
        }

        // Add corresponding methods for other desired gestures
    }

    fun setMinimumFlingVelocity(velocity: Float) {
        gestureDetectorCompat.setIsLongpressEnabled(false) // Optional optimization for fling
        gestureDetectorCompat.setOnDoubleTapListener(null) // Optional optimization for fling
        //gestureDetectorCompat.minimumFlingVelocity = velocity
    }

    fun setLongPressDuration(duration: Long) {
        gestureDetectorCompat.setIsLongpressEnabled(true)
        // gestureDetectorCompat.longPressDuration = duration
    }

    // Add setter methods for other customizable parameters as needed
}
