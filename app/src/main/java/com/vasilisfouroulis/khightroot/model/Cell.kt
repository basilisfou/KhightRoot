package com.vasilisfouroulis.khightroot.model

data class Cell (val x : Int , val y : Int,  val distance : Int){

    override fun equals(other: Any?): Boolean {

        other as Cell

        return this.x == other.x && this.y == other.y
    }
}