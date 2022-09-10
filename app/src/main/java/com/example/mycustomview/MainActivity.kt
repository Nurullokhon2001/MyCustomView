package com.example.mycustomview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomButtonsView.setOnClickInBottomButtonListener {
            when (it) {
                BottomButtonsClass.POSITIVE -> {
                    binding.bottomButtonsView.setBottomPositiveButtonText("Show")
                }
                BottomButtonsClass.NEGATIVE -> {
                    binding.bottomButtonsView.setBottomNegativeButtonText("Hide")
                }
            }
        }


    }

}