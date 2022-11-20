package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name:String,
    val url:String,
    val stats:List<Stats>,
    val abilities:List<Abilities>,
    val moves:List<Moves>,
    val types:List<Types>
)
