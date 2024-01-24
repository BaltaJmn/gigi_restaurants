package com.baltajmn.gigi.presentation.navigation.graphs

object GRAPH {
    val Main = "main_graph"
}

enum class MainGraph(val route: String) {
    Main("main"),
    Home("home"),
    Details("details/{id}"),
}