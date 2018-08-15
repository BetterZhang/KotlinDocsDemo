package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/15 下午3:00
 * Desc   : 空安全
 */

fun main(args: Array<String>) {
    // 可空类型与非空类型
    // NullPointerException 或简称 NPE
    // Kotlin 的类型系统旨在从我们的代码中消除 NullPointerException

    var a: String = "abc"
//    a = null                // 编译错误

    var b: String? = "abc"
    b = null                    // ok
    println(b)

    // 现在，如果你调用 a 的方法或者访问它的属性，它保证不会导致 NPE，这样你就可以放心地使用：
    val l1 = a.length
    // 但是如果你想访问 b 的同一个属性，那么这是不安全的，并且编译器会报告一个错误：
//    val l2 = b.length           // 错误：变量“b”可能为空

    // 但是我们还是需要访问该属性，对吧？有几种方式可以做到
    val l2 = if (b != null) b.length else -1

    val d = "Kotlin"
    if (d != null && d.length > 0)
        println("String of length ${d.length}")
    else
        println("Empty string!")

    // 安全的调用
    println(d?.length)
    val e: String? = null
    println(e?.length)

    // bob?.department?.head?.name

    // 如果要只对非空值执行某个操作，安全调用操作符可以与 let 一起使用：
    val listWithNulls: List<String?> = listOf("Kotlin", null)
    for (item in listWithNulls)
        item?.let { println(it) }   // 输出 A 并忽略 null


    // 安全调用也可以出现在赋值的左侧。
    // 这样，如果调用链中的任何一个接收者为空都会跳过赋值，而右侧的表达式根本不会求值
    // 如果 `person` 或者 `person.department` 其中之一为空，都不会调用该函数：
    // person?.department?.head = managersPool.getManager()

    // Elvis 操作符
    var l: Int = if (d != null) d.length else -1
    // 除了完整的 if-表达式，这还可以通过 Elvis 操作符表达，写作 ?:
    l = d?.length ?: -1
    println(l)

    // 因为 throw 和 return 在 Kotlin 中都是表达式，所以它们也可以用在 elvis 操作符右侧。
    // 这可能会非常方便，例如，检查函数参数：
//    fun foo(node: Node): String? {
//        val parent = node.getParent() ?: return null
//        val name = node.getName() ?: throw IllegalArgumentException("name expected")
//    }

    // !! 操作符——非空断言运算符
//    l = b!!.length

    // 安全的类型转换
    // 选择是使用安全的类型转换，如果尝试转换不成功则返回 null：
    val aInt: Int? = a as? Int
    println(aInt)

    // 可空类型的集合
    // 如果你有一个可空类型元素的集合，并且想要过滤非空元素，你可以使用 filterNotNull 来实现：
    val nullableList = listOf(1, 2, null, 4)
    val intList = nullableList.filterNotNull()
    println(nullableList)
    println(intList)

}