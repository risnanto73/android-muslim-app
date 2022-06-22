package com.trq.muslimapp.ui.home.admin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseAllUser(
	val pesan: String? = null,
	val data: List<DataItem?>? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class DataItem(
	val role: String? = null,
	val updatedAt: String? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val emailVerifiedAt: String? = null,
	val id: Int? = null,
	val gambar: String? = null,
	val email: String? = null
) : Parcelable
