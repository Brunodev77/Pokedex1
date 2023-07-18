package br.ifmg.edu.bsi.progmovel.pokedex1.evolutionview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.ifmg.edu.bsi.progmovel.pokedex1.R;

public class PokemonsViewHolder extends RecyclerView.ViewHolder{

    private TextView textoView;

    public PokemonsViewHolder(@NonNull View itemView) {
        super(itemView);
        textoView = itemView.findViewById(R.id.pName);
    }

    public void settings (PokemonsName pn){
        textoView.setText(pn.getName());
        textoView.setTextIsSelectable(true);
    }
}
