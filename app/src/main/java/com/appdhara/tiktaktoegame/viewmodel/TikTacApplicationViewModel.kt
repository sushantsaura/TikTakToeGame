package com.appdhara.tiktaktoegame.viewmodel

import androidx.lifecycle.ViewModel
import com.appdhara.tiktaktoegame.model.data.GameUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class TikTacApplicationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState : StateFlow<GameUIState> = _uiState.asStateFlow()

    fun onCellClick(index : Int ) {
        val currentState = _uiState.value

        if(currentState.board[index].isNotEmpty() || currentState.winner != null || currentState.isDraw) {
            return
        }

        val newBoard = currentState.board.toMutableList()
        newBoard[index] = currentState.currentPlayer

        val winner = checkWinner(newBoard)
        val winningLine = if(winner != null) getWinningLine(newBoard) else null

        val isDraw = winner == null && newBoard.none{it.isEmpty()}

        _uiState.update { state ->
            state.copy(
                board = newBoard,
                currentPlayer = if(state.currentPlayer == "X") "O" else "X",
                winner = winner,
                winningLine = winningLine,
                isDraw = isDraw,
                xScore = if(winner =="X") state.xScore + 1 else state.xScore,
                oScore = if(winner == "O") state.oScore + 1 else state.oScore
            )
        }
    }

    fun toggleTheme() {
        _uiState.update { state ->
            state.copy(isDarkMode = !state.isDarkMode)
        }
    }

    private fun checkWinner(board : List<String>) : String? {
        val winPatterns = listOf(
            listOf(0,1,2),
            listOf(3,4,5),
            listOf(6,7,8),
            listOf(0,3,6),
            listOf(1,4,7),
            listOf(2,5,8),
            listOf(0,4,8),
            listOf(2,4,6)
        )

        for(pattern in winPatterns) {
            val (a,b,c) = pattern
            if( board[a].isNotEmpty() && board[a] == board[b] && board[a] == board[c] ){
                return board[a]
            }
        }

        return null
    }

    private fun getWinningLine(board : List<String>) : List<Int>? {
        val winPatterns = listOf(
            listOf(0,1,2),
            listOf(3,4,5),
            listOf(6,7,8),
            listOf(0,3,6),
            listOf(1,4,7),
            listOf(2,5,8),
            listOf(0,4,8),
            listOf(2,4,6)
        )

        for(pattern in winPatterns) {
            val (a,b,c) = pattern
            if( board[a].isNotEmpty() && board[a] == board[b] && board[a] == board[c] ){
                return pattern
            }
        }

        return null
    }

}