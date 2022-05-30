package com.init_android.app.util

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.init_android.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageBind")
    fun setImage(imageView: ImageView, imageUrl: Int) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .circleCrop()
            .into(imageView)

    }

    @JvmStatic
    @BindingAdapter("pTypeBind")
    fun setPType(textView: TextView, pType: Int) {
        when(pType){
            0 -> {textView.text = "웹" }
            1 -> {textView.text = "모바일"}
            2 -> {textView.text = "게임"}
        }
    }

    @JvmStatic
    @BindingAdapter("pStateBind")
    fun setPState(textView: TextView, pType: Int) {
        when(pType){
            0 -> {textView.text = "D-Day" }
            1 -> {textView.text = "FIN"}
            2 -> {textView.text = "ING"}
        }
    }

    @JvmStatic
    @BindingAdapter("pStateBgBind")
    fun setPBgState(view: View, pType: Int) {
        when(pType){
            0 -> {view.setBackgroundColor(Color.parseColor("#FF4949"))}
            1 -> {view.setBackgroundColor(Color.parseColor("#A3A3A3"))}
            2 -> {view.setBackgroundColor(Color.parseColor("#000000"))}
        }
    }

    @JvmStatic
    @BindingAdapter("pOnOffBind")
    fun setPOnOff(textView: TextView, pType: Int) {
        when(pType){
            0 -> {textView.text = "온라인" }
            1 -> {textView.text = "오프라인"}
        }
    }

    @JvmStatic
    @BindingAdapter("pPostion")
    fun setPostion(textView: TextView, pType: Int) {
        when(pType) {
            0 -> {textView.text = "Planer"}
            1 -> {textView.text = "Designer"}
            2 -> {textView.text = "IOS Developer"}
            3 -> {textView.text = "Android Developer"}
            4 -> {textView.text = "Web Developer"}
            5 -> {textView.text = "Game Developer"}
            6 -> {textView.text = "Server Developer"}
        }
    }

    @JvmStatic
    @BindingAdapter("pLevel")
    fun setLevel(textView: TextView, pType: Int) {
        when(pType) {
            0 -> {textView.text = "상"}
            1 -> {textView.text = "중"}
            2 -> {textView.text = "하"}
        }
    }

    @JvmStatic
    @BindingAdapter("mAcademic")
    fun setAcademic(textView: TextView, pType: Int) {
        when(pType) {
            0 -> {textView.text = "재학"}
            1 -> {textView.text = "휴학"}
            2 -> {textView.text = "졸업"}
        }
    }

    @JvmStatic
    @BindingAdapter("mGender")
    fun setGender(textView: TextView, pType: Int) {
        when(pType) {
            0 -> {textView.text = "남"}
            1 -> {textView.text = "여"}
            2 -> {textView.text = "기타"}
        }
    }

}