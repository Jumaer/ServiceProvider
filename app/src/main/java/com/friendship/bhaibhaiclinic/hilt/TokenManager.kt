package com.friendship.bhaibhaiclinic.hilt

import android.content.Context
import android.content.SharedPreferences
import com.friendship.bhaibhaiclinic.base.Constant.PREFS_TOKEN_FILE
import com.friendship.bhaibhaiclinic.base.Constant.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {


    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    /**
     * Good practice to save token .. in sf..
     * But for security reasons some apps avoid this
     *
     * @param token will be used to save token
     * @return [Int] nothing to do then
     */
    fun saveToken(token: String) : Int{
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()

        return 0
    }


    /**
     * To fetch token from SF
     * @see .. doc that has been provided
     * currently non null is required but for realtime needs null
     * @return [String] the exact token..
     */
    fun getToken(): String? {

       // return prefs.getString(USER_TOKEN, null)
        return "5f54c461fb7d6b345b3a770fa4586795ec1125bb80459ed9daba5f1c26cf26e0"
    }
}