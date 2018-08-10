package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/10 下午4:00
 * Desc   : 对象表达式与对象声明
 */

// 有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。
// Java 用匿名内部类 处理这种情况。 Kotlin 用对象表达式和对象声明对这个概念稍微概括了下

// 对象表达式
//window.addMouseListener(object : MouseAdapter() {
//    override fun mouseClicked(e: MouseEvent) { …… }
//
//    override fun mouseEntered(e: MouseEvent) { …… }
//})


// 如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。
// 多个超类型可以由跟在冒号后面的逗号分隔的列表指定
open class A1(x: Int) {
    public open val y: Int = x
}

interface B1 {}

val ab: A1 = object : A1(1), B1 {
    override val y = 15
}

// 对象声明
// 单例模式在一些场景中很有用， 而 Kotlin（继 Scala 之后）使单例声明变得很容易
object DataProviderManager {
//    fun registerDataProvider(provider: DataProvider) {
//        // ……
//    }
//
//    val allDataProviders: Collection<DataProvider>
//        get() = // ……
}

// 伴生对象
// 类内部的对象声明可以用 companion 关键字标记
class MyClass1 {
    companion object Factory {
        fun create(): MyClass1 = MyClass1()
    }
}

// 可以省略伴生对象的名称，在这种情况下将使用名称 Companion
class MyClass2 {
    companion object {}
}

interface Factory<T> {
    fun create(): T
}

class MyClass3 {
    companion object : Factory<MyClass3> {
        override fun create(): MyClass3 = MyClass3()
    }
}

fun main(args: Array<String>) {
    // 该伴生对象的成员可通过只使用类名作为限定符来调用
    val instance = MyClass1.create()

    val instance2 = MyClass2.Companion
}

//对象表达式和对象声明之间的语义差异
//对象表达式和对象声明之间有一个重要的语义差别：
//
//——对象表达式是在使用他们的地方立即执行（及初始化）的；
//——对象声明是在第一次被访问到时延迟初始化的；
//——伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。