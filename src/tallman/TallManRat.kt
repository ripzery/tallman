package tallman

import tallman.data.PathCounter
import tallman.data.toPathCounter
import tallman.prototype.ExperimentalRat
import tallman.prototype.SmartRat

class TallManRat private constructor(
    override val map: Array<IntArray>,
    override val food: Pair<Int, Int>,
    override val position: Pair<Int, Int>
) : SmartRat, ExperimentalRat {

    override fun findFood(index: Int, historyPath: MutableList<PathCounter>): Boolean {
        val currentPath = historyPath[index]
        return when {
            currentPath == food.toPathCounter() -> {
                speakPathTraced(historyPath)
                true
            }
            index < historyPath.size -> {
                val nextIndex = index + 1

                return isPossiblePath(historyPath, currentPath.left())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.left()))
                    || isPossiblePath(historyPath, currentPath.right())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.right()))
                    || isPossiblePath(historyPath, currentPath.top())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.top()))
                    || isPossiblePath(historyPath, currentPath.bottom())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.bottom()))
            }
            else -> false
        }
    }

    override fun speakPathTraced(historyPath: MutableList<PathCounter>) {
        println(historyPath.joinToString(" -> "))
        println("Total steps: ${historyPath.last().counter}")
    }

    override fun isPossiblePath(historyPath: MutableList<PathCounter>, path: PathCounter): Boolean {
        val notWall: (PathCounter) -> Boolean = { map[it.x][it.y] != 1 }
        val inBound: (PathCounter) -> Boolean = { it.x >= 0 && it.y >= 0 && it.x < map.size && it.y < map[it.x].size }
        val unique: (PathCounter) -> Boolean = { !historyPath.contains(it) }

        return inBound(path) && notWall(path) && unique(path)
    }

    private fun MutableList<PathCounter>.cloneInsert(
        pathCounter: PathCounter
    ): MutableList<PathCounter> {
        return mutableListOf<PathCounter>().apply {
            addAll(this@cloneInsert)
            add(pathCounter)
        }
    }

    companion object {
        fun throwInMaze(map: Array<IntArray>, food: Pair<Int, Int>, position: Pair<Int, Int>): ExperimentalRat {
            return TallManRat(map, food, position)
        }
    }
}
