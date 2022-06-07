package com.init_android.app.presentation.ui.mypage

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.home.signin.viewmodel.SignViewModel
import com.init_android.app.presentation.ui.mypage.adapter.OtherPageTabAdapter
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.ActivityPartnerPageBinding
import com.playtogether_android.app.presentation.base.BaseActivity

class PartnerPageActivity : BaseActivity<ActivityPartnerPageBinding>(R.layout.activity_partner_page) {

    private lateinit var otherPageTabAdapter: OtherPageTabAdapter
    private val myPageViewModel : MyPageViewModel by viewModels()
    private val signViewModel : SignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        initNetwork()
        initTab()
    }




    private fun initNetwork() {

        val requestMyInfo = RequestMyInfo(
            //인텐트로 넘겨 온 mNum 세팅
            mNum = 2
        )

        Log.d("MyPage mNum: " , " " + signViewModel.signIn.value?.mNum)

        myPageViewModel.postMyInfo(requestMyInfo)

        myPageViewModel.myInfoData.observe(this) {
            binding.user = it.mInfo
        }
    }

    //tab layout adapter
    private fun initAdapter() {
        val fragmentList = listOf(
            MyPageInfoFragment(),
            MyPageProjectFragment(),
            MyPageFeedFragment(),
            MyPageRateFragment()
        )
        otherPageTabAdapter = OtherPageTabAdapter(this@PartnerPageActivity)
        otherPageTabAdapter.fragments.addAll(fragmentList)


        binding.vpHome.adapter = otherPageTabAdapter

    }

    private fun initTab() {
        val tabLabel = listOf("정보", "프로젝트", "피드", "평가")
        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}