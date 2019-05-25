package com.vasilisfouroulis.khightroot.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Vasilis Fouroulis on 25/5/2019.
 */
class MainViewModel : ViewModel() {

    val sizeOfchessBoard = MutableLiveData<Int>()
    val proceedLiveData = MutableLiveData<Int>()


    fun proceed(){
        if(sizeOfchessBoard.value == null) {
            return
        }

        proceedLiveData.value = sizeOfchessBoard.value
    }
}