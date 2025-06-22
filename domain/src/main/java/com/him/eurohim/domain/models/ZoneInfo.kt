package com.him.eurohim.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ZoneInfo(val id: String, val name: String) : Parcelable