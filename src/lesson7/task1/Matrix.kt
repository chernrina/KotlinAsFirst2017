@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    val result = MatrixImpl<E>(height, width)
    for (row in 0 until height) {
        for (column in 0 until width) {
            result[row, column] = e
        }
    }
    return result
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int) : Matrix<E> {
    private val map = mutableMapOf<Cell, E>()

    init {
        if (height <= 0 || width <= 0) throw IllegalArgumentException("MatrixImpl")
    }

    override fun get(row: Int, column: Int): E {
        val cell = Cell(row, column)
        return get(cell)
    }

    override fun get(cell: Cell): E {
        if (cell.row !in 0 until height || cell.column !in 0 until width)
            throw IndexOutOfBoundsException("MatrixImpl")
        return map[cell] ?: throw IllegalArgumentException("MatrixImpl")
    }

    override fun set(row: Int, column: Int, value: E) {
        val cell = Cell(row, column)
        return set(cell, value)
    }

    override fun set(cell: Cell, value: E) {
        if (cell.row !in 0 until height || cell.column !in 0 until width)
            throw IndexOutOfBoundsException("MatrixImpl")
        map[cell] = value
    }

    override fun equals(other: Any?): Boolean = other is MatrixImpl<*> &&
            height == other.height && width == other.width && map == other.map


    override fun toString(): String {
        val str = StringBuilder()
        str.append("[")
        for (row in 0 until height) {
            str.append("[")
            for (column in 0 until width) {
                str.append(map[Cell(row, column)])
                str.append(", ")
            }
            str.append("]")
        }
        str.append("]")
        return str.toString()
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + map.hashCode()
        return result
    }
}

