package com.example.winrate.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.example.winrate.databinding.FragmentWelcomeDotaBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import androidx.annotation.NonNull
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener



class WelcomeDotaFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeDotaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            com.example.winrate.R.layout.fragment_welcome_dota,container, false)

        initYoutubeVideo()

        return binding.root
    }

    private fun initYoutubeVideo() {
        val youTubePlayerView = binding.youtubeVideoPlayer
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "SmnqsdeHFT0"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }


}
