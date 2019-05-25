package com.vasilisfouroulis.khightroot

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

        val visited = Array(sizeBoard) { IntArray(sizeBoard) }
        val queue : Vector<Cell> = Vector()

        for (x in 0 until sizeBoard)
            for (y in 0 until sizeBoard)
                visited[x][y] = 0

        visited[startingCell.x][startingCell.y] = 0
        queue.addElement(startingCell)
//        val map = recursionUtil(startingCell,destinationCell , visited ,queue,1)

//        Log.d("map", map.toString())


    }

//    private fun recursionUtil(startingCell: Cell,
//                              destinationCell: Cell,
//                              visited: Array<IntArray>,
//                              queue : Vector<Cell>,
//                              pos: Int) : HashMap<Int,Vector<Cell>>  {
//
//        visited[startingCell.x][startingCell.y] = pos
//
//        if (isTargetFound(startingCell,destinationCell)) {
//            print(visited)
//            visited[startingCell.x][startingCell.y] = 0
//            return
//        }
//
//
//        for (k in 0..7) {
//            // Get the new position of Knight from current
//            // position on chessboard
//            val newX = startingCell.x + possibleXDestinations[k]
//            val newY = startingCell.y + possibleYDestinations[k]
//            val distance = startingCell.distance.plus(1)
//            val newCell = Cell(newX,newY,distance)
//
//            // if new position is a valid and not visited yet
//            if (isValidCoordinates(newCell) && visited[newX][newY] == 0)
//                recursionUtil(newCell,destinationCell,visited, pos + 1)
//        }
//
//        // backtrack from current square and remove it from current path
//        visited[startingCell.x][startingCell.y] = 0
//    }

    fun isLeafNode(depth : Int) : Boolean = depth == 3

    fun isTargetFound(nextCell: Cell, destinationCell: Cell) = nextCell == destinationCell && isLeafNode(nextCell.distance)

    fun isValidCoordinates(cell :Cell): Boolean {
        return cell.x in 1..sizeBoard && cell.y in 1..sizeBoard
    }

}