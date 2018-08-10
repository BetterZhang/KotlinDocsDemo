package com.betterzhang.learnkotlin.classesAndObjects

import kotlin.properties.Delegates

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/10 下午4:55
 * Desc   : 委托属性
 */

//有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。例如包括：
//
//——延迟属性（lazy properties）: 其值只在首次访问时计算；
//——可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；
//——把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。

// 延迟属性lazy
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

// 可观察属性 Observable
class User1 {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

// 把属性储存在映射中
class User3(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}


fun main(args: Array<String>) {
    println(lazyValue)
    println(lazyValue)

    val user = User1()
    user.name = "first"
    user.name = "second"

    val user2 = User3(mapOf(
        "name" to "John Doe",
        "age" to 25
    ))

    println(user2.name)
    println(user2.age)
}