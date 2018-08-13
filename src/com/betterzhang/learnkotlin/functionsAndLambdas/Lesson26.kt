package com.betterzhang.learnkotlin.functionsAndLambdas


/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/13 下午4:41
 * Desc   : Lambda表达式
 */

// 高阶函数
// 高阶函数是将函数用作参数或返回值的函数
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this)
        accumulator = combine(accumulator, element)
    return accumulator
}


fun main(args: Array<String>) {
    val items = listOf(1, 2, 3, 4, 5)
    // Lambdas 表达式是花括号括起来的代码块。
    items.fold(0) {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        acc: Int, i: Int ->
        println("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    }

    // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements") { acc, i -> acc + "" + i }
    // 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)
    println("joinedToString = $joinedToString")
    println("product = $product")

    // 函数类型实例调用
    val stringPlus: (String, String) -> String =  String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "World!"))
    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3))   // 类扩展调用

    val sum: (Int, Int) -> Int = { x, y -> x + y }
    // 将 lambda 表达式传给最后一个参数
    // 在 Kotlin 中有一个约定：如果函数的最后一个参数接受函数，
    // 那么作为相应参数传入的 lambda 表达式可以放在圆括号之外
    val product2 = items.fold(1) {
        acc, e -> acc * e
    }

    // 如果该 lambda 表达式是调用时唯一的参数，那么圆括号可以完全省略
    run { println("...") }

    // it：单个参数的隐式名称
    // 如果编译器自己可以识别出签名，也可以不用声明唯一的参数并忽略 ->。 该参数会隐式声明为 it
    val ints = arrayOf(1, 2, 3, -1, 0 ,9)
    ints.filter { it > 0 } // 这个字面值是“(it: Int) -> Boolean”类型的

    // 从 lambda 表达式中返回一个值
    // 我们可以使用限定的返回语法从 lambda 显式返回一个值。 否则，将隐式返回最后一个表达式的值。
    // 因此，以下两个片段是等价的：
    val i1 = ints.filter {
        val shouldFilter = it > 0
        shouldFilter
    }
    var i2 = ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }
    println(i1)
    println(i2)

    // 下划线用于未使用的变量（自 1.1 起）
    // 如果 lambda 表达式的参数未使用，那么可以用下划线取代其名称
//    ints.forEach { _, value -> println("$value") }

    // 匿名函数
    fun(x: Int, y: Int): Int = x + y

    // 匿名函数看起来非常像一个常规函数声明，除了其名称省略了。其函数体可以是表达式（如上所示）或代码块
    fun(x: Int, y: Int): Int {
        return x + y
    }

    ints.filter(fun(item) = item > 0)

    // 闭包
    // Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其 闭包 ，即在外部作用域中声明的变量。
    // 与 Java 不同的是可以修改闭包中捕获的变量：
    var sum2 = 0
    ints.filter { it > 0 } .forEach {
        sum2 += it
    }
    println(sum2)

    // 带有接收者的函数字面值
    // TODO
}