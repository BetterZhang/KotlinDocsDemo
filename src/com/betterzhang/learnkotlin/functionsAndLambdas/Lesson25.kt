package com.betterzhang.learnkotlin.functionsAndLambdas


/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/13 下午3:01
 * Desc   : 函数
 */

fun double(x: Int): Int {
    return 2 * x
}

// 默认参数
// 默认值通过类型后面的 = 及给出的值来定义
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

}

// 覆盖方法总是使用与基类型方法相同的默认参数值。
// 当覆盖一个带有默认参数值的方法时，必须从签名中省略默认参数值
open class A {
    open fun foo(i: Int = 10) {
        println(i)
    }
}

class B : A() {
    // 不能有默认值
    override fun foo(i: Int) {
        super.foo(i)
    }
}

// 如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用命名参数调用该函数来使用
fun foo(bar: Int = 0, baz: Int) {
    println("$bar -> $baz")
}

// 不过如果最后一个 lambda 表达式参数从括号外传给函数函数调用，那么允许默认参数不传值
fun foo2(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
    println("$bar -> $baz")
}

// 命名参数
// 可以在调用函数时使用命名的函数参数。当一个函数有大量的参数或默认参数时这会非常方便
fun reformat(str: String, normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {

}

// 可以通过使用星号操作符将可变数量参数（vararg） 以命名形式传入
fun foo3(vararg strings: String) {

}

// 返回 Unit 的函数
fun printHello1(name: String?): Unit {
    if (name != null)
        println("Hello $name")
    else
        println("Hi there!")

//    return        可选的
//    return Unit   可选的
}

fun printHello(str: String?) {

}

// 单表达式函数
fun double2(x: Int): Int = 2 * x
// 当返回值类型可由编译器推断时，显式声明返回类型是可选的
fun double3(x: Int) = 2 * x

// 可变数量的参数(Varargs)
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts)   // ts is an Array
        result.add(t)
    return result
}

// 中缀表示法
// 标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。
// 中缀函数必须满足以下要求：
// ——它们必须是成员函数或扩展函数；
// ——它们必须只有一个参数；
// ——其参数不得接受可变数量的参数且不能有默认值。
infix fun Int.shl(x: Int): Int {
    return 0
}

// 请注意，中缀函数总是要求指定接收者与参数。当使用中缀表示法在当前接收者上调用方法时，需要显式使用 this；
// 不能像常规方法调用那样省略。这是确保非模糊解析所必需的
class MyStringCollection {
    infix fun add(s: String) {}

    fun build() {
        this add "abc"  // 正确
        add("abc")      // 正确
//        add "abc"       // 错误: 必须指定接收者
    }
}

// 函数作用域
// 局部函数
// Kotlin 支持局部函数，即一个函数在另一个函数内部

// 成员函数
// 成员函数是在类或对象内部定义的函数
class Sample {
    fun foo() { println("foo") }
}

// 泛型函数
fun <T> singletonList(item: T): List<T> {
    return emptyList()
}

// 尾递归函数
// Kotlin 支持一种称为尾递归的函数式编程风格。
// 这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。
// 当一个函数用 tailrec 修饰符标记并满足所需的形式时，编译器会优化该递归，
// 留下一个快速而高效的基于循环的版本
tailrec fun findFixPoint(x: Double = 1.0): Double =
        if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

// 最终代码相当于这种更传统风格的代码
fun findFixPoint2(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (x == y) return x
        x = y
    }
}

fun main(args: Array<String>) {
    val result = double(2)
    println(result)

    A().foo()
    B().foo()

    foo(baz = 3)

    foo2(1) { println("hello") }
    foo2 { println("hello") }
    foo2(baz = 3) { println("hello") }

    reformat("hello")
    reformat("hello", true, true, false, '_')
    // 并且如果我们不需要所有的参数
    reformat("hello", wordSeparator = '_')

    foo3(strings = *arrayOf("a", "b", "c"))

    val list = asList(1, 2, 3)
    println(list)
    val a = arrayOf(1, 2, 3)
    // 我们使用伸展（spread）操作符（在数组前面加 *）
    val list2 = asList(-1, 0, *a, 4)
    println(list2)

    // 用中缀表示法调用该函数
    1 shl 2
    // 等同于这样
    1.shl(2)

    // 成员函数以点表示法调用
    Sample().foo()  // 创建类 Sample 实例并调用 foo

    println(findFixPoint())
    println(findFixPoint2())
}