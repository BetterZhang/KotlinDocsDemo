package com.betterzhang.learnkotlin.basics

import java.lang.Integer.parseInt

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/30 下午2:31
 * Desc   : 控制流(if、when、for、while)
 */

fun main(args: Array<String>) {
    // if表达式
    // 传统用法
    fun maxOf1(a: Int, b: Int): Int {
        var max = a
        if (a < b)
            max = b
        return max
    }
    // with else
    fun maxOf2(a: Int, b: Int): Int {
        var max: Int
        if (a > b)
            max = a
        else
            max = b
        return max
    }
    // 作为表达式
    fun maxOf3(a: Int, b: Int) = if (a > b) a else b

    // if的分支可以是代码块，最后的表达式作为该块的值
    fun maxOf4(a: Int, b: Int): Int {
        return if (a > b) {
            println("choose a")
            a
        } else {
            println("choose b")
            b
        }
    }

    println(maxOf1(3, 5))
    println(maxOf2(10, 4))
    println(maxOf3(20, -2))
    println(maxOf4(16, 19))

    // when表达式
    // 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件
    fun test1(x: Int) {
        when (x) {
            1 -> println("x == 1")
            2 -> println("x == 2")
            else -> println("x is neither 1 nor 2")
        }
    }
    test1(4)

    // 如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔
    fun test2(x: Int) {
        when(x) {
            1, 2 -> println("x == 1 or x == 2")
            else -> println("otherwise")
        }
    }
    test2(2)

    // 我们可以用任意表达式（而不只是常量）作为分支条件
    fun test3(x: Int) {
        when(x) {
            parseInt("4") -> println("s encodes x")
            else        -> println("s does not encode x")
        }
    }
    test3(4)

    // 我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中
    val validNumbers = listOf(3, 5, 9)
    fun test4(x: Int) {
        when (x) {
            in 1..10 -> println("x is in the range")
            in validNumbers -> println("x is valid")
            !in 10..20 -> println("x is outside the range")
            else -> println("none of above")
        }
    }
    test4(30)

    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }
    println(hasPrefix("prefixAfter"))

    val x: Int = 10
    when {
        x > 5 -> println("x > 5")
        x < 30 -> println("x < 30")
        else -> println("x > 30")
    }

    // for循环
    val array = arrayListOf(1, 3, 5)
    for (x in array)
        println(x)

    for (index in array.indices)
        println(array[index])

    for ((index, value) in array.withIndex())
        println("The element at $index is $value")

    // while循环
    var y = 5
    while (y > 0) {
        println(y)
        y--
    }

    y = 5
    // do while循环
    do {
        println(y)
        y--
    } while (y > 0)
}
