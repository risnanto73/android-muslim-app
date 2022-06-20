package com.trq.muslimapp.helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.trq.muslimapp.auth.model.User

class SharedPreference(activity: FragmentActivity) {

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
        return sharedPreference.getBoolean(login, false)
    }

    fun setUser(value: User){
        // ubah dari data object ke data string
        val data = Gson().toJson(value, User::class.java)
        sharedPreference.edit().putString(user, data).apply()
    }

    fun getUser(): User? {
        val data = sharedPreference.getString(user, null)
        return if (data != null) {
            // ubah dari data string ke data object
            Gson().fromJson(data, User::class.java)
        } else {
            null
        }
    }

    fun deleteUser(){
        sharedPreference.edit().remove(user).apply()
    }

}