package com.example.betternavigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController


private const val DEBUG_TAG = "Gestures"

private const val INTENT_HOME = Intent.ACTION_MAIN
private val INTENT_CAMERA = MediaStore.ACTION_IMAGE_CAPTURE

class MainActivityArchive : AppCompatActivity() {

    //Main Program/App Start
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

//        setupActionBarWithNavController(findNavController(R.id.homeFragment))

        val recents = findViewById<Button>(R.id.recents)
        val testAppButton = findViewById<Button>(R.id.testAppButton)
        val requestStatus = findViewById<Button>(R.id.requestStatus)
        val navigation = findViewById<Button>(R.id.navigation)
        val set1 = findViewById<Button>(R.id.set1)
        val set2 = findViewById<Button>(R.id.set2)
        val set3 = findViewById<Button>(R.id.set3)
        var navGestureDetector = AdvancedGestureDetector(this, navigation, GestureListener())
        //The listerner for the button: ID = navigation
        set1.setOnClickListener {
            setContentView(R.layout.fragment_home)
            //val navController = findNavController(R.id.homeFragment)

            Log.d(DEBUG_TAG, "Button 1")
            true
        }


        set2.setOnClickListener {
            setContentView(R.layout.activity_main)
            Log.d(DEBUG_TAG, "Button 2")
            true
        }
        set3.setOnClickListener {
            navGestureDetector.setAction(Intent.ACTION_MANAGE_NETWORK_USAGE)
            Log.d(DEBUG_TAG, "Button 3")
            true
        }

        fun onTouchEvent(event: MotionEvent): Boolean {
            return when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(DEBUG_TAG, "Action was DOWN")
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    Log.d(DEBUG_TAG, "Action was MOVE")
                    true
                }

                MotionEvent.ACTION_UP -> {
                    Log.d(DEBUG_TAG, "Action was UP")
                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    Log.d(DEBUG_TAG, "Action was CANCEL")
                    true
                }

                MotionEvent.ACTION_OUTSIDE -> {
                    Log.d(DEBUG_TAG, "Movement occurred outside bounds of current screen element")
                    true
                }

                else -> super.onTouchEvent(event)
            }
        }
        testAppButton.setOnClickListener {
//            setContentView(R.layout.content_settings)
//            startActivity(testButton)
        }

        recents.setOnClickListener {
//            accessibilityNav.perfAction(3)
//

            val recentsIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
            }

            startActivity(recentsIntent)


        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.homeFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}

