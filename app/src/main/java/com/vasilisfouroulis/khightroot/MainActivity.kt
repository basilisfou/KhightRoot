package com.vasilisfouroulis.khightroot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vasilisfouroulis.khightroot.databinding.ActivityMainBinding
import com.vasilisfouroulis.khightroot.ui.chessboard.ChessActivity
import com.vasilisfouroulis.khightroot.ui.chessboard.ChessActivity.Companion.CHESS_SIZE_KEY
import com.vasilisfouroulis.khightroot.ui.main.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.proceedLiveData.observe(this, Observer {
            if(it != null){
                val intent = Intent(this, ChessActivity::class.java)
                intent.putExtra(CHESS_SIZE_KEY,it)
                startActivity(intent)
            }
        })
    }
}
