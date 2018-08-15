package com.betterzhang.learnkotlin.other

import com.betterzhang.learnkotlin.basicSyntax.parseInt

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/15 下午3:45
 * Desc   : 异常
 */

// 异常类
// Kotlin 中所有异常类都是 Throwable 类的子孙类。 每个异常都有消息、堆栈回溯信息以及可选的原因
fun main(args: Array<String>) {

//    throw Exception("Hi There!")

    try {
        throw Exception("Hi There!")
    } catch (e: Exception) {
        println(e.message)
    } finally {
        println("finally")
    }
    // 可以有零到多个 catch 块。finally 块可以省略。 但是 catch 与 finally 块至少应该存在一个。

    // try 是一个表达式
    // try 是一个表达式，即它可以有一个返回值：
    // try-表达式的返回值是 try 块中的最后一个表达式或者是（所有）catch 块中的最后一个表达式。
    // finally 块中的内容不会影响表达式的结果。
    val a: Int? = try {
//        parseInt("a")
        parseInt("123")
    } catch (e: Exception) {
        10
    } catch (e: NumberFormatException) {
        null
    } finally {
        30
    }

    println(a)

    // Nothing 类型
    // 在 Kotlin 中 throw 是表达式，所以你可以使用它（比如）作为 Elvis 表达式的一部分：
//    val s = person.name ?: throw IllegalArgumentException("Name required")

    // throw 表达式的类型是特殊类型 Nothing。 该类型没有值，而是用于标记永远不能达到的代码位置。
    // 在你自己的代码中，你可以使用 Nothing 来标记一个永远不会返回的函数：
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    // 当你调用该函数时，编译器会知道执行不会超出该调用
//    val s = person.name ?: fail("Name required")
//    println(s)

    // 可能会遇到这个类型的另一种情况是类型推断。这个类型的可空变体 Nothing? 有一个可能的值是 null。
    // 如果用 null 来初始化一个要推断类型的值，而又没有其他信息可用于确定更具体的类型时，
    // 编译器会推断出 Nothing? 类型：
    val x = null                            // x具有类型，Nothing?
    val l = listOf(null)      // l具有类型List<Nothing?>

}