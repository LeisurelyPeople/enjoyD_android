package com.leisurely.people.enjoyd.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityMainBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.main.evaluation.EvaluationFragment
import com.leisurely.people.enjoyd.ui.main.home.HomeFragment
import com.leisurely.people.enjoyd.ui.main.mypage.MyPageFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    private val homeFragment = HomeFragment.newInstance()
    private val evaluationFragment = EvaluationFragment.newInstance()
    private val myPageFragment = MyPageFragment.newInstance()

    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBottomNavigation()
        setViewPager()
    }

    override fun onBackPressed() {
        if (activeFragment !is HomeFragment) {
            binding.bottomNavigationView.selectedItemId = R.id.home
            return
        }
        super.onBackPressed()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> binding.nonSwipeViewPager.setCurrentItem(0, false)
                    R.id.evaluation -> binding.nonSwipeViewPager.setCurrentItem(1, false)
                    R.id.my_page -> binding.nonSwipeViewPager.setCurrentItem(2, false)
                }
                setActivityFragment(binding.nonSwipeViewPager.currentItem)
                true
            }
        }
    }

    private fun setViewPager() {
        binding.nonSwipeViewPager.run {
            adapter = MainTabVPAdapter(
                supportFragmentManager,
                listOf(homeFragment, evaluationFragment, myPageFragment)
            )
            offscreenPageLimit = 2
        }
    }

    private fun setActivityFragment(page: Int) {
        when (page) {
            0 -> activeFragment = homeFragment
            1 -> activeFragment = evaluationFragment
            2 -> activeFragment = myPageFragment
        }
    }


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            or Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }
        }
    }
}
