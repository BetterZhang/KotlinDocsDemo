package com.betterzhang.learnkotlin.Idioms

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/27 下午3:18
 * Desc   : 习惯用法
 */

// 创建DTOs(POJOs/POCOs)
// 会为Customer类提供以下功能:
// ---所有属性的getters(对于var定义的还有setter)
// ---equals()
// ---hashCode()
// ---toString()
// ---copy()
// ---所有属性的component1()、component2()......等等

// 创建DTOs(POJOs/POCOs)
data class Customer(val name: String, val email: String)
data class User(val name: String, val age: Int)

// 函数的默认参数
fun foo(a: Int = 0, b: String = "") {
    println("a is $a and b is $b")
}

// 过滤list
val list = listOf(3, 1, 5, 2)
val positives1 = list.filter { x -> x > 2 }
val positives2 = list.filter { it > 2 }

fun test(x: Any): String {
    when(x) {
        is User -> return "User"
        is Customer -> return "Customer"
        else -> return "Unknown"
    }
}

// 创建单例
object Resource {
    val name = "Name"
}

/**
 * 返回when表达式
 */
fun transform1(color: String): Int {
    return when (color) {
        "Red" -> 1
        "Green" -> 2
        "Blue" -> 3
        else -> throw IllegalStateException("Invalid color param value")
    }
}

// 单表达式函数与其它惯用法一起使用能简化代码，例如和 when 表达式一起使用：
fun transform2(color: String): Int = when (color) {
    "Red" -> 1
    "Green" -> 2
    "Blue" -> 3
    else -> throw IllegalStateException("Invalid color param value")
}

/**
 * try/catch表达式
 */
fun test() {
    val result = try {
        count()
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
    // 使用result
    println(result)
}

fun count(): Int {
    return 10 / 2
//    return 10 / 0
}

/**
 * if表达式
 */
fun foo(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
    println(result)
}

/**
 * 返回类型为Unit的方法的Builder风格写法
 */
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

/**
 * 单表达式函数
 */
fun theAnswer1() = 42
// 等价于
fun theAnswer2(): Int {
    return 42
}


/**
 * 对一个对象实例调用多个方法（with）
 */
class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}



fun main(args: Array<String>) {
    foo()
    foo(5, "Hello")

    for (item in positives1) {
        print(item)
    }
    println()
    for (item in positives2) {
        print(item)
    }
    println()

    // String内插
    val name = "xiaoming"
    println("Name $name")

    // 类型判断
    val user = User("John", 21)
    val customer = Customer("Lucy", "Lucy@gmail.com")
    println(test(user))
    println(test(customer))

    // 遍历map/pair型list
    val map = hashMapOf<String, String>()
    map.put("one", "1")
    map.put("two", "2")
    map["one"] = "4"
    for ((k, v) in map) {
        println("$k -> $v")
    }

    // 使用区间
    for (i in 1..100) {
    }        // 闭区间:包含100
    for (i in 1 until 100) {
    }   // 半开区间:不包含100
    for (x in 2..10 step 2) {
    }
    for (x in 10 downTo 1) {
    }

    val x = 20
    if (x in 1..10) {
        println("x fits the range")
    } else {
        println("x is not fit the range")
    }

    // 只读list
    val list = listOf("a", "b", "c")
    // 只读map
    val map2 = mapOf("a" to 1, "b" to 2, "c" to 3)
    // 访问map
    println(map2["a"])
    for ((k, v) in map2) {
        println("$k -> $v")
    }

    // 延迟属性
    val p: String by lazy {
        return@lazy "Hello"
    }
    println(p)

    // 扩展函数
    fun String.spaceToCamelCase(): String {
        return this.toUpperCase()
    }
    println("Convert this to camelcase".spaceToCamelCase())

    val files = File("Test").listFiles()
    // if not null缩写
    println(files?.size)
    // if not null and else缩写
    println(files?.size ?: "empty")
    // if null执行一个语句
    val data = hashMapOf<String, String>()
    data.put("name", "betterzhang")
    data.put("email", "betterzhag.dev@gmail.com")
    val email = data["email"] ?: throw IllegalStateException("Email is missing!")
    println(email)

    // 在可能为空的集合中取第一个元素
    val emails = listOf("betterzhang.dev@gmail.com", "843477497@qq.com")
//    val emails = listOf<String>()
    val mainEmail = emails.firstOrNull() ?: "First email is empty"
    println(mainEmail)

    // if not null执行代码
//    val data1: String? = "abc"
    val data1: String? = null
    data1?.let {
        // 假如data不为null, 代码会执行到此处
        println("data is not null")
    }

    // 映射可空值(如果非空的话)
    // val value = ...
    // val mapped = value?.let { transformValue(it) } ?: defaultValueIfValueIsNull

    println(transform1("Red"))
    println(transform1("Blue"))
//    println(transform1("Gray"))

    println(transform2("Green"))
//    println(transform2("Hello"))


    test()
    foo(2)

    for (item in arrayOfMinusOnes(5)) {
        println(item)
    }

    val myTurtle = Turtle()
    with(myTurtle) {
        // 画一个100像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }

    // Java 7 的try with resources
//    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
//    stream.buffered().reader().use { reader ->
//        println(reader.readText())
//    }

    // 使用可空布尔
//    val b: Boolean? = null
    val b: Boolean? = true
    if (b == true) {
        println("b is true")
    } else {
        println("b is false or null")   // b是false或者null
    }

}

// 对于需要泛型信息的泛型函数的适宜形式
//inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)