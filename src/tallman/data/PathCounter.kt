package tallman.data

import java.util.Objects

data class PathCounter(val x: Int, val y: Int, val counter: Int) {
    fun left() = PathCounter(x, y - 1, counter + 1)
    fun right() = PathCounter(x, y + 1, counter + 1)
    fun top() = PathCounter(x - 1, y, counter + 1)
    fun bottom() = PathCounter(x + 1, y, counter + 1)

    override fun equals(other: Any?): Boolean {
        return if (other is PathCounter) {
            this.x == other.x && this.y == other.y
        } else {
            false
        }
    }

    override fun hashCode(): Int = Objects.hash(this.x, this.y)

    override fun toString(): String = "($x, $y)"
}

fun Pair<Int, Int>.toPathCounter(): PathCounter = PathCounter(this.first, this.second, 0)
