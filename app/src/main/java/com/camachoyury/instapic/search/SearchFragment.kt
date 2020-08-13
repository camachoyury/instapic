package com.camachoyury.instapic.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.camachoyury.instapic.databinding.FragmentSearchBinding


class SearchFragment: Fragment() {

    private lateinit var fragmentSearchBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater,container, false)
        return fragmentSearchBinding.root
    }
}