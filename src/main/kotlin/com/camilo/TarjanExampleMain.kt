package com.camilo


data class Graph(val numberOfNodes: Int) {
    val adjacentList = Array(numberOfNodes) { mutableListOf<Int>() }

    fun addEdge(from: Int, to: Int) {
        adjacentList[from].add(to)
    }
}


class TarjanSCC(private val graph: Graph) {

    private var index = 0
    private var indices = IntArray(graph.numberOfNodes) { -1 }
    private val lowLinks = IntArray(graph.numberOfNodes) { -1 }
    private val onStack = BooleanArray(graph.numberOfNodes) { false }
    private val stack = ArrayDeque<Int>()
    private val result = mutableListOf<List<Int>>()

    fun findSCSS(): List<List<Int>> {
        for (v in 0 until graph.numberOfNodes) {
            if(indices[v] == -1) strongConnect(v)

        }

        return result
    }

    private fun strongConnect(v: Int) {
        indices[v] = index
        lowLinks[v] = index
        index++
        stack.addFirst(v)
        onStack[v] = true

        for (w in graph.adjacentList[v]) {
            if(indices[w] == -1) {
                strongConnect(w)
                lowLinks[v] = minOf(lowLinks[w], lowLinks[v])
            } else if(onStack[w]) {
                lowLinks[v] = minOf(lowLinks[v], indices[w])
            }
        }

        if(lowLinks[v] == indices[v]) {
            val scc = mutableListOf<Int>()
            var w: Int
            do {
                w = stack.removeFirst()
                onStack[w] = false
                scc.add(w)
            } while (w != v)

            result.add(scc)
        }

    }


}

fun main() {
    val graph = Graph(8)
    graph.addEdge(0, 1)
    graph.addEdge(1, 2)
    graph.addEdge(2, 0)
    graph.addEdge(3, 1)
    graph.addEdge(3, 2)
    graph.addEdge(4, 3)
    graph.addEdge(4, 5)
    graph.addEdge(5, 6)
    graph.addEdge(6, 4)
    graph.addEdge(6, 7)

    val tarjan = TarjanSCC(graph)
    val sccs = tarjan.findSCSS()

    println("Strongly Connected Components:")
    for (scc in sccs) {
        println(scc)
    }
}
