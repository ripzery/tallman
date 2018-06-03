package tallman.prototype

import tallman.data.PathCounter
import tallman.data.toPathCounter

interface SmartRat {
    val position: Pair<Int, Int>
    val map: Array<IntArray>
    val food: Pair<Int, Int>
    val allPossiblePaths: MutableList<MutableList<PathCounter>>

    fun findFood(index: Int = 0, historyPath: MutableList<PathCounter> =
        mutableListOf(position.toPathCounter())): Boolean
    fun MutableList<PathCounter>.isPossiblePath(path: PathCounter): Boolean
    fun MutableList<PathCounter>.speakPathTraced()
}
