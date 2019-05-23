package com.vasilisfouroulis.khightroot

import com.vasilisfouroulis.khightroot.model.Cell
import com.vasilisfouroulis.khightroot.model.InvalideBoardSizeException

interface KnightTour {

    fun isValidCoordinates(x : Int, y : Int) : Boolean

    @Throws(InvalideBoardSizeException::class)
    fun getPossibleRoots(startingCell : Cell , destinationCell : Cell)  : HashMap<Int, List<Cell>>

    fun isValidBoardSize(boardSize : Int) : Boolean
}