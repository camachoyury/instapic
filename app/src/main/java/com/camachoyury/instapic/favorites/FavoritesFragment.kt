package com.camachoyury.instapic.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.camachoyury.instapic.databinding.FragmentFavoritesBinding


class FavoritesFragment: Fragment() {

    private lateinit var fragmentFavoritesBinding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater,container, false)
        return fragmentFavoritesBinding.root
    }
}