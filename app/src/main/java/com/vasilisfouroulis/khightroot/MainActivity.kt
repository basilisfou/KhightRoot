package com.vasilisfouroulis.khightroot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vasilisfouroulis.khightroot.model.Cell
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val helper = KnightTourImpl(6)
        val visited =  Array(6 + 1) { BooleanArray(6 + 1) }

        for (i in 1..6)
            for (j in 1..6)
                visited[i][j] = false


        val map = helper.getPossibleRoots(
            Cell(2,2, 0),
            Cell(4,5, 3),
            Vector(),
            hashMapOf(),
            visited)

        Log.d("mapLog",map.toString())
    }
}
