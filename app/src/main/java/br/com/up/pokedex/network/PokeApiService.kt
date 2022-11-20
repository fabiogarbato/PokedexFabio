package br.com.up.pokedex.network

import br.com.up.pokedex.model.PokeListApiResponse
import br.com.up.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon?limit=1500")
    fun getPokemons():Call<PokeListApiResponse>

    @GET("pokemon/{id}")
    fun getDetailsPokemons(@Path("id")pokemonId: String):Call<Pokemon>
}