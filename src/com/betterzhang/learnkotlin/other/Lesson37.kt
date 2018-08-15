package com.betterzhang.learnkotlin.other

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/15 下午4:39
 * Desc   : 注解
 */

// 注解声明

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Fancy

// 用法
@Fancy class Foo {
    @Fancy fun baz(@Fancy foo: Int): Int {
        return (@Fancy 1)
    }
}

// 如果需要对类的主构造函数进行标注，
// 则需要在构造函数声明中添加 constructor 关键字 ，并将注解添加到其前面：
// class Foo @Inject constructor(dependency: MyDependency) {}

// 你也可以标注属性访问器：
//class Foo2 {
//    val x: MyDependency? = null
//        @Inject set
//}

