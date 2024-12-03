package com.example.firstapp.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superHeroItemResponse: SuperHeroItemResponse,onItemSelected: (String) -> Unit){
        binding.superHeroName.text = superHeroItemResponse.SuperheroNAme
        Picasso.get().load(superHeroItemResponse.superHeroImgResponse.url).into(binding.imgViewSh)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.Superheroid) }
    }
}