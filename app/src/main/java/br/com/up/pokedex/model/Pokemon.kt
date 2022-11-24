package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name:String,
    val abilities:List<Abilities>,
    val url:String,
    val stats:List<Stats>,
    val types:List<Types>,
    val moves:List<Moves>,
)
