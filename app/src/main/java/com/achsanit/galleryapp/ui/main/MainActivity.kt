package com.achsanit.galleryapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.achsanit.core.model.PhotoItemEntity
import com.achsanit.galleryapp.adapter.PhotoAdapter
import com.achsanit.galleryapp.databinding.ActivityMainBinding
import com.achsanit.galleryapp.ui.dialog.PopUpImageDialogFragment
import com.achsanit.utils.Resource
import com.achsanit.utils.makeGone
import com.achsanit.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter {
            // pop up dialog when item photo clicked
            PopUpImageDialogFragment(it.photoUrl)
                .show(supportFragmentManager, PopUpImageDialogFragment.TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListener()
        setUpView()
    }

    private fun setUpView() {
        with(binding) {
            // setup recyclerview
            val staggered = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            staggered.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            rvPhoto.apply {
                layoutManager = staggered
                adapter = photoAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun setUpListener() {
        // listener updated data from livedata
        viewModel.photos.observe(this, ::photosCollector)
    }

    // function for update ui when data changed
    private fun photosCollector(data: Resource<List<PhotoItemEntity>>) {
        with(binding) {
            data.collect(
                onLoading = {
                    // handling when loading
                    pbPhotos.makeVisible()
                },
                onSuccess = {
                    // handling when data success
                    pbPhotos.makeGone()
                    photoAdapter.setData(it ?: emptyList())
                },
                onError = { s: String?, _: Int ->
                    // handling when data error
                    pbPhotos.makeGone()
                    Toast.makeText(this@MainActivity, "Failed Get Data...", Toast.LENGTH_SHORT)
                        .show()
                    tvMessageError.makeVisible()
                    tvMessageError.text = s
                }
            )
        }
    }

}