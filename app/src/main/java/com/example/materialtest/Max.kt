package com.example.materialtest

import java.lang.RuntimeException
import kotlin.math.max

fun main() {
    maxNumber()
}

fun maxNumber() {
    val a = 1
    val b = 2
    val c = 3
    val max = max(a, b)
    val max2 = max(a, b, c)
    val min = min(a, b, c)
    println(max)
    println(max2)
    println(min)
}

//fun max(vararg nums: Int): Int {
//    var maxNum = Int.MIN_VALUE
//    for (num in nums) {
//        maxNum = kotlin.math.max(maxNum, num)
//    }
//    return maxNum
//}

fun <T : Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) {
        throw  RuntimeException("Params can not be empty.")

    }
    var maxNum = nums[0]
    for (num in nums){
        if(num>maxNum){
            maxNum = num
        }
    }
    return maxNum
}
fun <T:Comparable<T>> min (vararg  nums:T):T{
    if (nums.isEmpty()) {
        throw  RuntimeException("Params can not be empty.")

    }
    var minNum = nums[0]
    for(num in nums){
        if(num<minNum){
            minNum = num
        }
    }
    return minNum
}
