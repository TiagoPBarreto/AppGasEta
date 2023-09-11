package com.barreto.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.barreto.appgaseta.R;
import com.barreto.appgaseta.controller.CombustivelController;
import com.barreto.appgaseta.model.Combustivel;
import com.barreto.appgaseta.util.UtilGasEta;

import java.util.List;

public class GasEtaActivity extends AppCompatActivity {
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    CombustivelController combustivelController;

   EditText editGasolina, editEtanol;
   Button btnCalcular, btnLimpar, btnSalvar, btnFinalizar;
   TextView txtResultado;

   List<Combustivel>dados;

   double precoGasolina;
   double precoEtanol;
   String recomendacao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);
        combustivelController = new CombustivelController(GasEtaActivity.this);
        dados = combustivelController.getListaDeDados();

        Toast.makeText(this, UtilGasEta.calcularMelhorPreco(3.49,5.70), Toast.LENGTH_SHORT).show();
        UUID();
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDadosOK = true;

                if (TextUtils.isEmpty(editEtanol.getText())){
                    editEtanol.setError("* Obrigatorio");
                    editEtanol.requestFocus();
                    isDadosOK = false;
                }
                if (TextUtils.isEmpty(editGasolina.getText())){
                    editGasolina.setError("* Obrigatorio");
                    editGasolina.requestFocus();
                    isDadosOK = false;
                }
                if(isDadosOK){
                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());
                    recomendacao = UtilGasEta.calcularMelhorPreco(precoGasolina,precoEtanol);
                    txtResultado.setText(recomendacao);
                    btnSalvar.setEnabled(true);
                }
                else {
                    Toast.makeText(GasEtaActivity.this, "Campos gasolina e etanol obrigatorios", Toast.LENGTH_SHORT).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorPreco(precoGasolina,precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorPreco(precoGasolina,precoEtanol));
                combustivelController.salvar(combustivelGasolina);
                combustivelController.salvar(combustivelEtanol);

            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEtanol.setText("");
                editGasolina.setText("");
                btnSalvar.setEnabled(false);

                combustivelController.limpar();
            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GasEtaActivity.this, "GASETA Volte Sempre", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void UUID(){
        editEtanol = findViewById(R.id.editEtanol);
        editGasolina = findViewById(R.id.editGasolina);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);
    }
}
