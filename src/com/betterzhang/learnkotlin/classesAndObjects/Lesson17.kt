package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/08 下午3:09
 * Desc   : 数据类
 */

data class User(val name: String, val age: Int)

// 编译器自动从主构造函数中声明的所有属性导出以下成员:
// —— equals()/hashCode() 对；
// —— toString() 格式是 "User(name=John, age=42)"；
// —— componentN() 函数 按声明顺序对应于所有属性；
// —— copy() 函数（见下文）。


// 在 JVM 中，如果生成的类需要含有一个无参的构造函数，则所有的属性必须指定默认值
data class User2(val name: String = "", val age: Int = 0)

// 在类体中声明的属性
// 对于那些自动生成的函数，编译器只使用在主构造函数内部定义的属性。
// 如需在生成的实现中排出一个属性，请将其声明在类体中
data class Person4(val name: String) {
    var age: Int = 0
}

fun main(args: Array<String>) {
    val person1 = Person4("John")
    val person2 = Person4("John")
    person1.age = 10
    person2.age = 20
    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: $person1")
    println("person2 with age ${person2.age}: $person2")

    // 复制
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    println(jack)
    println(olderJack)

    // 数据类与解构声明
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age")

}