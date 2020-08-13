package com.camachoyury.instapic.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.camachoyury.instapic.databinding.FragmentHomeBinding
import com.camachoyury.instapic.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

private lateinit var fragmentProfileBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater,container, false)
        return fragmentProfileBinding.root

    }

}