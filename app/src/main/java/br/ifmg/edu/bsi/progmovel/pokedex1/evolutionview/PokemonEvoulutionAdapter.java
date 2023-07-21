package br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ifmg.edu.bsi.progmovel.pokedex1.R;

public class PokemonEvoulutionAdapter extends RecyclerView.Adapter<PokemonsViewHolder> {

    private List<PokemonsName> pName;

    public PokemonEvoulutionAdapter(List<PokemonsName> pName) {
        this.pName = pName;
    }
/*
        public PokemonEvoulutionAdapter(LifecycleOwner lifecycleOwner, PokemonEvolutionViewModel vm) {
        //this.pName = Collections.emptyList();
        this.pName = Collections.emptyList();
*/

    @NonNull
    @Override
    public PokemonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pokemons_name, parent, false);
        return new PokemonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonsViewHolder holder, int position) {
        PokemonsName pName = this.pName.get(position);
        holder.settings(pName);
    }

    @Override
    public int getItemCount() {
        return pName.size();
    }

    
}
