package com.betterzhang.learnkotlin.BasicSyntax

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 下午2:23
 * Desc   : 基础语法
 */

fun main(args: Array<String>) {
    // 使用for循环
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    // 或者
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    // 使用while循环
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    //使用when表达式
    fun describe(obj: Any): String =
        when (obj) {
            1           -> "one"
            "Hello"     -> "Greeting"
            is Long     -> "Long"
            !is String  -> "Not a string"
            else        -> "Unknown"
        }
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    // 使用区间(range)
    // 使用in运算符来检测某个数字是否在指定范围内
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    // 检测某个数字是否在指定区间外
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range too")
    }

    // 区间迭代
    for (x in 1..5) {
        print(x)
    }
    println()

    // 数列迭代
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
    println()

    // 使用集合
    // 对集合进行迭代
    for (item in items) {
        println(item)
    }

    // 使用in运算符来判断集合内是否包含某实例
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    // 使用lambda表达式来过滤(filter)和映射(map)集合
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }


}