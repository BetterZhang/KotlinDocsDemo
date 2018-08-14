package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/14 下午2:36
 * Desc   : 集合
 */

// 集合：List、Set、Map
// 与大多数语言不同，Kotlin 区分可变集合与不可变集合（lists、sets、maps 等）。
// 精确控制什么时候集合可编辑有助于消除 bug 以及设计良好的 API。
/*
    Kotlin 的 List<out T> 类型是一个提供只读操作如 size、get等的接口。
    与 Java 类似，它继承自 Collection<T> 进而继承自 Iterable<T>。
    改变 list 的方法是由 MutableList<T> 加入的。
    这一模式同样适用于 Set<out T>/MutableSet<T> 及 Map<K, out V>/MutableMap<K, V>
*/

/*
    Kotlin 没有专门的语法结构创建 list 或 set。
    要用标准库的方法，如 listOf()、 mutableListOf()、 setOf()、 mutableSetOf()。
    在非性能关键代码中创建 map 可以用一个简单的惯用法来完成：mapOf(a to b, c to d)
 */

// 有时你想给调用者返回一个集合在某个特定时间的一个快照, 一个保证不会变的
class Controller {
    private val _items = mutableListOf<String>()
    // 这个 toList 扩展方法只是复制列表项，因此返回的 list 保证永远不会改变
    val items: List<String> get() = _items.toList()
}


fun main(args: Array<String>) {
    //  list 及 set 类型的基本用法
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    val readOnlyView: List<Int> = numbers

    println(numbers)
    println(readOnlyView)
    numbers.add(4)
    println(numbers)
    println(readOnlyView)

    numbers.clear()
    println(numbers)
    println(readOnlyView)
//    readOnlyView.clear()  // -> 不能编译

    val strings = hashSetOf("a", "b", "c", "c")
    println(strings)
    println(strings.size)
    assert(strings.size == 3)

    // 注意上面的 readOnlyView 变量（译者注：与对应可变集合变量 numbers）指向相同的底层 list 并会随之改变。
    // 如果一个 list 只存在只读引用，我们可以考虑该集合完全不可变。创建一个这样的集合的一个简单方式如下：
    val items = listOf(1, 2, 3)

    // List 与 set 有很多有用的扩展方法值得熟悉
    val items2 = listOf(1, 2, 3, 4)
    println(items2.first() == 1)
    println(items2.last() == 4)

    items2.filter { it % 2 == 0 }.forEach { println(it) }
    println(items2)

    val rwList = mutableListOf(1, 2, 3)
    rwList.requireNoNulls()
    println(rwList)

    if (rwList.none { it > 6 }) println("No items above 6")
    val item3 = rwList.firstOrNull()

    println(item3)

    // Map 遵循同样模式。它们可以容易地实例化与访问
    val readWriteMap = hashMapOf("foo" to 1, "bar" to 2)
    println(readWriteMap["foo"])
    val snapshot: Map<String, Int> = HashMap(readWriteMap)
    println(readWriteMap)
    println(snapshot)

}