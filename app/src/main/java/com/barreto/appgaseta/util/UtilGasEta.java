package com.barreto.appgaseta.util;

public class UtilGasEta {
    public static String calcularMelhorPreco(double gasolina, double etanol){
        //preço da gasolina: 5,12
        //preço do Etanol: 3,99
        //preço ideal = gasolina * 0.70 = 3,54

        double precoInicial = gasolina * 0.70;
        String mensagemRetorno;
        if (etanol<=precoInicial){
            mensagemRetorno = "Abastecer com Etanol";
        }
        else{
            mensagemRetorno = "Abastecer com Gasolina";
        }
        return mensagemRetorno;
    }
}
