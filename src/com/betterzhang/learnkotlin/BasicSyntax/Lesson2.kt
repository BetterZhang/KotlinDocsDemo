package com.betterzhang.learnkotlin.BasicSyntax

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 上午11:44
 * Desc   : 基础语法
 */

fun main(args: Array<String>) {
    // 定义变量
    // 一次赋值（只读）的局部变量
    val a: Int = 1  // 立即赋值
    val b = 2       // 自动推断出Int类型
    val c: Int      // 如果没有初始值，类型不能省略
    c = 3           // 明确赋值
    println("a = $a, b = $b, c = $c")

    // 可变变量
    var x = 5       // 自动推断出Int类型
    x += 1
    println("x = $x")
}