package com.younge.screenlib

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/**
 * 屏幕单位转换工具类
 * 提供dp、sp、px之间的相互转换
 */

/**
 * dp转px
 */
fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}

/**
 * px转dp
 */
fun Float.pxToDp(context: Context): Float {
    return this / context.resources.displayMetrics.density
}

/**
 * sp转px
 */
fun Float.spToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )
}

/**
 * px转sp
 */
fun Float.pxToSp(context: Context): Float {
    return this / context.resources.displayMetrics.scaledDensity
}

/**
 * 设置View的宽度（dp）
 */
fun View.setWidthDp(dp: Float) {
    val params = layoutParams
    params.width = dp.dpToPx(context).toInt()
    layoutParams = params
}

/**
 * 设置View的高度（dp）
 */
fun View.setHeightDp(dp: Float) {
    val params = layoutParams
    params.height = dp.dpToPx(context).toInt()
    layoutParams = params
}

/**
 * 设置View的边距（dp）
 */
fun View.setMarginsDp(left: Float, top: Float, right: Float, bottom: Float) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    params.setMargins(
        left.dpToPx(context).toInt(),
        top.dpToPx(context).toInt(),
        right.dpToPx(context).toInt(),
        bottom.dpToPx(context).toInt()
    )
    layoutParams = params
}

/**
 * 设置View的内边距（dp）
 */
fun View.setPaddingDp(left: Float, top: Float, right: Float, bottom: Float) {
    setPadding(
        left.dpToPx(context).toInt(),
        top.dpToPx(context).toInt(),
        right.dpToPx(context).toInt(),
        bottom.dpToPx(context).toInt()
    )
} 