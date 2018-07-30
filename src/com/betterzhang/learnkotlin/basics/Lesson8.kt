package com.betterzhang.learnkotlin.basics

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/30 上午10:24
 * Desc   : 基本类型(数字、字符、布尔值、数组、字符串)
 */


fun main(args: Array<String>) {

    /**
     * 数字(Double、Float、Long、Int、Short、Byte)
     * 注：在Kotlin中字符不是数字
     */
    // Double -> 64
    // Float  -> 32
    // Long   -> 64
    // Int    -> 32
    // Short  -> 16
    // Byte   -> 8

    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010

    println(oneMillion)
    println(creditCardNumber)
    println(socialSecurityNumber)
    println(hexBytes)
    println(bytes)

    val a: Int = 10000
    println(a == a)                     // true
    println(a === a)                    // true
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === a)               // false
    // 没有保留同一性
    println(boxedA === anotherBoxedA)   // false
    // 保留了相等性
    println(boxedA == anotherBoxedA)    // true

    // 较小的类型不能隐式转换为较大的类型，这意味着在不进行显式转换的情况下我们不能
    // 把Byte型值赋值给一个Int型变量
    val x: Int? = 1
//    val y: Long? = x

    val b: Byte = 10     // OK, 字面值是静态检测的
//    val i: Int = b      // 错误

    // 我们可以显式转换来拓宽数字
    val i: Int = b.toInt()
    println(i)
    println(i.toLong())
    println(i.toChar())

//    每个数字类型支持如下的转换:
//    i.toByte()
//    i.toShort()
//    i.toInt()
//    i.toLong()
//    i.toFloat()
//    i.toDouble()
//    i.toChar()

    // 缺乏隐式类型转换并不显著，因为类型会从上下文推断出来，而算术运算会有重载做适当转换
    val l = 1L + 3      // Long + Int = Long
    println("l is $l, ${l is Long}")

    val z = (1 shl 2) and 0x000FFF000
    println(z)

    // 这是完整的位运算列表（只用于 Int 和 Long）
//    shl(bits) – 有符号左移 (Java 的 <<)
//    shr(bits) – 有符号右移 (Java 的 >>)
//    ushr(bits) – 无符号右移 (Java 的 >>>)
//    and(bits) – 位与
//    or(bits) – 位或
//    xor(bits) – 位异或
//    inv() – 位非

    println(Double.NaN == Double.NaN)   // false
    println(-0.0 == 0.0)                // true

    // 字符
    println("\\")
    println("\'")
    println("\$")

    println(decimalDigitValue('8'))

    // 布尔类型
    val b1 = true
    val b2 = false
    val b3: Boolean?

    // 数组
    val arr1: IntArray = intArrayOf(1, 2, 3)
    val arr2: Array<Int> = arrayOf(1, 2, 3)
    println(arr1.joinToString())
    println(arr2.joinToString())
    arr1[0] = arr1[1] + arr1[2]
    for (i in arr1)
        print(i)
    println()

    val arr3 = arrayOfNulls<Int>(3)
    for (i in arr3)
        print(i)
    println()

    val arr4 = Array(5) { i -> (i * i).toString() }
    arr4.forEach { println(it) }

    // 字符串
    val str = "abcd"
    for (s in str)
        println(s)

    val s = "abc" + 1
    println(s + "def")

    // 字符串字面值
    val s1 = "Hello, world!\n"
    println(s1)

    val text1 = """
        for (c in "foo")
            print(c)
    """.trimIndent()
    println(text1)

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text2)

    val text3 = """
        >Tell me and I forget.
        >Teach me and I remember.
        >Involve me and I learn.
        >(Benjamin Franklin)
    """.trimMargin(">")
    println(text3)

    // 字符串模板
    val i1 = 10
    println("i1 = $i1")
    val s3 = "abc"
    println("${s3}.length is ${s3.length}")

    // 原始字符串和转义字符串内部都支持模板。
    // 如果你需要在原始字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：
    val price = """
        ${'$'}9.99
    """.trimIndent()
    println(price)

}

fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt()      // 显示转换为数字
}
