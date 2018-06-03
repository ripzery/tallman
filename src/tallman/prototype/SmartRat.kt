package tallman.prototype

import tallman.data.CoordinateCounter

interface SmartRat {
    val map: Array<IntArray>
    val food: Pair<Int, Int>

    fun isPossiblePath(memoryPath: MutableList<CoordinateCounter>, path: CoordinateCounter): Boolean
    fun speakPathTraced(passedPath: MutableList<CoordinateCounter>)
}
