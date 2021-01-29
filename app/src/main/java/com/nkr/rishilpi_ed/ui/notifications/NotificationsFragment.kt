package com.nkr.rishilpi_ed.ui.notifications

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nkr.rishilpi_ed.R
import com.nkr.rishilpi_ed.databinding.FragmentNotificationsBinding
import java.io.File
import java.lang.Exception

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.btnSaveVideo?.setOnClickListener {
            // save video from raw file

            val output_folder = getVideoStorageDirectory(requireContext(),"rishilpi_video")
            val output_file = File(output_folder,"demo_video.mp4")
            try {
                requireContext().openFileOutput(output_file.name, Context.MODE_PRIVATE).use {
                    it.write(R.raw.close_up)
                    Log.e("Storage_dir", "File Saved ${output_file.absolutePath}")
                    Log.e("Storage_dir", "Uri ${Uri.fromFile(output_file)}")


                }
            }catch (e:Exception){
                Log.e("Storage_dir", e.toString())
            }

            
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun getVideoStorageDirectory(context: Context, albumName: String): File? {
        // Get the pictures directory that's inside the app-specific directory on
        // external storage.
        val file = File(context.getExternalFilesDir(
                Environment.DIRECTORY_MOVIES), albumName)

        Log.e("Storage_dir", "Directory created")

        if (!file.mkdirs()) {
            Log.e("Storage_dir", "Directory not created")
        }
        return file
    }

}