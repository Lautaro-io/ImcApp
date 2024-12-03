package com.example.firstapp.superheroapp

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivitySuperHeroInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SuperHeroInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }
    private lateinit var binding :ActivitySuperHeroInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHerpInformation(id)
    }

    private fun getSuperHerpInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val details = getRetrofit().create(ApiService::class.java).getSuperHeroDetails(id)

            if (details.body() != null){
                runOnUiThread{
                    createUI(details.body()!!)
                }
                
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}