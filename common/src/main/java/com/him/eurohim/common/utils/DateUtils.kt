package com.him.eurohim.common.utils

import android.os.Build

object DateUtils {
    fun format(epoch: Long): String {
        return java.time.Instant.ofEpochSecond(epoch)
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDateTime()
            .toString()
    }
}