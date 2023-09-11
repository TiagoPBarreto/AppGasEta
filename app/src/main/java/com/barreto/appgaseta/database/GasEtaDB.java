package com.barreto.appgaseta.database;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.barreto.appgaseta.model.Combustivel;

import java.util.ArrayList;
import java.util.List;

public class GasEtaDB extends SQLiteOpenHelper {
    //Criar métodos para implementar crud
    //para criar o banco de dados
    // 1 - Nome do Banco de dados
    // 2 - Versão do banco de dados

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public GasEtaDB(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabela = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";
        db.execSQL(sqlTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void salvarObjeto(String tabela, ContentValues dados){
        db.insert(tabela,null,dados);

    }
    public List<Combustivel>listarDados(){
        List<Combustivel>lista = new ArrayList<>();
        Combustivel registro;
        String querySQL = "SELECT * FROM Combustivel";
        cursor = db.rawQuery(querySQL,null);
        if (cursor.moveToFirst()){

            do {
                registro = new Combustivel();
                registro.setId(cursor.getInt(0));
                registro.setNomeDoCombustivel(cursor.getString(1));
                registro.setPrecoDoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));
                lista.add(registro);

            }while (cursor.moveToNext());
        }
        return lista;
    }
    public void alterarObjeto(String tabela, ContentValues dados){
        //ID para ser alterado
        int id = dados.getAsInteger("id");
        db.update(tabela,dados,"id=?",new String[]{Integer.toString(id)});

    }

    public void deletar(String tabela, int id){
        //ID para ser alterado

        db.delete(tabela,"id=?",new String[]{Integer.toString(id)});

    }

}
