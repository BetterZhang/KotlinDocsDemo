package com.betterzhang.learnkotlin.classesAndObjects

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/10 下午3:14
 * Desc   : 枚举类
 */

// 枚举类的最基本的用法是实现类型安全的枚举
// 每一个枚举常量都是一个对象，枚举常量用逗号分隔
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

// 初始化
// 因为每一个枚举都是枚举类的实例，所以他们可以是这样初始化过的
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 匿名类
// 枚举常量也可以声明自己的匿名类
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WAITING
    };

    // 注意，如果枚举类定义任何成员，要使用分号将成员定义中的枚举常量定义分隔开，
    // 就像在 Java 中一样。
    abstract fun signal(): ProtocolState
}

// 在枚举类中实现接口
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int) = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int) = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

// 使用枚举常量
// 就像在 Java 中一样，Kotlin 中的枚举类也有合成方法允许列出定义的枚举常量以及通过名称获取枚举常量。
// 这些方法的签名如下（假设枚举类的名称是 EnumClass）
//EnumClass.valueOf(value: String): EnumClass
//EnumClass.values(): Array<EnumClass>


// 自 Kotlin 1.1 起，可以使用 enumValues<T>()
// 与 enumValueOf<T>() 函数以泛型的方式访问枚举类中的常量
enum class RGB {
    RED, GREEN, BLUE
}

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}



fun main(args: Array<String>) {
    println(Direction.NORTH is Direction)

    println(Color.RED.rgb)
    println(Color.values().forEach { println(it) })

    val a = 13
    val b = 31
    for (f in IntArithmetics.values()) {
        println("$f($a, $b) = ${f.apply(a, b)}")
    }

    println(Color.values() is Array<Color>)
    println(Color.valueOf("GREEN"))


    printAllValues<RGB>()
}