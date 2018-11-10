package com.freaky.id.footballscheduleapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentNext : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater!!.inflate(R.layout.fragment_next, container, false)

    companion object {
        fun newInstance(): FragmentNext = FragmentNext()
    }
}
