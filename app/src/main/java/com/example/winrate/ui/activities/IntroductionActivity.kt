package com.example.winrate.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.winrate.R
import com.example.winrate.databinding.ActivityIntoductionBinding
import com.example.winrate.ui.adapters.GeneralViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class IntroductionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntoductionBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: GeneralViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intoduction)

        createViewPager()
        goToMainActivity()
    }

    private fun goToMainActivity() {
        binding.generalSkipButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            finish()
        }
    }

    private fun createViewPager() {
        adapter = GeneralViewPagerAdapter(this)
        viewPager = binding.generalIntroViewPager
        val tabLayout = binding.generalIntroTabLayout

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

}