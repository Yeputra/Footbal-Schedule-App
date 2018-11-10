package com.freaky.id.footballscheduleapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freaky.id.footballscheduleapp.R

class FragmentNext : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_next, container, false)
        return rootView
    }

    companion object {
        fun newInstance(): FragmentNext =
            FragmentNext()
    }
}
