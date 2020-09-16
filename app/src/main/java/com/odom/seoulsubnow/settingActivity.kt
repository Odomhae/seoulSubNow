package com.odom.seoulsubnow

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import androidx.appcompat.app.AppCompatActivity

class settingActivity :AppCompatActivity(){

    val fragment = prefFragment()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.setting_layout)
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    class prefFragment : PreferenceFragment(){

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            addPreferencesFromResource(R.xml.pref)
        }
    }
}