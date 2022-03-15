package com.example.materialtest

import android.content.Context
import android.widget.Toast

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}
//duration 设置一个默认参数  如有需要则可以在调用是新增参数变更默认参数
fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}