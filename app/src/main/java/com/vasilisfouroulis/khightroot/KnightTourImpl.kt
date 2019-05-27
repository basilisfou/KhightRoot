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
        val map = solve(queue,destinationCell , Vector())



    }

    private fun solve(queue : Vector<Cell>,
                      destinationCell : Cell,
                      hashMap: Vector<Vector<Cell>>) : Boolean {

        var currentCell = queue.lastElement()

        for (k in 0..7) {
            var newX = currentCell.x + possibleXDestinations[k]
            var newY = currentCell.y + possibleYDestinations[k]
            val secondCell = Cell(newX, newY, 0)
            if(isSafe(secondCell,currentCell) ){
                queue.addElement(secondCell)

                for(i in 0..7) {
                    newX = secondCell.x + possibleXDestinations[i]
                    newY = secondCell.y + possibleYDestinations[i]
                    val trirdCell = Cell(newX, newY, 0)

                    if(isSafe(trirdCell,currentCell) && !queue.contains(trirdCell)){
                        queue.addElement(trirdCell)
                        for(j in 0..7){
                            newX = trirdCell.x + possibleXDestinations[j]
                            newY = trirdCell.y + possibleYDestinations[j]
                            val forthCell = Cell(newX, newY, 0)
                            if(isSafe(forthCell,currentCell) && isTargetFound(forthCell,destinationCell) && !queue.contains(forthCell)){
                                queue.addElement(forthCell)
                                val newQueue = Vector(queue)
                                hashMap.addElement(newQueue)
                                queue.removeElementAt(queue.size - 1)
                                break
                            }
                        }
                        queue.removeElementAt(queue.size - 1)
                    }
                }
                queue.removeElementAt(queue.size - 1)
            }
        }

        Log.d("Possible solutions" , hashMap.toString())

        return false
    }

    private fun isSafe(
        newCell: Cell,
        currentCell: Cell?
    ) = isValidCoordinates(newCell)

    fun isLeafNode(depth : Int) : Boolean = depth == 3

    fun isTargetFound(nextCell: Cell, destinationCell: Cell) = nextCell == destinationCell

    fun isValidCoordinates(cell :Cell): Boolean {
        return cell.x in 1..sizeBoard && cell.y in 1..sizeBoard
    }
}