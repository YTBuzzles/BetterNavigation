package com.example.betternavigation

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


// Problem with crashing seems to be todo with calling the functions from the wrong spot or time
// try to find a way to not do it in onCreate I guess? https://stackoverflow.com/questions/39532507/attempt-to-invoke-virtual-method-java-lang-object-android-content-context-getsy
// this website is where that thought came from

//I got it working i think just need to add debugging to test it
//can u show me how to use the Log thing i havent worked it out


private const val DEBUG_TAG = "Gestures"


class MainActivity : AppCompatActivity() {

    //Main Program/App Start
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.content_main)
        val recents = findViewById<Button>(R.id.recents)
        val recentsAppButton = findViewById<Button>(R.id.recentsAppButton)
        val accessibilityNav = MyAccessibilityService()
        val requestStatus = findViewById<Button>(R.id.requestStatus)
        val navigation = findViewById<Button>(R.id.navigation)

        //The listerner for the button: ID = navigation
        var navGestureDetector = AdvancedGestureDetector(this, navigation, MyGestureListener())
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)


        navigation.setOnClickListener { navGestureDetector }


        requestStatus.setOnClickListener {
            if (accessibilityNav.isAccessibilityServiceEnabled(applicationContext)) {
                requestStatus.text = "enabled"
            } else {
                requestStatus.text = "disabled"
            }
        }
        recentsAppButton.setOnClickListener {
            try {
                val Recentapps = Intent("com.sec.android.app.launcher")
                startActivity(Recentapps)
            }
            catch ( RuntimeException: NullPointerException)
            { RuntimeException}



        }
        recents.setOnClickListener {
//            accessibilityNav.perfAction(3)


            val recentsIntent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
            }

            startActivity(recentsIntent)


        }


    }


class MyAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {}

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onServiceConnected() {
        super.onServiceConnected()
        var sysAcns = systemActions
        Log.d(TAG, "onServiceConnected:")

    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun perfAction() {
        var sysAcns = systemActions
        performGlobalAction(3)
    }

    @RequiresApi(value = 30)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
    }
    fun isAccessibilityServiceEnabled(mContext: Context): Boolean {
        var accessibilityEnabled = 0
        val service: String =
            mContext.packageName + "/" + MyAccessibilityService::class.java.canonicalName
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                mContext.applicationContext.contentResolver,
                Settings.Secure.ACCESSIBILITY_ENABLED
            )
            Log.v(TAG, "accessibilityEnabled = $accessibilityEnabled")
        } catch (e: Settings.SettingNotFoundException) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: " + e.message)
        }
        val mStringColonSplitter = TextUtils.SimpleStringSplitter(':')
        if (accessibilityEnabled == 1) {
            Log.v(TAG, "Accessibility Is Enabled")
            val settingValue: String = Settings.Secure.getString(
                mContext.applicationContext.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
            mStringColonSplitter.setString(settingValue)
            while (mStringColonSplitter.hasNext()) {
                val accessibilityService = mStringColonSplitter.next()
                Log.v(TAG, "AccessibilityService :: $accessibilityService $service")
                if (accessibilityService.equals(service, ignoreCase = true)) {
                    Log.v(TAG, "accessibility is switched on!")
                    return true
                }
            }
        } else {
            Log.v(TAG, "accessibility is disabled")
        }
        return false
    }
}
}




