package br.ifmg.edu.bsi.progmovel.pokedex1;

import static androidx.lifecycle.ViewModelProvider.Factory.from;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import br.ifmg.edu.bsi.progmovel.pokedex1.R;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.databinding.ActivityMainBinding;
import br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview.PokemonEvoulutionAdapter;
import br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview.Repositorio;
import br.ifmg.edu.bsi.progmovel.pokedex1.pokemonevolution.PokemonEvolutionViewModel;

public class PokemonEvolutionActivity extends AppCompatActivity {
    public static String urlSpecies;
    public static Evolution[] evolves;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PokemonEvolutionViewModel pvm = new ViewModelProvider(this, from(PokemonEvolutionViewModel.initializer)).get(PokemonEvolutionViewModel.class);
        setContentView(R.layout.activity_pokemon_evolution);

       // pvm.getNome().observe(this, (newV) -> binding.pName.setText(newV));

        pvm.loadSpecies();
    }



    public void criarDados(){
        try {
            Repositorio rep = new Repositorio();
            PokemonEvoulutionAdapter adapter = new PokemonEvoulutionAdapter(rep.getpName());
            RecyclerView lista = findViewById(R.id.lista);
            Log.d("lista123", "lista123="+lista);
            lista.setLayoutManager(new LinearLayoutManager(this));
            lista.setAdapter(adapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}