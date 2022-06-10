package com.trq.muslimapp.ui.home.tahlil.model

import com.google.gson.annotations.SerializedName

data class ResponseTahlil(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("tahlil")
	val tahlil: String? = null,

	@field:SerializedName("isi")
	val isi: List<IsiItem?>? = null
)

data class IsiItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("translation_id")
	val translationId: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("transliteration")
	val transliteration: String? = null
)
