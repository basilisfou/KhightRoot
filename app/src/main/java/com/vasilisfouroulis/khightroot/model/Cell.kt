package com.vasilisfouroulis.khightroot.model

import java.util.*

data class Cell (val x : Int , val y : Int){

    override fun equals(other: Any?): Boolean {

        other as Cell

        return this.x == other.x && this.y == other.y
    }

    override fun toString(): String {
        return "x: $x ,y: $y"
    }
}