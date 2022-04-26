package com.example.pinterestrealniyclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.pinterestrealniyclone.R
import com.example.pinterestrealniyclone.adapters.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ChatFragment : BaseFragment() {
    private lateinit var rootView: View
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false)
        val titles = arrayListOf("Update", "Service")

        viewPager2 = rootView.findViewById(R.id.view_pager2)
        tabLayout = rootView.findViewById(R.id.tab_layout)
        val adapter = MyViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(UpdateFragment())
        adapter.addFragment(SmsFragment())
        viewPager2.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager2) { tab, pos -> tab.text = titles[pos] }.attach()

        return rootView
    }
}
