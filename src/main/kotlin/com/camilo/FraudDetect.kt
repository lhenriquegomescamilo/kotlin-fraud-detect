package com.camilo

import org.jgrapht.alg.cycle.TarjanSimpleCycles
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge


data class User(val name: String)
data class Address(val id: String)
data class Transaction(val from: User, val to: User, val address: Address, val amount: Double)

fun main(vararg args: String) {
    val graph = DefaultDirectedGraph<Any, DefaultEdge>(DefaultEdge::class.java)

    val alice = User("Alice")
    val bob = User("Bob")
    val charlie = User("Charlie")

    val address1 = Address("0xABC")
    val address2 = Address("0xDEF")

    listOf(alice, bob, charlie, address1, address2).forEach(graph::addVertex)

    graph.addEdge(alice, address1)
    graph.addEdge(bob, address1)
    graph.addEdge(charlie, address2)

    // Transactions
    val transactions = listOf(
        Transaction(alice, bob, address1, 100.0),
        Transaction(bob, alice, address1, 200.0), // suspicious (circular + same addr)
        Transaction(bob, charlie, address2, 50.0)
    )

    for (tx in transactions) {
        println("💸 ${tx.from.name} -> ${tx.to.name} via ${tx.address.id} [$${tx.amount}]")
        graph.addEdge(tx.from, address1)
    }

    val userOnlySubgraph = DefaultDirectedGraph<User, DefaultEdge>(DefaultEdge::class.java)
    for (user in listOf(alice, bob, charlie)) userOnlySubgraph.addVertex(user)
    for (transaction in transactions) userOnlySubgraph.addEdge(transaction.from, transaction.to)

    val cycles = TarjanSimpleCycles(userOnlySubgraph).findSimpleCycles()
    println("\n🔁 User-to-user Cycles:")
    for (cycle in cycles) println("Cycle: ${cycle.joinToString(" -> ") { it.name }}")

    val addressToUsers = mutableMapOf<Address, MutableSet<User>>()
    for (tx in transactions) {
        addressToUsers.computeIfAbsent(tx.address) { mutableSetOf() }.add(tx.from)
        addressToUsers[tx.address]?.add(tx.to)
    }

    println("\n🚨 Shared Address Involvement:")
    addressToUsers.forEach { (addr, users) ->
        if (users.size > 1) {
            println("Address ${addr.id} used by: ${users.joinToString { it.name }}")
        }
    }
}