package com.vasilisfouroulis.khightroot

import android.util.Log
import com.vasilisfouroulis.khightroot.model.Cell
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
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
    fun isValidBoardSize_validLowLimit_trueReturned() {
      val result = SUT.isValidBoardSize(6)
        assertThat(result,`is`(true))
    }

    @Test
    fun isValidBoardSize_validuperLimit_trueReturned() {
        val result = SUT.isValidBoardSize(16)
        assertThat(result,`is`(true))
    }

    @Test
    fun isValidBoardSize_notValidBelowLowLimit_falseReturned() {
        val result = SUT.isValidBoardSize(5)
        assertThat(result,`is`(false))
    }

    @Test
    fun isValidBoardSize_notValidAboveUpperLimit_falseReturned() {
        val result = SUT.isValidBoardSize(17)
        assertThat(result,`is`(false))
    }

    @Test
    fun isLeafNode_validLeaf_trueReturned(){
        val result = SUT.isLeafNode(3)
        assertThat(result,`is`(true))
    }

    @Test
    fun isLeafNode_inValidLeafAbove_falseReturned(){
        val result = SUT.isLeafNode(4)
        assertThat(result,`is`(false))
    }

    @Test
    fun isLeafNode_inValidLeafBelow_falseReturned(){
        val result = SUT.isLeafNode(2)
        assertThat(result,`is`(false))
    }


    //region helper methods

    // endregion helper methods

    //region for helper classes

    //endregion of helper classes

}