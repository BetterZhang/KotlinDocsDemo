package com.betterzhang.learnkotlin.classesAndObjects

/**
 * Created by IntelliJ IDEA.
 * Author : Andrew Zhang
 * Email  : betterzhang.dev@gmail.com
 * Time   : 2018/08/09 下午5:33
 * Desc   : 密封类
 */

// 一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员
// 密封类不允许有非-private 构造函数（其构造函数默认为 private）
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}

fun main(args: Array<String>) {
    println(eval(Const(2.34)))
    println(eval(Sum(Const(2.2), Const(1.1))))
    println(eval(NotANumber))
}