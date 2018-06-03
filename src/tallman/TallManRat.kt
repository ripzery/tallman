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

    override val allPossiblePaths: MutableList<MutableList<PathCounter>> = mutableListOf()

    /**
     * Rat will use its super brain power to think about all possible paths to reach to food.
     *
     * @return true if it found at least 1 way, otherwise false.
     */
    override fun think(option: RouteOptions): Boolean {
        findFood()
        return if (allPossiblePaths.isNotEmpty()) {
            when (option) {
                RouteOptions.BEST_ROUTE -> {
                    allPossiblePaths.sortBy { it.last().counter }
                    allPossiblePaths[0].speakPathTraced()
                }
                RouteOptions.ALL_ROUTE -> {
                    allPossiblePaths.forEach { it.speakPathTraced() }
                }
            }
            true
        } else {
            false
        }
    }

    override fun findFood(index: Int, historyPath: MutableList<PathCounter>): Boolean {
        val currentPath = historyPath[index]
        return when {
            currentPath == food.toPathCounter() -> {
                allPossiblePaths.add(historyPath)
                false
            }
            index < historyPath.size -> {
                val nextIndex = index + 1

                return historyPath.isPossiblePath(currentPath.left())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.left()))
                    || historyPath.isPossiblePath(currentPath.right())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.right()))
                    || historyPath.isPossiblePath(currentPath.top())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.top()))
                    || historyPath.isPossiblePath(currentPath.bottom())
                    && findFood(nextIndex, historyPath.cloneInsert(currentPath.bottom()))
            }
            else -> {
                throw IllegalStateException("Should not be here!!")
            }
        }
    }

    override fun MutableList<PathCounter>.speakPathTraced() {
        println(this.joinToString(" -> "))
        println("Total steps: ${this.last().counter}")
    }

    override fun MutableList<PathCounter>.isPossiblePath(path: PathCounter): Boolean {
        val notWall: (PathCounter) -> Boolean = { map[it.x][it.y] != 1 }
        val inBound: (PathCounter) -> Boolean = { it.x >= 0 && it.y >= 0 && it.x < map.size && it.y < map[it.x].size }
        val unique: (PathCounter) -> Boolean = { !this.contains(it) }

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
