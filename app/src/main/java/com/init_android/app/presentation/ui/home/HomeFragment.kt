package com.init_android.app.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.init_android.R
import com.init_android.app.data.model.ProjectItemData
import com.init_android.app.presentation.ui.home.adapter.ProjectVPAdapter
import com.init_android.databinding.FragmentHomeBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val includeList = mutableListOf<ProjectItemData>()
    val recoList = mutableListOf<ProjectItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testListFunc()

    }

    // 서버 통신 - 데이터 존재 여부 검사
    private fun testListFunc(){
        includeList.add(ProjectItemData("안드로이드","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))
        includeList.add(ProjectItemData("웹","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))
        includeList.add(ProjectItemData("웹","서울여대 해커톤 나가실 분","노원구","1/4","2022.06.11","2022.06.24","장윤정","D-DAY"))

        checkUI()
    }

    // 데이터 존재 여부 검사
    private fun checkUI(){
        // 소속 프로젝트
        initIncludeProject()

        // 추천 프로젝트
        initRecoProject()
    }

    // 소속 프로젝트 초기화 - (viewpager2)
    private fun initIncludeProject(){
        if(includeList.size < 1) { // 리스트 한 개 미만
            binding.tvIncludeShowAll.visibility = View.GONE // 전체보기 gone

            binding.cardViewNoInclude.visibility = View.VISIBLE // cardview on
            binding.vpIncludeProject.visibility = View.GONE // vp gone
            binding.indicatorInclude.visibility = View.GONE // indicator gone
        }else{ //리스트 한 개 이상
            binding.tvIncludeShowAll.visibility = View.VISIBLE // 전체보기 on

            binding.cardViewNoInclude.visibility = View.GONE // cardview gone
            binding.vpIncludeProject.visibility = View.VISIBLE // vp on
            binding.indicatorInclude.visibility = View.VISIBLE // indicator on

            initViewPager() // 뷰페이저 연결하기
        }
    }

    private fun initViewPager(){
        val vpAdapter = ProjectVPAdapter(includeList)
        binding.vpIncludeProject.adapter = vpAdapter
        binding.indicatorInclude.attachTo(binding.vpIncludeProject)
    }

    // 추천 프로젝트 초기화 - (recyclerview)
    private fun initRecoProject(){
        // 리스트 한 개 미만 -> cardview On

        // 리스트 한 개 이상 -> RecyclerView On
    }
}