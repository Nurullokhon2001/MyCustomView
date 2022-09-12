package com.example.mycustomview

enum class Cell {
    PLAYER_1,
    PLAYER_2,
    EMPTY
}

typealias OnFieldChangedListener = (field: TicTacToeField) -> Unit

class TicTacToeField(
    val rows: Int,
    val columns: Int,
) {

    private val cells = Array(rows) { Array(columns) { Cell.EMPTY } }

    val listener = mutableListOf<OnFieldChangedListener>()

    fun getCell(row: Int, column: Int): Cell {
        if (row < 0 || column < 0 || column >= columns || row >= rows) return Cell.EMPTY
        return cells[row][column]
    }

    fun setCell(row: Int, column: Int, cell: Cell) {
        if (row < 0 || column < 0 || column >= columns || row >= rows) return
        if (cells[row][column] != cell) {
            cells[row][column] = cell
            listener.forEach { it.invoke(this) }
        }
    }
}