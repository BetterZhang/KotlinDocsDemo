package com.betterzhang.learnkotlin.other

import jdk.nashorn.internal.runtime.regexp.JoniRegExp
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/15 下午5:18
 * Desc   : 反射
 */

/*
    反射是这样的一组语言和库功能，它允许在运行时自省你的程序的结构。
    Kotlin 让语言中的函数和属性做为一等公民、并对其自省（即在运行时获悉一个名称或者一个属性或函数的类型）
    与简单地使用函数式或响应式风格紧密相关。
 */

class MyClass

class Foo2

val x = 1
var y = 2

val String.lastChar: Char
    get() = this[length - 1]

fun main(args: Array<String>) {

    // 类引用
    val c = MyClass::class      // 该引用是 KClass 类型的值
    println(c)
    // 请注意，Kotlin 类引用与 Java 类引用不同。
    // 要获得 Java 类引用， 请在 KClass 实例上使用 .java 属性。

    // 可调用引用
    // 函数引用
    fun isOdd(x: Int) = x % 2 != 0

    println(isOdd(5))

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))
    // 这里 ::isOdd 是函数类型 (Int) -> Boolean 的一个值。

    fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

    println(numbers.filter(::isOdd))

    // 属性引用
    // 要把属性作为 Kotlin中 的一等对象来访问，我们也可以使用 :: 运算符：
    println(::x.get())
    println(::x.name)

    println(::y.set(5))
    println(y)

    // 属性引用可以用在不需要参数的函数处：
    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length))

    // 要访问属于类的成员的属性，我们这样限定它：
    class A(val p: Int)
    val prop = A::p
    println(prop.get(A(1)))

    // 对于扩展属性：
    println(String::lastChar.get("world"))

    // 与 Java 反射的互操作性
    println(A::p.javaGetter)
    println(A::p.javaField)

    // 构造函数引用
    fun function(factory: () -> Foo2) {
        val x: Foo2 = factory()
    }

    function(::Foo2)


    // 绑定的函数与属性引用
    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29"))

    val isNumber = numberRegex::matches
    println(isNumber("29"))

    val strings = listOf("abc", "124", "a70")
    println(strings.filter(numberRegex::matches))

    val isNumber2: (CharSequence) -> Boolean = numberRegex::matches
    val matches: (Regex, CharSequence) -> Boolean = Regex::matches

    // 属性引用也可以绑定
    val props = "abc"::length
    println(props.get())

    // 自 Kotlin 1.2 起，无需显式指定 this 作为接收者：this::foo 与 ::foo 是等价的。
    // inner 类的构造函数的绑定的可调用引用可通过提供外部类的实例来获得
    val o = Outer()
    val boundInnerCtor = o::Inner

}

class Outer {
    inner class Inner
}