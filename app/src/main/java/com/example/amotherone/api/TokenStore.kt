package com.example.amotherone.api

import android.content.Context
import android.content.SharedPreferences

object TokenStore {
    private const val PREFS_NAME = "imgur_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"
    private const val KEY_EXPIRES_IN = "expires_in"

    fun saveTokens(context: Context, accessToken: String, refreshToken: String?, expiresIn: String?) {
        val prefs = getPrefs(context)
        prefs.edit().apply {
            putString(KEY_ACCESS_TOKEN, accessToken)
            putString(KEY_REFRESH_TOKEN, refreshToken)
            putString(KEY_EXPIRES_IN, expiresIn)
            apply()
        }
    }

    fun getAccessToken(context: Context): String? {
        return getPrefs(context).getString(KEY_ACCESS_TOKEN, null)
    }

    fun clear(context: Context) {
        getPrefs(context).edit().clear().apply()
    }

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}
