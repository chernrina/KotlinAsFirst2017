@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        age % 10 in 5..9 || age % 10 == 0 || age / 10 == 1
                || age / 10 == 11 -> age.toString() + " лет"
        age % 10 == 1 -> age.toString() + " год"
        else -> age.toString() + " года"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = v1 * t1
    val s2 = v2 * t2
    val s3 = v3 * t3
    val s = (s1 + s2 + s3) / 2
    return when {
        s < s1 -> s / v1
        s == s1 -> t1
        s > s1 && s < s1 + s2 -> (s - s1) / v2 + t1
        s == s1 + s2 -> t1 + t2
        else -> (s - s1 - s2) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1 = rookX1 == kingX || rookY1 == kingY
    val rook2 = rookX2 == kingX || rookY2 == kingY
    val rook3 = rook1 && rook2
    return when {
        rook1 && !rook2 -> 1
        rook2 && !rook1 -> 2
        rook3 -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val rook = rookX == kingX || rookY == kingY
    val bishop = abs(kingX - bishopX) == abs(kingY - bishopY)
    return when {
        rook && !bishop -> 1
        bishop && !rook -> 2
        rook && bishop -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var bigside = a
    var side2 = b
    var side3 = c
    if (side2 > bigside && side2 > side3) {
        val time = bigside
        bigside = side2
        side2 = time
    }
    if (side3 > bigside && side3 > side2) {
        val time2 = bigside
        bigside = side3
        side3 = time2
    }
    val cos = (side2 * side2 + side3 * side3 - bigside * bigside) /
            2 * side2 * side3
    return when {
        bigside > (side2 + side3) -> -1
        cos == 0.0 -> 1
        cos < 0 -> 2
        else -> 0
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        c in a..b && d >= b -> b - c
    // расположение точек на оси a, c, b, d || a, c, b and d
        a >= c && b <= d -> b - a
    // расположение точек на оси a and c, b and d || c, a, b, d || c and a, b, d
    // || c, a, b and d
        a in c..d && b >= d -> d - a
    // расположение точек на оси c, a, d and b || c, a, d, b
        c >= a && d <= b -> d - c
    // расположение точек на оси a, c, d, b || a and c, d, b || a, c, d and b
    // || a and c, d and b
        else -> -1
    }
}