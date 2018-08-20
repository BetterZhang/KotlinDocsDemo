package com.betterzhang.learnkotlin.other

import java.io.File

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/20 下午4:34
 * Desc   : 类型别名
 */

/*
    类型别名为现有类型提供替代名称。 如果类型名称太长，你可以另外引入较短的名称，
    并使用新的名称替代原类型名。
 */

// 它有助于缩短较长的泛型类型。 例如，通常缩减集合类型是很有吸引力的：
// typealias NodeSet = Set<NetWork.Node>
typealias FileTable<K> = MutableMap<K, MutableList<File>>

// 你可以为函数类型提供另外的别名：
typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(42)

// 你可以为内部类和嵌套类创建新名称：
class AA {
    inner class Inner
}

class BB {
    inner class Inner
}

typealias AInner = AA.Inner
typealias BInner = BB.Inner

fun main(args: Array<String>) {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f))

    val p: Predicate<Int> = { it > 0 }
    println(listOf(-1, 2).filter(p))
}
