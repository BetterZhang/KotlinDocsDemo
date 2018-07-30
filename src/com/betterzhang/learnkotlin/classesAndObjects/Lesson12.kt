package com.betterzhang.learnkotlin.classesAndObjects

import javafx.scene.Parent

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/07/30 下午3:56
 * Desc   : 类和继承
 */

class Invoice {}

// 类声明由类名、类头（指定其类型参数、主构造函数等）以及由花括号包围的类体构成。
// 类头与类体都是可选的； 如果一个类没有类体，可以省略花括号。
class Empty

// 构造函数
class Person1 constructor(firstName: String) {}
// 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person2(firstName: String) {}

// 主构造函数不能包含任何的代码。
// 初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中。
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

}

// 请注意，主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用
class Customer1(name: String) {
    val customerKey = name.toUpperCase()
}

// 事实上，声明属性以及从主构造函数初始化属性，Kotlin 有简洁的语法
class Person3(val firstName: String, val lastName: String, var age: Int) {}

// 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
//class Customer2 public @Inject constructor(name: String) {}

// 次构造函数
// 类也可以声明前缀有 constructor的次构造函数
//class Person4 {
//    constructor(parent: Person4) {
//        parent.children.add(this)
//    }
//}

// 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，
// 可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可：
class Person5(val name: String) {
    constructor(name: String, parent: Person5) : this(name) {
//        parent.children.add(this)
    }
}

class Constructors {
    init {
        println("Init block")
    }
    constructor(i: Int) {
        println("Constructor")
    }
}

class DontCreateMe private constructor() {}

// 注意：在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值。
// 这使得 Kotlin 更易于使用像 Jackson 或者 JPA 这样的通过无参构造函数创建类的实例的库。
class Customer3(val customerName: String = "")




// 继承
// 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
class Example   // 从 Any 隐式继承

// 要声明一个显式的超类型，我们把类型放到类头的冒号之后：
// 默认情况下，在 Kotlin 中所有的类都是 final
open class Base1(p: Int)
class Derived1(p: Int) : Base1(p)


//class MyView : View {
//    constructor(ctx: Context) : super(ctx)
//    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
//}

// 覆盖方法
open class Base2 {
    open fun v() {}
    fun nv() {}
}
open class Derived2() : Base2() {
    override fun v() {
        super.v()
    }
}
// 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。
// 如果你想禁止再次覆盖，使用 final 关键字：
open class AnotherDerived() : Base2() {
    final override fun v() {
        super.v()
    }
}
class Haha1() : Derived2() {
    override fun v() {
        super.v()
    }
}
class Haha2() : AnotherDerived() {}


// 覆盖属性
//open class Foo {
//    open val x: Int get() {}
//}
//class Bar1 : Foo() {
//    override val x: Int
//        get() = super.x
//}


interface Foo {
    val count: Int
}
class Bar1(override val count: Int) : Foo
class Bar2 : Foo {
    override var count: Int = 0
}

// 派生类初始化顺序
open class Base3(val name: String) {
    init {
        println("Initializing Base")
    }
    open val size: Int = name.length.also{ println("Initializing size in Base: $it") }
}
class Derived3(
    name: String,
    val lastName: String
) : Base3(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

// 调用超类实现
// 派生类中的代码可以使用 super 关键字调用其超类的函数与属性访问器的实现
open class Foo1 {
    open fun f() {
        println("Foo.f()")
    }
    open val x: Int get() = 1
}
class Bar : Foo1() {
    override fun f() {
        super.f()
        println("Bar.f()")
    }

    override val x: Int
        get() = super.x + 1
}

// 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer
class Bar3 : Foo1() {
    override fun f() {
        super.f()
    }

    override val x: Int
        get() = 0

    inner class Baz {
        fun g() {
            super@Bar3.f()          // 调用 Foo 实现的 f()
            println(super@Bar3.x)   // 使用 Foo 实现的 x 的 getter
        }
    }
}


// 覆盖规则
// 在 Kotlin 中，实现继承由下述规则规定：如果一个类从它的直接超类继承相同成员的多个实现，
// 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）。
// 为了表示采用从哪个超类型继承的实现，我们使用由尖括号中超类型名限定的 super，如 super<Base>
open class A {
    open fun f() {
        println("A")
    }
    fun a() {
        println("a")
    }
}
interface B {
    // 接口成员默认就是open的
    fun f() {
        println("B")
    }
    fun b() {
        println("b")
    }
}
class C() : A(), B {
    // 编译器要求覆盖 f()：
    override fun f() {
        super<A>.f()    // 调用 A.f()
        super<B>.f()    // 调用 B.f()
    }
}

// 抽象类
// 我们可以用一个抽象成员覆盖一个非抽象的开放成员
open class Base4 {
    open fun f() {}
}
abstract class Derived4 : Base4() {
    override abstract fun f()
}


// 伴生对象
// 与 Java 或 C# 不同，在 Kotlin 中类没有静态方法。在大多数情况下，它建议简单地使用包级函数。

fun main(args: Array<String>) {
    // 创建类的实例
    InitOrderDemo("hello")
    Constructors(1)
    val invoice = Invoice()
    val customer1 = Customer1("Joe Smith")

    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived3("hello", "world")
}