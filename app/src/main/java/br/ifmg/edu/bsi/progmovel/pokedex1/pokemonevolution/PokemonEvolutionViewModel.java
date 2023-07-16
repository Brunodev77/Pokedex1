package br.ifmg.edu.bsi.progmovel.pokedex1.pokemonevolution;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
import static br.ifmg.edu.bsi.progmovel.pokedex1.pokemonevolution.PokemonEvolutionActivity.urlSpecies;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.ifmg.edu.bsi.progmovel.pokedex1.PokedexApplication;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokEvolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonSpecies;

public class PokemonEvolutionViewModel extends ViewModel {
    private PokedexApplication app;
    private MutableLiveData<Integer> loading;

    public static ViewModelInitializer<PokemonEvolutionViewModel> initializer = new ViewModelInitializer<>(
            PokemonEvolutionViewModel.class,
            creationExtras -> new PokemonEvolutionViewModel((PokedexApplication) creationExtras.get(APPLICATION_KEY)));

    public PokemonEvolutionViewModel(PokedexApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
    }

    public void loadSpecies() {
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {

            int idSpecies = getUrlId(urlSpecies);

            try {
                PokemonSpecies ps = app.getPokemonRepo().buscarSpecies(idSpecies);
                int idEv = getUrlId(ps.evolution_chain.url);
                PokEvolution ev = app.getPokemonRepo().buscarEvolution(idEv);
                getEvolution(ev);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                loading.postValue(View.GONE);
            }
        });
    }
    
    public void getEvolution(PokEvolution evolves){

        for (PokEvolution ev1: evolves) {
            Log.d("ev1","ev1="+ ev1.evolves_to);
        }
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
}
