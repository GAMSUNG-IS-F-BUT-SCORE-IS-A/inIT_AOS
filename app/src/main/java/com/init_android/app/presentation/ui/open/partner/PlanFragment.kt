package com.init_android.app.presentation.ui.open.partner

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestApproveProject
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyPlan
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerPlanAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyPlanAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.app.util.CustomDialog
import com.init_android.databinding.FragmentPlanBinding
import com.playtogether_android.app.presentation.base.BaseFragment


class PlanFragment : BaseFragment<FragmentPlanBinding>(R.layout.fragment_plan) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val projectViewModel: ProjectViewModel by viewModels()
    private lateinit var partnerPlanAdapter: PartnerPlanAdapter
    private lateinit var readyPlanAdapter: ReadyPlanAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
        initApprove()
        initSetting()
    }

    private fun initSetting() {
        val pNum = mainViewModel.projectNum.value ?: 1
        if (pNum != 1) {
            binding.tvPartnerApprove.visibility = View.GONE
            binding.tvPartnerApproveNum.visibility = View.GONE
            binding.rvApprovePlan.visibility = View.GONE
        } else {
            binding.tvPartnerApprove.visibility = View.VISIBLE
            binding.tvPartnerApproveNum.visibility = View.VISIBLE
            binding.rvApprovePlan.visibility = View.VISIBLE
        }
    }


    private fun initApprove() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewPlan(requestProjectMember)
        readyPlanAdapter = ReadyPlanAdapter(1)
        binding.rvApprovePlan.adapter = readyPlanAdapter
        projectViewModel.myCrewPlan.observe(viewLifecycleOwner) {
            readyPlanAdapter.setQuestionPost((it.waitingPlan) as MutableList<ResponseReadyPlan.WaitingPlan>)
        }
        initModelListener()
    }

    private fun initModelListener() {
        readyPlanAdapter.setOnItemClickListener(object : ReadyPlanAdapter.onItemClickListener {
            override fun onItemClick(user: Int, position: Int) {

                val title = "해당 팀원을 승인하시겠습니까?"
                val dialog = CustomDialog(requireContext(), title)
                dialog.showChoiceDialog(R.layout.dialog_yes_no)

                dialog.setOnClickedListener(object : CustomDialog.ButtonClickListener {
                    override fun onClicked(num: Int) {
                        if (num == 1) {
                            val userId = user
                            val requestApproveProject = RequestApproveProject(
                                mNum = 1,
                                pNum = 1,
                                apply = userId
                            )
                            Log.d("TestUSerId", userId.toString())

                            projectViewModel.postApprove(requestApproveProject)
                            projectViewModel.applyProject.observe(viewLifecycleOwner) {
                                if (it.code == 201) {
                                    Log.d("승인", "성공")
                                    Toast.makeText(
                                        requireContext(),
                                        "승인이 완료되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    initNetwork()
                                    initApprove()
                                } else {
                                    Log.d("승인", "거절")
                                    Toast.makeText(
                                        requireContext(),
                                        "메시지 전송이 취소되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    initNetwork()
                                    initApprove()
                                }
                            }


                        }
                    }
                })
            }
        })
    }

    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewPlan(requestProjectMember)
        partnerPlanAdapter = PartnerPlanAdapter(1)
        binding.rvPlan.adapter = partnerPlanAdapter
        projectViewModel.myCrewPlan.observe(viewLifecycleOwner) {
            partnerPlanAdapter.setQuestionPost((it.approvedPlan) as MutableList<ResponseReadyPlan.ApprovedPlan>)
        }
    }
}