package com.example.betternavigation

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.example.betternavigation.databinding.ActivityMainBinding
import com.example.betternavigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fabSettings.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        settings()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun settings() {
        val sp = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

        val signature = sp?.getString("signature", "")
        val defaultReplyAction = sp?.getString("reply", "")
        val sync = sp?.getBoolean("sync", true)
        val notifications = sp?.getBoolean("notifications", true)
        val volume = sp?.getInt("volume_notifications", 0)

        binding.tvSignature.text = "Signature: $signature"
        binding.tvReply.text = "Default reply: $defaultReplyAction"

        binding.tvSync.text = "Sync: $sync"
        binding.tvNotifications.text = "Disable notifications: $notifications"

        if (volume != null) {
            binding.pbVolume.setProgress(volume, true)
        }

    }

}