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





private const val DEBUG_TAG = "Gestures"

private const val INTENT_HOME = Intent.ACTION_MAIN
private val INTENT_CAMERA = MediaStore.ACTION_IMAGE_CAPTURE

class MainActivity : AppCompatActivity() {

    //Main Program/App Start
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.content_main)
        val recents = findViewById<Button>(R.id.recents)
        val testAppButton = findViewById<Button>(R.id.testAppButton)
//        val accessibilityNav = MyAccessibilityService()
        val requestStatus = findViewById<Button>(R.id.requestStatus)
        val navigation = findViewById<Button>(R.id.navigation)

        val set1 = findViewById<Button>(R.id.set1)
        val set2 = findViewById<Button>(R.id.set2)
        val set3 = findViewById<Button>(R.id.set3)
        var navGestureDetector = AdvancedGestureDetector(this, navigation, GestureListener())
        //The listerner for the button: ID = navigation
        set1.setOnClickListener {
            setContentView(R.layout.content_settings)
            Log.d(DEBUG_TAG, "Button 1")
            true
        }

        set2.setOnClickListener {
            navGestureDetector.setAction(INTENT_HOME)
            Log.d(DEBUG_TAG, "Button 2")
            true
        }
        set3.setOnClickListener {
            navGestureDetector.setAction(Intent.ACTION_MANAGE_NETWORK_USAGE)
            Log.d(DEBUG_TAG, "Button 3")
            true
        }


//        val accessibilityIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
//        startActivity(accessibilityIntent)


//        navigation.setOnClickListener {
//            Log.d(TAG, "Navigation")
//        }
//        navigation.setOnTouchListener{
//            var navGestureDetector.listener.onDown(event: MotionEvent)
//            }
//
//        navigation.set


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


//        requestStatus.setOnClickListener {
//                if (accessibilityNav.isAccessibilityServiceEnabled(applicationContext)) {
//                    requestStatus.text = "enabled"
//                } else {
//                    requestStatus.text = "disabled"
//                }
//            }
        testAppButton.setOnClickListener {
            setContentView(R.layout.content_settings)
//            startActivity(testButton)
        }

        recents.setOnClickListener {
//            accessibilityNav.perfAction(3)


            val recentsIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
            }

            startActivity(recentsIntent)


            }


    }


//        class MyAccessibilityService : AccessibilityService() {
//            override fun onInterrupt() {}
//
//            @RequiresApi(Build.VERSION_CODES.R)
//            override fun onServiceConnected() {
//                super.onServiceConnected()
//                var sysAcns = systemActions
//                Log.d(TAG, "onServiceConnected:")
//
//            }

//            @RequiresApi(Build.VERSION_CODES.R)
//            fun perfAction() {
//                var sysAcns = systemActions
//                performGlobalAction(3)
//            }
//
//            @RequiresApi(value = 30)
//            override fun onAccessibilityEvent(event: AccessibilityEvent?) {
//            }
//
//            fun isAccessibilityServiceEnabled(mContext: Context): Boolean {
//                var accessibilityEnabled = 0
//                val service: String =
//                    mContext.packageName + "/" + MyAccessibilityService::class.java.canonicalName
//                try {
//                    accessibilityEnabled = Settings.Secure.getInt(
//                        mContext.applicationContext.contentResolver,
//                        Settings.Secure.ACCESSIBILITY_ENABLED
//                    )
//                    Log.v(TAG, "accessibilityEnabled = $accessibilityEnabled")
//                } catch (e: Settings.SettingNotFoundException) {
//                    Log.e(
//                        TAG,
//                        "Error finding setting, default accessibility to not found: " + e.message
//                    )
//                }
//                val mStringColonSplitter = TextUtils.SimpleStringSplitter(':')
//                if (accessibilityEnabled == 1) {
//                    Log.v(TAG, "Accessibility Is Enabled")
//                    val settingValue: String = Settings.Secure.getString(
//                        mContext.applicationContext.contentResolver,
//                        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
//                    )
//                    mStringColonSplitter.setString(settingValue)
//                    while (mStringColonSplitter.hasNext()) {
//                        val accessibilityService = mStringColonSplitter.next()
//                        Log.v(TAG, "AccessibilityService :: $accessibilityService $service")
//                        if (accessibilityService.equals(service, ignoreCase = true)) {
//                            Log.v(TAG, "accessibility is switched on!")
//                            return true
//                        }
//                    }
//                } else {
//                    Log.v(TAG, "accessibility is disabled")
//                }
//                return false
//            }
//        }
}



