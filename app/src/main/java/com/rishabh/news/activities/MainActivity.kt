package com.rishabh.news.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.funtoolearn.util.ViewModelUtil
import com.rishabh.news.MyApplication
import com.rishabh.news.R
import com.rishabh.news.databinding.ActivityMainBinding
import com.rishabh.news.viewmodels.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    lateinit var mainActivityViewBinding: ActivityMainBinding
    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        setRecyclerView()
        setObservers()
    }

    private fun init() {
        MyApplication.getComponent().injectMainActivity(this)
        val viewModelFactory = ViewModelUtil.createFor(mainActivityViewModel)
        mainActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            MainActivityViewModel::class.java
        )
        mainActivityViewBinding.activityViewModel = mainActivityViewModel
    }

    private fun setRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        mainActivityViewBinding.recyclerView.layoutManager = linearLayoutManager
    }

    private fun setObservers() {
        mainActivityViewModel.showProgress.observe(this, Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })
    }
}
