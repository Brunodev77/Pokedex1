package br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Evolution;

public class Repositorio {
    static List<PokemonsName> pName;

    public Repositorio() {
        pName = new ArrayList<>();
        PokemonsName pn;
        for (Evolution ev1 : PokemonEvolutionActivity.evolves) {
            Log.d("ev1", "ev1=" + ev1);
            for (Evolution ev2 : ev1.evolves_to) {
                Log.d("ev2", "ev2=" + ev2.species.name);
                pn = new PokemonsName(ev2.species.name);
                pName.add(pn);
            }
        }
    }
    public List<PokemonsName> getpName() {
        return pName;
    }


}
