package com.vasilisfouroulis.khightroot.ui.chessboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vasilisfouroulis.khightroot.KnightTourImpl
import com.vasilisfouroulis.khightroot.R
import com.vasilisfouroulis.khightroot.databinding.ActivityChessBinding
import com.vasilisfouroulis.khightroot.model.Cell
import kotlinx.android.synthetic.main.activity_chess.*
import java.util.*
import android.graphics.Color
import android.text.method.ScrollingMovementMethod
import android.widget.LinearLayout
import android.content.DialogInterface
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog


class ChessActivity : AppCompatActivity() {

    private lateinit var viewModel : ViewModelChessBoard

    companion object{
        const val CHESS_SIZE_KEY = "chess.size.key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChessBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_chess)

        viewModel = ViewModelProviders.of(this).get(ViewModelChessBoard::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        createChessBoard(getRow(intent),getCol(intent),null,null,null)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.resetObserver.observe(this, Observer {
            if(it == true){
                createChessBoard(getRow(intent),getCol(intent),null,null,null)
            }
        })

        viewModel.calculateObserver.observe(this, Observer {
            if(it == true){
                val map = KnightTourImpl(getRow(intent)).getPossibleRoots(viewModel.startPoint.value!!,viewModel.destinationPoint.value!!)
                if(map.size == 0){
                    Toast.makeText(this,getString(R.string.no_paths),Toast.LENGTH_LONG).show()
                } else {
                    setAlertDialog(map)
                }
            }
        })
    }

    private fun getRow(intent: Intent) : Int {
        return intent.getIntExtra(CHESS_SIZE_KEY, -1)
    }

    private fun getCol(intent: Intent) : Int {
        return intent.getIntExtra(CHESS_SIZE_KEY, -1)
    }

    private fun createChessBoard(mRows : Int , mCols : Int, map : Vector<Vector<Cell>>?, startPoint : Cell? , endPoint : Cell?) {

        if(!isValidBoardSize(mRows) || !isValidBoardSize(mCols) || mRows != mCols){
            Toast.makeText(this,"Invalid chess board", Toast.LENGTH_LONG).show()
            return
        }

        val color1 = resources.getColor(R.color.odd_chess)
        val color2 = resources.getColor(R.color.even_chess)
        var id: Int
        val idArray = Array(mRows) { IntArray(mCols) }

        for (iRow in 0 until mRows) {
            for (iCol in 0 until mCols) {


                val tile = LinearLayout(this)
                val imageView = ImageView(this)

                val lp = ConstraintLayout.LayoutParams(
                    ConstraintSet.MATCH_CONSTRAINT,
                    ConstraintSet.MATCH_CONSTRAINT
                )

                id = View.generateViewId()
                idArray[iRow][iCol] = id
                tile.apply {
                    this.id = id
                    setBackgroundColor(if ((iRow + iCol) % 2 == 0) color1 else color2)
                }
                parentChessBoard.addView(tile, lp)

                tile.setOnClickListener {
                    if(!viewModel.startPointIsPeaked){
                        viewModel.startPointIsPeaked = true
                        viewModel.startPoint.value = Cell(iCol + 1,iRow+1)
                        imageView.setImageDrawable(getDrawable(R.drawable.knight))
                    }else if(viewModel.startPointIsPeaked && !viewModel.endPointIsPeaked){
                        viewModel.endPointIsPeaked = true
                        viewModel.destinationPoint.value = Cell(iCol+1,iRow+1)
                        imageView.setImageDrawable(getDrawable(R.drawable.knight))
                    }
                }

                tile.addView(imageView)

            }
        }

        val cs = ConstraintSet()
        cs.clone(parentChessBoard)
        cs.setDimensionRatio(R.id.chessBoard, "$mCols:$mRows")
        for (iRow in 0 until mRows) {
            for (iCol in 0 until mCols) {
                id = idArray[iRow][iCol]
                cs.setDimensionRatio(id, "1:1")
                if (iRow == 0) {
                    cs.connect(id, ConstraintSet.TOP, R.id.chessBoard, ConstraintSet.TOP)
                } else {
                    cs.connect(id, ConstraintSet.TOP, idArray[iRow - 1][0], ConstraintSet.BOTTOM)
                }
            }

            cs.createHorizontalChain(
                R.id.chessBoard, ConstraintSet.LEFT,
                R.id.chessBoard, ConstraintSet.RIGHT,
                idArray[iRow], null, ConstraintSet.CHAIN_PACKED
            )
        }

        cs.applyTo(parentChessBoard)
    }

    private fun isValidBoardSize(boardSize: Int): Boolean {
        return boardSize in 6..16
    }

    private fun setAlertDialog(map : Vector<Vector<Cell>>){
        val builderSingle = AlertDialog.Builder(this)

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.text_result)
        for(vector in map){
            arrayAdapter.add(vector.toString())
        }


        builderSingle.setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }

        builderSingle.setAdapter(arrayAdapter,null)
        builderSingle.show()
    }
}
