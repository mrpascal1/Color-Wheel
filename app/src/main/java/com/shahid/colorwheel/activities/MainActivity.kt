package com.shahid.colorwheel.activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.shahid.colorwheel.R
import com.shahid.colorwheel.databinding.ActivityMainBinding
import com.shahid.colorwheel.utils.AppUtils.changeAlpha
import com.shahid.colorwheel.utils.AppUtils.changeBackground
import com.shahid.colorwheel.utils.AppUtils.changeBrightness
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var selected: Int = 1
    private var rgb = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSelectedColor()

        initColorWheel()

        initBrightness()
    }

    private fun initColorWheel(){
        binding.colorWheel.colorChangeListener = {
            rgb = it
            val hex = String.format("#%02X%02X%02X", it.red, it.green, it.blue)
            when (selected) {
                1 -> {
                    binding.color1.changeBackground(hex)
                }
                2 -> {
                    binding.color2.changeBackground(hex)
                }
                else -> {
                    binding.color3.changeBackground(hex)
                }
            }
        }
    }

    private fun initSelectedColor(){
        binding.color1Layout.setOnClickListener {
            binding.color1Layout.setDefault(this, true, 1)
            binding.color2Layout.setDefault()
            binding.color3Layout.setDefault()
        }
        binding.color2Layout.setOnClickListener {
            binding.color2Layout.setDefault(this, true, 2)
            binding.color1Layout.setDefault()
            binding.color3Layout.setDefault()
        }
        binding.color3Layout.setOnClickListener {
            binding.color3Layout.setDefault(this, true, 3)
            binding.color1Layout.setDefault()
            binding.color2Layout.setDefault()
        }
    }

    private fun initBrightness(){
        binding.slider.addOnChangeListener { _, value, _ ->
            val color = changeAlpha(rgb, (value.roundToInt()*2.55).roundToInt())
            when (selected){
                1 -> {
                    binding.color1.changeBrightness(color)
                }
                2 -> {
                    binding.color2.changeBrightness(color)
                }
                else -> {
                    binding.color3.changeBrightness(color)
                }
            }
        }
    }

    private fun RelativeLayout.setDefault(context: Context = applicationContext, isSelected: Boolean = false, selectedId: Int = 1){
        if (isSelected) {
            selected = selectedId
            this.background = ContextCompat.getDrawable(context, R.color.selected_background)
        }else{
            this.background = null
        }
    }
}