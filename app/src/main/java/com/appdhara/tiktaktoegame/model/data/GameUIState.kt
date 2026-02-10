package com.appdhara.tiktaktoegame.model.data

data class GameUIState(
    val board : List<String> = List(size = 9){""},
    val currentPlayer : String = "X",
    val winner : String? = null,
    val winningLine : List<Int>? = null,
    val isDraw : Boolean = false,
    val xScore : Int = 0,
    val oScore : Int = 0,
    val isDarkMode : Boolean = true
)
