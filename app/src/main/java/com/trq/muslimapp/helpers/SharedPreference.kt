package com.trq.muslimapp.helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.trq.muslimapp.auth.model.DataUser

class SharedPreference(activity: Activity) {

    val login = "Login"
    val myPref = "Main_Pref"

    val user = "User"
    val sharedPreference: SharedPreferences

    init {
        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean){
        sharedPreference.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin():Boolean{
        return sharedPreference.getBoolean(login, true)
    }

    fun setUser(user: DataUser){
        val gson = Gson()
        val json = gson.toJson(user)
        sharedPreference.edit().putString(user.toString(), json).apply()
    }

    fun getUser(): DataUser? {
        val data = sharedPreference.getString(user, null)
        return if (data != null) {
            // ubah dari data string ke data object
            Gson().fromJson(data, DataUser::class.java)
        } else {
            null
        }
    }

}