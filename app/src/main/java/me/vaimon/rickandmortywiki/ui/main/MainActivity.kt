package me.vaimon.rickandmortywiki.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.rickandmortywiki.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // Launching from Android Studio may cause icon disappearance: https://issuetracker.google.com/issues/205021357
        val splashScreen = installSplashScreen()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        splashScreen.setKeepOnScreenCondition {
            viewModel?.uiState?.value?.keepSplashOn ?: true
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        viewModel?.emulatePredefinedSettingsFetching()
    }
}