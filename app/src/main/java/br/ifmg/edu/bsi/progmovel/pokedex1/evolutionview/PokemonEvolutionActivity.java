package br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview;

import static androidx.lifecycle.ViewModelProvider.Factory.from;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import br.ifmg.edu.bsi.progmovel.pokedex1.PokemonEvolutionViewModel;
import br.ifmg.edu.bsi.progmovel.pokedex1.R;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.databinding.ActivityPokemonEvolutionBinding;

public class PokemonEvolutionActivity extends AppCompatActivity {
    public static String urlSpecies;
    public static Evolution[] evolves;
    private ActivityPokemonEvolutionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityPokemonEvolutionBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        PokemonEvolutionViewModel pvm = new ViewModelProvider(this, from(PokemonEvolutionViewModel.initializer)).get(PokemonEvolutionViewModel.class);
        //setContentView(R.layout.activity_pokemon_evolution);
        pvm.getLoading().observe(this, (newV) -> this.binding.progressBar.setVisibility(newV));
        pvm.loadSpecies();



        PokemonEvoulutionAdapter adapter = new PokemonEvoulutionAdapter(Repositorio.pName);
        RecyclerView lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);
    }


}