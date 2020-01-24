package com.corro.gothouses.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.corro.gothouses.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(SplashFragment.newInstance())
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        if (addToBackStack) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }
}
