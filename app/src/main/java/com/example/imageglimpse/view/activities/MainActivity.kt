package com.example.imageglimpse.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageglimpse.R
import com.example.imageglimpse.databinding.ActivityMainBinding
import com.example.imageglimpse.repository.Repository
import com.example.imageglimpse.view.adapters.MainAdapter
import com.example.imageglimpse.view.network.RetrofitInstance
import com.example.imageglimpse.viewmodel.MainViewModel
import com.example.imageglimpse.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var lm: LinearLayoutManager
    private lateinit var progressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()


        progressDialog = Dialog(this).apply {
            setContentView(R.layout.progress_dialog_layout)
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        val imageApi = RetrofitInstance.api
        val repository = Repository(imageApi)
        mainViewModel =ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        progressDialog.show()

         mainViewModel.imageLiveData.observe(this, Observer {
             imageAdapter.images = it
             progressDialog.hide()
         })
    }

    fun setUpRecyclerView() = binding.rvMain.apply {
        imageAdapter = MainAdapter(this@MainActivity)
        adapter=imageAdapter
        lm= LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        layoutManager=lm
    }
}