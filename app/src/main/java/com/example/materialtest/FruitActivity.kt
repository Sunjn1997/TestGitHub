package com.example.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {
    //初步理解  伴生对象   Java中的静态属性
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""//否则返回空字符串
        val fruitImage = intent.getIntExtra(FRUIT_IMAGE_ID, 0)//如果没有取到数据则返回0
        setSupportActionBar(toolbar)//设置标题栏
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//
        collapsingToolbar.title = fruitName//给标题栏赋值
        Glide.with(this).load(fruitImage).into(fruitImageView)
        fruitContextText.text = generateFruitContent(fruitName)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun generateFruitContent(fruitName: String): CharSequence {
        return fruitName.repeat(500)
    }
}