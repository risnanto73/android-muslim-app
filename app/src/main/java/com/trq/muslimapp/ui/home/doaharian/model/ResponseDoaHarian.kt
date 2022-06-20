package com.trq.muslimapp.ui.home.doaharian.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDoaHarian(
	val pesan: String? = null,
	val dzikir: List<DzikirItem?>? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class DzikirItem(
	val updatedAt: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val judul: String? = null,
	val isi: String? = null
) : Parcelable
