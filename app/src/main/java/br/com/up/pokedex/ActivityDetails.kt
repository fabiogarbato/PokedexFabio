package br.com.up.pokedex

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.up.pokedex.network.Api
import com.squareup.picasso.Picasso

class ActivityDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_details)

        val id = intent.getStringExtra("id")
        var output: String = ""
        val detailOutput: TextView = findViewById(R.id.textContent)
        val imgPokedex: ImageView = findViewById(R.id.pokeImage)
        val pulaLinha: String = "\n"

        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id!!}.png"

        Api().getDetailsPokemons(id!!) { pokemons ->
            if(pokemons != null){
                output += "Nome Pokemon: "
                output += pokemons.name
                output += pulaLinha

                output += "Habilidades Pokemon: "
                for (ItemPokemon in pokemons.abilities.indices) {
                    output += pokemons.abilities[ItemPokemon].ability.name
                }
                output += pulaLinha

                output += "Stats Pokemon: "
                for (ItemPokemon: Int in pokemons.stats.indices){
                    output += pokemons.stats[ItemPokemon].stat.name
                }

                output += pulaLinha
                output += "Movimentos Pokemon: "
                for (ItemPokemon: Int in pokemons.moves.indices){
                    output += pokemons.moves[ItemPokemon].move.name
               }

                output += pulaLinha
                output += "Tipos Pokemon: "
               for (ItemPokemon: Int in pokemons.types.indices){
                   output += pokemons.types[ItemPokemon].type.name
                }

                Picasso.get().load(url).into(imgPokedex)

                detailOutput.setText(output)
            }
        }

    }

}
