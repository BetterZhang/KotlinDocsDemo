package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/15 上午10:24
 * Desc   : 操作符重载
 */

fun main(args: Array<String>) {

    // 一元操作
    // 一元前缀操作符
    var a = 10
    a.unaryPlus()       // +a
    a.unaryMinus()      // -a
    println(-a)

    var b = true
    b.not()             // !b
    println(!b)         // false

    // 这个表是说，当编译器处理例如表达式 +a 时，它执行以下步骤：

    // ——确定 a 的类型，令其为 T；
    // ——为接收者 T 查找一个带有 operator 修饰符的无参函数 unaryPlus（），即成员函数或扩展函数；
    // ——如果函数不存在或不明确，则导致编译错误；
    // ——如果函数存在且其返回类型为 R，那就表达式 +a 具有类型 R；

    val point = Point(10, 20)
    println(-point)

    // 递增与递减
    a = a.inc()             // a++
    a = a.dec()             // a--
    println(a)

    // 二元操作符
    // 算术运算符
    var c = 5
    a.plus(c)               // a + c
    a.minus(c)              // a - c
    a.times(c)              // a * c
    a.div(c)                // a / c
    a.rem(c)                // a % c
    a.mod(c)                // a % c (已弃用)
    a.rangeTo(c)            // a..c


    val counter = Counter(10)
    println(counter + 5)

    // "In"操作符
    val str1 = "a"
    val str2 = "abc"

    str2.contains(str1)         // str1 in str2
    !str2.contains(str1)        // str1 !in str2

    // 索引访问操作符
    val arr = intArrayOf(1, 2, 3, 4)
    arr[1]                      // arr.get(1)
//    arr[i, j]                   // arr.get(i, j)
    arr[2] = 10                 // arr.set(2, 10)
//    arr[i, j] = 20              // arr.set(i, j, 20)

    // 调用操作符
//    a()                         // a.invoke()
//    a(i)                        // a.invoke(i)
//    a(i, j)                     // a.invoke(i, j)
//    a(i_1, ......, i_n)         // a.invoke(i_1, ......, i_n)

    // 广义赋值
    a += c
    println(a)
    a -= c
    a *= c
    a /= c
    a %= c

    // 相等与不等操作符
    a == c              // a?.equals(c) ?: (c === null)
    a != c              // !(a?.equals(b) ?: (b === null))

    println(null == null)
    println(null === null)

    val x: Any? = null
    println(x == null)
    println(x === null)

    // 比较操作符
    a > c               // a.compareTo(c) > 0
    a < c               // a.compareTo(c) < 0
    a >= c              // a.compareTo(c) >= 0
    a <= c              // a.compareTo(c) <= 0

    // 属性委托操作符
    // provideDelegate、getValue()、setValue()

}

// 以下是如何重载一元减运算符的示例：
data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

// 下面是一个从给定值起始的 Counter 类的示例，它可以使用重载的 + 运算符来增加计数：
data class Counter(val dayIndex: Int) {
    operator fun plus(increment: Int): Counter {
        return Counter(dayIndex + increment)
    }
}