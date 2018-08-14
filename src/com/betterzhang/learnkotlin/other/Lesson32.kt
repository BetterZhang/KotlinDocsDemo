package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/14 下午3:25
 * Desc   : 类型检查与转换
 */

// is 与 !is 操作符
// 我们可以在运行时通过使用 is 操作符或其否定形式 !is 来检查对象是否符合给定类型：
fun main(args: Array<String>) {
    val str: Any
    str = "abc"
    if (str is String)
        println(str.length)

    if (str !is String)
        println("Not a String")
    else
        println(str.length)

    // 智能转换
    fun demo(x: Any) {
        if (x is String)
            println(x.length)   // x自动转换成字符串
    }

    demo("Hello")

    // 编译器足够聪明，能够知道如果反向检查导致返回那么该转换是安全的：
    if (str !is String) return
    println(str.length)

    // 或者在 && 和 || 的右侧：
    // `||` 右侧的 x 自动转换为字符串
    if (str !is String || str.length == 0) return
    // `&&` 右侧的 x 自动转换为字符串
    if (str is String && str.length > 0)
        println(str)

    val x: Any
//    x = 10
    x = intArrayOf(1, 2, 3)
    when (x) {
        is Int -> println(x + 1)
        is String -> println(x.length + 1)
        is IntArray -> println(x.sum())
    }

    // "不安全的"转换操作符
    // 通常，如果转换是不可能的，转换操作符会抛出一个异常。因此，我们称之为不安全的。
    // Kotlin 中的不安全转换由中缀操作符 as（参见operator precedence）完成
//    val y: String = x as String
    val z: Any? = null
    val y: String? = z as String?
    println(y)      // "null"

    // "安全的"（可空）转换操作符
    // 为了避免抛出异常，可以使用安全转换操作符 as?，它可以在失败时返回 null：
    // 请注意，尽管事实上 as? 的右边是一个非空类型的 String，但是其转换的结果是可空的
    val m: String? = x as? String
    println(m)

    // 类型擦除与泛型检测
    // 为此，编译器会禁止由于类型擦除而无法执行的 is 检测，
    // 例如 ints is List<Int> 或者 list is T（类型参数）。
    // 当然，你可以对一个实例检测星投影的类型：
//    if (something is List<*>) {
//        something.forEach { println(it) }     // 这些项的类型都是 `Any?
//    }

    fun handleStrings(list: List<String>) {
        if (list is ArrayList) {
            // list会智能转换为ArrayList<String>
        }
    }

}