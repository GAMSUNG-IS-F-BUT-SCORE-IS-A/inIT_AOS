package com.init_android.app.presentation.ui.open.partner.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.init_android.app.presentation.ui.mypage.PartnerPageActivity
import com.init_android.databinding.ItemPartnerListBinding

class PartnerAdapter
//    (var userId : Int) : RecyclerView.Adapter<PartnerAdapter.PartnerListViewHolder>() {
//    var partnerData = mutableListOf<MyPageLikeQuestionData.Data.LikePost>()
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): PartnerListViewHolder {
//        val binding = ItemPartnerListBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return PartnerListViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(
//        holder: PartnerListViewHolder,
//        position: Int
//    ) {
//        holder.onBind(partnerData[position])
//        holder.binding.root.setOnClickListener {
//
//            val context = holder.itemView.context
//
//            val intent = Intent(context, PartnerPageActivity::class.java)
//            val postId = partnerData[position].postId
//            intent.putExtra("userId", userId)
//            ContextCompat.startActivity(holder.itemView.context,intent, null)
//
//        }
//    }
//
//    override fun getItemCount(): Int = partnerData.size
//
//    inner class PartnerListViewHolder(
//        val binding: ItemPartnerListBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun onBind(partnerData: MyPageLikeQuestionData.Data.LikePost) {
//            binding.apply {
//                myPageLikeQuestion = partnerData
//                executePendingBindings()
//            }
//        }
//    }
//
//    fun setQuestionPost(partnerData: MutableList<MyPageLikeQuestionData.Data.LikePost>) {
//        this.myPageLikeQuestionData = partnerData
//        notifyDataSetChanged()
//
//    }
//}