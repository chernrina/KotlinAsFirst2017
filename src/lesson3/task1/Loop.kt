@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var number = 0
    var N = n
    do {
        number++
        N /= 10
    } while (N > 0)
    return number
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1 || n == 2) return 1
    else return fib(n - 2) + fib(n - 1)
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val max = max(m, n)
    if (m == n || m % n == 0) return m
    else if (n % m == 0) return n
    else for (i in 2..max) {
        if (n % i == 0 && m % i == 0) return m / i * n
    }
    return m * n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2 until n) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = min(m, n)
    for (i in 2..min) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val m1 = Math.sqrt(m.toDouble()).toInt()
    val n1 = Math.sqrt(n.toDouble()).toInt()
    var k = 0
    for (i in m1..n1) {
        if (i * i in m..n) {
            k++
        }
    }
    return k != 0
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var i = 1
    var f = 3.0
    var x1 = x
    while (x1 > PI * 2) {
        x1 -= PI * 2
    }
    var sin = x1
    while (abs(pow(x1, f) / factorial(f.toInt())) >= eps) {
        if (i % 2 == 1) {
            sin -= pow(x1, f) / factorial(f.toInt())
        } else sin += pow(x1, f) / factorial(f.toInt())
        i++
        f += 2
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var i = 1
    var f = 2.0
    var x1 = x
    while (x1 > PI * 2) {
        x1 -= PI * 2
    }
    var cos = 1.0
    while (abs(pow(x1, f) / factorial(f.toInt())) >= eps) {
        if (i % 2 == 1) {
            cos -= pow(x1, f) / factorial(f.toInt())
        } else cos += pow(x1, f) / factorial(f.toInt())
        i++
        f += 2
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var number = n
    var i = 0.0
    var number2 = 0.0
    while (number / 10 != 0) {
        i++
        number /= 10
    }
    var n1 = n
    while (i >= 0.0) {
        number2 += n1 % 10 * pow(10.0, i)
        i--
        n1 /= 10
    }
    return number2.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean =
        n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1 = n
    while (n1 % 10 == (n / 10) % 10 && n1 > 10) {
        n1 /= 10
    }
    return n1 > 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var sequenceLength = 0
    var i = 1
    var lengthOfSquare = 0
    var sqr = i * i
    while (sequenceLength < n) {
        while (sqr > 0) {
            sqr /= 10
            lengthOfSquare++
        }
        sequenceLength += lengthOfSquare
        i++
        lengthOfSquare = 0
        sqr = i * i
    }
    val lasti = i - 1
    var lastsqr = lasti * lasti
    if (sequenceLength == n) return lastsqr % 10
    else while (sequenceLength != n) {
        sequenceLength--
        lastsqr /= 10
    }
    return lastsqr % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var i = 1
    var number = fib(i)
    var lengthOfNumber = 0
    var sequenceLength = 0
    while (sequenceLength < n) {
        while (number > 0) {
            number /= 10
            lengthOfNumber++
        }
        sequenceLength += lengthOfNumber
        i++
        lengthOfNumber = 0
        number = fib(i)
    }
    var lastnumber = fib(i - 1)
    if (sequenceLength == n) return lastnumber % 10
    else while (sequenceLength != n) {
        sequenceLength--
        lastnumber /= 10
    }
    return lastnumber % 10
}
