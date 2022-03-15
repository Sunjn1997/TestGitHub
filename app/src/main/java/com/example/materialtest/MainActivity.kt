package com.example.materialtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.materialtest.adapter.FruitAdapter
import com.example.materialtest.entity.Fruit
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )
    private val fruitList = ArrayList<Fruit>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)//设置为默认选中
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
        fab.setOnClickListener {
            Toast.makeText(this, "click is fab", Toast.LENGTH_SHORT).show()
            //简化前
//            Snackbar.make(it, "data delete", Snackbar.LENGTH_SHORT).setAction("undo") {
//                Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
//            }.show()
            //简化后
            it.showSnackbar("123","undo"){
                "Data restored".showToast(this)
            }

        }
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        recyclerview.adapter = adapter

        swipeRefresh.setColorSchemeColors(R.color.colorPrimary)
        //刷新功能
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }


    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                //切换至主线程
                initFruits()
                adapter.notifyDataSetChanged()//recyclerview 进行刷新
                swipeRefresh.isRefreshing = false//关闭刷新动画
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits.get(index))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.backup -> {
                Toast.makeText(this, "click backup", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "click delete", Toast.LENGTH_SHORT).show()
            }
            R.id.setting -> {
                Toast.makeText(this, "click setting", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
    }
}