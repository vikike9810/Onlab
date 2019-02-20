package com.onlab.gymapp

import android.app.Application
import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class LoginPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getCount(): Int {
        return COUNT
    }

    override fun getItem(p0: Int): Fragment {
        var fragment: Fragment = LoginFragment()
        when (p0) {
            0 -> fragment = LoginFragment()
            1 -> fragment = RegisterFragment()
        }
        return fragment
    }

    override fun getPageTitle(p0: Int): CharSequence {
        var title: CharSequence = "Bejelentkezés"
        when (p0) {
            0 -> title = "Bejelentkezés"
            1 -> title = "Regisztráció"
        }
        return title
    }
}