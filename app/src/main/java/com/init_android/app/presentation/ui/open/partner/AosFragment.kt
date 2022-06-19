package com.init_android.app.presentation.ui.open.partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.init_android.R
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.response.project.ready.ResponseReadyAos
import com.init_android.app.presentation.ui.main.MainViewModel
import com.init_android.app.presentation.ui.open.partner.adapter.PartnerAosAdapter
import com.init_android.app.presentation.ui.open.partner.adapter.ready.ReadyAosAdapter
import com.init_android.app.presentation.ui.open.viewmodel.ProjectViewModel
import com.init_android.databinding.FragmentAosBinding
import com.playtogether_android.app.presentation.base.BaseFragment

class AosFragment : BaseFragment<FragmentAosBinding>(R.layout.fragment_aos) {

    private val projectViewModel: ProjectViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var partnerAosAdapter: PartnerAosAdapter
    private lateinit var readyAosAdapter: ReadyAosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initNetwork()
        initApprove()
    }

    //승인 대기 중
    private fun initApprove() {
        val pNum = mainViewModel.projectNum.value ?: 1
        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewAos(requestProjectMember)
        readyAosAdapter = ReadyAosAdapter(1)
        binding.rvApproveAos.adapter = readyAosAdapter
        projectViewModel.myCrewAos.observe(viewLifecycleOwner) {
            readyAosAdapter.setQuestionPost((it.waitingAos) as MutableList<ResponseReadyAos.WaitingAos>)
        }
    }

    //승인 대기 완료
    private fun initNetwork() {
        val pNum = mainViewModel.projectNum.value ?: 1

        val requestProjectMember = RequestProjectMember(pNum = pNum)
        projectViewModel.postMyCrewAos(requestProjectMember)
        partnerAosAdapter = PartnerAosAdapter(1)
        binding.rvAos.adapter = partnerAosAdapter
        projectViewModel.myCrewAos.observe(viewLifecycleOwner) {
            partnerAosAdapter.setQuestionPost((it.approvedAos) as MutableList<ResponseReadyAos.ApprovedAos>)
        }
    }

}
