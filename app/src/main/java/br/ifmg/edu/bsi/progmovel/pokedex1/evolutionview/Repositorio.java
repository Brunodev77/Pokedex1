package br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ifmg.edu.bsi.progmovel.pokedex1.PokemonEvolutionActivity;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;

public class Repositorio {
    List<PokemonsName> pName;

    public Repositorio() throws IOException {
        pName = new ArrayList<>();
    }
    public List<PokemonsName> getpName() {
        return pName;
    }


}
