package com.aristidevs.androidmaster.superheroapp

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aristidevs.androidmaster.R

import com.aristidevs.androidmaster.databinding.ItemSuperheroBinding


class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view)  {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = superheroItemResponse.name
//        Esta seria la linea correcta pero esta api da errores por el captcha por lo que lo dejo en una url fija que si funcione
//       ____binding.ivSuperhero.load(superheroItemResponse.image.url) ____
        binding.ivSuperhero.load("https://picsum.photos/400/400")

        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}