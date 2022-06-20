package com.trq.muslimapp.ui.home.khutbah.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseKhutbah(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("khutbah")
	val khutbah: List<KhutbahItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class KhutbahItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("pemateri")
	val pemateri: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("isi")
	val isi: String? = null
) : Parcelable
