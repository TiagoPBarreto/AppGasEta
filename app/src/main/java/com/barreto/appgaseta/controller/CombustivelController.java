package com.barreto.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import com.barreto.appgaseta.database.GasEtaDB;
import com.barreto.appgaseta.model.Combustivel;
import com.barreto.appgaseta.view.GasEtaActivity;

import java.util.List;

public class CombustivelController extends GasEtaDB {

    SharedPreferences preferences;
    SharedPreferences.Editor dadosPreferences;
    public static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity){
        super(activity);
        preferences = activity.getSharedPreferences(NOME_PREFERENCES,0);
        dadosPreferences = preferences.edit();
    }
    public void salvar(Combustivel combustivel){
        ContentValues dados = new ContentValues();
        dadosPreferences.putString("combustivel", combustivel.getNomeDoCombustivel());
        dadosPreferences.putFloat("preco do combustivel",(float) combustivel.getPrecoDoCombustivel());
        dadosPreferences.putString("recomendacao",combustivel.getRecomendacao());
        dadosPreferences.apply();

        dados.put("nomeDoCombustivel",combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel",combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao",combustivel.getRecomendacao());
        salvarObjeto("Combustivel",dados);
    }
    public List<Combustivel> getListaDeDados(){
        return listarDados();
    }
    public void limpar(){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }
    public void alterar(Combustivel combustivel){
        ContentValues dados = new ContentValues();
        dados.put("id",combustivel.getId());
        dados.put("nomeDoCombustivel",combustivel.getNomeDoCombustivel());
        dados.put("precoDoCombustivel",combustivel.getPrecoDoCombustivel());
        dados.put("recomendacao",combustivel.getRecomendacao());
        alterarObjeto("Combustivel",dados);
    }
    public void deletar(int id){
        deletar("combustivel",id);
    }
}
