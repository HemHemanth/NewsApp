package com.hemanth.newsapp.utils

import android.content.Context

class Utility {
    companion object {
        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi / 160f)
        }
    }
}