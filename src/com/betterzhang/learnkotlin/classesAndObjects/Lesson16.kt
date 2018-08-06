package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/06 下午5:07
 * Desc   : 扩展
 */

// 扩展函数
// 声明一个扩展函数，我们需要用一个 接收者类型 也就是被扩展的类型来作为他的前缀
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    // this对应该列表
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main(args: Array<String>) {
    val l = mutableListOf(1, 2, 3)
    println(l)
    l.swap(0, 2)    // swap内部的this得到l的值
    println(l)

    val s = mutableListOf("a", "b", "c")
    println(s)
    s.swap2(0, 2)
    println(s)

    printFoo(DD())

    val ee: EE = EE()
    ee.foo()
    ee.foo(1)

    MyClass.foo()
}

fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

// 扩展是静态解析的
open class CC

class DD : CC()

fun CC.foo() = "c"
fun DD.foo() = "d"

fun printFoo(cc: CC) {
    println(cc.foo())
}

// 如果一个类定义有一个成员函数和一个扩展函数，
// 而这两个函数又有相同的接收者类型、相同的名字并且都适用给定的参数，
// 这种情况总是取成员函数
class EE {
    fun foo() {
        println("member")
    }
}

fun EE.foo() {
    println("extension")
}

fun EE.foo(i: Int) {
    println("extension")
}

// 可空接收者
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

// 扩展属性
val <T> List<T>.lastIndex: Int
    get() = size - 1

//val Foo.bar = 1     // 错误；扩展属性不能有初始化器

// 伴生对象的扩展
class MyClass {
    // 将被称为 "Companion"
    companion object {}
}

fun MyClass.Companion.foo() {
    println("MyClass.Companion")
}

// 扩展作用域
