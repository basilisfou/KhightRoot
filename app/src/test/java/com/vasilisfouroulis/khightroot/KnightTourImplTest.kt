package com.vasilisfouroulis.khightroot

import android.util.Log
import com.vasilisfouroulis.khightroot.model.Cell
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class KnightTourImplTest {
    //region constants

    //endregion constants

    //region helper fields

    //endregion helper fields
    lateinit var SUT: KnightTourImpl

    @Before
    fun setup() {

        val sizeBoard = 10

        SUT = KnightTourImpl(sizeBoard)

    }

    @Test
    fun getPossibleRoots() {
        val map = SUT.getPossibleRoots(Cell(2,2,0), Cell(4,1,3) , Vector(), hashMapOf())

    }

    //region helper methods

    // endregion helper methods

    //region for helper classes

    //endregion of helper classes

}