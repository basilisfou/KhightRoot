package com.vasilisfouroulis.khightroot

import com.vasilisfouroulis.khightroot.model.Cell
import com.vasilisfouroulis.khightroot.model.InvalideBoardSizeException
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.coroutineContext
import android.R.attr.y
import android.R.attr.x



class KnightTourImpl(private val sizeBoard : Int)  {

    companion object{
        // x and y direction, where a knight can move
        var possibleXDestinations = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
        var possibleYDestinations = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
    }

    fun isValidBoardSize(boardSize: Int): Boolean {
        return boardSize in 6..16
    }

    @Throws(InvalideBoardSizeException::class)
    fun getPossibleRoots(startingCell : Cell ,
                         destinationCell : Cell,
                         stack : Vector<Cell>,hashMap : HashMap<Int, Vector<Cell>>,
                         visited : Array<BooleanArray>)
            : HashMap<Int, Vector<Cell>>  {

        if(!isValidBoardSize(sizeBoard)){
            throw InvalideBoardSizeException("Not a valid BoardSize")
        }

        stack.add(startingCell)

        visited[startingCell.x][startingCell.y] = true

        while (!stack.isEmpty()) {

            for (i in 0..7) {
                val tempCell = stack.lastElement()
                val x = tempCell.x + possibleXDestinations[i]
                val y = tempCell.y + possibleYDestinations[i]
                val newCell = Cell(x,y,1)

                if (isValidCoordinates(x, y) && !visited[x][y] && stack.size < 4) {

                    stack.addElement(newCell)
                    visited[x][y] = true

                    if (isTargetFound(stack.lastElement(),destinationCell) && stack.size == 4){
                        val newStack = Vector<Cell>()
                        newStack.addAll(stack)
                        hashMap[hashMap.size + 1] = newStack
                        stack.removeElement(stack.lastElement())
                    }
                }
            }

            stack.removeElement(stack.lastElement())


        }

        return hashMap
    }


    fun isTargetFound(nextCell: Cell, destinationCell: Cell) = nextCell == destinationCell

    fun isValidCoordinates(x: Int, y: Int): Boolean {
        return x in 1..sizeBoard && y in 1..sizeBoard
    }

}