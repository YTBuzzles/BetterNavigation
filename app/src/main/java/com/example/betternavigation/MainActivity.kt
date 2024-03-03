package com.example.betternavigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.betternavigation.databinding.ActivityMainBinding

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

        // make sure app has appear on top permissions
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName"))
            startActivity(intent)
        } else {
            // Permission already granted, proceed with your overlay logic
            return
        }

//        // Check if the WRITE_SETTINGS permission is granted
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//            !Settings.System.canWrite(this)
//        ) {
//            // If not granted, request the WRITE_SETTINGS permission
//            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
//            intent.data = Uri.parse("package:" + this.packageName)
//            this.startActivity(intent)
//        }
    }




//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment_main)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}
