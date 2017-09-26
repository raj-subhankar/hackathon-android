package com.webviander.hackathonapp

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs



/**
 * Created by vivek-3102 on 26/09/17.
 */

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }
}