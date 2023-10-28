package com.csm.roomimage

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.csm.roomimage.adapter.MyAdapter
import com.csm.roomimage.room.models.Person
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Inisialisasi adapter menggunakan delegasi by lazy
    private val adapter by lazy { MyAdapter() }
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Membuat dan mendapatkan instance ViewModel
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Mengatur adapter untuk RecyclerView
        recyclerView.adapter = adapter
        // Mengatur layout manager untuk RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val person = Person(1, "Fahmy", "Fauzi", getBitmap())
            myViewModel.insertPerson(person)
        }

        myViewModel.readPerson.observe(this, {
            adapter.setData(it)
        })
    }

    // Fungsi suspending untuk mengambil gambar dari URL
    private suspend fun getBitmap(): Bitmap {
        // Membuat instance ImageLoader
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data("https://avatars.githubusercontent.com/u/58255031?v=4")
            .build()

        // Menjalankan permintaan gambar dan mengambil hasilnya
        val result = (loading.execute(request) as SuccessResult).drawable

        // Mengambil Bitmap dari Drawable
        return (result as BitmapDrawable).bitmap
    }

}