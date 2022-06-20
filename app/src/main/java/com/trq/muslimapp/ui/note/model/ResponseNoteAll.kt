package com.trq.muslimapp.ui.note.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseNoteAll(
	val pesan: String? = null,
	val user: User? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class DataItem(
	val updatedAt: String? = null,
	val userId: String? = null,
	val catatan: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val deskripsi: String? = null
) : Parcelable

@Parcelize
data class User(
	val data: List<DataItem?>? = null
) : Parcelable
