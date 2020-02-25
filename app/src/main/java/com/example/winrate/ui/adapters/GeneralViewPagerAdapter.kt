package com.example.winrate.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.winrate.ui.fragments.FirstIntroFragment
import com.example.winrate.ui.fragments.SecondIntroFragment
import com.example.winrate.ui.fragments.ThirdIntroFragment

class GeneralViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstIntroFragment()
            1 -> SecondIntroFragment()
            2 -> ThirdIntroFragment()
            else -> FirstIntroFragment()
        }
    }

    fun nextPage() {

    }
}