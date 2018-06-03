package tallman.prototype

import tallman.data.PathCounter

interface SmartRat {
    val map: Array<IntArray>
    val food: Pair<Int, Int>

    fun MutableList<PathCounter>.isPossiblePath(path: PathCounter): Boolean
    fun MutableList<PathCounter>.speakPathTraced()
}
