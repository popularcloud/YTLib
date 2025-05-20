package com.younge.commlib

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import java.util.*

/**
 * 设备唯一标识符工具类
 * 提供多种获取设备唯一标识符的方法
 */
object DeviceIdUtils {

    /**
     * 获取设备唯一标识符
     * 按照优先级依次尝试不同的方法
     */
    fun getDeviceId(context: Context): String {
        return getUniqueId(context)
    }

    /**
     * 获取设备唯一标识符
     * 优先使用ANDROID_ID，如果获取失败则使用UUID
     */
    @SuppressLint("HardwareIds")
    private fun getUniqueId(context: Context): String {
        var deviceId = ""
        
        try {
            // 尝试获取ANDROID_ID
            deviceId = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            
            // 如果ANDROID_ID为空或无效，则使用UUID
            if (deviceId.isNullOrEmpty() || deviceId == "9774d56d682e549c") {
                deviceId = UUID.randomUUID().toString()
            }
        } catch (e: Exception) {
            // 发生异常时使用UUID
            deviceId = UUID.randomUUID().toString()
        }
        
        return deviceId
    }

    /**
     * 获取设备信息
     * 返回包含设备型号、制造商、系统版本等信息的字符串
     */
    fun getDeviceInfo(): String {
        return StringBuilder().apply {
            append("设备型号: ${Build.MODEL}\n")
            append("制造商: ${Build.MANUFACTURER}\n")
            append("系统版本: ${Build.VERSION.RELEASE}\n")
            append("SDK版本: ${Build.VERSION.SDK_INT}\n")
            append("设备名称: ${Build.DEVICE}\n")
            append("产品名称: ${Build.PRODUCT}\n")
            append("硬件名称: ${Build.HARDWARE}\n")
        }.toString()
    }

    /**
     * 获取设备指纹
     * 组合多个设备特征生成唯一指纹
     */
    fun getDeviceFingerprint(context: Context): String {
        val deviceInfo = StringBuilder().apply {
            append(Build.BOARD)
            append(Build.BRAND)
            append(Build.DEVICE)
            append(Build.HARDWARE)
            append(Build.MANUFACTURER)
            append(Build.MODEL)
            append(Build.PRODUCT)
            append(Build.SERIAL)
        }.toString()

        return UUID.nameUUIDFromBytes(deviceInfo.toByteArray()).toString()
    }

    /**
     * 检查设备是否为模拟器
     */
    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk" == Build.PRODUCT)
    }
} 