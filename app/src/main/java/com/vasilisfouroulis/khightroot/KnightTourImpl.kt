package com.vasilisfouroulis.khightroot

import com.vasilisfouroulis.khightroot.model.Cell
import java.util.*


class KnightTourImpl(private val sizeBoard : Int)  {

    companion object{
        var possibleXDestinations = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
        var possibleYDestinations = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
    }

    fun isValidBoardSize(boardSize: Int): Boolean {
        return boardSize in 6..16
    }


    fun getPossibleRoots(startingCell : Cell ,
                         destinationCell : Cell) : Vector<Vector<Cell>> {

        val queue: Vector<Cell> = Vector()
        val paths : Vector<Vector<Cell>> = Vector()
        queue.addElement(startingCell)

        val currentCell = queue.lastElement()

        for (k in 0..7) {
            val secondCell = getNextSell(currentCell,k)

            if (isValidCoordinates(secondCell)) {
                queue.addElement(secondCell)

                for (i in 0..7) {
                    val thirdCell  = getNextSell(secondCell,i)

                    if (isValidCoordinates(thirdCell) && !isPreviousCell(queue.firstElement(),thirdCell)) {
                        queue.addElement(thirdCell)

                        for (j in 0..7) {
                            val forthCell  = getNextSell(thirdCell,j)

                            if (isValidCoordinates(forthCell) && isTargetFound(forthCell, destinationCell) && !isPreviousCell(queue[1],forthCell)) {
                                queue.addElement(forthCell)
                                val newQueue = Vector(queue)
                                paths.addElement(newQueue)
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

        return paths
    }

    fun getNextSell(currentCell : Cell, positionMove : Int) : Cell {
        val newX = currentCell.x + possibleXDestinations[positionMove]
        val newY = currentCell.y + possibleYDestinations[positionMove]

        return Cell(newX,newY)
    }

    fun isPreviousCell(newCell: Cell,oldCell : Cell) : Boolean {
        return newCell == oldCell
    }

    private fun isTargetFound(nextCell: Cell, destinationCell: Cell) = nextCell == destinationCell

    fun isValidCoordinates(cell :Cell): Boolean {
        return cell.x in 1..sizeBoard && cell.y in 1..sizeBoard
    }
}