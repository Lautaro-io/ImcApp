package com.example.firstapp.superheroapp

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivitySuperHeroInfoBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class SuperHeroInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivitySuperHeroInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val details =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetails(id)
            Log.i("details", details.toString())

            if (details.body() != null) {
                val json = details.body().toString()
                Log.i("Entro", json)
                runOnUiThread { createUI(details.body()!!) }

            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        binding.tvSuperHeroRealName.text = superHero.biography.fullName
        binding.tvPublisher.text = superHero.biography.publisher
        prepareStats(superHero.powerstats)

    }

    private fun prepareStats(superHeroStats: SuperHeroStatsResponse) {
        updateHeight(binding.viewCombat, superHeroStats.combat)
        updateHeight(binding.viewDurability, superHeroStats.durability)
        updateHeight(binding.viewIntelligence, superHeroStats.intelligence)
        updateHeight(binding.viewPower, superHeroStats.power)
        updateHeight(binding.viewStrength, superHeroStats.strength)
        updateHeight(binding.viewSpeed, superHeroStats.speed)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}