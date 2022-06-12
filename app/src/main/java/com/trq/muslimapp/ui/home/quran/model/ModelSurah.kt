package com.trq.muslimapp.ui.home.quran.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelSurah : Serializable {
	@SerializedName("arti")
	lateinit var arti: String

	@SerializedName("asma")
	lateinit var asma: String

	@SerializedName("ayat")
	lateinit var ayat: String

	@SerializedName("nama")
	lateinit var nama: String

	@SerializedName("type")
	lateinit var type: String

	@SerializedName("audio")
	lateinit var audio: String

	@SerializedName("nomor")
	lateinit var nomor: String

	@SerializedName("keterangan")
	lateinit var keterangan: String
}