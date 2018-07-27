// 定义包
package com.betterzhang.learnkotlin.BasicSyntax

// 导入包
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 上午11:25
 * Desc   : 基础语法
 */

/**
 * 带有两个Int参数、返回Int的函数
 */
fun sum1(a: Int, b: Int): Int {
    return a + b
}

/**
 * 将表达式作为函数体、返回值类型自动推断的函数
 */
fun sum2(a: Int, b: Int) = a + b

/**
 * 函数返回无意义的值
 */
fun printSum1(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

/**
 * Unit返回类型可以省略
 */
fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun main(args: Array<String>) {
    print("sum of 3 and 5 is ")
    println(sum1(3, 5))

    println("sum of 19 and 23 is ${sum2(19, 23)}")

    printSum1(-1, 8)
    printSum2(-1, 8)
}