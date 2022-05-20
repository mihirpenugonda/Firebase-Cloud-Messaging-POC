package com.mhirrr.fcm

import com.mhirrr.fcm.NotificationData

data class PushNotification(
    val data: NotificationData,
    val to: String
)