package br.ifmg.edu.bsi.progmovel.pokedex1.dados;

import java.io.IOException;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonEvolution;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokemon;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.PokemonSpecies;

public class PokemonRepo {

    private Pokeapi api;

    public PokemonRepo(Pokeapi api) {
        this.api = api;
    }

    public Pokemon buscar(String nome) throws IOException {
        Pokemon p = api.fetch(nome).execute().body();
        return p;
    }

    public PokemonSpecies buscarSpecies(int id) throws IOException {
        PokemonSpecies ps = api.fetchSpeciesById(id).execute().body();
        return ps;
    }

    public PokemonEvolution buscarEvolution(int id) throws IOException {
        PokemonEvolution pe = api.fetchEvolutionById(id).execute().body();
        return pe;
    }


}
