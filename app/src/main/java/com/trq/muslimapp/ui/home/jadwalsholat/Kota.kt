package com.trq.muslimapp.ui.home.jadwalsholat

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class Kota {
	var id: Int? = null
	var nama: String? = null

	override fun toString(): String {
		return nama.toString()
	}
}
