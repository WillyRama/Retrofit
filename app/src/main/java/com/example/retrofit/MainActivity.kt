package com.example.retrofit

import android.os.Bundle
import android.service.autofill.SavedDatasetsInfo
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(){
    private val list = ArrayList<PostResponse>()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

       RetrofitClient.instance.getPosts().enqueue(object : retrofit2.Callback<ArrayList<PostResponse>> {
           override fun onResponse(
               call: retrofit2.Call<ArrayList<PostResponse>>,
               response: retrofit2.Response<ArrayList<PostResponse>>
           ) {
               val responseCode = response.code().toString()
               tvResponse.text =responseCode
               response.body()?.let { list.addAll(it) }
               val adapter = PostAdapter(list)
               rvPost.adapter = adapter
           }

           override fun onFailure(call: retrofit2.Call<ArrayList<PostResponse>>, t: Throwable) {

           }

       })
    }
}