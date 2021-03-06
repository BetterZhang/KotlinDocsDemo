package com.betterzhang.learnkotlin.basics

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/30 下午3:06
 * Desc   : 返回与跳转
 */

// Kotlin 有三种结构化跳转表达式：
// ---return   默认从最直接包围它的函数或者匿名函数返回
// ---break    终止最直接包围它的循环
// ---continue 继续下一次最直接包围它的循环

fun main(args: Array<String>) {

    for (i in 1..10) {
        if (i == 3)
            break
        print(i)
    }
    println()

    for (i in 1..10) {
        if (i == 5)
            continue
        print(i)
    }
    println()

    for (i in 1..5) {
        for (j in 1..5) {
            if (j == 3)
                break
            print("" + i * j)
        }
        println()
    }

    // 在Kotlin中任何表达式都可以用标签（label）来标记
    // 标签限制的break跳转到刚好位于该标签指定的循环后面的执行点
    loop@
    for (i in 1..5) {
        for (j in 1..5) {
            if (j == 3)
                break@loop
            print("" + i * j)
        }
    }
    println()

    // continue继续标签指定的循环的下一次迭代
    loop@
    for (i in 1..5) {
        for (j in 1..5) {
            if (j == 3)
                continue@loop
            print("" + i * j)
        }
        println()
    }
    println()

    val ints = arrayOf(1, 3, 5, 0, 4, 9)
    // 标签处返回
    fun foo1() {
        ints.forEach {
            if (it == 0) return
            print(it)
        }
        println("this point is unreachable")
    }
    foo1()
    println()

    // 如果我们需要从lambda表达式中返回，我们必须给它加标签并用以限制return
    fun foo2() {
        ints.forEach list@ {
            if (it == 0) return@list
            print(it)
        }
        print(" done with explicit label")
    }
    foo2()
    println()

    // 通常情况下使用隐式标签更方便，该标签与接受该lambda的函数同名
    fun foo3() {
        ints.forEach {
            if (it == 0) return@forEach
            print(it)
        }
        print(" done with implicit label")
    }
    foo3()
    println()

    // 我们用一个匿名函数替代lambda表达式，匿名函数内部的return语句将从该匿名函数自身返回
    fun foo4() {
        ints.forEach(fun(value: Int) {
            if (value == 0) return
            print(value)
        })
        print(" done with anonymous function")
    }
    foo4()
    println()

    // break 的直接等价形式
    fun foo5() {
        run loop@ {
            ints.forEach {
                if (it == 0) return@loop
                print(it)
            }
        }
        print(" done with nested loop")
    }
    foo5()
    println()

    fun foo6() {
        run {
            ints.forEach {
                if (it == 0) return@run
                print(it)
            }
        }
        print(" done with nested loop")
    }
    foo6()
    println()
}