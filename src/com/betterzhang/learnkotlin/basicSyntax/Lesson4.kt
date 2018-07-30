package com.betterzhang.learnkotlin.basicSyntax

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 上午11:55
 * Desc   : 基础语法
 */

// 这是一个单行注释

/*
    这是一个多行
    块注释
    /*
        与java不同的是, Kotlin的块注释可以嵌套
     */
 */

/**
 * 使用条件表达式
 */
fun maxOf1(a: Int, b: Int): Int {
    if (a > b)
        return a
    else
        return b
}

/**
 * 使用if作为表达式
 */
fun maxOf2(a: Int, b: Int) = if (a > b) a else b

/**
 * 使用可空值及null检测
 * 使用返回可空值的函数
 */
fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct1(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用x*y会导致编译错误,因为他们可能为null
    if (x != null && y != null) {
        // 在空检测后，x和y会自动转换为非控制(non-nullable)
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

fun printProduct2(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if (x == null) {
        println("Wrong number format in arg1: '$arg1'")
        return
    }
    if (y == null) {
        println("Wrong number format in arg2: '$arg2'")
        return
    }

    // 在空检测后, x和y会自动转换为非空值
    println(x * y)
}

/**
 * 使用类型检测及自动类型转换
 */
fun getStringLength1(obj: Any): Int? {
    if (obj is String) {
        // obj在该条件分支内自动转换成String
        return obj.length
    }
    // 在离开类型检测分支后, obj仍然是Any类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null
    // obj在这一分支自动转换为String
    return obj.length
}

fun getStringLength3(obj: Any): Int? {
    // obj在&&右边自动转换成String类型
    if (obj is String && obj.length > 0) return obj.length
    return null
}


fun main(args: Array<String>) {
    // 使用字符串模板
    var a = 1
    // 模板中的简单名称
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s2)

    println("max of 0 and 42 is ${maxOf1(0, 42)}")
    println("max of 0 and 42 is ${maxOf2(0, 42)}")

    printProduct1("6", "7")
    printProduct1("a", "7")
    printProduct1("a", "b")

    printProduct2("6", "7")
    printProduct2("a", "7")
    printProduct2("99", "b")

    fun printLength1(obj: Any) {
        println("'$obj' string length is ${getStringLength1(obj) ?: "...err, not a string"}")
    }
    printLength1("Incomprehensibilities")
    printLength1(1000)
    printLength1(listOf(Any()))

    fun printLength2(obj: Any) {
        println("'$obj' string length is ${getStringLength2(obj) ?: "...err, not a string"}")
    }
    printLength2("Incomprehensibilities")
    printLength2(1000)
    printLength2(listOf(Any()))

    fun printLength3(obj: Any) {
        println("'$obj' string length is ${getStringLength3(obj) ?: "...err, is empty or not a string at all"}")
    }
    printLength3("Incomprehensibilities")
    printLength3("")
    printLength3(1000)

}