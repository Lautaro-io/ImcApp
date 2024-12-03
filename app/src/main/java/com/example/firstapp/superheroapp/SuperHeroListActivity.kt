package com.example.firstapp.superheroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivitySuperHeroListBinding
import com.example.firstapp.superheroapp.SuperHeroInfoActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.schView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        adapter = SuperHeroAdapter{id -> navigateToDetail(id)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }


    private fun searchByName(query: String) {
        binding.progresBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperHero(query)
            if (myResponse.isSuccessful) {
                Log.i("Chelo", "Succes")
                val mySuperHero = myResponse.body()
                if (mySuperHero != null) {
                    Log.i("Chelo", mySuperHero.toString())

                    runOnUiThread {
                        adapter.updateList(mySuperHero.superHeros)
                        binding.progresBar.isVisible = false
                    }
                }
            } else {
                Log.i("Chelo", "Failed")

            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    private fun navigateToDetail(id:String){
        val intent = Intent(this, SuperHeroInfoActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}