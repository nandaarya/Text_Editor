package com.dicoding.text_editor

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        supportActionBar?.title = intent.getStringExtra("text_editor_name")

        var imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        var tvName: TextView = findViewById(R.id.tv_item_name)
        var tvDetail: TextView = findViewById(R.id.tv_item_detail)

        tvName.text = intent.getStringExtra("text_editor_name")
        tvDetail.text = intent.getStringExtra("text_editor_detail")
    }
}