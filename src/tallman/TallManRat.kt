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
        val currentPath = memoryPath[index]
        return when {
            currentPath == food.toCoordinateCounter() -> {
                speakPathTraced(memoryPath)
                true
            }
            index < memoryPath.size -> {
                val nextIndex = index + 1
                
                return isPossiblePath(memoryPath, currentPath.left())
                    && findFood(nextIndex, memoryPath.cloneInsert(currentPath.left()))
                    || isPossiblePath(memoryPath, currentPath.right())
                    && findFood(nextIndex, memoryPath.cloneInsert(currentPath.right()))
                    || isPossiblePath(memoryPath, currentPath.top())
                    && findFood(nextIndex, memoryPath.cloneInsert(currentPath.top()))
                    || isPossiblePath(memoryPath, currentPath.bottom())
                    && findFood(nextIndex, memoryPath.cloneInsert(currentPath.bottom()))
            }
            else -> false
        }
    }

    override fun speakPathTraced(passedPath: MutableList<CoordinateCounter>) {
        println(passedPath.joinToString(" -> "))
        println("Total steps: ${passedPath.last().counter}")
    }

    override fun isPossiblePath(memoryPath: MutableList<CoordinateCounter>, path: CoordinateCounter): Boolean {
        val notWall: (CoordinateCounter) -> Boolean = { map[it.x][it.y] != 1 }
        val inBound: (CoordinateCounter) -> Boolean = { it.x >= 0 && it.y >= 0 && it.x < map.size && it.y < map[it.x].size }
        val unique: (CoordinateCounter) -> Boolean = { !memoryPath.contains(it) }

        return inBound(path) && notWall(path) && unique(path)
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
