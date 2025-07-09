# 🕵️ Fraud Detection with Graph Algorithms and KotlinDL

This project demonstrates a **hybrid fraud detection system** that combines:

- 📊 **Graph features** extracted from user, address, and transaction relationships.
- 🤖 A **machine learning model** (built with [KotlinDL](https://github.com/Kotlin/kotlindl)) to classify fraudulent transactions.

---

## 🚀 Features Explored
### 🎯 Main goal
Class: com.camilo.FraudDetect
- Models users, addresses, and transactions as a graph.
- Detects suspicious behavior like:
    - Cycles in transactions (e.g., money laundering patterns)
    - Shared addresses across unrelated users
- TODO: Extracts features from the graph:
    - `amount`, `inDegree`, `outDegree`, `sharedAddressCount`
- TODO: Trains a neural network to predict fraud likelihood.
### 🧠 Tarjan Algorithm
Class: com.camilo.TarjanExampleMain
- On this class I'm exploring the behaviour of a Tarjan Algorithm, which is quite common used when you want to find all Strongly Connected Components(SCCs) in a directed graph. It operates in linear time O(v + E) using Depth-First Search.

---

## 🛠 Tech Stack

- **Kotlin**
- [JGraphT](https://jgrapht.org/) – for graph modeling
- [KotlinDL](https://github.com/Kotlin/kotlindl) – for neural network training [TODO]

---

## 📦 Setup

### Requirements

- Kotlin 2.1+
- Gradle KTS
- JDK 21+

### Dependencies

Add the following to `build.gradle.kts`:

```kotlin
dependencies {
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-api:0.6.0")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:0.6.0")
}