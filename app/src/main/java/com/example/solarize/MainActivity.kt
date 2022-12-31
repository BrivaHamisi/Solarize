package com.example.solarize

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.solarize.adapter.ViewPagerAdapter
import com.example.solarize.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: ViewPagerAdapter?= null
    var tabLayout:TabLayout?= null
    var onBoardingViewPager: ViewPager?= null
    var next:TextView?=null
    var position = 0
    var sharedPreferences: SharedPreferences?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()){
            val i = Intent(applicationContext, HomeActivity::class.java)
            startActivity(i)
            finish()
        }
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.text_next)

//        data to the Model Class
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData(title = "Solution", desc = "Easy access to cleaner energy and conducive spaces to students at any time.(Mental Health wellbeing)\n" +
                "Adoption of solar as a suitable power option to power students' laptops and phones \n" +
                "Creation of decent innovations and infrastructure hence attaining SDG 7,8 & 9", R.drawable.oboarding1))
        onBoardingData.add(OnBoardingData(title = "It's Uniqueness", desc = "Compared to other sources of energy, solar energy has higher installation costs but lower maintenance costs in the long run.\n" +
                "Implementation of this project will be the first in Kenyan Universities benefiting many university students .\n" +
                "Mentalwell being for all", R.drawable.oboarding2))
        onBoardingData.add(OnBoardingData(title = "Milestones", desc = "Registration of Project\n" +
                " Load Analysis.\n" +
                " Industrial Tinkering.\n" +
                "Green Hugs", R.drawable.oboarding3))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener(){
            if(position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
            }
        }
        tabLayout!!.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if(tab.position == onBoardingData.size -1 ){
                    next!!.text = "Get Started"
                }else{
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setOnBoardingViewPagerAdapter(OnBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.onboardingViewPager)
        onBoardingViewPagerAdapter = ViewPagerAdapter(this, OnBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter

        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }
    private  fun savePrefData(){
        sharedPreferences = application.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }
    private  fun restorePrefData():Boolean{
        sharedPreferences = application.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}