package com.example.solarize.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.solarize.R
import com.example.solarize.model.OnBoardingData

class ViewPagerAdapter(private var context:Context, private var onBoardingDataList: List <OnBoardingData>) : PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ==`object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null)
        val imageView: ImageView
        val title: TextView
        val desc: TextView

        imageView = view.findViewById(R.id.imageView2)
        title = view.findViewById(R.id.Onboarding_title)
        desc = view.findViewById(R.id.description)

        imageView.setImageResource(onBoardingDataList[position].ImageUrl)
        title.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view

    }
}