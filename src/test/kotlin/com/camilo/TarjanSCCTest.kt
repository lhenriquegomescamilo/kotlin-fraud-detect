package com.camilo

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class TarjanSCCTest : DescribeSpec({
    describe("TarjanSCC's Strongly connected componented algorithm") {

        it("should return empty list for an empty graph") {
            val graph = Graph(0)
            val scc = TarjanSCC(graph).findSCSS()
            scc shouldBe emptyList()
        }

        it("should return one SCC for a single node without edges") {
            val graph = Graph(1)
            val scc = TarjanSCC(graph).findSCSS()
            scc shouldBe listOf(listOf(0))
        }


        it("should find a single SCC in a cycle graph") {
            val graph = Graph(3)
            graph.addEdge(0, 1)
            graph.addEdge(1, 2)
            graph.addEdge(2, 0)

            val scc = TarjanSCC(graph).findSCSS()
            scc shouldContainExactlyInAnyOrder listOf(listOf(2, 1, 0))
        }


        it("should find multiple SCCs in a graph with separate cycles") {
            val graph = Graph(5)

            graph.addEdge(0, 1)
            graph.addEdge(1, 0)
            graph.addEdge(2, 3)
            graph.addEdge(3, 2)
            graph.addEdge(4, 4)

            val scc = TarjanSCC(graph).findSCSS()

            scc shouldContainExactly listOf(
                listOf(1, 0),
                listOf(3, 2),
                listOf(4)
            )
        }

        it("should handle disconnected nodes") {
            val graph = Graph(4)
            graph.addEdge(0, 1)
            graph.addEdge(1, 0)

            val scc = TarjanSCC(graph).findSCSS()
            scc shouldContainExactlyInAnyOrder listOf(
                listOf(1, 0),
                listOf(2),
                listOf(3)
            )
        }
    }
})