package br.ifmg.edu.bsi.progmovel.pokedex1;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
import static br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview.PokemonEvolutionActivity.urlSpecies;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonEvolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonSpecies;
import br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview.PokemonEvolutionActivity;

public class PokemonEvolutionViewModel extends ViewModel {
    private PokedexApplication app;
    private MutableLiveData<Integer> loading;

    private MutableLiveData<String> nome;


    public static ViewModelInitializer<PokemonEvolutionViewModel> initializer = new ViewModelInitializer<>(
            PokemonEvolutionViewModel.class,
            creationExtras -> new PokemonEvolutionViewModel((PokedexApplication) creationExtras.get(APPLICATION_KEY)));

    public PokemonEvolutionViewModel(PokedexApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        nome = new MutableLiveData<>();
    }

    public void loadSpecies() {
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {
            int idSpecies = getUrlId(urlSpecies);
            try {
                PokemonSpecies ps = app.getPokemonRepo().buscarSpecies(idSpecies);
                int idEv = getUrlId(ps.evolution_chain.url);
                PokemonEvolution ev = app.getPokemonRepo().buscarEvolution(idEv);
                PokemonEvolutionActivity.evolves = ev.chain.evolves_to;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                loading.postValue(View.GONE);
            }
        });
    }

    public int getUrlId(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            String[] segments = path.split("/");
            String id = segments[segments.length - 1];
            return Integer.parseInt(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public MutableLiveData<String> getNome() {
        return nome;
    }

    public MutableLiveData<Integer> getLoading() {
        return loading;
    }

}
