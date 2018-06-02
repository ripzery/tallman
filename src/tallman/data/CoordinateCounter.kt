package tallman.data

import java.util.Objects

data class CoordinateCounter(val x: Int, val y: Int, val counter: Int) {
    fun left() = CoordinateCounter(x, y - 1, counter + 1)
    fun right() = CoordinateCounter(x, y + 1, counter + 1)
    fun top() = CoordinateCounter(x - 1, y, counter + 1)
    fun bottom() = CoordinateCounter(x + 1, y, counter + 1)

    override fun equals(other: Any?): Boolean {
        return if (other is CoordinateCounter) {
            this.x == other.x && this.y == other.y
        } else {
            false
        }
    }

    override fun hashCode(): Int = Objects.hash(this.x, this.y)

    override fun toString(): String = "($x, $y)"
}

fun Pair<Int, Int>.toCoordinateCounter(): CoordinateCounter {
    return CoordinateCounter(this.first, this.second, 0)
}
