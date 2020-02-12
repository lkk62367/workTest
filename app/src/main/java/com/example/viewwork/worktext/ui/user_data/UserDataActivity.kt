package com.example.viewwork.worktext.ui.user_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.viewwork.worktext.R
import com.example.viewwork.worktext.data.UserData
import kotlinx.android.synthetic.main.activity_user_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDataActivity : AppCompatActivity() {

    companion object {

        private const val USER_NAME = "USER_NAME"

        fun launch(activity: Activity, userName: String) {
            activity.startActivity(Intent(activity, UserDataActivity::class.java).apply {
                putExtra(USER_NAME, userName)
            })
        }
    }

    private val viewModel by viewModel<UserDataViewModel>()

    private val userName: String
        get() = intent.getStringExtra(USER_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)


        imageViewCancel.setOnClickListener {
            finish()
        }
        initData()
    }

    private fun initData() {
        viewModel.apply {
            getUserData(userName)
            userDataLiveData.observe(this@UserDataActivity, Observer {
                setData(it)
            })
        }
    }

    private fun setData(userData: UserData) {
        Glide.with(imageViewAvatar)
            .load(userData.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(imageViewAvatar)
        textViewName.text = userData.name
        textViewBio.text = userData.bio
        textViewLogin.text = userData.blog
        if (userData.siteAdmin) {
            textViewSiteAdmin.visibility = View.VISIBLE
        }else{
            textViewSiteAdmin.visibility = View.GONE
        }
        textViewLocation.text = userData.location
        textViewBlog.text = userData.blog
    }
}