package com.betterzhang.learnkotlin.other


/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/14 下午2:10
 * Desc   : 解构声明
 */

// 有时把一个对象 解构 成很多变量会很方便
// val (name, age) = person
// println(name)
// println(age)
// 一个解构声明会被编译成以下代码
// val name = person.component1()
// val age = person.component2()

// 解构声明也可以用在 for-循环中
// 变量 a 和 b 的值取自对集合中的元素上调用 component1() 和 component2() 的返回值
// for ((a, b) in collection) {}

// 例：从函数中返回两个变量
// 因为数据类自动声明 componentN() 函数，所以这里可以用解构声明
data class Result(val result: Int, val status: Boolean)

fun function(): Result {
    // 各种计算
    return Result(10, true)
}

// 例：解构声明和映射
// for ((key, value) in map) {}

// 在 lambda 表达式中解构（自 1.1 起）
// 如果 lambda 表达式具有 Pair 类型（或者 Map.Entry 或任何其他具有相应 componentN 函数的类型）的参数，
// 那么可以通过将它们放在括号中来引入多个新参数来取代单个新参数：
// map.mapValues { entry -> "${entry.value}!" }
// map.mapValues { (key, value) -> "$value!" }
// 如果解构的参数中的一个组件未使用，那么可以将其替换为下划线，以避免编造其名称
// map.mapValues { (_, value) -> "$value!" }
// 你可以指定整个解构的参数的类型或者分别指定特定组件的类型
// map.mapValues { (_, value): Map.Entry<Int, String> -> "$value!" }
// map.mapValues { (_, value: String) -> "$value!" }

// 注意声明两个参数和声明一个解构对来取代单个参数之间的区别
/*
{ a //-> …… } // 一个参数
    { a, b //-> …… } // 两个参数
        { (a, b) //-> …… } // 一个解构对
            { (a, b), c //-> …… } // 一个解构对以及其他参数
*/

fun main(args: Array<String>) {
    // 现在，使用该函数
    val (result, status) = function()
    println("$result -> $status")

    // 下划线用于未使用的变量（自 1.1 起）
    // 如果在解构声明中你不需要某个变量，那么可以用下划线取代其名称
    val (result2, _) = function()
    println("$result2")

}