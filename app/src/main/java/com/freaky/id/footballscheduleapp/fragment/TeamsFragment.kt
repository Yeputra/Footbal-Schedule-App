package com.freaky.id.footballscheduleapp.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.EditText
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.activity.SearchTeamActivity

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout1)

        val viewPager: ViewPager = view.findViewById(R.id.view_pager1)

        val adapter = TabTeamAdapter(childFragmentManager)

        setHasOptionsMenu(true)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFFFF"))
        tabLayout.setSelectedTabIndicatorHeight(((5 * getResources().getDisplayMetrics().density).toInt()))
        tabLayout.setTabTextColors(Color.parseColor("#d1cfcf"), Color.parseColor("#FFFFFFFF"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.searchMenu)?.actionView as SearchView?



        searchView?.queryHint = "Search teams"

        val hint = searchView?.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
        if (hint != null) {
            hint.setHintTextColor(Color.GRAY)
        }


        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(context, SearchTeamActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })


    }



}
