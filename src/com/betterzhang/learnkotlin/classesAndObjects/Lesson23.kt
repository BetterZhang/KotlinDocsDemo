package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/10 下午4:34
 * Desc   : 委托
 */

// 委托模式已经证明是实现继承的一个很好的替代方式， 而 Kotlin 可以零样板代码地原生支持它。
// Derived 类可以通过将其所有公有成员都委托给指定对象来实现一个接口 Base
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

// --------------------------------

interface Base5 {
    fun printMessage()
    fun printMessageLine()
}

class Base5Impl(val x: Int) : Base5 {
    override fun printMessage() {
        print(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}

class Derived5(b: Base5) : Base5 by b {
    override fun printMessage() {
        print("abc")
    }
}

// -------------------------------------

interface Base6 {
    val message: String
    fun print()
}

class Base6Impl(val x: Int) : Base6 {
    override val message = "Base6Impl: x = $x"
    override fun print() {
        println(message)
    }
}

class Derived6(b: Base6) : Base6 by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message: String = "Message of Derived"
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print()
    println()

    val b2 = Base5Impl(10)
    Derived5(b2).printMessage()
    println()
    Derived5(b2).printMessageLine()

    val b3 = Base6Impl(10)
    val derived6 = Derived6(b3)
    derived6.print()
    println(derived6.message)
}