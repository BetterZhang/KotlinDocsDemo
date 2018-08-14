package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/14 下午3:08
 * Desc   : 区间
 */
// 区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
// 区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现
fun main(args: Array<String>) {
    for (i in 1..10)    // 等同于1 <= i && i <= 10
        print(i)

    println()

    // 整型区间（IntRange、 LongRange、 CharRange）有一个额外的特性：它们可以迭代。
    // 编译器负责将其转换为类似 Java 的基于索引的 for-循环而无额外开销
    for (i in 1..4)
        print(i)
    println()
    for (i in 4..1)
        print(i)

    println()
    // 倒序迭代数字
    for (i in 4 downTo 1)
        print(i)

    println()
    // 任意步长迭代数字
    for (i in 1..4 step 2)
        print(i)

    println()
    for (i in 4 downTo 1 step 2)
        print(i)

    println()
    // 要创建一个不包括其结束元素的区间，可以使用 until 函数
    for (i in 1 until 10)
        print(i)

    println()
    println((1..12 step 2).last)
    println((1..12 step 3).last)
    println((1..12 step 4).last)
}