package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/10 下午2:27
 * Desc   : 泛型
 */

class Box<T>(t: T) {
    var value = t
}

// 泛型函数
fun <T> singletonList(item: T): List<T> {
    return emptyList()
}
// 扩展函数
fun <T> T.basicToString(): String {
    return ""
}

// 泛型约束
// 能够替换给定类型参数的所有可能类型的集合可以由泛型约束限制
// 冒号之后指定的类型是上界：只有 Comparable<T> 的子类型可以替代 T
fun <T: Comparable<T>> sort(list: List<T>) {

}

fun main(args: Array<String>) {
    val box1: Box<Int> = Box<Int>(1)
    // 如果类型参数可以推断出来，例如从构造函数的参数或者从其他途径，允许省略类型参数
    val box2 = Box(1)

    val list = singletonList(1)

    sort(listOf(1, 2, 3))                   // OK。Int 是 Comparable<Int> 的子类型
//    sort(listOf(HashMap<Int, String>()))    //错误：HashMap<Int, String> 不是 Comparable<HashMap<Int, String>> 的子类型
}