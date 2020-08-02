package com.lnsergey.softbalance.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.lnsergey.softbalance.R
import com.lnsergey.softbalance.app.view_model.MainActivityViewModel
import com.lnsergey.softbalance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MainActivityViewModel()
        binding.viewModel = viewModel
        binding.recyclerView.adapter = viewModel.forecastRecyclerAdapter

        viewModel.loading.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.loading.get())
                    binding.imageView3.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate))
                else
                    binding.imageView3.clearAnimation()

            }

        })
    }
}