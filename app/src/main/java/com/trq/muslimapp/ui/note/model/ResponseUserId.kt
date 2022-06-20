package com.trq.muslimapp.ui.note.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUserId(
	val pesan: String? = null,
	val mutabaah: List<MutabaahItem?>? = null,
	val status: Int? = null
) : Parcelable

@Parcelize
data class MutabaahItem(
	val updatedAt: String? = null,
	val userId: String? = null,
	val catatan: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val deskripsi: String? = null
) : Parcelable
