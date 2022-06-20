package com.trq.muslimapp.ui.home.zakat.model

import com.google.gson.annotations.SerializedName

data class ResponseHargaEmas(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("emas")
	val emas: List<EmasItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class EmasItem(

	@field:SerializedName("hargaemas")
	val hargaemas: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
