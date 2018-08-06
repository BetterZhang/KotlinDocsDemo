package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/06 下午4:03
 * Desc   : 接口
 */

/**
 *  Kotlin 的接口与 Java 8 类似，既包含抽象方法的声明，也包含实现。
 *  与抽象类不同的是，接口无法保存状态。它可以有属性但必须声明为抽象或提供访问器实现
 */
// 定义接口
interface MyInterface1 {
    fun bar()
    fun foo() {
        // 可选的方法体
    }
}

// 实现接口
class Child1 : MyInterface1 {
    override fun bar() {
        // 方法体
    }
}

// 接口中的属性
// 在接口中声明的属性要么是抽象的，要么提供访问器的实现
// 在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们
interface MyInterface2 {
    val prop: Int       // 抽象的
    val propWithImplementation: String
    get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child2 : MyInterface2 {
    override val prop: Int = 29
}

// 接口继承
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String
        get() = "$firstName $lastName"
}

data class Employee(
    // 不必实现'name'
    override val firstName: String,
    override val lastName: String,
    val position: Child1
) : Person

// 解决覆盖冲突
interface E {
    fun foo() {
        print("E")
    }
    fun bar()
}

interface F {
    fun foo() {
        print("F")
    }
    fun bar() {
        print("bar")
    }
}

class G : E {
    override fun bar() {
        print("bar")
    }
}

class H : E, F {
    override fun foo() {
        super<E>.foo()
        super<F>.foo()
    }
    override fun bar() {
        super<F>.bar()
    }
}

fun main(args: Array<String>) {
    val h = H()
    h.foo()
    println()
    h.bar()
}