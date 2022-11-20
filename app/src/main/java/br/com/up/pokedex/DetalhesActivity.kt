package br.com.up.pokedex

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.up.pokedex.extension.id
import br.com.up.pokedex.model.Abilities
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.network.Api
import com.squareup.picasso.Picasso

class DetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_poke_detalhes)

        val id = intent.getStringExtra("id")

        val textDetalhes: TextView = findViewById(R.id.textDetalhes)
        val imgPokedex: ImageView = findViewById(R.id.imgPokemon)
        var texto: String = ""

        Api().getDetailsPokemons(id!!) { pokemons ->
            if(pokemons != null){
                texto += "Nome: "
                texto += pokemons.name
                texto += "\n"

                texto += "Habilidades: "
                for (ItemPokemon in pokemons.abilities.indices) {
                    texto += pokemons.abilities[ItemPokemon].ability.name
                    if (ItemPokemon > 1 && pokemons.types.size - 1 != ItemPokemon)
                        texto += ", "
                }

                texto += "\n"

                texto += "Stats: "
                for (ItemPokemon: Int in pokemons.stats.indices){
                    texto += pokemons.stats[ItemPokemon].stat.name
                    if (ItemPokemon > 1 && pokemons.types.size - 1 != ItemPokemon)
                        texto += ", "
                }

                texto += "\n"

                texto += "Movimentos: "
                for (ItemPokemon: Int in pokemons.moves.indices){
                    texto += pokemons.moves[ItemPokemon].move.name
                    if (ItemPokemon > 1 && pokemons.types.size - 1 != ItemPokemon)
                        texto += ", "
               }

               texto += "\n"

               texto += "Tipos: "
               for (ItemPokemon: Int in pokemons.types.indices){
                    texto += pokemons.types[ItemPokemon].type.name
                    if (ItemPokemon > 1 && pokemons.types.size - 1 != ItemPokemon)
                        texto += ", "
                }

                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id!!}.png"
                Picasso.get().load(url).into(imgPokedex)

                textDetalhes.setText(texto)
            }
        }

    }

}
