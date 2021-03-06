package tallman

private val map1: Array<IntArray> = arrayOf(
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(1, 1, 0, 1, 1, 1, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
    intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
    intArrayOf(1, 0, 1, 1, 0, 1, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 1, 1, 0, 1, 1, 0)
)

private val map2: Array<IntArray> = arrayOf(
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(1, 1, 0, 1, 1, 1, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
    intArrayOf(0, 0, 0, 0, 1, 0, 1, 0),
    intArrayOf(1, 0, 1, 1, 0, 1, 1, 0),
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
    intArrayOf(0, 1, 1, 1, 0, 1, 1, 0)
)

fun main(whatever: Array<String>) {
    val rat = TallManRat.throwInMaze(map = map1, food = 4 to 5, position = 0 to 0)
    println(rat.think(SpeakOptions.ALL_ROUTE))
}
