package br.com.up.pokedex

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.up.pokedex.adapter.PokeAdapter
import br.com.up.pokedex.extension.id
import br.com.up.pokedex.model.Pokemon
import br.com.up.pokedex.network.Api
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var pokemons:List<Pokemon>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editPesquisa : TextInputEditText = findViewById(R.id.text_input_search_pokemon)

        val recyclerView : RecyclerView =
            findViewById(R.id.recycler_pokemons)

        recyclerView.layoutManager =
            GridLayoutManager(this, 3)

        Api().getPokemons{ pokemons ->
            if(pokemons != null){

                this@MainActivity.pokemons = pokemons
                recyclerView.adapter  =
                    PokeAdapter(pokemons) { pokemon ->

                    }
            }else{
                //TODO Error
            }
        }

        editPesquisa.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

                    if(pokemons != null){
                        val listaPokemons = pokemons!!.filter { pokemon ->
                            pokemon.name.contains(s)
                        }

                        recyclerView.adapter  =
                            PokeAdapter(listaPokemons) { pokemon ->
                                val intent = Intent(
                                  applicationContext,
                                  DetalhesActivity::class.java
                                )
                                intent.putExtra("id", pokemon.id())
                               startActivity(intent)
                            }

                    }else{
                        //TODO Error
                    }


            }
        })

    }

}