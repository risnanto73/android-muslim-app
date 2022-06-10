package com.trq.muslimapp.ui.home.tahlil.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseYasin(

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

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem?>? = null,

	@field:SerializedName("typeEn")
	val typeEn: String? = null
) : Parcelable

@Parcelize
data class AyahsItem(

	@field:SerializedName("verseId")
	val verseId: Int? = null,

	@field:SerializedName("ayahText")
	val ayahText: String? = null,

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int? = null,

	@field:SerializedName("ruku")
	val ruku: Int? = null,

	@field:SerializedName("enText")
	val enText: String? = null,

	@field:SerializedName("readText")
	val readText: String? = null,

	@field:SerializedName("manzil")
	val manzil: Int? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("sajda")
	val sajda: Boolean? = null,

	@field:SerializedName("indoText")
	val indoText: String? = null,

	@field:SerializedName("juz")
	val juz: Int? = null
) : Parcelable
