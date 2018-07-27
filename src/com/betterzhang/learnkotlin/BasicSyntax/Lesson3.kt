package com.betterzhang.learnkotlin.BasicSyntax

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 上午11:53
 * Desc   : 基础语法
 */

// 顶层变量
val PI = 3.14
var x = 0

fun incrementX() {
    x += 1
}

fun main(args: Array<String>) {
    println("x = $x; PI = $PI")
    incrementX()
    println("incrementX()")
    println("x = $x; PI = $PI")
}