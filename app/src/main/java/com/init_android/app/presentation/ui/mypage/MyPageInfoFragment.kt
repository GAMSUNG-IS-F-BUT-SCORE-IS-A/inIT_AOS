package com.init_android.app.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.init_android.R
import com.init_android.app.data.request.mypage.RequestMyInfo
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.init_android.databinding.FragmentMyPageInfoBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class MyPageInfoFragment : BaseFragment<FragmentMyPageInfoBinding>(R.layout.fragment_my_page_info) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val myPageViewModel: MyPageViewModel by viewModels()
    //private val mainViewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClickListener()
        linkClickListener()
        basicInfoListener()
        //initNetwork()
        initModifyBtn()

    }

    override fun onResume() {
        super.onResume()
        initNetwork()

    }

    private fun btnClickListener() {
        binding.tvMypageBasicStackModify.setOnClickListener {
            val intentStack = Intent(requireActivity(), MyPageModifyStackActivity::class.java)
            startActivity(intentStack)
        }
    }

    private fun initNetwork() {


        val mNum = mainViewModel.otherNum.value ?: 1
        val requestMyInfo = RequestMyInfo(
            mNum = mNum
        )
        myPageViewModel.postMyInfo(requestMyInfo)



        myPageViewModel.myInfoData.observe(viewLifecycleOwner) {
            binding.user = it.mInfo

            val list = it.mInfo.mStacks



            if (list?.size != null) {
                binding.chipMypage.removeAllViews()
                for (i in 0 until list?.size!!) {
                    val chip = Chip(binding.chipMypage.getContext())
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(5, 0, 5, 0)
                    chip.layoutParams = layoutParams
                    chip.setText(list.get(i))
                    chip.closeIcon!!.isVisible
                    //chip.isCloseIconEnabled = true
                    chip.chipBackgroundColor = resources.getColorStateList(R.color.main_default)
                    chip.setTextColor(resources.getColorStateList(R.color.white))
                    //chip.closeIconTint = resources.getColorStateList(R.color.white)
                    chip.isClickable = true
                    chip.isCheckable = false

                    binding.chipMypage.addView(chip)

                }
            }
        }


    }

    //링크 수정 클릭 리스너
    private fun linkClickListener() {
        binding.tvMypageBasicLinkModify.setOnClickListener {
            val intentLink = Intent(requireActivity(), MyPageModifyLinkActivity::class.java)
            intentLink.putExtra("github", binding.tvMypageBasicGithubAnswer.text.toString())
            intentLink.putExtra("notion", binding.tvMypageBasicNotionAnswer.text.toString())
            intentLink.putExtra("blog", binding.tvMypageBasicBlogAnswer.text.toString())
            startActivity(intentLink)
        }
    }

    //기본 정보 수정 클릭 리스너
    private fun basicInfoListener() {
        binding.tvMypageBasicInfoModify.setOnClickListener {
            val intentBasicInfo =
                Intent(requireActivity(), MyPageModifyBasicInfoActivity::class.java)
            intentBasicInfo.putExtra("email", binding.tvMypageBasicInfoEmailAnswer.text.toString())
            intentBasicInfo.putExtra(
                "belong",
                binding.tvMypageBasicInfoBelongAnswer.text.toString()
            )
            intentBasicInfo.putExtra(
                "academic",
                binding.tvMypageBasicInfoAcademicAnswer.text.toString()
            )
            intentBasicInfo.putExtra(
                "gender",
                binding.tvMypageBasicInfoGenderAnswer.text.toString()
            )


            startActivity(intentBasicInfo)
        }
    }

    private fun initModifyBtn() {

        val mNum = mainViewModel.otherNum.value ?: 1
        if (mNum != 1) {
            binding.tvMypageBasicLinkModify.visibility = View.GONE
            binding.tvMypageBasicStackModify.visibility = View.GONE
            binding.tvMypageBasicInfoModify.visibility = View.GONE
        } else {
            binding.tvMypageBasicLinkModify.visibility = View.VISIBLE
            binding.tvMypageBasicStackModify.visibility = View.VISIBLE
            binding.tvMypageBasicInfoModify.visibility = View.VISIBLE
        }

    }


}