package com.demo.nasapictureapp.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.nasapictureapp.R
import com.demo.nasapictureapp.data.api.ApiHelper
import com.demo.nasapictureapp.data.api.ApiServiceImpl
import com.demo.nasapictureapp.data.model.NasaImageModel
import com.demo.nasapictureapp.data.viewmodels.MainViewModel
import com.demo.nasapictureapp.data.viewmodels.ViewModelFactory
import com.demo.nasapictureapp.databinding.ActivityMainBinding
import com.demo.nasapictureapp.listeners.OnItemClickListener
import com.demo.nasapictureapp.ui.adapter.MainAdapter
import com.demo.nasapictureapp.utils.NetworkUtil
import com.demo.nasapictureapp.utils.ToastUtil

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var mContext: Context
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    var images: ArrayList<NasaImageModel> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize Context
        mContext = this
        //Initialize View
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Initialize ViewModel
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
        // on below line we are creating a variable
        // for our grid layout manager and specifying
        // column count as 3
        val adapter = MainAdapter(object : OnItemClickListener {
            override fun onClick(position: Int) {
                binding.recyclerview.visibility = View.GONE
                binding.mainContainer.visibility = View.VISIBLE
            }
        })
        val layoutManager = GridLayoutManager(this, 3)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter
        //Observe Data On UI
        viewModel.imageList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            images.addAll(it)
            adapter.setImageList(it)
        })
        //Call getAllImages
        if (NetworkUtil.checkForInternet(mContext)) {
            viewModel.getAllImages()
        } else {
            ToastUtil.showMessage(mContext,getString(R.string.str_please_check_your_internet_connection))
        }

    }
}