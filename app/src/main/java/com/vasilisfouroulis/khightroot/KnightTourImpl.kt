package com.vasilisfouroulis.khightroot

import android.util.Log
import com.vasilisfouroulis.khightroot.model.Cell
import java.util.*


class KnightTourImpl(private val sizeBoard : Int)  {

    companion object{
        // x and y direction, where a knight can move
        var possibleXDestinations = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
        var possibleYDestinations = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
    }

    fun isValidBoardSize(boardSize: Int): Boolean {
        return boardSize in 6..16
    }


    fun getPossibleRoots(startingCell : Cell ,
                         destinationCell : Cell){

        val queue : Vector<Cell> = Vector()

        queue.addElement(startingCell)
        val map = solve(queue,destinationCell , hashMapOf())



    }

    private fun solve(queue : Vector<Cell>,
                      destinationCell : Cell,
                      hashMap: HashMap<Int,Vector<Cell>>) : Boolean {

        val currentCell = queue.lastElement()

        if (queue.isEmpty()) {
            Log.d("Possible solutions" , hashMap.toString())
            return true
        }


        for (k in 0..7) {
            // Get the new position of Knight from current
            // position on chessboard
            val newX = currentCell.x + possibleXDestinations[k]
            val newY = currentCell.y + possibleYDestinations[k]
            val distance = currentCell.distance.plus(1)
            val newCell = Cell(newX, newY, distance)

            if (isSafe(newCell, currentCell, distance)) {
                queue.addElement(newCell)
                newCell.head = currentCell

                if(isTargetFound(newCell,destinationCell) && isLeafNode(newCell.distance)) {
                    hashMap[hashMap.size] = queue
                }

                if(isLeafNode(newCell.distance)){
                    queue.removeElementAt(queue.lastIndex)
                }

                return solve(queue,destinationCell,hashMap)
            }
        }

        return false
    }

    private fun isSafe(
        newCell: Cell,
        currentCell: Cell?,
        distance: Int
    ) = isValidCoordinates(newCell) && newCell.head != currentCell && distance < 3

    fun isLeafNode(depth : Int) : Boolean = depth == 3

    fun isTargetFound(nextCell: Cell, destinationCell: Cell) = nextCell == destinationCell

    fun isValidCoordinates(cell :Cell): Boolean {
        return cell.x in 1..sizeBoard && cell.y in 1..sizeBoard
    }
}