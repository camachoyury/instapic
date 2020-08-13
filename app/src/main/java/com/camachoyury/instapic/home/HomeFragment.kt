package com.camachoyury.instapic.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.camachoyury.instapic.databinding.FragmentHomeBinding
import com.camachoyury.instapic.model.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(){

    private lateinit var fragmentHomeBinding:FragmentHomeBinding
    private  val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container, false)
        return fragmentHomeBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = FeedAdapter(listOf<Post>())
    }

    override fun onStart() {
        super.onStart()
        homeFragmentViewModel.loadPost()
        homeFragmentViewModel.postLiveData.observe(this, Observer {
            adapter = FeedAdapter(it)
            fragmentHomeBinding.feedRecycler.adapter = adapter
            fragmentHomeBinding.feedRecycler.layoutManager = LinearLayoutManager(this.context)

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}