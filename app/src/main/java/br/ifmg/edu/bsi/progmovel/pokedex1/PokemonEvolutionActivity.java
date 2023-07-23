package br.ifmg.edu.bsi.progmovel.pokedex1;

import static androidx.lifecycle.ViewModelProvider.Factory.from;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import br.ifmg.edu.bsi.progmovel.pokedex1.databinding.ActivityPokemonEvolutionBinding;
import br.ifmg.edu.bsi.progmovel.pokedex1.verpokemon.VerPokemonViewModel;

public class PokemonEvolutionActivity extends AppCompatActivity {

    public static String LIST_POKEMON_NAMES = "ViewPokemonActivity_LIST_POKEMON_NAMES";
    public static String urlSpecies;

    private ActivityPokemonEvolutionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityPokemonEvolutionBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        PokemonEvolutionViewModel vm = new ViewModelProvider(this, from(PokemonEvolutionViewModel.initializer)).get(PokemonEvolutionViewModel.class);

        vm.getLoading().observe(this, (newV) -> binding.progressBar2.setVisibility(newV));
        vm.getMutablename().observe(this, (newV) -> binding.mutablename.setText(newV));

        Intent intent = getIntent();
        String pEvolution = intent.getStringExtra(LIST_POKEMON_NAMES);
        Log.d("pEvolution", "pEvolution= "+pEvolution);

        //intent.putExtra(PokemonEvolutionActivity.LIST_POKEMON_NAMES,vm.getNome());
        binding.listEvolution.setText(pEvolution);
    }
}



