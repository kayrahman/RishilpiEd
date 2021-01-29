package com.nkr.rishilpi_ed.ui.dashboard

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.nkr.rishilpi_ed.R
import com.nkr.rishilpi_ed.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //exo player
    private lateinit var exoplayer : SimpleExoPlayer
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition : Long = 0


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun initializePlayer(){
        //1.1 create an exo player
        exoplayer = SimpleExoPlayer.Builder(requireActivity()).build()
        binding.playerView.player = exoplayer
        //1.2 create a media item
      //  val media_item = MediaItem.fromUri(getString(R.string.sample_media_url_mp3))
        try {
       //     val media_item = MediaItem.fromUri(getString(R.string.sample_media_url_local_mp4))
        //    exoplayer.setMediaItem(media_item)

            val uri = Uri.parse(getString(R.string.sample_media_url_local_mp4))
            val mediaSource = buildMediaSource(uri)
            exoplayer.prepare(mediaSource!!, true, false)

            exoplayer.playWhenReady = playWhenReady
           // exoplayer.seekTo(currentWindow, playbackPosition)
          //  exoplayer.prepare()

        }catch (e: Exception){
            Log.e("media_item", e.toString())
        }
       // val media_item = MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.close_up))

    }


    override fun onStart() {
        super.onStart()
        if(Util.SDK_INT >= 24){
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
      //  hideSystemUi()
        if(Util.SDK_INT < 24 || exoplayer == null ){
            initializePlayer()
        }
    }


    override fun onPause() {
        super.onPause()

        if(Util.SDK_INT < 24){
            releasePlayer()
        }

    }

    override fun onStop() {
        super.onStop()

        if(Util.SDK_INT >= 24){
            releasePlayer()
        }

    }

    private fun releasePlayer() {
        playWhenReady = exoplayer.playWhenReady
        currentWindow = exoplayer.currentWindowIndex
        playbackPosition = exoplayer.currentPosition
        binding.playerView.player = null
        exoplayer.release()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }


    private fun buildMediaSource(uri: Uri): MediaSource? {
        return ExtractorMediaSource.Factory(
                DefaultDataSourceFactory(requireActivity(), "Exoplayer-local")).createMediaSource(uri)
    }


}