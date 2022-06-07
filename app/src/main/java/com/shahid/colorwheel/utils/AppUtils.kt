package com.shahid.colorwheel.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.RelativeLayout

object AppUtils {
    fun RelativeLayout.changeBackground(hex: String){
        this.background.setTint(Color.parseColor(hex))
    }

    fun RelativeLayout.changeBrightness(color: Int){
        (this.background as GradientDrawable).setColor(color)
    }
    fun changeAlpha(argb: Int, alpha: Int): Int{
        return Color.argb(
            alpha,
            Color.red(argb),
            Color.green(argb),
            Color.blue(argb)
        )
    }
}