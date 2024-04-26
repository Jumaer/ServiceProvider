package com.friendship.bhaibhaiclinic.util

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.friendship.bhaibhaiclinic.R
import java.lang.Exception

class Helper() {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun hideKeyboard(view: View){
            try {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }catch (e: Exception){

            }
        }



        fun getColors(context: Context) : ArrayList<Int>{

            val list = ArrayList<Int>()
            list.add(context.getColor(R.color.sky_blue))
            list.add(context.getColor(R.color.orange))
            list.add(context.getColor(R.color.theme_color))
            list.add(context.getColor(R.color.yellow))

            return list
        }




    }

}