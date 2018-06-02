package tallman

import tallman.data.CoordinateCounter
import tallman.data.toCoordinateCounter
import tallman.prototype.ExperimentalRat
import tallman.prototype.SmartRat

class TallManRat private constructor(
    override val map: Array<IntArray>,
    override val food: Pair<Int, Int>,
    override val position: Pair<Int, Int>
) : SmartRat, ExperimentalRat {

    override fun findFood(index: Int, memoryPath: MutableList<CoordinateCounter>): Boolean {
        return when {
            memoryPath[index] == food.toCoordinateCounter() -> {
                speakPassedPath(memoryPath)
                true
            }
            index < memoryPath.size -> {
                val path = memoryPath[index]
                val nextIndex = index + 1

                return isPossiblePath(memoryPath, path.left())
                    && findFood(nextIndex, memoryPath.cloneInsert(path.left()))
                    || isPossiblePath(memoryPath, path.right())
                    && findFood(nextIndex, memoryPath.cloneInsert(path.right()))
                    || isPossiblePath(memoryPath, path.top())
                    && findFood(nextIndex, memoryPath.cloneInsert(path.top()))
                    || isPossiblePath(memoryPath, path.bottom())
                    && findFood(nextIndex, memoryPath.cloneInsert(path.bottom()))
            }
            else -> false
        }
    }

    override fun speakPassedPath(passedPath: MutableList<CoordinateCounter>) {
        println(passedPath.joinToString(" -> "))
        println("Total steps: ${passedPath.last().counter}")
    }

    override fun isPossiblePath(memoryPath: MutableList<CoordinateCounter>, path: CoordinateCounter): Boolean {
        val walkable: (CoordinateCounter) -> Boolean = { map[it.x][it.y] != 1 }
        val inBound: (CoordinateCounter) -> Boolean = { it.x >= 0 && it.y >= 0 && it.x < map.size && it.y < map[it.x].size }
        val unique: (CoordinateCounter) -> Boolean = { !memoryPath.contains(it) }

        return inBound(path) && walkable(path) && unique(path)
    }

    private fun MutableList<CoordinateCounter>.cloneInsert(
        coordinateCounter: CoordinateCounter
    ): MutableList<CoordinateCounter> {
        return mutableListOf<CoordinateCounter>().apply {
            addAll(this@cloneInsert)
            add(coordinateCounter)
        }
    }

    companion object {
        fun throwInMaze(map: Array<IntArray>, food: Pair<Int, Int>, position: Pair<Int, Int>): ExperimentalRat {
            return TallManRat(map, food, position)
        }
    }
}
