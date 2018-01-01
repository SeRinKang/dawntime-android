package com.peaktime.dawntime

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by LEESANGYUN on 2018-01-01.
 */

class SharedPreferInstance {

    fun putPrefer(key: String, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    //
//    fun putLockPrefer(key: String, value: Boolean) {
//        editor!!.putBoolean(key, value)
//        editor!!.commit()
//    }
//
//    fun putNoticePrefer(key: String, value: Boolean) {
//        editor!!.putBoolean(key, value)
//        editor!!.commit()
//    }
//
//    fun putBlindPreder(key: String, value: Boolean) {
//        editor!!.putBoolean(key, value)
//        editor!!.commit()
//    }
//
    fun getPrefer(key: String): Boolean? {
        return prefer!!.getBoolean(key, false)
    }
//
//    fun getLockPrefer(key: String): Boolean? {
//        return prefer!!.getBoolean(key, false)
//    }
//
//    fun getNoticePrefer(key: String): Boolean? {
//        return prefer!!.getBoolean(key, false)
//    }
//
//    fun getBlindPrefer(key: String): Boolean? {
//        return prefer!!.getBoolean(key, false)
//    }

    companion object {

        val PREFERENCE_NAME = "dawntime_sharedPreference"
        private var instance: SharedPreferInstance? = null
        private var mContext: Context? = null
        private var prefer: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private var email: String? = null

        fun getInstance(context: Context): SharedPreferInstance {
            mContext = context

            if (instance == null) {
                instance = SharedPreferInstance()
            }
            if (prefer == null) {
                prefer = mContext!!.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                editor = prefer!!.edit()
            }
            return instance as SharedPreferInstance
        }
    }

    fun getEamil(): String? {
        return email
    }

    fun putEmail(e: String?) {
        email = e
    }
}
