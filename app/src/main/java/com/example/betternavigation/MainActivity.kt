package com.example.betternavigation


import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


// Problem with crashing seems to be todo with calling the functions from the wrong spot or time
// try to find a way to not do it in onCreate I guess? https://stackoverflow.com/questions/39532507/attempt-to-invoke-virtual-method-java-lang-object-android-content-context-getsy
// this website is where that thought came from


private const val DEBUG_TAG = "Gestures"
class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    private gestureDetector GestureDetector;

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        val recents = findViewById<Button>(R.id.recents)
        val recentsAppButton = findViewById<Button>(R.id.recentsAppButton)
        val accessibilityNav = MyAccessibilityService()
        val requestStatus = findViewById<Button>(R.id.requestStatus)
        val gestureDetector = GestureDetector(this , this)


        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)

        requestStatus.setOnClickListener {
            if (accessibilityNav.isAccessibilityServiceEnabled(applicationContext)) {
                requestStatus.text = "enabled"
            }
            else {
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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        Log.d("myTag", "This is my message");


        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent): Boolean {

        return false
    }

    override fun onShowPress(p0: MotionEvent) {
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {
    }

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
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
        val service: String = mContext.packageName + "/" + MyAccessibilityService::class.java.canonicalName
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




