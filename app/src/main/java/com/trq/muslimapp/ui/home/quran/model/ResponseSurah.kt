package com.trq.muslimapp.ui.home.quran.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSurah(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("translationEn")
	val translationEn: String? = null,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int? = null,

	@field:SerializedName("orderNumber")
	val orderNumber: Int? = null,

	@field:SerializedName("translationId")
	val translationId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("typeId")
	val typeId: String? = null,

	@field:SerializedName("asma")
	val asma: String? = null,

	@field:SerializedName("typeEn")
	val typeEn: String? = null
) : Parcelable
