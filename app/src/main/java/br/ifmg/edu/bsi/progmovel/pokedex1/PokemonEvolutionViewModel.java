package br.ifmg.edu.bsi.progmovel.pokedex1;

import static br.ifmg.edu.bsi.progmovel.pokedex1.PokemonEvolutionActivity.urlSpecies;

import android.util.Log;
import android.view.View;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonEvolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonSpecies;

public class PokemonEvolutionViewModel extends ViewModel {
    private PokedexApplication app;
    private MutableLiveData<String> mutablename;

    private MutableLiveData<Integer> loading;

    public static ViewModelInitializer<PokemonEvolutionViewModel> initializer = new ViewModelInitializer<>(
            PokemonEvolutionViewModel.class,
            creationExtras -> new PokemonEvolutionViewModel((PokedexApplication) creationExtras.get(APPLICATION_KEY)));

    public PokemonEvolutionViewModel(PokedexApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        mutablename = new MutableLiveData<>();
        loadSpecies();

    }

    public void loadSpecies() {
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {
            int idSpecies = getUrlId(urlSpecies);
            try {
                PokemonSpecies ps = app.getPokemonRepo().buscarSpecies(idSpecies);
                int idEv = getUrlId(ps.evolution_chain.url);
                PokemonEvolution ev = app.getPokemonRepo().buscarEvolution(idEv);
                extractEvolution(ev.chain.evolves_to);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                loading.postValue(View.GONE);
            }
        });
    }

    public void extractEvolution(Evolution[] evolution){
        String pEvoluton = "";
        for (Evolution ev1 : evolution) {
            for (Evolution ev2 : ev1.evolves_to) {
                pEvoluton += ev2.species.name+"\n";
            }
        }
        mutablename.postValue(pEvoluton);
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

    public LiveData<Integer> getLoading() {
        return loading;
    }

    public MutableLiveData<String> getMutablename() {
        return mutablename;
    }
}
