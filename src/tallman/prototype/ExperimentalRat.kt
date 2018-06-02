package tallman.prototype

import tallman.data.CoordinateCounter
import tallman.data.toCoordinateCounter

interface ExperimentalRat {
    val position: Pair<Int, Int>
    fun findFood(index: Int = 0, memoryPath: MutableList<CoordinateCounter> =
        mutableListOf(position.toCoordinateCounter())): Boolean
}
