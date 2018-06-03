package tallman.prototype

import tallman.data.PathCounter
import tallman.data.toPathCounter

interface ExperimentalRat {
    val position: Pair<Int, Int>
    fun findFood(index: Int = 0, historyPath: MutableList<PathCounter> =
        mutableListOf(position.toPathCounter())): Boolean
}
