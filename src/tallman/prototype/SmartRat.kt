package tallman.prototype

import tallman.data.PathCounter

interface SmartRat {
    val map: Array<IntArray>
    val food: Pair<Int, Int>

    fun isPossiblePath(historyPath: MutableList<PathCounter>, path: PathCounter): Boolean
    fun speakPathTraced(historyPath: MutableList<PathCounter>)
}
