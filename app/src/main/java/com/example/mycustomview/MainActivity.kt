package com.example.mycustomview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.mycustomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val token = Any()

    private val hander = Handler(Looper.getMainLooper())

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomButtonsView.setOnClickInBottomButtonListener {
            when (it) {
                BottomButtonsClass.POSITIVE -> {
                    binding.bottomButtonsView.isProgressMode = true
                    hander.postDelayed(
                        {
                            Toast.makeText(this, "POSITIVE", Toast.LENGTH_SHORT).show()
                        }, token, 500
                    )

                }
                BottomButtonsClass.NEGATIVE -> {
                    binding.bottomButtonsView.isProgressMode = false
                    hander.postDelayed(
                        {
                            Toast.makeText(this, "NEGATIVE", Toast.LENGTH_SHORT).show()
                        }, token, 500
                    )
                }
            }
        }

        binding.bottomButtonsView.setBottomPositiveButtonText("Show")
        binding.bottomButtonsView.setBottomNegativeButtonText("Hide")

    }

    override fun onDestroy() {
        super.onDestroy()
        hander.removeCallbacksAndMessages(token)
    }
}