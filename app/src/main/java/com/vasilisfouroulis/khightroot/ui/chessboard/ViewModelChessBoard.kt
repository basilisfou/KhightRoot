package com.vasilisfouroulis.khightroot.ui.chessboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vasilisfouroulis.khightroot.model.Cell

/**
 * Created by Vasilis Fouroulis on 25/5/2019.
 */
class ViewModelChessBoard  : ViewModel(){

    var startPointIsPeaked  = false
    var endPointIsPeaked  = false

    val startPoint = MutableLiveData<Cell>()
    val destinationPoint = MutableLiveData<Cell>()

    val resetObserver = MutableLiveData<Boolean>()
    val calculateObserver = MutableLiveData<Boolean>()

    fun reset(){
        startPointIsPeaked = false
        endPointIsPeaked = false

        startPoint.value = null
        destinationPoint.value = null

        resetObserver.value = true
    }

    fun calculate(){
        calculateObserver.value = true
    }

}