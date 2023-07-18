package br.ifmg.edu.bsi.progmovel.pokedex1.verpokemon;

import static androidx.lifecycle.ViewModelProvider.Factory.from;
import static br.ifmg.edu.bsi.progmovel.pokedex1.PokemonEvolutionActivity.urlSpecies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import br.ifmg.edu.bsi.progmovel.pokedex1.databinding.ActivityVerPokemonBinding;
import br.ifmg.edu.bsi.progmovel.pokedex1.PokemonEvolutionActivity;

public class VerPokemonActivity extends AppCompatActivity {

    public static final String EXTRA_NOME_POKEMON = "br.ifmg.edu.bsi.progmovel.pokedex1.extra_nome_pokemon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Primeiro, nós 'inflamos' os componentes de interface
        ActivityVerPokemonBinding binding = ActivityVerPokemonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Daí, criamos (ou reaproveitamos, não sabemos) o nosso ViewModel
        VerPokemonViewModel vm = new ViewModelProvider(this, from(VerPokemonViewModel.initializer)).get(VerPokemonViewModel.class);

        // Agora, nós criamos vínculos entre os dados do viewModel
        // e os componentes de interface.
        // A lógica aqui é a seguinte:
        // uma vez que os dados do viewModel são modificados,
        // os observadores são acionados.
        // Aqui, meus observadores apenas atualizam os
        // componentes de interface com os dados novos.
        vm.getLoading().observe(this, (newV) -> binding.progressBar.setVisibility(newV));
        vm.getNome().observe(this, (newV) -> binding.textView.setText(newV));
        vm.getUrlImagem().observe(this,
                (newV) -> Picasso.get().load(newV).into(binding.imageView));
        vm.getHeight().observe(this, (altura) -> binding.tvAltura.setText("" + altura));
        vm.getWeight().observe(this, (peso) -> binding.tvPeso.setText("" + peso));

        // Aqui, eu recebo da activity anterior o nome do pokemon
        Intent intent = getIntent();
        String nomePokemon = "charmander";
        if (intent != null && intent.getStringExtra(EXTRA_NOME_POKEMON) != null) {
            nomePokemon = intent.getStringExtra(EXTRA_NOME_POKEMON);
        }

        // Bora carregar o monstro.
        vm.loadPokemon(nomePokemon);
    }

    public void onClickEvolution(View view) {

        Intent intent = new Intent(this, PokemonEvolutionActivity.class);
        startActivity(intent);
    }

}