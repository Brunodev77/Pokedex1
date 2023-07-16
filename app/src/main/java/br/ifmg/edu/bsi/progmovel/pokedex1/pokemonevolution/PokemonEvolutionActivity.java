package br.ifmg.edu.bsi.progmovel.pokedex1.pokemonevolution;

import static androidx.lifecycle.ViewModelProvider.Factory.from;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import br.ifmg.edu.bsi.progmovel.pokedex1.R;
import br.ifmg.edu.bsi.progmovel.pokedex1.verpokemon.VerPokemonViewModel;

public class PokemonEvolutionActivity extends AppCompatActivity {
    public static String urlSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PokemonEvolutionViewModel pvm = new ViewModelProvider(this, from(PokemonEvolutionViewModel.initializer)).get(PokemonEvolutionViewModel.class);
        pvm.loadSpecies();
        setContentView(R.layout.activity_pokemon_evolution);

    }


}