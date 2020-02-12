package com.example.viewwork.worktext.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewwork.worktext.R
import com.example.viewwork.worktext.base.BaseActivity
import com.example.viewwork.worktext.ui.user_data.UserDataActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var source = 0

    private val viewModel by viewModel<MainViewModel>()

    private val userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
        userAdapter.apply {
            setOnLoadMoreListener {
                source += 50
                viewModel.getUserList(source)
            }
            setOnItemClickListener {
                UserDataActivity.launch(this@MainActivity, it.login.orEmpty())
            }
        }
        initData()
    }

    private fun initData() {
        viewModel.apply {
            getUserList(source)
            userListLiveData.observe(this@MainActivity, Observer {
                userAdapter.updateData(it)
            })
        }
    }

}
